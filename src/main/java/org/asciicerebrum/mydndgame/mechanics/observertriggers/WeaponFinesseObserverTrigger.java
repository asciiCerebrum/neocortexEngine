package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.rules.Encumbrance;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.core.particles.BooleanParticle;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.rules.WeaponPrototype;
import org.asciicerebrum.mydndgame.domain.rules.WeaponPrototypes;
import org.asciicerebrum.mydndgame.facades.game.CharacterServiceFacade;
import org.asciicerebrum.mydndgame.facades.game.InventoryItemServiceFacade;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.mechanics.observertriggers.interfaces.ObserverTriggerStrategy;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class WeaponFinesseObserverTrigger implements ObserverTriggerStrategy {

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
     * Facade for retrieving basic values of the item entity.
     */
    private InventoryItemServiceFacade itemFacade;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final DndCharacter dndCharacter, final UniqueEntity contextItem) {

        Boni boni = (Boni) object;

        if (!(contextItem instanceof Weapon)) {
            return boni;
        }

        Weapon usedWeapon = (Weapon) contextItem;

        final boolean situationContextValidity
                = this.determineSituationContextValidity(dndCharacter,
                        usedWeapon);

        final BooleanParticle useFinesse
                = this.situationContextService.getFlagForKey(this.registryKey,
                        dndCharacter, usedWeapon);

        if (!useFinesse.isValue() || !situationContextValidity) {
            return boni;
        }

        // remove STR bonus
        final Iterator<Bonus> boniIterator = boni.iterator();
        while (boniIterator.hasNext()) {
            final Bonus bonus = boniIterator.next();
            if (this.removeBonus.resembles(bonus)) {
                boniIterator.remove();
            }
        }

        // inculde DEX bonus
        boni.addBonus(this.replacementBonus);

        return boni;
    }

    /**
     * Test if the weapon finesse feat is applicable for the given weapon.
     *
     * @param dndCharacter the character wielding the weapon.
     * @param usedWeapon the weapon in question.
     * @return the validity of the feat.
     */
    private boolean determineSituationContextValidity(
            final DndCharacter dndCharacter, final Weapon usedWeapon) {

        // test size category
        if (!this.itemFacade.getSize(usedWeapon, dndCharacter).equals(
                this.characterServiceFacade.getSize(dndCharacter))) {
            return false;
        }

        // test encumbrance
        if (this.weaponServiceFacade.hasEncumbrance(this.validEncumbrance,
                usedWeapon, dndCharacter)) {
            return true;
        }

        // test specific name
        final Iterator<WeaponPrototype> prototypeIterator
                = this.validWeaponPrototypes.iterator();
        while (prototypeIterator.hasNext()) {
            final WeaponPrototype prototype = prototypeIterator.next();
            if (usedWeapon.isOfWeaponPrototype(prototype)) {
                return true;
            }
        }

        //TODO test natural weapon
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
     * @param itemFacadeItem the itemFacade to set
     */
    public final void setItemFacade(
            final InventoryItemServiceFacade itemFacadeItem) {
        this.itemFacade = itemFacadeItem;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

}
