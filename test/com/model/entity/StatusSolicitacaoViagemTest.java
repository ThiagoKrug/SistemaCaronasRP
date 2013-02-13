/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

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
public class StatusSolicitacaoViagemTest {
    
    public StatusSolicitacaoViagemTest() {
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
     * Test of values method, of class StatusSolicitacaoViagem.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        StatusSolicitacaoViagem[] result = StatusSolicitacaoViagem.values();
        assertEquals(3, result.length);
        assertEquals(StatusSolicitacaoViagem.SOLICITADO, result[0]);
        assertEquals(StatusSolicitacaoViagem.CANCELADO, result[1]);
        assertEquals(StatusSolicitacaoViagem.EFETIVADO, result[2]);
    }

    /**
     * Test of valueOf method, of class StatusSolicitacaoViagem.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        
        StatusSolicitacaoViagem expResult = StatusSolicitacaoViagem.CANCELADO;
        StatusSolicitacaoViagem result = StatusSolicitacaoViagem.valueOf("CANCELADO");
        assertEquals(expResult, result);
        
        expResult = StatusSolicitacaoViagem.SOLICITADO;
        result = StatusSolicitacaoViagem.valueOf("SOLICITADO");
        assertEquals(expResult, result);
    }
}