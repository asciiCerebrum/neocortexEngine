package org.asciicerebrum.mydndgame.interfaces.entities;

import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;

/**
 *
 * @author Tabea Raab
 */
public interface IBonus {

    BonusValueProvider getDynamicValueProvider();
    
    void setValue(Long value);
    
    Long getValue();
    
    void setTarget(BonusTarget bonusTarget);
    
    BonusTarget getTarget();
    
    void setRank(Long rank);
    
    Long getRank();
    
    void setBonusType(IBonusType bonusType);
    
    IBonusType getBonusType();
    
    IBonus makeCopy();

}
