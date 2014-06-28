package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class LevelAdvancement {

    private final String className;
    private final Long hpAddition;
    private final String abilityName;

    public LevelAdvancement(final String classNameInput,
            final Long hpAdditionInput, final String abilityNameInput) {
        this.className = classNameInput;
        this.hpAddition = hpAdditionInput;
        this.abilityName = abilityNameInput;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return the abilityName
     */
    public String getAbilityName() {
        return abilityName;
    }

    /**
     * @return the hpAddition
     */
    public Long getHpAddition() {
        return hpAddition;
    }
}
