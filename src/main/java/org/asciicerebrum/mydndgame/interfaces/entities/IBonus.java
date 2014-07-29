package org.asciicerebrum.mydndgame.interfaces.entities;

import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueContext;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;

/**
 *
 * @author Tabea Raab
 */
public interface IBonus {

    /**
     * @return the dynamicValueProvider.
     */
    BonusValueProvider getDynamicValueProvider();

    /**
     * @param valueInput the value to set.
     */
    void setValue(Long valueInput);

    /**
     * @return the value.
     */
    Long getValue();

    /**
     * @param bonusTargetInput the target to set.
     */
    void setTarget(BonusTarget bonusTargetInput);

    /**
     * @return the target.
     */
    BonusTarget getTarget();

    /**
     * @param rankInput the rank to set.
     */
    void setRank(Long rankInput);

    /**
     * @return the rank.
     */
    Long getRank();

    /**
     * @param bonusTypeInput the bonusType to set.
     */
    void setBonusType(IBonusType bonusTypeInput);

    /**
     * @return the bonusType.
     */
    IBonusType getBonusType();

    /**
     *
     * @return the cloned version of this bonus.
     */
    IBonus makeCopy();

    /**
     * Use either the constant value of the bonus or (if null) the dynamic
     * value.
     *
     * @param context the context for the dynamic version.
     * @return either the constant or dynamic value.
     */
    Long getEffectiveValue(BonusValueContext context);

}
