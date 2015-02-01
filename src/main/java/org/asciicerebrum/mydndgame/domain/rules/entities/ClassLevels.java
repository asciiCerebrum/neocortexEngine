package org.asciicerebrum.mydndgame.domain.rules.entities;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.particles.Level;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class ClassLevels {

    private UniqueId id;

    private final List<ClassLevel> elements = new ArrayList<ClassLevel>();

    public void addClass(final ClassLevel classLevel) {
        this.elements.add(classLevel);
    }

    /**
     *
     * @param level the position in the classLevel list. That is the class
     * level.
     * @return the level-th element of the classLevel list.
     */
    public ClassLevel getClassLevelByLevel(final Level level) {
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
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
        this.id = id;
    }

}
