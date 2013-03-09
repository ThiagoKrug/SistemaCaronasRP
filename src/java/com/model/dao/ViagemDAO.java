package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.Passageiro;
import com.model.entity.SolicitacaoViagem;
import com.model.entity.StatusSolicitacaoViagem;
import com.model.entity.Usuario;
import com.model.entity.Veiculo;
import com.model.entity.Viagem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ViagemDAO implements Dao {

    private Connection connection;

    @Override
    public int inserir(Entity entity) {
        Viagem viagem = (Viagem) entity;
        int result = 0;
        String sql = "insert into viagem "
                + "(id_autorizante, id_motorista, id_veiculo, "
                + "data_efetivacao) values (?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, viagem.getAutorizante().getIdUsuario());
            stmt.setInt(2, viagem.getMotorista().getIdUsuario());
            stmt.setInt(3, viagem.getVeiculo().getIdVeiculo());
            stmt.setDate(4, new Date(viagem.getDataEfetivacao().getTime()));
            result = stmt.executeUpdate();
            ResultSet rsid = stmt.getGeneratedKeys();
            rsid.next();
            Integer gid = rsid.getInt(1);
            stmt.close();
            SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(this.connection);
            for (SolicitacaoViagem sol : viagem.getSolicitacoes(this.connection)) {
                try {
                    sol.setStatus(StatusSolicitacaoViagem.EFETIVADO.toString());
                    svdao.alterar(sol);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            String sql2 = "insert into viagem_has_passageiro "
                    + "(id_passageiro, id_viagem) values "
                    + "(?,?)";
            for (Passageiro pas : viagem.getPassageiros(this.connection)) {
                PreparedStatement stmt2 = this.connection.prepareStatement(sql2);
                stmt2.setInt(1, viagem.getIdViagem());
                stmt2.setInt(2, pas.getIdPassageiro());
                stmt2.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int alterar(Entity entity) {
        Viagem viagem = (Viagem) entity;
        int result = 0;
        String sql = "update viagem set "
                + "id_autorizante=?, id_motorista=?, id_veiculo=?, "
                + "data_efetivacao=? "
                + "where id_viagem=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, viagem.getAutorizante().getIdUsuario());
            stmt.setInt(2, viagem.getMotorista().getIdUsuario());
            stmt.setInt(3, viagem.getVeiculo().getIdVeiculo());
            stmt.setDate(4, new Date(viagem.getDataEfetivacao().getTime()));
            stmt.setInt(5, viagem.getIdViagem());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deletar(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Integer> getPassIds(Integer id) {
        String sql = "select id_passageiro from "
                + "viagem_has_passageiro where "
                + "id_viagem=?";
        List<Integer> result = new ArrayList<Integer>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(res.getInt("id_passageiro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Integer> getSolIds(Integer id) {
        List<Integer> ids = new ArrayList<Integer>();
        String sql = "select id_solicitacao_viagem from "
                + "solicitacao_viagem where id_viagem=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                ids.add(res.getInt("id_solicitacao_viagem"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public List<Viagem> getByIdVeiculo(Integer idVeiculo) {
        String sql = "select * from viagem where id_veiculo=?";
        List<Viagem> viagens = new ArrayList<Viagem>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Viagem viagem = new Viagem();

                Usuario autorizante = new Usuario();
                autorizante.setIdUsuario(rs.getInt("id_autorizante"));
                viagem.setAutorizante(autorizante);

                Calendar c = Calendar.getInstance();
                Date dataEfetivacao = rs.getDate("data_efetivacao");
                if (dataEfetivacao != null) {
                    c.setTime(dataEfetivacao);
                    viagem.setDataEfetivacao(c.getTime());
                }

                viagem.setIdViagem(rs.getInt("id_viagem"));

                Usuario motorista = new Usuario();
                motorista.setIdUsuario(rs.getInt("id_motorista"));
                viagem.setMotorista(motorista);

                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("id_veiculo"));
                viagem.setVeiculo(veiculo);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }
}
