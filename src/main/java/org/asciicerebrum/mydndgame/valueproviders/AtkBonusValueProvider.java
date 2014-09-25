package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;

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
    public final Long getDynamicValue(final ICharacter character) {

        Long totalBonus = 0L;
        for (ILevel cLevel : character.getClassLevels()) {

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
