package org.asciicerebrum.mydndgame.observers;

import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class RemoveBonusObserver extends AbstractObserver {

    /**
     * The bonus to resemble the bonus which must be removed.
     */
    private IBonus removeBonus;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final ISituationContext situationContext) {

        if (!this.getConditionEvaluator().evaluate(situationContext)) {
            return object;
        }

        List<IBonus> boni = (List<IBonus>) object;
        Iterator<IBonus> boniIterator = boni.iterator();
        while (boniIterator.hasNext()) {
            IBonus bonus = boniIterator.next();
            if (this.getRemoveBonus().resembles(bonus)) {
                boniIterator.remove();
            }
        }
        return object;
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

}
