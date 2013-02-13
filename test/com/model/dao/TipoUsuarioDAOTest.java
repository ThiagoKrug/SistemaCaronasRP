package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.TipoUsuario;
import java.sql.Connection;
import java.sql.SQLException;
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
public class TipoUsuarioDAOTest {

    Connection connection;

    public TipoUsuarioDAOTest() {
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
     * Test of getById method, of class TipoUsuarioDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'TipoUsuarioDAO'");
        TipoUsuarioDAO instance = new TipoUsuarioDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'TipoUsuarioDAO' não pode ser criada! <<<", instance);

        TipoUsuario admin = new TipoUsuario();
        admin.setIdTipoUsuario(1);
        admin.setTipoUsuario("Administrador de Frota");

        TipoUsuario servidor = new TipoUsuario();
        servidor.setIdTipoUsuario(2);
        servidor.setTipoUsuario("Servidor Solicitante");

        TipoUsuario motorista = new TipoUsuario();
        motorista.setIdTipoUsuario(3);
        motorista.setTipoUsuario("Motorista");

        TipoUsuario tu1 = instance.getById(admin.getIdTipoUsuario());
        TipoUsuario tu2 = instance.getById(servidor.getIdTipoUsuario());
        TipoUsuario tu3 = instance.getById(motorista.getIdTipoUsuario());

        assertEquals(tu1.getIdTipoUsuario(), admin.getIdTipoUsuario());
        assertEquals(tu1.getTipoUsuario(), admin.getTipoUsuario());

        assertEquals(tu2.getIdTipoUsuario(), servidor.getIdTipoUsuario());
        assertEquals(tu2.getTipoUsuario(), servidor.getTipoUsuario());

        assertEquals(tu3.getIdTipoUsuario(), motorista.getIdTipoUsuario());
        assertEquals(tu3.getTipoUsuario(), motorista.getTipoUsuario());
    }

    /**
     * Test of getTiposUsuarios method, of class TipoUsuarioDAO.
     */
    @Test
    public void testGetTiposUsuarios() {
        System.out.println("getTiposUsuarios");

        System.out.println("Testando a criação de Intâcia: 'TipoUsuarioDAO'");
        TipoUsuarioDAO instance = new TipoUsuarioDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'TipoUsuarioDAO' não pode ser criada! <<<", instance);

        TipoUsuario admin = new TipoUsuario();
        admin.setIdTipoUsuario(1);
        admin.setTipoUsuario("Administrador de Frota");

        TipoUsuario servidor = new TipoUsuario();
        servidor.setIdTipoUsuario(2);
        servidor.setTipoUsuario("Servidor Solicitante");

        TipoUsuario motorista = new TipoUsuario();
        motorista.setIdTipoUsuario(3);
        motorista.setTipoUsuario("Motorista");

        List<TipoUsuario> result = instance.getTiposUsuarios();
        TipoUsuario tu1 = result.get(0);
        TipoUsuario tu2 = result.get(1);
        TipoUsuario tu3 = result.get(2);

        assertEquals(tu1.getIdTipoUsuario(), admin.getIdTipoUsuario());
        assertEquals(tu1.getTipoUsuario(), admin.getTipoUsuario());

        assertEquals(tu2.getIdTipoUsuario(), servidor.getIdTipoUsuario());
        assertEquals(tu2.getTipoUsuario(), servidor.getTipoUsuario());

        assertEquals(tu3.getIdTipoUsuario(), motorista.getIdTipoUsuario());
        assertEquals(tu3.getTipoUsuario(), motorista.getTipoUsuario());
    }
}