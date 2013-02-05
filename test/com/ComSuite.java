package com;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * Suite que engloba as demais suites do package com.
 * 
 * @author Juliano Macedo
 * @since 28/01/2013
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.model.ModelSuite.class, com.jdbc.JdbcSuite.class})
public class ComSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}