package org.asciicerebrum.mydndgame;

import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

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
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final ISituationContext situationContext) {
        List<IBonus> boni = (List<IBonus>) object;

        Long addBonusValue = (Long) situationContext.getCharacter().getSetup()
                .getStateRegistry().get(this.getRegistryKey());

        if (addBonusValue != null
                && addBonusValue != 0L) {
            Bonus addBonus = new Bonus();
            addBonus.setBonusType(this.getBonusType());
            addBonus.setTarget(this.getBonusTarget());
            addBonus.setValue(addBonusValue);

            boni.add(addBonus);
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

}
