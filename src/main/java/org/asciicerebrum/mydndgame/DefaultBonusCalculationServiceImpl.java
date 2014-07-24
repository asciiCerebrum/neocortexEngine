package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.services.BonusCalculationService;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueContext;
import org.springframework.util.StringUtils;

/**
 *
 * @author species8472
 */
public class DefaultBonusCalculationServiceImpl
        implements BonusCalculationService {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER
            = Logger.getLogger(DefaultBonusCalculationServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long retrieveEffectiveBonusValueByTarget(
            final ICharacter iCharacter, final Object source,
            final BonusTarget target) {

        List<IBonus> foundBoni = this.traverseBoniByTarget(source, target);
        return this.accumulateBonusValue(iCharacter, foundBoni);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long accumulateBonusValue(final ICharacter iCharacter,
            final List<IBonus> foundBoni) {
        //TODO filter out non-stacking boni
        //TODO track the origin of the bonus, e.g. from ability Constitution
        //TODO skip boni of value 0 - really erase them from the list or don't
        // put them into the list in the first place
        // for hp
        Long totalBonusVal = 0L;
        for (IBonus bonus : foundBoni) {
            Long bonusVal;
            if (bonus.getDynamicValueProvider() != null) {
                bonusVal = bonus.getDynamicValueProvider()
                        .getDynamicValue((BonusValueContext) iCharacter);
            } else {
                bonusVal = bonus.getValue();
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Bonus " + bonus.getBonusType().getId()
                        + " :: " + bonusVal);
            }
            totalBonusVal += bonusVal;
        }
        return totalBonusVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IBonus> traverseBoniByTarget(final Object source,
            final BonusTarget target) {

        List<IBonus> traversedBoni = new ArrayList<IBonus>();

        if (source instanceof BonusSource) {
            BonusSource bonusSource = (BonusSource) source;
            traversedBoni.addAll(this.filterBonusListByTarget(
                    bonusSource.getBoni(), target));
        }

        for (Field field : source.getClass().getDeclaredFields()) {

            if (!field.isAnnotationPresent(BonusGranter.class)) {
                continue;
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Found field: " + field.getName());
            }
            try {
                Method getter = source.getClass().getMethod(
                        "get" + StringUtils.capitalize(field.getName()));

                // invoke getter of annotated field
                Object getterResult = getter.invoke(source);

                this.traversePerField(field, getterResult,
                        traversedBoni, target);

            } catch (NoSuchMethodException ex) {
                LOGGER.error("Annotated field does not have an associated"
                        + " getter.", ex);
            } catch (IllegalAccessException ex) {
                LOGGER.error("Access to getter of annotated field "
                        + "found to be illegal.", ex);
            } catch (InvocationTargetException ex) {
                LOGGER.error("Invocation of getter of annotated field "
                        + "seems problematic.", ex);
            }
        }
        return traversedBoni;
    }

    /**
     *
     * @param field The base for the continuation of the tree traversal.
     * @param getterResult The rsult of the getter to continue with.
     * @param traversedBoni The so far collected list of boni.
     * @param target The bonus target which filters the type of boni needed.
     */
    private void traversePerField(final Field field, final Object getterResult,
            final List<IBonus> traversedBoni, final BonusTarget target) {
        if (field.getType().equals(List.class)) {
            // list of objects

            for (Object listElement : (List) getterResult) {
                // go recursively into the list elements
                traversedBoni.addAll(
                        this.traverseBoniByTarget(
                                listElement, target));
            }

        } else if (field.getType().equals(Map.class)) {
            // map of objects = list of key entries

            for (Object mapKey : ((Map) getterResult).keySet()) {
                traversedBoni.addAll(
                        this.traverseBoniByTarget(mapKey, target));
            }

        } else {
            // single object
            traversedBoni.addAll(
                    this.traverseBoniByTarget(
                            getterResult, target));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IBonus> filterBonusListByTarget(final List<IBonus> boni,
            final BonusTarget target) {
        List<IBonus> filteredBoni = new ArrayList<IBonus>();

        for (IBonus bonus : boni) {
            if (bonus.getTarget().equals(target)) {
                filteredBoni.add(bonus);
            }
        }
        return filteredBoni;
    }

}
