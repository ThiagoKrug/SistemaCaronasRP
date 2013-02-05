package com.model.dao;

import com.model.entity.SolicitacaoViagem;
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
public class SolicitacaoViagemDAOTest {

    public SolicitacaoViagemDAOTest() {
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
     * Test of inserir method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        SolicitacaoViagem solicitacao = null;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.inserir(solicitacao);
    }

    /**
     * Test of alterar method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        SolicitacaoViagem solicitacao = null;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        try {
            instance.alterar(solicitacao);
            assertTrue(false);
        } catch (NullPointerException e) {
            assertTrue("Deu exceção =D", true);
        }
    }

    /**
     * Test of deletar method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testDeletar_int() throws Exception {
        System.out.println("deletar");
        SolicitacaoViagem sv = new SolicitacaoViagem();
        sv.setId(0);

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.deletar(sv);
    }

    /**
     * Test of deletar method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testDeletar_SolicitacaoViagem() throws Exception {
        System.out.println("deletar");
        SolicitacaoViagem solicitacaoViagem = null;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.deletar(solicitacaoViagem);
    }

    /**
     * Test of getSolicitacoes method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testGetSolicitacoes() throws Exception {
        System.out.println("getSolicitacoes");

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        List expResult = null;
        List result = instance.getSolicitacoes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getById method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO();
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        SolicitacaoViagem expResult = null;
        SolicitacaoViagem result = instance.getById(id);
        assertEquals(expResult, result);
    }
}