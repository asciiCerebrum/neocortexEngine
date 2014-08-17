package org.asciicerebrum.mydndgame.parametersetters;

import org.asciicerebrum.mydndgame.conditionevaluator.AndListEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.conditionevaluator.CorrectWeaponEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IFeat;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.IParameterSetter;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.observers.RemoveBonusObserver;

/**
 *
 * @author species8472
 */
public class WeaponForProficiencyFeatSetter implements IParameterSetter {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFeatParameter(final IFeat feat,
            final Object parameter) {

        final IWeapon weapon = (IWeapon) parameter;

        RemoveBonusObserver rbObserver = null;
        for (IObserver observer : feat.getObservers()) {
            if (observer != null && observer instanceof RemoveBonusObserver) {
                rbObserver = (RemoveBonusObserver) observer;
                break;
            }
        }

        if (rbObserver == null) {
            return;
        }

        final ConditionEvaluator evaluator = rbObserver.getConditionEvaluator();

        if (evaluator == null) {
            return;
        }

        final AndListEvaluator andEval = (AndListEvaluator) evaluator;

        for (ConditionEvaluator subEval : andEval.getConditionEvaluators()) {
            if (subEval instanceof CorrectWeaponEvaluator) {
                final CorrectWeaponEvaluator cwEval
                        = (CorrectWeaponEvaluator) subEval;
                cwEval.setWeapon(weapon);
                break;
            }
        }
    }
}
