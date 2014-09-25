package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacterSetup;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class CharacterSetup implements ICharacterSetup {

    /**
     * Spring application context for finding beans from id strings.
     */
    private final ApplicationContext appContext;
    /**
     * The name of the character.
     */
    private String name;
    /**
     * The list of character class level advancements, the character has.
     */
    private List<LevelAdvancement> levelAdvancementStack
            = new ArrayList<LevelAdvancement>();
    /**
     * The abilities from character creation together with their values.
     */
    private Map<String, Long> baseAbilityMap
            = new HashMap<String, Long>();
    /**
     * The race of the character as a string, which is the bean-id.
     */
    private String race;
    /**
     * The number of hitpints, the character currently has.
     */
    private int currentHp;
    /**
     * The number of nonlethal hitpoints, the character currently has.
     */
    private int currentHpNonlethal;
    /**
     * The number of experience points, the character currently has.
     */
    private int currentXp;

    /**
     * Holds the possessions of a character: key = the unique id of the item,
     * value = the name of the body slot type or other position where it is
     * located.
     */
    private final Map<String, String> possessionContainer
            = new HashMap<String, String>();

    /**
     * Keeps the state settings of a character: key = unique state identifier,
     * value = object related to this setting. (normally it is a string or a
     * number of any kind.)
     */
    private final Map<String, Object> stateRegistry
            = new HashMap<String, Object>();

    /**
     * Creation of a character setup object. The application context is needed
     * for wrapping functionality of the state registry. Beans must be created
     * from IDs.
     *
     * @param appContextInput the application context.
     */
    public CharacterSetup(final ApplicationContext appContextInput) {
        this.appContext = appContextInput;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the race
     */
    public final String getRace() {
        return race;
    }

    /**
     * @param raceInput the race to set
     */
    public final void setRace(final String raceInput) {
        this.race = raceInput;
    }

    /**
     * @return the currentHp
     */
    public final int getCurrentHp() {
        return currentHp;
    }

    /**
     * @param currentHpInput the currentHp to set
     */
    public final void setCurrentHp(final int currentHpInput) {
        this.currentHp = currentHpInput;
    }

    /**
     * @return the currentHpNonlethal
     */
    public final int getCurrentHpNonlethal() {
        return currentHpNonlethal;
    }

    /**
     * @param currentHpNonlethalInput the currentHpNonlethal to set
     */
    public final void setCurrentHpNonlethal(final int currentHpNonlethalInput) {
        this.currentHpNonlethal = currentHpNonlethalInput;
    }

    /**
     * @return the baseAbilityMap
     */
    public final Map<String, Long> getBaseAbilityMap() {
        return baseAbilityMap;
    }

    /**
     * @param baseAbilityMapInput the baseAbilityMap to set
     */
    public final void setBaseAbilityMap(
            final Map<String, Long> baseAbilityMapInput) {
        this.baseAbilityMap = baseAbilityMapInput;
    }

    /**
     * @return the currentXp
     */
    public final int getCurrentXp() {
        return currentXp;
    }

    /**
     * @param currentXpInput the currentXp to set
     */
    public final void setCurrentXp(final int currentXpInput) {
        this.currentXp = currentXpInput;
    }

    /**
     * @return the levelAdvancementStack
     */
    public final List<LevelAdvancement> getLevelAdvancementStack() {
        return levelAdvancementStack;
    }

    /**
     * @param levelAdvancementStackInput the levelAdvancementStack to set
     */
    public final void setLevelAdvancementStack(
            final List<LevelAdvancement> levelAdvancementStackInput) {
        this.levelAdvancementStack = levelAdvancementStackInput;
    }

    /**
     * @return the possessionContainer
     */
    public final Map<String, String> getPossessionContainer() {
        return possessionContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, Object> getStateRegistry() {
        return stateRegistry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <T> T getStateRegistryBeanForKey(final String registryKey,
            final Class<T> beanClass) {

        String beanId = null;
        Object mapValue = this.stateRegistry.get(registryKey);

        if (mapValue instanceof String) {
            beanId = (String) mapValue;
        }
        if (StringUtils.isBlank(beanId)) {
            return null;
        }

        return this.appContext.getBean(beanId, beanClass);
    }

}
