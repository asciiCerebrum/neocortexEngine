package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class AbilityBonusValueProvider implements BonusValueProvider {

    private Ability ability;

    public Long getDynamicValue(final DndCharacter dndCharacter) {
        
        //TODO collect all boni/mali with this ability as target
        // e.g. sicknesses can give a -4 malus on Constitution.
        // or some potions can grant a +4 bonus on Dexterity (Cat's Grace)
        
        final Long abilityScore 
                = dndCharacter.getAbilityMap().get(this.ability);
        Long abilityMod = this.calculateAbilityMod(abilityScore);
        
        return abilityMod;
    }

    private Long calculateAbilityMod(final Long score) {
        return Math.round(Math.floor((score - 10) / 2.0));
    }

    /**
     * @return the ability
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * @param ability the ability to set
     */
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

}
