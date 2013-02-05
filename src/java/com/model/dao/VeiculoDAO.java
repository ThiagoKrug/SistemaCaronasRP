package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.Entity;
import com.model.entity.Veiculo;
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
public class VeiculoDAO implements Dao {

    private Connection connection;

    public VeiculoDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int inserir(Entity entity) {
        Veiculo veiculo = (Veiculo) entity;
        int result = 0;
        String sql = "insert into veiculo (id_tipo_veiculo, "
                + "placa, quilometragem, capacidade_passageiro, "
                + "cor) values (?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, veiculo.getTipoVeiculo().getId());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setFloat(3, veiculo.getQuilometragem());
            stmt.setInt(4, veiculo.getCapacidadePassageiro());
            stmt.setString(5, veiculo.getCor());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Entity getById(Integer id) {
        String sql = "select * from veiculo where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCapacidadePassageiro(rs.getInt("capacidade_passageiro"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setQuilometragem(rs.getFloat("quilometragem"));
                Integer tid = rs.getInt("id_tipo_veiculo");
                veiculo.setTipoVeiculo(new TipoVeiculoDAO().getById(tid));
                return veiculo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int alterar(Entity entity) {
        Veiculo veiculo = (Veiculo) entity;
        int result = 0;
        String sql = "update veiculo set id_tipo_veiculo=?, placa=?,"
                + "quilometragem=?, capacidade_passageiro=?, cor=? where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getTipoVeiculo().getId());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setFloat(3, veiculo.getQuilometragem());
            stmt.setInt(4, veiculo.getCapacidadePassageiro());
            stmt.setString(5, veiculo.getCor());
            stmt.setInt(6, veiculo.getId());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Veiculo> getVeiculos() {
        String sql = "select * from veiculo";
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCapacidadePassageiro(rs.getInt("capacidade_passageiro"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setQuilometragem(rs.getFloat("quilometragem"));
                veiculo.setTipoVeiculo(new TipoVeiculoDAO().getById(rs.getInt("id_tipo_veiculo")));
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    @Override
    public int deletar(Entity entity) {
        Veiculo veiculo = (Veiculo) entity;
        int result = 0;
        String sql = "delete from veiculo where id=?";

        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            stmt.setInt(1, veiculo.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
