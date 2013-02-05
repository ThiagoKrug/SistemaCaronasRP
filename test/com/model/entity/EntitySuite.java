package com.model.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Juliano Macedo
 * @since 28/01/2013
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.model.entity.TipoUsuarioTest.class, com.model.entity.PassageiroTest.class, com.model.entity.SolicitacaoViagemTest.class, com.model.entity.VeiculoTest.class, com.model.entity.StatusSolicitacaoViagemTest.class, com.model.entity.UsuarioTest.class, com.model.entity.TipoVeiculoTest.class})
public class EntitySuite {

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