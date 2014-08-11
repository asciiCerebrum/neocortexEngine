package org.asciicerebrum.mydndgame.observers;

import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IEncumbrance;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;

/**
 *
 * @author species8472
 */
public class WeaponFinesseObserver extends AbstractObserver {

    /**
     * Retrieve the value from the character's state registry by this key.
     */
    private String registryKey;

    /**
     * The valid encumbrance of the weapon to use this feat.
     */
    private IEncumbrance validEncumbrance;

    /**
     * Valid weapons this feat is made for.
     */
    private List<IWeapon> validWeaponPrototypes;

    /**
     * Bonus to remove from the list. Normally the STR bonus.
     */
    private IBonus removeBonus;

    /**
     * Bonus to add to the list. Normally the DEX bonus.
     */
    private IBonus replacementBonus;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final ISituationContext situationContext) {

        List<IBonus> boni = (List<IBonus>) object;

        ICharacter character = situationContext.getCharacter();
        IWeapon usedWeapon = (IWeapon) character.getBodySlotByType(
                situationContext.getBodySlotType()).getItem();

        Boolean situationContextValidity
                = this.determineSituationContextValidity(character,
                        usedWeapon);

        String stateRegistryKey = this.registryKey + "." + usedWeapon.getId();
        Boolean useFinesse = Boolean.parseBoolean(
                (String) character.getSetup().getStateRegistry()
                .get(stateRegistryKey));

        if (!useFinesse || !situationContextValidity) {
            return boni;
        }

        // remove STR bonus
        Iterator<IBonus> boniIterator = boni.iterator();
        while (boniIterator.hasNext()) {
            IBonus bonus = boniIterator.next();
            if (this.getRemoveBonus().resembles(bonus)) {
                boniIterator.remove();
            }
        }

        // inculde DEX bonus
        boni.add(this.getReplacementBonus());

        return boni;
    }

    /**
     * Test if the weapon finesse feat is applicable for the given weapon.
     *
     * @param characterContext the character wielding the weapon.
     * @param usedWeapon the weapon in question.
     * @return the validity of the feat.
     */
    private Boolean determineSituationContextValidity(
            final ICharacter characterContext, final IWeapon usedWeapon) {

        // test size category
        if (!usedWeapon.getSize().equals(characterContext.getRace()
                .getSize())) {
            return Boolean.FALSE;
        }

        // test encumbrance
        if (usedWeapon.getEncumbrance().equals(this.getValidEncumbrance())) {
            return Boolean.TRUE;
        }

        // test specific name
        for (IWeapon prototype : this.getValidWeaponPrototypes()) {
            if (prototype.getName().equals(usedWeapon.getName())) {
                return Boolean.TRUE;
            }
        }

        //TODO test natural weapon
        return Boolean.FALSE;
    }

    /**
     * @return the registryKey
     */
    public final String getRegistryKey() {
        return registryKey;
    }

    /**
     * @param registryKeyInput the registryKey to set
     */
    public final void setRegistryKey(final String registryKeyInput) {
        this.registryKey = registryKeyInput;
    }

    /**
     * @return the validEncumbrance
     */
    public final IEncumbrance getValidEncumbrance() {
        return validEncumbrance;
    }

    /**
     * @param validEncumbranceInput the validEncumbrance to set
     */
    public final void setValidEncumbrance(
            final IEncumbrance validEncumbranceInput) {
        this.validEncumbrance = validEncumbranceInput;
    }

    /**
     * @return the validWeaponPrototypes
     */
    public final List<IWeapon> getValidWeaponPrototypes() {
        return validWeaponPrototypes;
    }

    /**
     * @param validWeaponPrototypesInput the validWeaponPrototypes to set
     */
    public final void setValidWeaponPrototypes(
            final List<IWeapon> validWeaponPrototypesInput) {
        this.validWeaponPrototypes = validWeaponPrototypesInput;
    }

    /**
     * @return the removeBonus
     */
    public final IBonus getRemoveBonus() {
        return removeBonus;
    }

    /**
     * @param removeBonusInput the removeBonus to set
     */
    public final void setRemoveBonus(final IBonus removeBonusInput) {
        this.removeBonus = removeBonusInput;
    }

    /**
     * @return the replacementBonus
     */
    public final IBonus getReplacementBonus() {
        return replacementBonus;
    }

    /**
     * @param replacementBonusInput the replacementBonus to set
     */
    public final void setReplacementBonus(final IBonus replacementBonusInput) {
        this.replacementBonus = replacementBonusInput;
    }

}
