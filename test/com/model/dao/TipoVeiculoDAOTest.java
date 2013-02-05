/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.TipoVeiculo;
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
public class TipoVeiculoDAOTest {

    public TipoVeiculoDAOTest() {
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
     * Test of getById method, of class TipoVeiculoDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'TipoVeiculoDAO'");
        TipoVeiculoDAO instance = new TipoVeiculoDAO();
        assertNotNull(">>> A instância da Classe 'TipoVeiculoDAO' não pode ser criada! <<<", instance);


        TipoVeiculo expResult = null;
        TipoVeiculo result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTiposVeiculos method, of class TipoVeiculoDAO.
     */
    @Test
    public void testGetTiposVeiculos() {
        System.out.println("getTiposVeiculos");

        System.out.println("Testando a criação de Intâcia: 'TipoUsuarioDAO'");
        TipoVeiculoDAO instance = new TipoVeiculoDAO();
        assertNotNull(">>> A instância da Classe 'TipoVeiculoDAO' não pode ser criada! <<<", instance);

        List<TipoVeiculo> expResult = new ArrayList<TipoVeiculo>();
        List result = instance.getTiposVeiculos();
        assertEquals(expResult, result);
    }
}