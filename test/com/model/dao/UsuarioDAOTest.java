package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.TipoUsuario;
import com.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
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
public class UsuarioDAOTest {

    Connection connection;

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
     * Test of getById method, of class UsuarioDAO.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");

        System.out.println("Testando a criação de Instâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);

        TipoUsuario tu = new TipoUsuario();
        tu.setIdTipoUsuario(1);
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(tu);
        usuario.setNome("TesteGetById");
        instance.inserir(usuario);

        for (Iterator<Usuario> it = instance.getUsuarios().iterator(); it.hasNext();) {
            Usuario u = it.next();
            if (u.getNome() != null) {
                if (u.getNome().equalsIgnoreCase(usuario.getNome())) {
                    usuario = u;
                    break;
                }
            }
        }

        Usuario result = instance.getById(usuario.getIdUsuario());
        assertEquals(usuario.getEmail(), result.getEmail());
        assertEquals(usuario.getIdUsuario(), result.getIdUsuario());
        assertEquals(usuario.getNome(), result.getNome());
        assertEquals(usuario.getNumeroServidor(), result.getNumeroServidor());
        assertEquals(usuario.getRg(), result.getRg());
        assertEquals(usuario.getSenha(), result.getSenha());
        assertEquals(usuario.getTelefone(), result.getTelefone());
        assertEquals(usuario.getTipoUsuario().getIdTipoUsuario(), result.getTipoUsuario().getIdTipoUsuario());
        assertEquals(usuario.getUsername(), result.getUsername());
    }

    /**
     * Test of getUsuarios method, of class UsuarioDAO.
     */
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");

        System.out.println("Testando a criação de Instâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);

        for (Iterator<Usuario> it = instance.getUsuarios().iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            instance.deletar(usuario);
        }

        TipoUsuario admin = new TipoUsuario();
        admin.setIdTipoUsuario(1);
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("email1");
        usuario1.setNome("usuario1");
        usuario1.setNumeroServidor("12345");
        usuario1.setRg("0987654321");
        usuario1.setSenha("1111");
        usuario1.setTelefone("96968787");
        usuario1.setTipoUsuario(admin);
        usuario1.setUsername("user1");
        instance.inserir(usuario1);

        TipoUsuario solicitante = new TipoUsuario();
        solicitante.setIdTipoUsuario(2);
        Usuario usuario2 = new Usuario();
        usuario2.setEmail("email2");
        usuario2.setNome("usuario2");
        usuario2.setNumeroServidor("54321");
        usuario2.setRg("1234567890");
        usuario2.setSenha("2222");
        usuario2.setTelefone("65748392");
        usuario2.setTipoUsuario(solicitante);
        usuario2.setUsername("user2");
        instance.inserir(usuario2);

        TipoUsuario motorista = new TipoUsuario();
        motorista.setIdTipoUsuario(3);
        Usuario usuario3 = new Usuario();
        usuario3.setEmail("email3");
        usuario3.setNome("usuario3");
        usuario3.setNumeroServidor("98765");
        usuario3.setRg("0912873465");
        usuario3.setSenha("3333");
        usuario3.setTelefone("92939495");
        usuario3.setTipoUsuario(motorista);
        usuario3.setUsername("user3");
        instance.inserir(usuario3);

        List<Usuario> usuarios = instance.getUsuarios();
        Usuario u1 = usuarios.get(0);
        Usuario u2 = usuarios.get(1);
        Usuario u3 = usuarios.get(2);

        assertEquals(usuario1.getEmail(), u1.getEmail());
        assertEquals(usuario1.getNome(), u1.getNome());
        assertEquals(usuario1.getNumeroServidor(), u1.getNumeroServidor());
        assertEquals(usuario1.getRg(), u1.getRg());
        assertEquals(usuario1.getSenha(), u1.getSenha());
        assertEquals(usuario1.getTelefone(), u1.getTelefone());
        assertEquals(usuario1.getTipoUsuario().getIdTipoUsuario(), u1.getTipoUsuario().getIdTipoUsuario());
        assertEquals(usuario1.getUsername(), u1.getUsername());

        assertEquals(usuario2.getEmail(), u2.getEmail());
        assertEquals(usuario2.getNome(), u2.getNome());
        assertEquals(usuario2.getNumeroServidor(), u2.getNumeroServidor());
        assertEquals(usuario2.getRg(), u2.getRg());
        assertEquals(usuario2.getSenha(), u2.getSenha());
        assertEquals(usuario2.getTelefone(), u2.getTelefone());
        assertEquals(usuario2.getTipoUsuario().getIdTipoUsuario(), u2.getTipoUsuario().getIdTipoUsuario());
        assertEquals(usuario2.getUsername(), u2.getUsername());

        assertEquals(usuario3.getEmail(), u3.getEmail());
        assertEquals(usuario3.getNome(), u3.getNome());
        assertEquals(usuario3.getNumeroServidor(), u3.getNumeroServidor());
        assertEquals(usuario3.getRg(), u3.getRg());
        assertEquals(usuario3.getSenha(), u3.getSenha());
        assertEquals(usuario3.getTelefone(), u3.getTelefone());
        assertEquals(usuario3.getTipoUsuario().getIdTipoUsuario(), u3.getTipoUsuario().getIdTipoUsuario());
        assertEquals(usuario3.getUsername(), u3.getUsername());
    }

    /**
     * Test of inserir method, of class UsuarioDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Usuario usuario = new Usuario();

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);

        TipoUsuario tu = new TipoUsuario();
        tu.setIdTipoUsuario(1);
        usuario.setTipoUsuario(tu);
        assertEquals(1, instance.inserir(usuario));
    }

    /**
     * Test of alterar method, of class UsuarioDAO.
     */
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        Usuario usuario = new Usuario();

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO(this.connection);
        assertNotNull(">>> A instância da Classe 'UsuarioDAO' não pode ser criada! <<<", instance);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        Usuario u = usuario;

        try {
            instance.alterar(usuario);
            fail("Alterou uma instância sem id de Usuário");
        } catch (NullPointerException e) {
            assertEquals(u, usuario);
        }
    }

    /**
     * Test of deletar method, of class UsuarioDAO.
     */
    @Test
    public void testDeletar() {
        System.out.println("deletar");
        Usuario usuario = new Usuario();

        System.out.println("Testando a criação de Intâcia: 'UsuarioDAO'");
        UsuarioDAO instance = new UsuarioDAO(this.connection);
        /**
         * Forçando erro, pois não existem dado na instância.
         */
        Usuario u = usuario;
        try {
            assertEquals(1, instance.deletar(usuario));
            fail("Deletou uma instância sem id de Usuário");
        } catch (NullPointerException e) {
            assertEquals(u, usuario);
        }
    }
}