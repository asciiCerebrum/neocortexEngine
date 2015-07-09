package org.asciicerebrum.neocortexengine.mechanics.observertriggers;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Encumbrance;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.neocortexengine.domain.core.particles.BooleanParticle;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus.ResemblanceFacet;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBonus;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototypes;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.ObserverTriggerStrategy;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class WeaponFinesseObserverTrigger implements ObserverTriggerStrategy {

    /**
     * The logger.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(WeaponFinesseObserverTrigger.class);

    /**
     * Retrieve the value from the character's state registry by this key.
     */
    private StateParticle registryKey;

    /**
     * The valid encumbrance of the weapon to use this feat.
     */
    private Encumbrance validEncumbrance;

    /**
     * Valid weapons this feat is made for.
     */
    private WeaponPrototypes validWeaponPrototypes;

    /**
     * Bonus to remove from the list. Normally the STR bonus.
     */
    private Bonus removeBonus;

    /**
     * Bonus to add to the list. Normally the DEX bonus.
     */
    private Bonus replacementBonus;

    /**
     * The service for retrieving situation context settings.
     */
    private SituationContextService situationContextService;

    /**
     * Facade for retrieving basic values of the entity.
     */
    private CharacterServiceFacade characterServiceFacade;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final ICharacter dndCharacter, final UniqueEntity contextItem) {

        LOG.debug("Entering weapon finesse trigger for character {}.",
                ((DndCharacter) dndCharacter).getUniqueId().getValue());

        if (((DndCharacter) dndCharacter).getStateRegistry() == null) {
            LOG.error("The state registry is null!");
        }

        final ContextBoni ctxBoni = (ContextBoni) object;

        if (!(contextItem instanceof Weapon)) {
            return ctxBoni;
        }

        final Weapon usedWeapon = (Weapon) contextItem;

        final boolean situationContextValidity
                = this.determineSituationContextValidity(
                        (DndCharacter) dndCharacter, usedWeapon);

        LOG.debug("Status of situation context validity: {}.",
                situationContextValidity);

        final BooleanParticle useFinesse
                = this.getSituationContextService().getFlagForKey(
                        this.getRegistryKey(),
                        (DndCharacter) dndCharacter, usedWeapon.getUniqueId());

        LOG.debug("Status of using finesse by key {} and item id {}: {}.",
                new Object[]{this.getRegistryKey(),
                    usedWeapon.getUniqueId().getValue(), useFinesse.isValue()});

        if (!useFinesse.isValue() || !situationContextValidity) {
            return ctxBoni;
        }

        // remove STR bonus
        final Iterator<ContextBonus> boniIterator = ctxBoni.iterator();
        while (boniIterator.hasNext()) {
            final ContextBonus ctxBonus = boniIterator.next();
            if (this.getRemoveBonus().resembles(ctxBonus.getBonus(),
                    ResemblanceFacet.BONUS_TYPE,
                    ResemblanceFacet.DYNAMIC_VALUE_PROVIDER,
                    ResemblanceFacet.TARGET)) {
                boniIterator.remove();
            }
        }

        // inculde DEX bonus
        ctxBoni.add(this.getReplacementBonus(), contextItem);

        return ctxBoni;
    }

    /**
     * Test if the weapon finesse feat is applicable for the given weapon.
     *
     * @param dndCharacter the character wielding the weapon.
     * @param usedWeapon the weapon in question.
     * @return the validity of the feat.
     */
    final boolean determineSituationContextValidity(
            final DndCharacter dndCharacter, final Weapon usedWeapon) {

        // test size category
        if (!this.getWeaponServiceFacade().getSize(usedWeapon, dndCharacter)
                .equals(this.getCharacterServiceFacade()
                        .getSize(dndCharacter))) {
            return false;
        }

        // test encumbrance
        if (this.getWeaponServiceFacade().hasEncumbrance(
                this.getValidEncumbrance(), usedWeapon, dndCharacter)) {
            return true;
        }

        // test specific name
        final Iterator<WeaponPrototype> prototypeIterator
                = this.getValidWeaponPrototypes().iterator();
        while (prototypeIterator.hasNext()) {
            final WeaponPrototype prototype = prototypeIterator.next();
            if (usedWeapon.isOfWeaponPrototype(prototype)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param registryKeyInput the registryKey to set
     */
    public final void setRegistryKey(final StateParticle registryKeyInput) {
        this.registryKey = registryKeyInput;
    }

    /**
     * @param validEncumbranceInput the validEncumbrance to set
     */
    public final void setValidEncumbrance(
            final Encumbrance validEncumbranceInput) {
        this.validEncumbrance = validEncumbranceInput;
    }

    /**
     * @param validWeaponPrototypesInput the validWeaponPrototypes to set
     */
    public final void setValidWeaponPrototypes(
            final WeaponPrototypes validWeaponPrototypesInput) {
        this.validWeaponPrototypes = validWeaponPrototypesInput;
    }

    /**
     * @param removeBonusInput the removeBonus to set
     */
    public final void setRemoveBonus(final Bonus removeBonusInput) {
        this.removeBonus = removeBonusInput;
    }

    /**
     * @param replacementBonusInput the replacementBonus to set
     */
    public final void setReplacementBonus(final Bonus replacementBonusInput) {
        this.replacementBonus = replacementBonusInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @param characterServiceFacadeItem the characterServiceFacade to set
     */
    public final void setCharacterServiceFacade(
            final CharacterServiceFacade characterServiceFacadeItem) {
        this.characterServiceFacade = characterServiceFacadeItem;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

    /**
     * @return the registryKey
     */
    public final StateParticle getRegistryKey() {
        return registryKey;
    }

    /**
     * @return the validEncumbrance
     */
    public final Encumbrance getValidEncumbrance() {
        return validEncumbrance;
    }

    /**
     * @return the validWeaponPrototypes
     */
    public final WeaponPrototypes getValidWeaponPrototypes() {
        return validWeaponPrototypes;
    }

    /**
     * @return the removeBonus
     */
    public final Bonus getRemoveBonus() {
        return removeBonus;
    }

    /**
     * @return the replacementBonus
     */
    public final Bonus getReplacementBonus() {
        return replacementBonus;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

    /**
     * @return the characterServiceFacade
     */
    public final CharacterServiceFacade getCharacterServiceFacade() {
        return characterServiceFacade;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

}
