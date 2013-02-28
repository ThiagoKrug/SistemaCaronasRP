package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.TipoUsuario;
import com.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO implements Dao {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
//        try {
//            this.connection = new ConnectionFactory().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        this.connection = connection;
    }

    @Override
    public Usuario getById(Integer id) {
        String sql = "select * from usuario where id_usuario=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            Usuario usuario = new Usuario();
            usuario.setEmail(rs.getString("email"));
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setNumeroServidor(rs.getString("numero_servidor"));
            usuario.setRg(rs.getString("rg"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setUsername(rs.getString("nome_usuario"));
            usuario.setSituacao(rs.getString("situacao"));
            Integer tipo_id = rs.getInt("id_tipo_usuario");
            usuario.setTipoUsuario(new TipoUsuarioDAO(this.connection).getById(tipo_id));
            rs.close();
            stmt.close();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        String sql = "select * from usuario";
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(rs.getString("email"));
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setNumeroServidor(rs.getString("numero_servidor"));
                usuario.setRg(rs.getString("rg"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setUsername(rs.getString("nome_usuario"));
                usuario.setSituacao(rs.getString("situacao"));

                TipoUsuario tipo = new TipoUsuarioDAO(this.connection).getById(rs.getInt("id_tipo_usuario"));

                usuario.setTipoUsuario(tipo);
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public int inserir(Entity entity) {
        Usuario usuario = (Usuario) entity;
        int result = 0;
        String sql = "insert into usuario ("
                + "id_tipo_usuario, nome, rg, nome_usuario, numero_servidor, "
                + "senha, telefone, email, situacao) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, usuario.getTipoUsuario().getIdTipoUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getRg());
            stmt.setString(4, usuario.getUsername());
            stmt.setString(5, usuario.getNumeroServidor());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, usuario.getTelefone());
            stmt.setString(8, usuario.getEmail());
            stmt.setString(9, usuario.getSituacao());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int alterar(Entity entity) {
        Usuario usuario = (Usuario) entity;
        int result = 0;
        String sql = "update usuario set "
                + "id_tipo_usuario=?, nome=?, rg=?, nome_usuario=?, numero_servidor=?, "
                + "senha=?, telefone=?, email=?, situacao=? where id_usuario=?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, usuario.getTipoUsuario().getIdTipoUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getRg());
            stmt.setString(4, usuario.getUsername());
            stmt.setString(5, usuario.getNumeroServidor());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, usuario.getTelefone());
            stmt.setString(8, usuario.getEmail());
            stmt.setString(9, usuario.getSituacao());
            stmt.setInt(10, usuario.getIdUsuario());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deletar(Entity entity) {
        Usuario usuario = (Usuario) entity;
        int result = 0;
        String sql = "delete from usuario where id_usuario=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getIdUsuario());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public int mudarSituacao(Entity entity) {
        Usuario usuario = (Usuario) entity;
        if (usuario.getSituacao().equals("ativo")) {
            usuario.setSituacao("inativo");
        }
        else {
            usuario.setSituacao("ativo");
        }
        return this.alterar(usuario);
    }
}
