package org.asciicerebrum.mydndgame;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.util.StringUtils;

/**
 *
 * @author species8472
 */
public class BonusCalculationService {

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
        System.out.println("Found boni for " + target.getId() + ":");
        for (Bonus bonus : foundBoni) {
            Long bonusVal;
            if (bonus.getDynamicValueProvider() != null) {
                bonusVal = bonus.getDynamicValueProvider()
                        .getDynamicValue(dndCharacter);
            } else {
                bonusVal = bonus.getValue();
            }

            System.out.println("Bonus " + bonus.getBonusType().getId()
                    + " :: " + bonusVal);
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

                System.out.println("Found field: " + field.getName());

                try {
                    Method getter = source.getClass().getMethod(
                            "get" + StringUtils.capitalize(field.getName()));

                    // invoke getter of annotated field
                    Object getterResult = getter.invoke(source);

                    if (field.getType().equals(List.class)) {
                        // list of objects
                        System.out.println("is list");

                        for (Object listElement : (List) getterResult) {
                            // go recursively into the list elements
                            traversedBoni.addAll(
                                    this.traverseBoniByTarget(
                                            listElement, target));
                        }

                    } else if (field.getType().equals(Map.class)) {
                        // map of objects = list of key entries
                        System.out.println("is map");

                        for (Object mapKey : ((Map) getterResult).keySet()) {
                            traversedBoni.addAll(
                                    this.traverseBoniByTarget(mapKey, target));
                        }

                    } else {
                        // single object
                        System.out.println("is standard type");
                        traversedBoni.addAll(
                                this.traverseBoniByTarget(
                                        getterResult, target));
                    }

                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(BonusCalculationService.class.getName())
                            .log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(BonusCalculationService.class.getName())
                            .log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BonusCalculationService.class.getName())
                            .log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(BonusCalculationService.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return traversedBoni;
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
