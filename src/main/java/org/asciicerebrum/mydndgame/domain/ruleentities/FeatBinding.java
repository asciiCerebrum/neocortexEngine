package org.asciicerebrum.mydndgame.domain.ruleentities;

/**
 * Marks objects as bindable to certain feats, e.g. Weapon prototypes, etc.
 *
 * @author species8472
 */
public interface FeatBinding {

    /**
     * Some feats are valid with and without a feat binging. E.g. the martial
     * weapon proficiency can also be used without a weapon given in the feat
     * binding attribute. In this case the feat is valid for ALL martial
     * weapons. So instead of leaving the attribute at null, you use these
     * generic binding object. In this case all.
     */
    public enum GenericBinding implements FeatBinding {

        /**
         * Feat valid without a specified binding.
         */
        ALL,
        /**
         * Feat invalid without a specified binding.
         */
        NONE;
    }
}
