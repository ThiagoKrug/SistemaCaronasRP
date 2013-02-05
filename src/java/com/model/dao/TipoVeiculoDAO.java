package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.Entity;
import com.model.entity.TipoVeiculo;
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
public class TipoVeiculoDAO implements Dao {

    private Connection connection;

    public TipoVeiculoDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public TipoVeiculo getById(Integer id) {
        String sql = "select * from tipo_veiculo where id_tipo_veiculo=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoVeiculo usuario = new TipoVeiculo();
                usuario.setId(rs.getInt("id_tipo_veiculo"));
                usuario.setTipoVeiculo(rs.getString("tipo_veiculo"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TipoVeiculo> getTiposVeiculos() {
        String sql = "select * from tipo_veiculo";
        List<TipoVeiculo> tipos = new ArrayList<TipoVeiculo>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoVeiculo tipo = new TipoVeiculo();
                tipo.setId(rs.getInt("id_tipo_veiculo"));
                tipo.setTipoVeiculo(rs.getString("tipo_veiculo"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
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
