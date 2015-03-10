package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.core.particles.Level;
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
public class ClassLevelsTest {

    private ClassLevels classLevels;

    public ClassLevelsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.classLevels = new ClassLevels();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getClassLevelByLevelNullTest() {
        assertNull(this.classLevels.getClassLevelByLevel(new Level(1L)));
    }

    @Test
    public void getClassLevelByLevelSuccessTest() {
        final ClassLevel lvl1 = new ClassLevel();
        lvl1.setLevel(new Level(1L));
        final ClassLevel lvl2 = new ClassLevel();
        lvl2.setLevel(new Level(2L));
        final ClassLevel lvl3 = new ClassLevel();
        lvl3.setLevel(new Level(3L));

        this.classLevels.addClass(lvl1);
        this.classLevels.addClass(lvl2);
        this.classLevels.addClass(lvl3);

        assertEquals(lvl2, this.classLevels
                .getClassLevelByLevel(new Level(2L)));
    }

}
