package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.services.core.BonusCalculationService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.mockito.Mockito.mock;

/**
 *
 * @author species8472
 */
public class DefaultHpCalculationServiceTest {

    private DefaultHpCalculationService service;

    private DiceAction hpAction;

    private Ability conAbility;

    private BonusCalculationService bonusService;

    private AbilityCalculationService abilityService;

    public DefaultHpCalculationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultHpCalculationService();
        this.bonusService = mock(BonusCalculationService.class);
        this.abilityService = mock(AbilityCalculationService.class);
        this.hpAction = new DiceAction();
        this.conAbility = new Ability();

        this.service.setHpAction(this.hpAction);
        this.service.setConAbility(this.conAbility);
        this.service.setBonusService(this.bonusService);
        this.service.setAbilityService(this.abilityService);
    }

    @After
    public void tearDown() {
    }

}
