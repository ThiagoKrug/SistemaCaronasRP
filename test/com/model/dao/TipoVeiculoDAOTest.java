package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.TipoVeiculo;
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
public class TipoVeiculoDAOTest {

    Connection connection;

    public TipoVeiculoDAOTest() {
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
     * Test of getById method, of class TipoVeiculoDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");

        System.out.println("Testando a criação de Intâcia: 'TipoVeiculoDAO'");
        TipoVeiculoDAO instance = new TipoVeiculoDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'TipoVeiculoDAO' não pode ser criada! <<<", instance);

        TipoVeiculo onibus = new TipoVeiculo();
        onibus.setIdTipoVeiculo(1);
        onibus.setTipoVeiculo("Ônibus");

        TipoVeiculo microOnibus = new TipoVeiculo();
        microOnibus.setIdTipoVeiculo(2);
        microOnibus.setTipoVeiculo("Micro-ônibus");

        TipoVeiculo veiculoPassageiro = new TipoVeiculo();
        veiculoPassageiro.setIdTipoVeiculo(3);
        veiculoPassageiro.setTipoVeiculo("Veiculo de Passageiro");

        TipoVeiculo veiculoUtilitario = new TipoVeiculo();
        veiculoUtilitario.setIdTipoVeiculo(4);
        veiculoUtilitario.setTipoVeiculo("Veiculo Utilitário");

        TipoVeiculo veiculoCarga = new TipoVeiculo();
        veiculoCarga.setIdTipoVeiculo(5);
        veiculoCarga.setTipoVeiculo("Veiculo de Carga");

        TipoVeiculo v1 = instance.getById(onibus.getIdTipoVeiculo());
        TipoVeiculo v2 = instance.getById(microOnibus.getIdTipoVeiculo());
        TipoVeiculo v3 = instance.getById(veiculoPassageiro.getIdTipoVeiculo());
        TipoVeiculo v4 = instance.getById(veiculoUtilitario.getIdTipoVeiculo());
        TipoVeiculo v5 = instance.getById(veiculoCarga.getIdTipoVeiculo());

        assertEquals(v1.getIdTipoVeiculo(), onibus.getIdTipoVeiculo());
        assertEquals(v1.getTipoVeiculo(), onibus.getTipoVeiculo());

        assertEquals(v2.getIdTipoVeiculo(), microOnibus.getIdTipoVeiculo());
        assertEquals(v2.getTipoVeiculo(), microOnibus.getTipoVeiculo());

        assertEquals(v3.getIdTipoVeiculo(), veiculoPassageiro.getIdTipoVeiculo());
        assertEquals(v3.getTipoVeiculo(), veiculoPassageiro.getTipoVeiculo());

        assertEquals(v4.getIdTipoVeiculo(), veiculoUtilitario.getIdTipoVeiculo());
        assertEquals(v4.getTipoVeiculo(), veiculoUtilitario.getTipoVeiculo());

        assertEquals(v5.getIdTipoVeiculo(), veiculoCarga.getIdTipoVeiculo());
        assertEquals(v5.getTipoVeiculo(), veiculoCarga.getTipoVeiculo());
    }

    /**
     * Test of getTiposVeiculos method, of class TipoVeiculoDAO.
     */
    @Test
    public void testGetTiposVeiculos() {
        System.out.println("getTiposVeiculos");

        System.out.println("Testando a criação de Intâcia: 'TipoUsuarioDAO'");
        TipoVeiculoDAO instance = new TipoVeiculoDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'TipoVeiculoDAO' não pode ser criada! <<<", instance);

        TipoVeiculo onibus = new TipoVeiculo();
        onibus.setIdTipoVeiculo(1);
        onibus.setTipoVeiculo("Ônibus");

        TipoVeiculo microOnibus = new TipoVeiculo();
        microOnibus.setIdTipoVeiculo(2);
        microOnibus.setTipoVeiculo("Micro-ônibus");

        TipoVeiculo veiculoPassageiro = new TipoVeiculo();
        veiculoPassageiro.setIdTipoVeiculo(3);
        veiculoPassageiro.setTipoVeiculo("Veiculo de Passageiro");

        TipoVeiculo veiculoUtilitario = new TipoVeiculo();
        veiculoUtilitario.setIdTipoVeiculo(4);
        veiculoUtilitario.setTipoVeiculo("Veiculo Utilitário");

        TipoVeiculo veiculoCarga = new TipoVeiculo();
        veiculoCarga.setIdTipoVeiculo(5);
        veiculoCarga.setTipoVeiculo("Veiculo de Carga");

        List<TipoVeiculo> result = instance.getTiposVeiculos();
        TipoVeiculo v1 = result.get(0);
        TipoVeiculo v2 = result.get(1);
        TipoVeiculo v3 = result.get(2);
        TipoVeiculo v4 = result.get(3);
        TipoVeiculo v5 = result.get(4);

        assertEquals(v1.getIdTipoVeiculo(), onibus.getIdTipoVeiculo());
        assertEquals(v1.getTipoVeiculo(), onibus.getTipoVeiculo());

        assertEquals(v2.getIdTipoVeiculo(), microOnibus.getIdTipoVeiculo());
        assertEquals(v2.getTipoVeiculo(), microOnibus.getTipoVeiculo());

        assertEquals(v3.getIdTipoVeiculo(), veiculoPassageiro.getIdTipoVeiculo());
        assertEquals(v3.getTipoVeiculo(), veiculoPassageiro.getTipoVeiculo());

        assertEquals(v4.getIdTipoVeiculo(), veiculoUtilitario.getIdTipoVeiculo());
        assertEquals(v4.getTipoVeiculo(), veiculoUtilitario.getTipoVeiculo());

        assertEquals(v5.getIdTipoVeiculo(), veiculoCarga.getIdTipoVeiculo());
        assertEquals(v5.getTipoVeiculo(), veiculoCarga.getTipoVeiculo());
    }
}