package org.asciicerebrum.neocortexengine.mechanics.interactionworkflows;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.neocortexengine.domain.core.particles.CriticalFactor;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;

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
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

    @Override
    public final void runWorkflow(final Interaction interaction,
            final Campaign campaign) {

        final UniqueEntity sourceWeapon
                = this.getEntityPoolService().getEntityById(
                        this.getSituationContextService()
                        .getActiveItemId(interaction.getTriggeringCharacter()));

        if (!(sourceWeapon instanceof Weapon)) {
            // no damage in this case.
            return;
        }

        // standard when there is no critical hit
        final CriticalFactor damageMuliplicator
                = this.getWeaponServiceFacade().getCriticalFactor(
                        (Weapon) sourceWeapon,
                        interaction.getTriggeringCharacter());

        for (long i = 0; i < damageMuliplicator.getValue(); i++) {
            this.getDamageWorkflow().runWorkflow(interaction, campaign);
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

    /**
     * @return the damageWorkflow
     */
    public final IWorkflow getDamageWorkflow() {
        return damageWorkflow;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

}
