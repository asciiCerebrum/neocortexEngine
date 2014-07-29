package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class LevelAdvancement {

    /**
     * The class name of the associated character class of this advancement.
     */
    private String className;
    /**
     * How many hit points are gained in this advancement.
     */
    private Long hpAddition;
    /**
     * The name of the ability that gains one more point in this advancement.
     */
    private String abilityName;
    /**
     * The name of the feat that is acquired in this advancement.
     */
    private String featName;

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

    /**
     * @param classNameInput the className to set
     * @return this instance.
     */
    public final LevelAdvancement setClassName(final String classNameInput) {
        this.className = classNameInput;
        return this;
    }

    /**
     * @param hpAdditionInput the hpAddition to set
     * @return this instance.
     */
    public final LevelAdvancement setHpAddition(final Long hpAdditionInput) {
        this.hpAddition = hpAdditionInput;
        return this;
    }

    /**
     * @param abilityNameInput the abilityName to set
     * @return this instance.
     */
    public final LevelAdvancement setAbilityName(
            final String abilityNameInput) {
        this.abilityName = abilityNameInput;
        return this;
    }

    /**
     * @param featNameInput the featName to set
     * @return this instance.
     */
    public final LevelAdvancement setFeatName(final String featNameInput) {
        this.featName = featNameInput;
        return this;
    }

}
