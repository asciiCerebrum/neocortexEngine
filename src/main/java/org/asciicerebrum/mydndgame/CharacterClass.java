package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.Identifiable;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.exceptions.UndefinedCharacterClassLevelException;
import org.asciicerebrum.mydndgame.interfaces.entities.IClass;
import org.asciicerebrum.mydndgame.interfaces.entities.IFeat;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;

/**
 *
 * @author species8472
 */
public class CharacterClass implements Identifiable, IClass {

    /**
     * The unique id of the character class.
     */
    private String id;
    /**
     * The type of hit die associated with this character class.
     */
    private Dice hitDice;
    /**
     * The list of class levels which define the character advancement within
     * this character class.
     */
    private List<ILevel> classLevels = new ArrayList<ILevel>();

    /**
     * The list of feats which are automatically granted to the character when
     * acquiring this class.
     */
    private List<IFeat> classFeats = new ArrayList<IFeat>();

    /**
     *
     * @param level the position in the classLevel list. That is the class
     * level.
     * @return the level-th element of the classLevel list.
     */
    public final ILevel getClassLevelByLevel(final Integer level) {
        for (ILevel cl : this.classLevels) {
            if (cl.getLevel().equals(level)) {
                return cl;
            }
        }
        throw new UndefinedCharacterClassLevelException(this, level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dice getHitDice() {
        return hitDice;
    }

    /**
     * @param hitDiceInput the hitDice to set
     */
    public final void setHitDice(final Dice hitDiceInput) {
        this.hitDice = hitDiceInput;
    }

    /**
     * @return the classLevels
     */
    public final List<ILevel> getClassLevels() {
        return classLevels;
    }

    /**
     * @param classLevelsInput the classLevels to set
     */
    public final void setClassLevels(final List<ILevel> classLevelsInput) {
        this.classLevels = classLevelsInput;
    }

    /**
     * @return the classFeats
     */
    public final List<IFeat> getClassFeats() {
        return classFeats;
    }

    /**
     * @param classFeatsInput the classFeats to set
     */
    public final void setClassFeats(final List<IFeat> classFeatsInput) {
        this.classFeats = classFeatsInput;
    }

}
