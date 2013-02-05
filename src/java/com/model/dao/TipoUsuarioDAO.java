package com.model.dao;

import com.jdbc.ConnectionFactory;
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

    public TipoUsuarioDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public TipoUsuario getById(Integer id) {
        String sql = "select * from tipo_usuario where id_tipo_usuario=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoUsuario usuario = new TipoUsuario();
                usuario.setId(rs.getInt("id_tipo_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TipoUsuario> getTiposUsuarios() {
        String sql = "select * from tipo_usuario";
        List<TipoUsuario> usuarios = new ArrayList<TipoUsuario>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoUsuario usuario = new TipoUsuario();
                usuario.setId(rs.getInt("id_tipo_usuario"));
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
