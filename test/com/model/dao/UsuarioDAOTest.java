/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.Usuario;
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
public class UsuarioDAOTest {

    public UsuarioDAOTest() {
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
     * Test of getById method, of class UsuarioDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO();
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);

        Usuario expResult = null;
        Usuario result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuarios method, of class UsuarioDAO.
     */
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO();
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);

        List<Usuario> expResult = new ArrayList<Usuario>();
        List result = instance.getUsuarios();
        assertEquals(expResult, result);
    }

    /**
     * Test of inserir method, of class UsuarioDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Usuario usuario = new Usuario();

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO();
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.inserir(usuario);
    }

    /**
     * Test of alterar method, of class UsuarioDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        Usuario usuario = new Usuario();

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO();
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.alterar(usuario);
    }

    /**
     * Test of deletar method, of class UsuarioDAO.
     */
    @Test
    public void testDeletar() {
        System.out.println("deletar");
        Usuario usuario = new Usuario();

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO();
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.deletar(usuario);
    }
}