package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.Passageiro;
import com.model.entity.SolicitacaoViagem;
import com.model.entity.TipoVeiculo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class SolicitacaoViagemDAO implements Dao {

    private Connection connection;

    public SolicitacaoViagemDAO(Connection connection) {
//        try {
//            this.connection = new ConnectionFactory().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        this.connection = connection;
    }

    @Override
    public int inserir(Entity entity) {
        SolicitacaoViagem solicitacao = (SolicitacaoViagem) entity;
        int result = 0;
        String sql = "insert into solicitacao_viagem "
                + "(numero_pedido, numero_transportados, servidores, data_saida, hora_saida, "
                + "local_saida, data_retorno, hora_retorno, local_retorno, "
                + "percurso, objetivo_viagem, id_tipo_veiculo, id_responsavel_solicitacao, "
                + "id_responsavel_autorizante, status) values (?,?,?,?,?,?,?,?,?,?,?,"
                + "?, ?, ?, ?)";

        String sql2 = "insert into passageiro_solicitacao_viagem "
                + "(id_passageiro, id_solicitacao_viagem) values (?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (solicitacao.getNumeroPedido() != null) {
                stmt.setInt(1, solicitacao.getNumeroPedido());
            } else {
                stmt.setString(1, null);
            }
            if (solicitacao.getNumeroTransportados() != null) {
                stmt.setInt(2, solicitacao.getNumeroTransportados());
            } else {
                stmt.setString(2, null);
            }
            if (solicitacao.getServidores() != null) {
                stmt.setBoolean(3, solicitacao.getServidores());
            } else {
                stmt.setString(3, null);
            }
            if (solicitacao.getDataSaida() != null) {
                stmt.setDate(4, new Date(solicitacao.getDataSaida().getTime()));
            } else {
                stmt.setString(4, null);
            }
            if (solicitacao.getHoraSaida() != null) {
                stmt.setTimestamp(5, new Timestamp(solicitacao.getHoraSaida().getTime()));
            } else {
                stmt.setString(5, null);
            }
            stmt.setString(6, solicitacao.getLocalSaida());
            if (solicitacao.getDataRetorno() != null) {
                stmt.setDate(7, new Date(solicitacao.getDataRetorno().getTime()));
            } else {
                stmt.setString(7, null);
            }
            if (solicitacao.getHoraRetorno() != null) {
                stmt.setTimestamp(8, new Timestamp(solicitacao.getHoraRetorno().getTime()));
            } else {
                stmt.setString(8, null);
            }
            stmt.setString(9, solicitacao.getLocalRetorno());
            stmt.setString(10, solicitacao.getPercurso());
            stmt.setString(11, solicitacao.getObjetivo());
            stmt.setInt(12, solicitacao.getTipoVeiculo().getIdTipoVeiculo());
            stmt.setInt(13, solicitacao.getSolicitante().getIdUsuario());
            if (solicitacao.getAutorizante() == null) {
                stmt.setString(14, null);
            } else {
                stmt.setInt(14, solicitacao.getAutorizante().getIdUsuario());
            }
            stmt.setString(15, solicitacao.getStatus());

            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            ResultSet rsid = stmt.getGeneratedKeys();
            rsid.next();
            Integer gid = rsid.getInt(1);
            stmt.close();

            List<Passageiro> passageiros = solicitacao.getPassageiros();
            this.cadastraPassageiros(passageiros);
            this.linkPassageiros(passageiros, gid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void cadastraPassageiros(List<Passageiro> pass) {
        for (Passageiro p : pass) {
            if (p.getIdPassageiro() == null) {
                new PassageiroDAO(this.connection).inserir(p);
            }
        }
    }

    private void linkPassageiros(List<Passageiro> pass, Integer id)
            throws SQLException {
        String sql_insert = "insert into passageiro_solicitacao_viagem ("
                + "id_passageiro, id_solicitacao_viagem) values (?,?)";
        String sql_del = "delete from passageiro_solicitacao_viagem"
                + " where id_solicitacao_viagem=?";
        PreparedStatement st_del = this.connection.prepareStatement(sql_del);
        st_del.setInt(1, id);
        System.out.println(st_del.toString());
        st_del.executeUpdate();
        st_del.close();
        for (Passageiro p : pass) {
            PreparedStatement st_insert = this.connection.prepareStatement(
                    sql_insert);
            st_insert.setInt(1, p.getIdPassageiro());
            st_insert.setInt(2, id);
            System.out.println(st_insert.toString());
            st_insert.executeUpdate();
            st_insert.close();
        }
    }

    @Override
    public int alterar(Entity entity) {
        SolicitacaoViagem solicitacao = (SolicitacaoViagem) entity;
        int result = 0;
        String sql = "update solicitacao_viagem set "
                + "numero_pedido=?, numero_transportados=?, servidores=?, data_saida=?, hora_saida=?, "
                + "local_saida=?, data_retorno=?, hora_retorno=?, local_retorno=?, "
                + "percurso=?, objetivo_viagem=?, id_tipo_veiculo=?, id_responsavel_solicitacao=?, "
                + "id_responsavel_autorizante=?, status=? where id_solicitacao_viagem=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            if (solicitacao.getNumeroPedido() == null) {
                stmt.setString(1, null);
            } else {
                stmt.setInt(1, solicitacao.getNumeroPedido());
            }
            if (solicitacao.getNumeroTransportados() == null) {
                stmt.setString(2, null);
            } else {
                stmt.setInt(2, solicitacao.getNumeroTransportados());
            }
            if (solicitacao.getServidores() == null) {
                stmt.setString(3, null);
            } else {
                stmt.setBoolean(3, solicitacao.getServidores());
            }
            if (solicitacao.getDataSaida() == null) {
                stmt.setDate(4, null);
            } else {
                stmt.setDate(4, new Date(solicitacao.getDataSaida().getTime()));
            }
            if (solicitacao.getHoraSaida() == null) {
                stmt.setDate(5, null);
            } else {
                stmt.setTimestamp(5, new Timestamp(solicitacao.getHoraSaida().getTime()));
            }
            stmt.setString(6, solicitacao.getLocalSaida());
            if (solicitacao.getDataRetorno() == null) {
                stmt.setDate(7, null);
            } else {
                stmt.setDate(7, new Date(solicitacao.getDataRetorno().getTime()));
            }
            if (solicitacao.getHoraRetorno() == null) {
                stmt.setDate(8, null);
            } else {
                System.out.println(solicitacao.getHoraRetorno().getTime());
                Timestamp t = new Timestamp(solicitacao.getHoraRetorno().getTime());
                System.out.println(t.getTime());
                stmt.setTimestamp(8, t);
            }
            stmt.setString(9, solicitacao.getLocalRetorno());
            stmt.setString(10, solicitacao.getPercurso());
            stmt.setString(11, solicitacao.getObjetivo());
            stmt.setInt(12, solicitacao.getTipoVeiculo().getIdTipoVeiculo());
            stmt.setInt(13, solicitacao.getSolicitante().getIdUsuario());
            if (solicitacao.getAutorizante() == null) {
                stmt.setString(14, null);
            } else {
                stmt.setInt(14, solicitacao.getAutorizante().getIdUsuario());
            }
            stmt.setString(15, solicitacao.getStatus());
            stmt.setInt(16, solicitacao.getIdSolicitacaoViagem());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            stmt.close();

            List<Passageiro> passageiros = solicitacao.getPassageiros();
            cadastraPassageiros(passageiros);
            linkPassageiros(passageiros, solicitacao.getIdSolicitacaoViagem());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deletar(Entity entity) {
        SolicitacaoViagem solicitacao = (SolicitacaoViagem) entity;
        int result = 0;
        String sql = "delete from solicitacao_viagem where id_solicitacao_viagem=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, solicitacao.getIdSolicitacaoViagem());
            System.out.println(stmt.toString());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<SolicitacaoViagem> getSolicitacoes() {
        String sql = "select * from solicitacao_viagem";
        List<SolicitacaoViagem> solicitacoes = new ArrayList<SolicitacaoViagem>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs.next())) {
                SolicitacaoViagem sv = this.setsFromDatabase(rs);
                solicitacoes.add(sv);
            }
            return solicitacoes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SolicitacaoViagem getById(Integer id) {
        String sql = "select * from solicitacao_viagem where id_solicitacao_viagem=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs.next())) {
                return this.setsFromDatabase(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private SolicitacaoViagem setsFromDatabase(ResultSet rs) {
        String sql2 = "select * from passageiro_solicitacao_viagem where "
                + "id_solicitacao_viagem = ?";
        try {
            SolicitacaoViagem sv = new SolicitacaoViagem();
            sv.setIdSolicitacaoViagem(rs.getInt("id_solicitacao_viagem"));
            Calendar c = Calendar.getInstance();
            Date dataSaida = rs.getDate("data_saida");
            if (dataSaida != null) {
                c.setTime(dataSaida);
                sv.setDataSaida(c.getTime());
            }
            Timestamp horaSaida = rs.getTimestamp("hora_saida");
            if (horaSaida != null) {
                sv.setHoraSaida(horaSaida);
            }
            c = Calendar.getInstance();
            Date dataRetorno = rs.getDate("data_retorno");
            if (dataRetorno != null) {
                c.setTime(dataRetorno);
                sv.setDataRetorno(c.getTime());
            }
            Timestamp horaRetorno = rs.getTimestamp("hora_retorno");
            if (horaRetorno != null) {
                System.out.println(horaRetorno.getTime());
                sv.setHoraRetorno(horaRetorno);
            }
            sv.setLocalSaida(rs.getString("local_saida"));
            sv.setLocalRetorno(rs.getString("local_retorno"));
            sv.setNumeroTransportados(rs.getInt("numero_transportados"));
            sv.setNumeroPedido(rs.getInt("numero_pedido"));
            sv.setObjetivo(rs.getString("objetivo_viagem"));
            sv.setPercurso(rs.getString("percurso"));
            sv.setServidores(rs.getBoolean("servidores"));
            try {
                sv.setStatus(rs.getString("status"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            PreparedStatement stmt2 = this.connection.prepareStatement(sql2);
            stmt2.setInt(1, sv.getIdSolicitacaoViagem());
            System.out.println(stmt2.toString());
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                Integer ids = rs2.getInt("id_passageiro");
                try {
                    sv.getPassageiros().add(new PassageiroDAO(this.connection).getById(ids));
                } catch (Exception ex) {
                    Logger.getLogger(SolicitacaoViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Integer uid = rs.getInt("id_responsavel_solicitacao");
            sv.setSolicitante(new UsuarioDAO(this.connection).getById(uid));
            Integer uaid = rs.getInt("id_responsavel_autorizante");
            sv.setAutorizante(new UsuarioDAO(this.connection).getById(uaid));
            Integer vid = rs.getInt("id_tipo_veiculo");
            sv.setTipoVeiculo((TipoVeiculo) new TipoVeiculoDAO(this.connection).getById(vid));
            return sv;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
