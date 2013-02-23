package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.Passageiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        public static final String endereco = "endereco";

        public String getTuple() {
            return "(" + PassageiroFields.rg + ", " + PassageiroFields.nome + ", " + PassageiroFields.telefone + ", " + PassageiroFields.endereco + ")";
        }
    }
    private Connection connection;

    public PassageiroDAO(Connection connection) {
        this.connection = connection;
    }

    public Passageiro inserirGetID(Passageiro passageiro) throws Exception {
        int result = 0;
        String sql = "insert into passageiro "
                + new PassageiroFields().getTuple() + " values (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, passageiro.getRg());
            stmt.setString(2, passageiro.getNome());
            stmt.setString(3, passageiro.getTelefone());
            stmt.setString(4, passageiro.getEndereco());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            if (result == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                passageiro.setIdPassageiro(rs.getInt(1));
            } else {
                throw new Exception("Não conseguiu inserir o passageiro!");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passageiro;
    }

    @Override
    public int inserir(Entity entity) {
        Passageiro passageiro = (Passageiro) entity;
        int result = 0;
        String sql = "insert into passageiro "
                + new PassageiroFields().getTuple() + " values (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, passageiro.getRg());
            stmt.setString(2, passageiro.getNome());
            stmt.setString(3, passageiro.getTelefone());
            stmt.setString(4, passageiro.getEndereco());
            System.out.println(stmt.toString());
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
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
                passageiro.setNome(rs.getString(PassageiroFields.nome));
                passageiro.setRg(rs.getString(PassageiroFields.rg));
                passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
                passageiro.setEndereco(rs.getString(PassageiroFields.endereco));
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
                + "rg=?, telefone=?, endereco=? where id_passageiro=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getRg());
            stmt.setString(3, passageiro.getTelefone());
            stmt.setString(4, passageiro.getEndereco());
            stmt.setInt(5, passageiro.getIdPassageiro());
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
        Passageiro passageiro = (Passageiro) entity;
        int result = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from passageiro where id_passageiro=?");
            stmt.setInt(1, passageiro.getIdPassageiro());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Passageiro getById(Integer id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from passageiro where id_passageiro=?");
            stmt.setInt(1, id);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            Passageiro passageiro = new Passageiro();
            passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
            passageiro.setNome(rs.getString(PassageiroFields.nome));
            passageiro.setRg(rs.getString(PassageiroFields.rg));
            passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
            passageiro.setEndereco(rs.getString(PassageiroFields.endereco));

            rs.close();
            stmt.close();
            return passageiro;
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
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
                passageiro.setNome(rs.getString(PassageiroFields.nome));
                passageiro.setRg(rs.getString(PassageiroFields.rg));
                passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
                passageiro.setEndereco(rs.getString(PassageiroFields.endereco));
                passageiros.add(passageiro);

            }
            rs.close();
            stmt.close();

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
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            Passageiro passageiro = new Passageiro();
            passageiro.setIdPassageiro(rs.getInt(PassageiroFields.id));
            passageiro.setNome(rs.getString(PassageiroFields.nome));
            passageiro.setRg(rs.getString(PassageiroFields.rg));
            passageiro.setTelefone(rs.getString(PassageiroFields.telefone));
            passageiro.setEndereco(rs.getString(PassageiroFields.endereco));
            rs.close();
            stmt.close();
            return passageiro;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
