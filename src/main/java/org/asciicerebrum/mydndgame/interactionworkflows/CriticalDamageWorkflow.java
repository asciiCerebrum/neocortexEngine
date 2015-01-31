package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.domain.transfer.Interaction;
import org.asciicerebrum.mydndgame.facades.gameentities.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CriticalDamageWorkflow implements IWorkflow {

    /**
     * Workflow for the single damage.
     */
    private IWorkflow damageWorkflow;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc }
     */
    @Override
    public final void runWorkflow(final Interaction interaction) {

        final InventoryItem sourceWeapon = this.situationContextService
                .getActiveItem(interaction.getTriggeringCharacter());

        if (!(sourceWeapon instanceof Weapon)) {
            // no damage in this case.
            return;
        }

        // standard when there is no critical hit
        final CriticalFactor damageMuliplicator
                = this.weaponServiceFacade.getCriticalFactor(
                        (Weapon) sourceWeapon,
                        interaction.getTriggeringCharacter());

        for (long i = 0; i < damageMuliplicator.getValue(); i++) {
            this.damageWorkflow.runWorkflow(interaction);
        }
    }

    /**
     * @param damageWorkflowInput the damageWorkflow to set
     */
    public final void setDamageWorkflow(final IWorkflow damageWorkflowInput) {
        this.damageWorkflow = damageWorkflowInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

}
