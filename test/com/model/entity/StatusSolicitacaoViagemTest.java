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
        StatusSolicitacaoViagem[] expResult = null;
        StatusSolicitacaoViagem[] result = StatusSolicitacaoViagem.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class StatusSolicitacaoViagem.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        StatusSolicitacaoViagem expResult = null;
        StatusSolicitacaoViagem result = StatusSolicitacaoViagem.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class StatusSolicitacaoViagem.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        StatusSolicitacaoViagem instance = null;
        String expResult = "status";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}