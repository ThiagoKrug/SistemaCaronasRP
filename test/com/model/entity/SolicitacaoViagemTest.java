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
 * Classe pai contem apenas métodos Set e Get.
 *
 * @author Juliano Macedo
 * @since 28/01/2013
 */
public class SolicitacaoViagemTest {
    
    public SolicitacaoViagemTest() {
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
    public void testSolicitacaoViagem() {
        System.out.println("testSolicitacaoViagem");

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagem'");
        SolicitacaoViagem instance = new SolicitacaoViagem();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagem' não pode ser criada! <<<", instance);
    }
}