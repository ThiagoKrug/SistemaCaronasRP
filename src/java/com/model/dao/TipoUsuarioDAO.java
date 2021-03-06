package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.TipoUsuario;
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
public class TipoUsuarioDAO implements Dao {

    private Connection connection;

    public TipoUsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TipoUsuario getById(Integer id) {
        String sql = "select * from tipo_usuario where id_tipo_usuario=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoUsuario usuario = new TipoUsuario();
                usuario.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TipoUsuario getMotorista() {
        String sql = "select * from tipo_usuario where tipo_usuario like ?";
        TipoUsuario tipoUsuario = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, "%motorista%");
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            tipoUsuario = new TipoUsuario();
            tipoUsuario.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
            tipoUsuario.setTipoUsuario(rs.getString("tipo_usuario"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoUsuario;
    }
    
    public TipoUsuario getAdministrador() {
        String sql = "select * from tipo_usuario where tipo_usuario like ?";
        TipoUsuario tipoUsuario = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, "%Administrador%");
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            tipoUsuario = new TipoUsuario();
            tipoUsuario.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
            tipoUsuario.setTipoUsuario(rs.getString("tipo_usuario"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoUsuario;
    }

    public List<TipoUsuario> getTiposUsuarios() {
        String sql = "select * from tipo_usuario";
        List<TipoUsuario> usuarios = new ArrayList<TipoUsuario>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoUsuario usuario = new TipoUsuario();
                usuario.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public int inserir(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int alterar(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletar(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
