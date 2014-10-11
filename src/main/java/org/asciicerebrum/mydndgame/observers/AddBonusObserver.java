package org.asciicerebrum.mydndgame.observers;

import java.util.List;
import org.asciicerebrum.mydndgame.Bonus;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

/**
 *
 * @author species8472
 */
public class AddBonusObserver extends AbstractObserver {

    /**
     * Type of bonus to add.
     */
    private IBonusType bonusType;
    /**
     * Retrieve the value from the character's state registry by this key.
     */
    private String registryKey;
    /**
     * Target of the bonus.
     */
    private BonusTarget bonusTarget;
    /**
     * The bonus to add.
     */
    private IBonus addBonus;

    /**
     * {@inheritDoc} A given bonus has priority over a lookup in the characters
     * state registry.
     */
    @Override
    protected final Object triggerCallback(final Object object,
            final ICharacter character) {
        List<IBonus> boni = (List<IBonus>) object;

        if (this.addBonus != null) {
            boni.add(this.addBonus);
            return boni;
        }

        Long addBonusValue = (Long) character.getSetup()
                .getStateRegistry().get(this.getRegistryKey());

        if (addBonusValue != null
                && addBonusValue != 0L) {
            IBonus altAddBonus = new Bonus();
            altAddBonus.setBonusType(this.getBonusType());
            altAddBonus.setTarget(this.getBonusTarget());
            altAddBonus.setValue(addBonusValue);

            boni.add(altAddBonus);
        }

        return boni;
    }

    /**
     * @return the bonusType
     */
    public final IBonusType getBonusType() {
        return bonusType;
    }

    /**
     * @param bonusTypeInput the bonusType to set
     */
    public final void setBonusType(final IBonusType bonusTypeInput) {
        this.bonusType = bonusTypeInput;
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
     * @return the bonusTarget
     */
    public final BonusTarget getBonusTarget() {
        return bonusTarget;
    }

    /**
     * @param bonusTargetInput the bonusTarget to set
     */
    public final void setBonusTarget(final BonusTarget bonusTargetInput) {
        this.bonusTarget = bonusTargetInput;
    }

    /**
     * @param addBonusInput the addBonus to set
     */
    public final void setAddBonus(final IBonus addBonusInput) {
        this.addBonus = addBonusInput;
    }

}
