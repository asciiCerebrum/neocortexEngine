package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueContext;

/**
 *
 * @author Tabea Raab
 */
public class AtkBonusValueProvider implements BonusValueProvider {

    /**
     * The rank of the atk bonus in question.
     */
    private Long rank;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getDynamicValue(final BonusValueContext context) {

        DndCharacter dndCharacter = (DndCharacter) context;

        Long totalBonus = 0L;
        for (ILevel cLevel : dndCharacter.getClassLevels()) {

            totalBonus
                    += cLevel.getBaseAtkBonusValueDeltaByRank(this.getRank());
        }

        return totalBonus;
    }

    /**
     * @return the rank
     */
    public final Long getRank() {
        return rank;
    }

    /**
     * @param rankInput the rank to set
     */
    public final void setRank(final Long rankInput) {
        this.rank = rankInput;
    }
}
