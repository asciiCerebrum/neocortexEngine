package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.mechanics.managers.DiceRollManager;
import org.asciicerebrum.mydndgame.services.application.ConditionApplicationService;
import org.asciicerebrum.mydndgame.services.core.EntityPoolService;
import org.asciicerebrum.mydndgame.services.core.MapBasedEntityPoolService;
import org.asciicerebrum.mydndgame.services.statistics.InitiativeCalculationService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class InitializeCombatRoundWorkflowTest {

    private InitializeCombatRoundWorkflow wf;
    private DiceRollManager drManager;
    private DiceAction initiativeAction;
    private ConditionType flatFootedType;
    private InitiativeCalculationService initService;
    private ConditionApplicationService conditionService;
    private DndCharacter positiveCharacter;
    private DndCharacter negativeCharacter;
    private EntityPoolService entityPoolService;

    public InitializeCombatRoundWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.wf = new InitializeCombatRoundWorkflow();
        this.drManager = mock(DiceRollManager.class);
        this.initiativeAction = mock(DiceAction.class);
        this.flatFootedType = mock(ConditionType.class);
        this.initService = mock(InitiativeCalculationService.class);
        this.conditionService = mock(ConditionApplicationService.class);
        this.entityPoolService = new MapBasedEntityPoolService();

        this.wf.setConditionApplicationService(this.conditionService);
        this.wf.setDiceRollManager(this.drManager);
        this.wf.setFlatFootedType(this.flatFootedType);
        this.wf.setInitiativeAction(this.initiativeAction);
        this.wf.setInitiativeCalculationService(this.initService);
        this.wf.setEntityPoolService(this.entityPoolService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rollInitiativeTest() {
        final DndCharacters characters = new DndCharacters();
        final CombatRound combatRound = new CombatRound();

        final DndCharacter characterA = new DndCharacter();
        characterA.setUniqueId(new UniqueId("A"));
        final DndCharacter characterB = new DndCharacter();
        characterA.setUniqueId(new UniqueId("B"));
        characters.addDndCharacter(characterA);
        characters.addDndCharacter(characterB);
        
        this.entityPoolService.registerUniqueEntity(characterA);
        this.entityPoolService.registerUniqueEntity(characterB);

        when(this.initService.calcInitBonus(characterA))
                .thenReturn(new BonusValue(3));
        when(this.initService.calcInitBonus(characterB))
                .thenReturn(new BonusValue(5));
        when(this.drManager.rollDice(this.initiativeAction))
                .thenReturn(new DiceRoll(0L));

        this.wf.rollInitiative(characters.iterator(), combatRound);

        final Iterator<UniqueId> dndIt = combatRound.participantsIterator();
        dndIt.next();

        assertEquals(characterB.getUniqueId(), dndIt.next());
    }

    private CombatRound setupCombatRound() {
        final CombatRound combatRound = new CombatRound();
        this.positiveCharacter = new DndCharacter();
        this.positiveCharacter.setUniqueId(new UniqueId("A"));
        final DndCharacter characterB = new DndCharacter();
        characterB.setUniqueId(new UniqueId("B"));
        this.negativeCharacter = new DndCharacter();
        this.negativeCharacter.setUniqueId(new UniqueId("C"));

        combatRound.addParticipant(this.positiveCharacter.getUniqueId(),
                new CombatRoundPosition("a"));
        combatRound.addParticipant(this.negativeCharacter.getUniqueId(),
                new CombatRoundPosition("c"));
        combatRound.addParticipant(characterB.getUniqueId(),
                new CombatRoundPosition("a"));

        this.entityPoolService.registerUniqueEntity(characterB);
        this.entityPoolService.registerUniqueEntity(this.positiveCharacter);
        this.entityPoolService.registerUniqueEntity(this.negativeCharacter);

        return combatRound;
    }

    @Test
    public void getTieingParticipantsPositiveTest() {
        final DndCharacters dndCharacters
                = this.wf.getTieingParticipants(this.setupCombatRound());

        assertTrue(dndCharacters.contains(this.positiveCharacter));
    }

    @Test
    public void getTieingParticipantsNegativeTest() {
        final DndCharacters dndCharacters
                = this.wf.getTieingParticipants(this.setupCombatRound());

        assertFalse(dndCharacters.contains(this.negativeCharacter));
    }

    private DndCharacters setupTieingParticipants(final CombatRound cRound) {
        final DndCharacters tieingParticipants = new DndCharacters();
        tieingParticipants.addDndCharacter(this.negativeCharacter);
        final DndCharacter furtherCharacter = new DndCharacter();
        furtherCharacter.setUniqueId(new UniqueId("D"));
        tieingParticipants.addDndCharacter(furtherCharacter);

        cRound.addParticipant(furtherCharacter.getUniqueId(),
                new CombatRoundPosition("c"));
        // the positive character is already there but has to get another
        // position to not interfere with the tieing of the others.
        cRound.addParticipant(this.positiveCharacter.getUniqueId(),
                new CombatRoundPosition("xyz"));

        return tieingParticipants;
    }

    @Test
    public void tieResolutionStepPositiveTest() {
        final CombatRound combatRound = this.setupCombatRound();
        final DndCharacters tieingParticipants
                = this.setupTieingParticipants(combatRound);

        // resolving ties with different dice rolls
        when(this.drManager.rollDice(this.initiativeAction))
                .thenReturn(new DiceRoll(1L), new DiceRoll(2L));

        final DndCharacters dndCharacters
                = this.wf.tieResolutionStep(tieingParticipants, combatRound);

        assertFalse(dndCharacters.hasEntries());
    }

    @Test
    public void tieResolutionStepNegativeTest() {
        final CombatRound combatRound = this.setupCombatRound();
        final DndCharacters tieingParticipants
                = this.setupTieingParticipants(combatRound);

        // keeping ties with equal dice rolls
        when(this.drManager.rollDice(this.initiativeAction))
                .thenReturn(new DiceRoll(3L), new DiceRoll(3L));

        final DndCharacters dndCharacters
                = this.wf.tieResolutionStep(tieingParticipants, combatRound);

        assertTrue(dndCharacters.hasEntries());
    }
}
