package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.Passageiro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

    Connection connection;

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
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of inserir method, of class PassageiroDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");

        System.out.println("Testando a criação de Instâcia: 'Passageiro'");
        Passageiro passageiro = new Passageiro();
        assertNotNull(">>> A instância da Classe 'Passageiro' não pode ser criada! <<<", passageiro);

        System.out.println("Testando a criação de Instâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        assertEquals(1, instance.inserir(passageiro));
        for (Iterator<Passageiro> it = instance.getPassageiros().iterator(); it.hasNext();) {
            Passageiro passageiro1 = it.next();
            assertEquals(1, instance.deletar(passageiro1));
        }
    }

    /**
     * Test of getPassageiros method, of class PassageiroDAO.
     */
    @Test
    public void testGetPassageiros() {
        System.out.println("getPassageiros");

        System.out.println("Testando a criação de Instâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);
        List<Passageiro> passageiros = instance.getPassageiros();
        for (Iterator<Passageiro> it = passageiros.iterator(); it.hasNext();) {
            Passageiro passageiro = it.next();
            instance.deletar(passageiro);
        }

        Passageiro pUm = new Passageiro();
        pUm.setNome("p1");
        pUm.setRg("12345");
        pUm.setTelefone("54321");
        instance.inserir(pUm);

        Passageiro pDois = new Passageiro();
        pDois.setNome("p2");
        pDois.setRg("11111");
        pDois.setTelefone("22222");
        instance.inserir(pDois);

        List<Passageiro> result = instance.getPassageiros();
        assertNotNull(result);
        assertEquals(2, result.size());
        Passageiro p1 = result.get(0);
        Passageiro p2 = result.get(1);
        assertNotNull(p1.getIdPassageiro());
        assertEquals(pUm.getNome(), p1.getNome());
        assertEquals(pUm.getRg(), p1.getRg());
        assertEquals(pUm.getTelefone(), p1.getTelefone());
        assertEquals(1, instance.deletar(p1));
        assertNotNull(p2);
        assertEquals(pDois.getNome(), p2.getNome());
        assertEquals(pDois.getRg(), p2.getRg());
        assertEquals(pDois.getTelefone(), p2.getTelefone());
        assertEquals(1, instance.deletar(p2));
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
        PassageiroDAO instance = new PassageiroDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        Passageiro p = passageiro;

        try {
            instance.alterar(passageiro);
            fail("Alterou uma instância sem id de Passageiro");
        } catch (NullPointerException e) {
            assertEquals(p, passageiro);
        }
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
        PassageiroDAO instance = new PassageiroDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        Passageiro p = passageiro;
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        try {
            instance.deletar(passageiro);
            fail("Deletou uma instância sem id de Passageiro");
        } catch (NullPointerException e) {
            assertEquals(p, passageiro);
        }
    }

    /**
     * Test of getById method, of class PassageiroDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'PassageiroDAO'");
        PassageiroDAO instance = new PassageiroDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);
        Passageiro passageiro = instance.getById(id);
        if (passageiro != null) {
            instance.deletar(passageiro);
        }

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
        PassageiroDAO instance = new PassageiroDAO(this.connection);
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
        PassageiroDAO instance = new PassageiroDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'PassageiroDAO' não pode ser criada! <<<", instance);

        Passageiro expResult = null;
        Passageiro result = instance.getByRG(nome);
        assertEquals(expResult, result);
    }
}