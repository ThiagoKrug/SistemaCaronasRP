package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.TipoVeiculo;
import com.model.entity.Veiculo;
import java.sql.Connection;
import java.sql.SQLException;
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
    
    Connection connection;

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
     * Test of inserir method, of class VeiculoDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Veiculo veiculo = new Veiculo();

        System.out.println("Testando a criação de Intâcia: 'VeiculoDAO'");
        VeiculoDAO instance = new VeiculoDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'VeiculoDAO' não pode ser criada! <<<", instance);

        TipoVeiculo tv = new TipoVeiculo();
        tv.setIdTipoVeiculo(1);
        veiculo.setTipoVeiculo(tv);
        assertEquals(1, instance.inserir(veiculo));
    }

    /**
     * Test of altera method, of class VeiculoDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        Veiculo veiculo = new Veiculo();

        System.out.println("Testando a criação de Intâcia: 'VeiculoDAO'");
        VeiculoDAO instance = new VeiculoDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'VeiculoDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        Veiculo v = veiculo;
        
        try {
            instance.alterar(veiculo);
            fail("Alterou uma instância sem id de Veículo");
        } catch (NullPointerException e) {
            assertEquals(v, veiculo);
        }
    }

    /**
     * Test of deletar method, of class VeiculoDAO.
     */
    @Test
    public void testDeletar() {
        System.out.println("deletar");
        Veiculo veiculo = new Veiculo();

        System.out.println("Testando a criação de Intâcia: 'VeiculoDAO'");
        VeiculoDAO instance = new VeiculoDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'VeiculoDAO' não pode ser criada! <<<", instance);
        
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        Veiculo v = veiculo;
        try {
            instance.deletar(veiculo);
            fail("Deletou uma instância sem id de Veículo");
        } catch (NullPointerException e) {
            assertEquals(v, veiculo);
        }
    }
}