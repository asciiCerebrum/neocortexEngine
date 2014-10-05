package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.Damage;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamage;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;

/**
 *
 * @author species8472
 */
public class DamageWorkflow implements IWorkflow {

    /**
     * Service for rolling dice.
     */
    private IDiceRollManager diceRollManager;

    /**
     * Key for retrieving the criticalness of the attack.
     */
    private InteractionResponseKey attackCriticalKey;

    /**
     * The least damage you can do on a successful attack roll.
     */
    private Long minimumDamage;

    /**
     * {@inheritDoc} Applies damage on the character.
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction,
            final IInteractionResponse response) {

        ISituationContext sourceContext = interaction.getTriggeringCharacter()
                .getSituationContext();
        IWeapon sourceWeapon = (IWeapon) interaction.getTriggeringCharacter()
                .getBodySlotByType(sourceContext.getBodySlotType()).getItem();

        // standard when there is no critical hit
        Integer damageMuliplicator = 1;
        // consider critical hits and damage multiplication!
        Boolean isCritical
                = response.getValue(this.getAttackCriticalKey(), Boolean.class);
        if (isCritical != null && isCritical) {
            damageMuliplicator = sourceWeapon.getCriticalFactor();
        }

        // make a damage roll
        Long sourceDamageRollResult = 0L;
        for (int i = 0; i < damageMuliplicator; i++) {
            sourceDamageRollResult += this.getDiceRollManager()
                    .rollDice(sourceWeapon.getDamage());
        }

        Long sourceDamageBonus = interaction.getTriggeringCharacter()
                .getDamageBonus();
        // consider minimum damage!
        Long totalSourceDamage
                = Math.max(this.getMinimumDamage(),
                        sourceDamageRollResult + sourceDamageBonus);

        // apply damage: this must be done at the character (special method for
        // applying damage) because special attributes like damage reduction
        // could apply! (character has observers for that.)
        //TODO consider possible sneak attack of rogue, etc.
        //TODO an attack could inflict multiple types of damage,
        // e.g. +1d6 poison, etc. So we deliver a list of damages.
        IDamage sourceDamage = new Damage();
        sourceDamage.setDamageValue(totalSourceDamage);
        sourceDamage.setWeapon(sourceWeapon);
        sourceDamage.setDamageType(sourceContext.getDamageType());

        interaction.getTargetCharacters().get(0).applyDamage(sourceDamage);

        // set conditions, e.g. unconscious --> this is done in the character!
        return response;
    }

    /**
     * @return the diceRollManager
     */
    public final IDiceRollManager getDiceRollManager() {
        return diceRollManager;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final IDiceRollManager diceRollManagerInput) {
        this.diceRollManager = diceRollManagerInput;
    }

    /**
     * @return the minimumDamage
     */
    public final Long getMinimumDamage() {
        return minimumDamage;
    }

    /**
     * @param minimumDamageInput the minimumDamage to set
     */
    public final void setMinimumDamage(final Long minimumDamageInput) {
        this.minimumDamage = minimumDamageInput;
    }

    /**
     * @return the attackCriticalKey
     */
    public final InteractionResponseKey getAttackCriticalKey() {
        return attackCriticalKey;
    }

    /**
     * @param attackCriticalKeyInput the attackCriticalKey to set
     */
    public final void setAttackCriticalKey(
            final InteractionResponseKey attackCriticalKeyInput) {
        this.attackCriticalKey = attackCriticalKeyInput;
    }

}
