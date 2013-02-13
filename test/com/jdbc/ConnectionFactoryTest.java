package com.jdbc;

import com.model.dao.SolicitacaoViagemDAO;
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
public class ConnectionFactoryTest {

    public ConnectionFactoryTest() {
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
     * Test of getConnection method, of class ConnectionFactory.
     */
    @Test
    public void testConnectionFactory() {
        System.out.println("ConnectionFactory");

        System.out.println("Testando a criação de Intâcia: 'ConnectionFactory'");
        ConnectionFactory instance = new ConnectionFactory();
        assertNotNull(">>> A instância da Classe 'ConnectionFactory' não pode ser criada! <<<", instance);
    }

    @Test
    public void testGetConnection() throws SQLException {
        System.out.println("ConnectionFactory");

        System.out.println("Testando a criação de Intâcia: 'ConnectionFactory'");
        ConnectionFactory instance = new ConnectionFactory();
        assertNotNull(">>> A instância da Classe 'ConnectionFactory' não pode ser criada! <<<", instance);

        Connection conn = instance.getConnection();
        assertNotNull(conn);
    }
}