package org.asciicerebrum.mydndgame;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 *
 * @author species8472
 */
public class BonusCalculationService {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER
            = Logger.getLogger(BonusCalculationService.class);

    /**
     *
     * @param dndCharacter the context as a dndCharacter.
     * @param source the source to start collecting boni from (and then going
     * down the tree).
     * @param target the bonus target of interest.
     * @return the total bonus value calculated from all boni given by the
     * source object in the context of the dndCharacter.
     */
    public final Long retrieveEffectiveBonusValueByTarget(
            final DndCharacter dndCharacter, final Object source,
            final BonusTarget target) {

        List<Bonus> foundBoni = this.traverseBoniByTarget(source, target);

        //TODO filter out non-stacking boni
        //TODO track the origin of the bonus, e.g. from ability Constitution
        // for hp
        Long totalBonusVal = 0L;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Found boni for " + target.getId() + ":");
        }
        for (Bonus bonus : foundBoni) {
            Long bonusVal;
            if (bonus.getDynamicValueProvider() != null) {
                bonusVal = bonus.getDynamicValueProvider()
                        .getDynamicValue(dndCharacter);
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
     *
     * @param source the bonus source as the starting point for the tree
     * taversal.
     * @param target the bonus target of interest.
     * @return the collection list of all boni found through the object graph.
     */
    public final List<Bonus> traverseBoniByTarget(final Object source,
            final BonusTarget target) {

        List<Bonus> traversedBoni = new ArrayList<Bonus>();

        if (source instanceof BonusSource) {
            BonusSource bonusSource = (BonusSource) source;
            traversedBoni.addAll(this.filterBonusListByTarget(
                    bonusSource.getBoni(), target));
        }

        for (Field field : source.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(BonusGranter.class)) {

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
                } catch (SecurityException ex) {
                    LOGGER.error("Security problems occured.", ex);
                } catch (IllegalAccessException ex) {
                    LOGGER.error("Access to getter of annotated field "
                            + "found to be illegal.", ex);
                } catch (InvocationTargetException ex) {
                    LOGGER.error("Invocation of getter of annotated field "
                            + "seems problematic.", ex);
                }
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
            final List<Bonus> traversedBoni, final BonusTarget target) {
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
     *
     * @param boni the list of boni to be filtered.
     * @param target the filter bonus target.
     * @return the list of boni filtered by the target.
     */
    public final List<Bonus> filterBonusListByTarget(final List<Bonus> boni,
            final BonusTarget target) {
        List<Bonus> filteredBoni = new ArrayList<Bonus>();

        for (Bonus bonus : boni) {
            if (bonus.getTarget().equals(target)) {
                filteredBoni.add(bonus);
            }
        }
        return filteredBoni;
    }

}
