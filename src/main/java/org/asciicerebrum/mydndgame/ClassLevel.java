package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;

/**
 *
 * @author species8472
 */
public class ClassLevel implements ILevel {

    /**
     * The level number of this class level.
     */
    private Integer level;
    /**
     * The list of base attack boni that are granted with this level of this
     * character class.
     */
    private List<IBonus> baseAtkBoni = new ArrayList<IBonus>();
    /**
     * The next class level in the list of class levels of this character class.
     */
    private ILevel nextClassLevel;
    /**
     * The previous class level in the list of class levels of this character
     * class.
     */
    private ILevel previousClassLevel;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBonus getBaseAtkBonusByRank(final Long rank) {
        for (IBonus bonus : this.baseAtkBoni) {
            if (bonus.getRank().equals(rank)) {
                return bonus;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getBaseAtkBonusValueDeltaByRank(final Long rank) {
        IBonus currentBonus = this.getBaseAtkBonusByRank(rank);
        Long deltaValue = currentBonus.getValue();

        ILevel prevCLevel = this.getPreviousClassLevel();
        if (prevCLevel == null) {
            return deltaValue;
        }
        IBonus prevBonus = prevCLevel.getBaseAtkBonusByRank(rank);
        if (prevBonus == null) {
            return deltaValue;
        }

        return currentBonus.getValue() - prevBonus.getValue();
    }

    /**
     * @return the level
     */
    public final Integer getLevel() {
        return level;
    }

    /**
     * @param levelInput the level to set
     */
    public final void setLevel(final Integer levelInput) {
        this.level = levelInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IBonus> getBaseAtkBoni() {
        return baseAtkBoni;
    }

    /**
     * @param baseAtkBoniInput the baseAtkBoni to set
     */
    public final void setBaseAtkBoni(final List<IBonus> baseAtkBoniInput) {
        this.baseAtkBoni = baseAtkBoniInput;
    }

    /**
     * @return the nextClassLevel
     */
    public final ILevel getNextClassLevel() {
        return nextClassLevel;
    }

    /**
     * @param nextClassLevelInput the nextClassLevel to set
     */
    public final void setNextClassLevel(final ClassLevel nextClassLevelInput) {
        this.nextClassLevel = nextClassLevelInput;
    }

    /**
     * @return the previousClassLevel
     */
    public final ILevel getPreviousClassLevel() {
        return previousClassLevel;
    }

    /**
     * @param previousClassLevelInput the previousClassLevel to set
     */
    public final void setPreviousClassLevel(
            final ILevel previousClassLevelInput) {
        this.previousClassLevel = previousClassLevelInput;
    }

}
