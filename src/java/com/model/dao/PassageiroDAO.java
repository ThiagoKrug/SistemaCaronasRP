package com.model.dao;

import com.jdbc.ConnectionFactory;
import com.model.entity.Entity;
import com.model.entity.Passageiro;
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
public class PassageiroDAO implements Dao {

    private class PassageiroFields {

        public static final String id = "id_passageiro";
        public static final String nome = "nome";
        public static final String rg = "rg";
        public static final String telefone = "telefone";

        public String getTuple() {
            return "(" + PassageiroFields.rg + ", " + PassageiroFields.nome + ", " + PassageiroFields.telefone + ")";
        }
    }
    private Connection connection;

    public PassageiroDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int inserir(Entity entity) {
        Passageiro passageiro = (Passageiro) entity;
        int result = 0;
        String sql = "insert into passageiro "
                + new PassageiroFields().getTuple() + " values (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, passageiro.getRg());
            stmt.setString(2, passageiro.getNome());
            stmt.setString(3, passageiro.getTelefone());
            result = stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Passageiro> getPassageiros() {
        try {
            List<Passageiro> passageiros = new ArrayList<Passageiro>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from passageiro");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
                passageiro.setNome(rs.getString(PassageiroFields.nome));
                passageiro.setRg(rs.getString(PassageiroFields.rg));
                passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
                passageiros.add(passageiro);

            }
            rs.close();
            stmt.close();
            return passageiros;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int alterar(Entity entity) {
        Passageiro passageiro = (Passageiro) entity;
        int result = 0;
        String sql = "update passageiro set nome=?,"
                + "rg=?, telefone=? where id_passageiro=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getRg());
            stmt.setString(3, passageiro.getTelefone());
            stmt.setInt(4, passageiro.getIdPassageiro());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deletar(Entity entity) {
        Passageiro passageiro = (Passageiro) entity;
        int result = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from passageiro where id_passageiro=?");
            stmt.setInt(1, passageiro.getIdPassageiro());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Passageiro getById(Integer id) {
        try {
            List<Passageiro> passageiros = new ArrayList<Passageiro>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from passageiro where id_passageiro=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
                passageiro.setNome(rs.getString(PassageiroFields.nome));
                passageiro.setRg(rs.getString(PassageiroFields.rg));
                passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
                passageiros.add(passageiro);

            }
            rs.close();
            stmt.close();
            return passageiros.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Passageiro> getByNome(String nome) {
        List<Passageiro> passageiros = new ArrayList<Passageiro>();
        try {

            PreparedStatement stmt = this.connection.prepareStatement(
                    "select * from passageiro where nome=?");
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
                passageiro.setNome(rs.getString(PassageiroFields.nome));
                passageiro.setRg(rs.getString(PassageiroFields.rg));
                passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
                passageiros.add(passageiro);

            }
            rs.close();
            stmt.close();
            //return passageiros;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passageiros;

    }

    public Passageiro getByRG(String nome) {
        try {

            PreparedStatement stmt = this.connection.prepareStatement(
                    "select * from passageiro where rg=?");
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
                passageiro.setNome(rs.getString(PassageiroFields.nome));
                passageiro.setRg(rs.getString(PassageiroFields.rg));
                passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
                rs.close();
                stmt.close();
                return passageiro;

            }
            rs.close();
            stmt.close();
            //return passageiros;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
