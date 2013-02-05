/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.TipoUsuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juliano Macedo
 * @since 28/01/2013
 */
public class TipoUsuarioDAOTest {

    public TipoUsuarioDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getById method, of class TipoUsuarioDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'TipoUsuarioDAO'");
        TipoUsuarioDAO instance = new TipoUsuarioDAO();
        assertNotNull(">>> A instância da Classe 'TipoUsuarioDAO' não pode ser criada! <<<", instance);

        TipoUsuario expResult = null;
        TipoUsuario result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTiposUsuarios method, of class TipoUsuarioDAO.
     */
    @Test
    public void testGetTiposUsuarios() {
        System.out.println("getTiposUsuarios");

        System.out.println("Testando a criação de Intâcia: 'TipoUsuarioDAO'");
        TipoUsuarioDAO instance = new TipoUsuarioDAO();
        assertNotNull(">>> A instância da Classe 'TipoUsuarioDAO' não pode ser criada! <<<", instance);

        List<TipoUsuario> expResult = new ArrayList<TipoUsuario>();
        List result = instance.getTiposUsuarios();
        assertEquals(expResult, result);
    }
}