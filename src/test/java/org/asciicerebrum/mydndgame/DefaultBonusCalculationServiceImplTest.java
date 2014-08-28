package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.logappender.RecordingAppender;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author species8472
 */
public class DefaultBonusCalculationServiceImplTest {

    private static class ListBonusGranter {

        @BonusGranter
        private List<BonusSource> bonusSources;

        /**
         * @return the bonusSources
         */
        public List<BonusSource> getBonusSources() {
            return bonusSources;
        }

        /**
         * @param bonusSources the bonusSources to set
         */
        public void setBonusSources(List<BonusSource> bonusSources) {
            this.bonusSources = bonusSources;
        }
    }

    private static class MapBonusGranter {

        @BonusGranter
        private Map<BonusSource, Integer> bonusSources;

        /**
         * @return the bonusSources
         */
        public Map<BonusSource, Integer> getBonusSources() {
            return bonusSources;
        }

        /**
         * @param bonusSources the bonusSources to set
         */
        public void setBonusSources(Map<BonusSource, Integer> bonusSources) {
            this.bonusSources = bonusSources;
        }
    }

    private static class ObjectBonusGranter {

        @BonusGranter
        private BonusSource bonusSource;

        /**
         * @return the bonusSource
         */
        public BonusSource getBonusSource() {
            return bonusSource;
        }

        /**
         * @param bonusSource the bonusSource to set
         */
        public void setBonusSource(BonusSource bonusSource) {
            this.bonusSource = bonusSource;
        }
    }

    private static class NoSuchMethodBonusGranter {

        @BonusGranter
        private BonusSource bonusSource;
    }

    private DefaultBonusCalculationServiceImpl bcService;
    private List<IBonus> boni;
    private DiceAction targetTrue;
    private DiceAction targetFalse;
    private Bonus bonusTrue;
    private ObjectBonusGranter objBonusGranter;
    private ListBonusGranter listBonusGranter;

    private ISituationContext sitCon;

    public DefaultBonusCalculationServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.sitCon = mock(ISituationContext.class);

        this.bcService = new DefaultBonusCalculationServiceImpl();
        this.targetTrue = new DiceAction();
        this.targetTrue.setId("testTargetTrue");

        this.targetFalse = new DiceAction();
        this.targetFalse.setId("testTargetFalse");

        this.boni = new ArrayList<IBonus>();
        this.bonusTrue = new Bonus();
        this.bonusTrue.setTarget(this.targetTrue);
        this.bonusTrue.setValue(3L);
        Bonus bonusFalse = new Bonus();
        bonusFalse.setTarget(this.targetFalse);
        bonusFalse.setDynamicValueProvider(new BonusValueProvider() {

            public Long getDynamicValue(ISituationContext context) {
                return 7L;
            }
        });
        boni.add(this.bonusTrue);
        boni.add(bonusFalse);

        this.objBonusGranter = new ObjectBonusGranter();
        this.objBonusGranter.setBonusSource(new BonusSource() {
            public List<IBonus> getBoni() {
                return boni;
            }
        });

        this.listBonusGranter = new ListBonusGranter();
        List<BonusSource> bonusSources = new ArrayList<BonusSource>();
        bonusSources.add(new BonusSource() {

            public List<IBonus> getBoni() {
                return boni;
            }
        });
        bonusSources.add(new BonusSource() {

            public List<IBonus> getBoni() {
                return boni;
            }
        });
        bonusSources.add(new BonusSource() {

            public List<IBonus> getBoni() {
                return boni;
            }
        });
        this.listBonusGranter.setBonusSources(bonusSources);

        configureLog();
    }

    @After
    public void tearDown() {
    }

    private void configureLog() {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.removeAllAppenders();
        rootLogger.setLevel(Level.ALL);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout(
                "%d [%t] %-5p %c{1} - %m%n")));
        rootLogger.addAppender(RecordingAppender.appender(new PatternLayout(
                "%-5p - %m%n")));
    }

    /**
     * Test of filterBonusListByTarget method, of class BonusCalculationService.
     * Test of the correct result list size.
     */
    @Test
    public void testFilterBonusListByTarget() {
        List<IBonus> resultBoni
                = this.bcService.filterBonusListByTarget(
                        this.boni, this.targetTrue);

        assertEquals(1, resultBoni.size());
    }

    /**
     * Test of filterBonusListByTarget method, of class BonusCalculationService.
     * Test of the correct filter result.
     */
    @Test
    public void testFilterBonusListByTargetCorrectFilterResult() {
        List<IBonus> resultBoni
                = this.bcService.filterBonusListByTarget(
                        this.boni, this.targetTrue);

        assertEquals(this.bonusTrue, resultBoni.get(0));
    }

    /**
     * Traverse a list-based bonus granter object. Check size of result list.
     */
    @Test
    public void testTraverseBoniByTargetList() {
        List<IBonus> traversedBoni = this.bcService.traverseBoniByTarget(
                this.listBonusGranter, this.targetTrue);

        assertEquals(3, traversedBoni.size());
    }

    /**
     * Traverse a map-based bonus granter object. Check size of result list.
     */
    @Test
    public void testTraverseBoniByTargetMap() {
        MapBonusGranter mapBonusGranter = new MapBonusGranter();
        Map<BonusSource, Integer> bonusSources
                = new HashMap<BonusSource, Integer>();
        bonusSources.put(new BonusSource() {

            public List<IBonus> getBoni() {
                return boni;
            }
        }, 1);
        bonusSources.put(new BonusSource() {

            public List<IBonus> getBoni() {
                return boni;
            }
        }, 2);
        mapBonusGranter.setBonusSources(bonusSources);

        List<IBonus> traversedBoni = this.bcService.traverseBoniByTarget(
                mapBonusGranter, this.targetTrue);

        assertEquals(2, traversedBoni.size());
    }

    /**
     * Traverse a single object-based bonus granter object. Check size of result
     * list.
     */
    @Test
    public void testTraverseBoniByTargetObject() {

        List<IBonus> traversedBoni = this.bcService.traverseBoniByTarget(
                this.objBonusGranter, this.targetTrue);

        assertEquals(1, traversedBoni.size());
    }

    /**
     * Traverse a single object-based bonus granter object. Check content of
     * result list for correct target.
     */
    @Test
    public void testTraverseBoniByTargetObjectContent() {

        List<IBonus> traversedBoni = this.bcService.traverseBoniByTarget(
                this.objBonusGranter, this.targetTrue);

        assertEquals(this.targetTrue, traversedBoni.get(0).getTarget());
    }

    private boolean logContains(String expected) {
        String actual[] = RecordingAppender.messages();
        for (String log : actual) {
            if (log.contains(expected)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Traverse a single object-based bonus granter object. Bonus granter object
     * has no getter for the annotated field. So an exception should be thrown.
     */
    @Test
    public void testTraverseBoniByTargetNoSuchMethodException() {

        this.bcService.traverseBoniByTarget(
                new NoSuchMethodBonusGranter(), this.targetTrue);

        assertTrue(logContains("Annotated field does not have an associated"
                + " getter."));
    }

    /**
     * Granted boni are static and not dynamic.
     */
    @Test
    public void testRetrieveEffectiveBonusValueByTarget() {
        Long effectiveBonus
                = this.bcService.retrieveEffectiveBonusValueByTarget(
                        this.sitCon, this.listBonusGranter,
                        this.targetTrue);

        assertEquals(Long.valueOf(9), effectiveBonus);
    }

    /**
     * Granted boni are dynamic.
     */
    @Test
    public void testRetrieveEffectiveBonusValueByTargetDynamic() {
        Long effectiveBonus
                = this.bcService.retrieveEffectiveBonusValueByTarget(
                        this.sitCon, this.listBonusGranter,
                        this.targetFalse);

        assertEquals(Long.valueOf(21), effectiveBonus);
    }
}
