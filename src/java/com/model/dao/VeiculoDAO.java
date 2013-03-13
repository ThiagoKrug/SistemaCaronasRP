package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class VeiculoDAO implements Dao {

    private Connection connection;

    public VeiculoDAO(Connection connection) {
//        try {
//            this.connection = new ConnectionFactory().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        this.connection = connection;
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

            stmt.setInt(1, veiculo.getTipoVeiculo().getIdTipoVeiculo());
            stmt.setString(2, veiculo.getPlaca());
            if (veiculo.getQuilometragem() != null) {
                stmt.setFloat(3, veiculo.getQuilometragem());
            } else {
                stmt.setString(3, null);
            }
            if (veiculo.getCapacidadePassageiro() != null) {
                stmt.setInt(4, veiculo.getCapacidadePassageiro());
            } else {
                stmt.setString(4, null);
            }
            stmt.setString(5, veiculo.getCor());
            System.out.println(stmt.toString());
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
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            Veiculo veiculo = new Veiculo();
            veiculo.setCapacidadePassageiro(rs.getInt("capacidade_passageiro"));
            veiculo.setCor(rs.getString("cor"));
            veiculo.setIdVeiculo(rs.getInt("id"));
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setQuilometragem(rs.getFloat("quilometragem"));
            Integer tid = rs.getInt("id_tipo_veiculo");
            veiculo.setTipoVeiculo(new TipoVeiculoDAO(this.connection).getById(tid));
            rs.close();
            stmt.close();
            return veiculo;

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
            stmt.setInt(1, veiculo.getTipoVeiculo().getIdTipoVeiculo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setFloat(3, veiculo.getQuilometragem());
            stmt.setInt(4, veiculo.getCapacidadePassageiro());
            stmt.setString(5, veiculo.getCor());
            stmt.setInt(6, veiculo.getIdVeiculo());
            System.out.println(stmt.toString());
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
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCapacidadePassageiro(rs.getInt("capacidade_passageiro"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setIdVeiculo(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setQuilometragem(rs.getFloat("quilometragem"));
                veiculo.setTipoVeiculo(new TipoVeiculoDAO(this.connection).getById(rs.getInt("id_tipo_veiculo")));
                veiculos.add(veiculo);
            }
            rs.close();
            stmt.close();
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
            stmt.setInt(1, veiculo.getIdVeiculo());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(stmt.toString());
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
    
    
    public List<Veiculo> getByQuilometragem() {
        List<Veiculo> veis = this.getVeiculos();
        Collections.sort(veis);
        return veis;
    }
}
