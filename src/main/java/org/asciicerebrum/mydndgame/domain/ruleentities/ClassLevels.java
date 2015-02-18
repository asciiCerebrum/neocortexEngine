package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.particles.Level;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class ClassLevels {

    /**
     * The unique identifier for the class levels collection.
     */
    private UniqueId id;

    /**
     * The central collection of class levels.
     */
    private final List<ClassLevel> elements = new ArrayList<ClassLevel>();

    /**
     * Adds a further class level to the collection.
     *
     * @param classLevel the class leve to add.
     */
    public final void addClass(final ClassLevel classLevel) {
        this.elements.add(classLevel);
    }

    /**
     *
     * @param level the position in the classLevel list. That is the class
     * level.
     * @return the level-th element of the classLevel list.
     */
    public final ClassLevel getClassLevelByLevel(final Level level) {
        for (ClassLevel clLvl : this.elements) {
            if (level.equals(clLvl.getLevel())) {
                return clLvl;
            }
        }
        return null;
    }

    /**
     * @return the id
     */
    public final UniqueId getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final UniqueId idInput) {
        this.id = idInput;
    }

}
