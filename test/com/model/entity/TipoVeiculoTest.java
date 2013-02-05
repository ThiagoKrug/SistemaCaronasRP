/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Classe pai contem apenas métodos Set e Get. E por este motivo aqui só
 * testamos se a criação de instâncias ocorre normalmente.
 *
 * @author Juliano Macedo
 * @since 28/01/2013
 */
public class TipoVeiculoTest {

    public TipoVeiculoTest() {
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
     * Classe pai contem apenas métodos Set e Get. E por este motivo aqui só
     * testamos se a criação de instâncias ocorre normalmente.
     */
    @Test
    public void testTipoVeiculo() {
        System.out.println("testTipoVeiculo");

        System.out.println("Testando a criação de Intâcia: 'TipoVeiculo'");
        TipoVeiculo instance = new TipoVeiculo();
        assertNotNull(">>> A instância da Classe 'TipoVeiculo' não pode ser criada! <<<", instance);
    }
}