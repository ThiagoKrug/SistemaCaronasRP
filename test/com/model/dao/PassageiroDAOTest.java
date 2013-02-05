package com.model.dao;

import com.model.entity.Passageiro;
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
public class PassageiroDAOTest {

    public PassageiroDAOTest() {
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
     * Test of inserir method, of class PassageiroDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");

        System.out.println("Testando a criação de Intâcia: 'Passageiro'");
        Passageiro passageiro = new Passageiro();
        assertNotNull(">>> A instância da Classe 'Passageiro' não pode ser criada! <<<", passageiro);

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.inserir(passageiro);
    }

    /**
     * Test of getPassageiros method, of class PassageiroDAO.
     */
    @Test
    public void testGetPassageiros() {
        System.out.println("getPassageiros");

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        List expResult = null;
        List result = instance.getPassageiros();
        assertEquals(expResult, result);
    }

    /**
     * Test of altera method, of class PassageiroDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");

        System.out.println("Testando a criação de Intâcia: 'Passageiro'");
        Passageiro passageiro = new Passageiro();
        assertNotNull(">>> A instância da Classe 'Passageiro' não pode ser criada! <<<", passageiro);

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.alterar(passageiro);
    }

    /**
     * Test of remove method, of class PassageiroDAO.
     */
    @Test
    public void testDeletar() {
        System.out.println("deletar");

        System.out.println("Testando a criação de Intâcia: 'Passageiro'");
        Passageiro passageiro = new Passageiro();
        assertNotNull(">>> A instância da Classe 'Passageiro' não pode ser criada! <<<", passageiro);

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.deletar(passageiro);
    }

    /**
     * Test of getById method, of class PassageiroDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        Passageiro expResult = null;
        Passageiro result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getByNome method, of class PassageiroDAO.
     */
    @Test
    public void testGetByNome() {
        System.out.println("getByNome");
        String nome = "";

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        List<Passageiro> expResult = new ArrayList<Passageiro>();
        List result = instance.getByNome(nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of getByRG method, of class PassageiroDAO.
     */
    @Test
    public void testGetByRG() {
        System.out.println("getByRG");
        String nome = "";

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO();
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        Passageiro expResult = null;
        Passageiro result = instance.getByRG(nome);
        assertEquals(expResult, result);
    }
}