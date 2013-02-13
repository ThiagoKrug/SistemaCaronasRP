package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.SolicitacaoViagem;
import com.model.entity.StatusSolicitacaoViagem;
import com.model.entity.TipoUsuario;
import com.model.entity.TipoVeiculo;
import com.model.entity.Usuario;
import com.model.entity.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Juliano Macedo
 * @since 28/01/2013
 */
public class SolicitacaoViagemDAOTest {

    Connection connection;

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
     * Test of inserir method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        SolicitacaoViagem solicitacao = new SolicitacaoViagem();

        System.out.println("Testando a criação de Instâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        Veiculo v = new Veiculo();
        v.setPlaca("abc-9999");
        TipoVeiculo tv = new TipoVeiculo();
        tv.setIdTipoVeiculo(1);
        v.setTipoVeiculo(tv);

        VeiculoDAO vdao = new VeiculoDAO(connection);
        assertEquals(1, vdao.inserir(v));
        for (Iterator<Veiculo> it = vdao.getVeiculos().iterator(); it.hasNext();) {
            Veiculo veiculo = it.next();
            if (veiculo.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                v = veiculo;
                break;
            }
        }

        TipoUsuario tu = new TipoUsuario();
        tu.setIdTipoUsuario(1);
        Usuario solicitanteEAutorizante = new Usuario();
        solicitanteEAutorizante.setNome("solicitanteEAutorizante");
        solicitanteEAutorizante.setTipoUsuario(tu);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        assertEquals(1, usuarioDAO.inserir(solicitanteEAutorizante));
        for (Iterator<Usuario> it = usuarioDAO.getUsuarios().iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getNome().equalsIgnoreCase(solicitanteEAutorizante.getNome())) {
                solicitanteEAutorizante = usuario;
                break;
            }
        }

        solicitacao.setAutorizante(solicitanteEAutorizante);
        solicitacao.setSolicitante(solicitanteEAutorizante);
        solicitacao.setVeiculo(v);
        assertEquals(1, instance.inserir(solicitacao));

        for (Iterator<SolicitacaoViagem> it = instance.getSolicitacoes().iterator(); it.hasNext();) {
            SolicitacaoViagem solicitacaoViagem = it.next();
            if (solicitacaoViagem.getAutorizante().getIdUsuario().intValue() == solicitanteEAutorizante.getIdUsuario().intValue() && solicitacaoViagem.getSolicitante().getIdUsuario().intValue() == solicitanteEAutorizante.getIdUsuario().intValue()) {
                solicitacao = solicitacaoViagem;
                break;
            }
        }
        assertEquals(1, instance.deletar(solicitacao));
        assertEquals(1, usuarioDAO.deletar(solicitanteEAutorizante));
        assertEquals(1, vdao.deletar(v));
    }

    /**
     * Test of alterar method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        SolicitacaoViagem solicitacao = null;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        SolicitacaoViagem s = solicitacao;

        try {
            instance.alterar(solicitacao);
            fail("Alterou uma instância sem id de SolicitacaoViagem");
        } catch (NullPointerException e) {
            assertEquals(s, solicitacao);
        }
    }

    /**
     * Test of deletar method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        SolicitacaoViagem solicitacaoViagem = null;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        /**
         * Forçando erro, pois não existem dado na instância.
         */
        SolicitacaoViagem s = solicitacaoViagem;
        try {
            assertEquals(1, instance.deletar(solicitacaoViagem));
            fail("Deletou uma instância sem id de SolicitacaoViagem");
        } catch (NullPointerException e) {
            assertEquals(s, solicitacaoViagem);
        }
    }

    /**
     * Test of getSolicitacoes method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testGetSolicitacoes() throws Exception {
        System.out.println("getSolicitacoes");

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        for (Iterator<SolicitacaoViagem> it = instance.getSolicitacoes().iterator(); it.hasNext();) {
            SolicitacaoViagem solicitacaoViagem = it.next();
            assertEquals(1, instance.deletar(solicitacaoViagem));
        }

        Veiculo v = new Veiculo();
        v.setPlaca("abc-8888");
        TipoVeiculo tv = new TipoVeiculo();
        tv.setIdTipoVeiculo(1);
        v.setTipoVeiculo(tv);

        VeiculoDAO vdao = new VeiculoDAO(connection);
        assertEquals(1, vdao.inserir(v));
        for (Iterator<Veiculo> it = vdao.getVeiculos().iterator(); it.hasNext();) {
            Veiculo veiculo = it.next();
            if (veiculo.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                v = veiculo;
                break;
            }
        }
        TipoUsuario tu = new TipoUsuario();
        tu.setIdTipoUsuario(1);
        Usuario solicitanteEAutorizante = new Usuario();
        solicitanteEAutorizante.setNome("solicitanteEAutorizante");
        solicitanteEAutorizante.setTipoUsuario(tu);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        assertEquals(1, usuarioDAO.inserir(solicitanteEAutorizante));
        for (Iterator<Usuario> it = usuarioDAO.getUsuarios().iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getNome().equalsIgnoreCase(solicitanteEAutorizante.getNome())) {
                solicitanteEAutorizante = usuario;
                break;
            }
        }

        SolicitacaoViagem sv1 = new SolicitacaoViagem();
        sv1.setAutorizante(solicitanteEAutorizante);
        sv1.setDataRetorno(new Date());
        sv1.setDataSaida(new Date());
        sv1.setHoraRetorno(new Date());
        sv1.setHoraSaida(new Date());
        sv1.setLocalRetorno("Bagé");
        sv1.setLocalSaida("Alegrete");
        sv1.setNumeroPedido(12345);
        sv1.setObjetivo("Passear");
        sv1.setPercurso("Alegrete - Rosário - Bagé");
        sv1.setServidores(true);
        sv1.setSolicitante(solicitanteEAutorizante);
        sv1.setStatus(StatusSolicitacaoViagem.SOLICITADO.toString());
        sv1.setVeiculo(v);
        assertEquals(1, instance.inserir(sv1));

        SolicitacaoViagem sv2 = new SolicitacaoViagem();
        sv2.setAutorizante(solicitanteEAutorizante);
        sv2.setDataRetorno(null);
        sv2.setDataSaida(null);
        sv2.setHoraRetorno(null);
        sv2.setHoraSaida(null);
        sv2.setLocalRetorno("Alegrete");
        sv2.setLocalSaida("Uruguaiana");
        sv2.setNumeroPedido(54321);
        sv2.setObjetivo("Viajar");
        sv2.setPercurso("Uruguaiana - Alegrete");
        sv2.setServidores(true);
        sv2.setSolicitante(solicitanteEAutorizante);
        sv2.setStatus(StatusSolicitacaoViagem.CANCELADO.toString());
        sv2.setVeiculo(v);
        assertEquals(1, instance.inserir(sv2));

        List<SolicitacaoViagem> solicitacoesViagem = instance.getSolicitacoes();
        SolicitacaoViagem sv11 = solicitacoesViagem.get(0);
        assertEquals(sv11.getAutorizante().getIdUsuario(), sv1.getAutorizante().getIdUsuario());
        assertEquals(sv11.getDataRetorno(), sv1.getDataRetorno());
        assertEquals(sv11.getDataSaida(), sv1.getDataSaida());
        assertEquals(0, sv11.getHoraRetorno().compareTo(sv1.getHoraRetorno()));
        assertEquals(sv11.getHoraSaida().getTime(), sv1.getHoraSaida().getTime());
        assertEquals(sv11.getLocalRetorno(), sv1.getLocalRetorno());
        assertEquals(sv11.getLocalSaida(), sv1.getLocalSaida());
        assertEquals(sv11.getNumeroPedido(), sv1.getNumeroPedido());
        assertEquals(sv11.getObjetivo(), sv1.getObjetivo());
        assertEquals(sv11.getPassageiros(), sv1.getPassageiros());
        assertEquals(sv11.getPercurso(), sv1.getPercurso());
        assertEquals(sv11.getServidores(), sv1.getServidores());
        assertEquals(sv11.getSolicitante().getIdUsuario(), sv1.getSolicitante().getIdUsuario());
        assertEquals(sv11.getStatus(), sv1.getStatus());
        assertEquals(sv11.getVeiculo().getIdVeiculo(), sv1.getVeiculo().getIdVeiculo());
        assertEquals(1, instance.deletar(sv11));

        SolicitacaoViagem sv22 = solicitacoesViagem.get(0);
        assertEquals(sv22.getAutorizante().getIdUsuario(), sv2.getAutorizante().getIdUsuario());
        assertEquals(sv22.getDataRetorno(), sv2.getDataRetorno());
        assertEquals(sv22.getDataSaida(), sv2.getDataSaida());
        assertEquals(sv22.getHoraRetorno(), sv2.getHoraRetorno());
        assertEquals(sv22.getHoraSaida(), sv2.getHoraSaida());
        assertEquals(sv22.getLocalRetorno(), sv2.getLocalRetorno());
        assertEquals(sv22.getLocalSaida(), sv2.getLocalSaida());
        assertEquals(sv22.getNumeroPedido(), sv2.getNumeroPedido());
        assertEquals(sv22.getObjetivo(), sv2.getObjetivo());
        assertEquals(sv22.getPassageiros(), sv2.getPassageiros());
        assertEquals(sv22.getPercurso(), sv2.getPercurso());
        assertEquals(sv22.getServidores(), sv2.getServidores());
        assertEquals(sv22.getSolicitante().getIdUsuario(), sv2.getSolicitante().getIdUsuario());
        assertEquals(sv22.getStatus(), sv2.getStatus());
        assertEquals(sv22.getVeiculo().getIdVeiculo(), sv2.getVeiculo().getIdVeiculo());
        assertEquals(1, instance.deletar(sv22));

        assertEquals(1, usuarioDAO.deletar(solicitanteEAutorizante));
        assertEquals(1, vdao.deletar(v));
    }

    @Test
    public void testCompareHora() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(337);
        Veiculo veiculo = new Veiculo();
        veiculo.setIdVeiculo(90);
        SolicitacaoViagem solicitacaoViagem = new SolicitacaoViagem();
        solicitacaoViagem.setIdSolicitacaoViagem(91);
        solicitacaoViagem.setAutorizante(usuario);
        solicitacaoViagem.setSolicitante(usuario);
        solicitacaoViagem.setVeiculo(veiculo);

        solicitacaoViagem.setHoraRetorno(new Date(System.currentTimeMillis()));

        SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
        svdao.alterar(solicitacaoViagem);
        SolicitacaoViagem s = svdao.getById(91);
        assertEquals(s.getHoraRetorno().getTime(), solicitacaoViagem.getHoraRetorno().getTime());
    }

    @Test
    public void testCompareHoraHardcore() {
        String sql = "update solicitacao_viagem set "
                + "hora_retorno=?, id_veiculo=?, id_responsavel_solicitacao=?, "
                + "id_responsavel_autorizante=? where id_solicitacao_viagem=? ";
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setTimestamp(1, timestamp1);
            stmt.setInt(2, 90);
            stmt.setInt(3, 337);
            stmt.setInt(4, 337);
            stmt.setInt(5, 91);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Timestamp timestamp2 = null;
        try {
            sql = "select * from solicitacao_viagem where id_solicitacao_viagem=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 91);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            rs.next();
            timestamp2 = rs.getTimestamp("hora_retorno");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
//        assertEquals(0, timestamp1.compareTo(timestamp2));
        assertEquals(timestamp1.getTime(), timestamp2.getTime());
    }

    /**
     * Test of getById method, of class SolicitacaoViagemDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        Integer id = 1;

        System.out.println("Testando a criação de Intâcia: 'SolicitacaoViagemDAO'");
        SolicitacaoViagemDAO instance = new SolicitacaoViagemDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'SolicitacaoViagemDAO' não pode ser criada! <<<", instance);

        SolicitacaoViagem expResult = null;
        SolicitacaoViagem result = instance.getById(id);
        assertEquals(expResult, result);
    }
}