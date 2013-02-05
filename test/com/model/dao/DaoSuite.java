/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

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
@Suite.SuiteClasses({com.model.dao.SolicitacaoViagemDAOTest.class, com.model.dao.PassageiroDAOTest.class, com.model.dao.TipoVeiculoDAOTest.class, com.model.dao.VeiculoDAOTest.class, com.model.dao.TipoUsuarioDAOTest.class, com.model.dao.UsuarioDAOTest.class})
public class DaoSuite {

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