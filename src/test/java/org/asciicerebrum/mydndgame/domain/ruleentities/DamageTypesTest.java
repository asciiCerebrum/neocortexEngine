package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class DamageTypesTest {

    private DamageTypes damageTypes;

    public DamageTypesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.damageTypes = new DamageTypes();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getFirstEmptyTest() {
        assertNull(this.damageTypes.getFirst());
    }

    @Test
    public void getFirstNonEmptyTest() {
        final DamageType dTypeA = new DamageType();
        final DamageType dTypeB = new DamageType();
        this.damageTypes.addDamageType(dTypeA);
        this.damageTypes.addDamageType(dTypeB);

        assertEquals(dTypeA, this.damageTypes.getFirst());
    }

}
