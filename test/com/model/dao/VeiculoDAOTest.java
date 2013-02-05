package com.model.dao;

import com.model.entity.Veiculo;
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
public class VeiculoDAOTest {

    public VeiculoDAOTest() {
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
     * Test of inserir method, of class VeiculoDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Veiculo veiculo = new Veiculo();

        System.out.println("Testando a criação de Intâcia: 'VeiculoDAO'");
        VeiculoDAO instance = new VeiculoDAO();
        assertNotNull(">>> A instância da Classe 'VeiculoDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.inserir(veiculo);
    }

    /**
     * Test of altera method, of class VeiculoDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        Veiculo veiculo = new Veiculo();

        System.out.println("Testando a criação de Intâcia: 'VeiculoDAO'");
        VeiculoDAO instance = new VeiculoDAO();
        assertNotNull(">>> A instância da Classe 'VeiculoDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.alterar(veiculo);
    }

    /**
     * Test of deletar method, of class VeiculoDAO.
     */
    @Test
    public void testDeletar() {
        System.out.println("deletar");
        Veiculo veiculo = new Veiculo();

        System.out.println("Testando a criação de Intâcia: 'VeiculoDAO'");
        VeiculoDAO instance = new VeiculoDAO();
        assertNotNull(">>> A instância da Classe 'VeiculoDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        instance.deletar(veiculo);
    }
}