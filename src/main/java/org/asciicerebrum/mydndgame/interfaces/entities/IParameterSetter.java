package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IParameterSetter {

    /**
     * Sets the parameter of the feat. E.g. Exotic Weapon Proficiency
     * [Scimitar].
     *
     * @param feat the feat to set the parameter for.
     * @param parameter the parameter to set as an object.
     */
    void setFeatParameter(IFeat feat, Object parameter);

}
