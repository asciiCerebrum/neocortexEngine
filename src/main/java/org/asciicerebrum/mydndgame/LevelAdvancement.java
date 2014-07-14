package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class LevelAdvancement {

    /**
     * The class name of the associated character class of this advancement.
     */
    private final String className;
    /**
     * How many hit points are gained in this advancement.
     */
    private final Long hpAddition;
    /**
     * The name of the ability that gains one more point in this advancement.
     */
    private final String abilityName;
    /**
     * The name of the feat that is acquired in this advancement.
     */
    private final String featName;

    /**
     *
     * @param classNameInput the name of the character class.
     * @param hpAdditionInput the additional number of hit points.
     * @param abilityNameInput the name of the ability that gains 1 point.
     * @param featNameInput the name of the additional feat that is acquired.
     */
    public LevelAdvancement(final String classNameInput,
            final Long hpAdditionInput, final String abilityNameInput,
            final String featNameInput) {
        this.className = classNameInput;
        this.hpAddition = hpAdditionInput;
        this.abilityName = abilityNameInput;
        this.featName = featNameInput;
    }

    /**
     * @return the className
     */
    public final String getClassName() {
        return className;
    }

    /**
     * @return the abilityName
     */
    public final String getAbilityName() {
        return abilityName;
    }

    /**
     * @return the hpAddition
     */
    public final Long getHpAddition() {
        return hpAddition;
    }

    /**
     * @return the featName
     */
    public final String getFeatName() {
        return featName;
    }
}
