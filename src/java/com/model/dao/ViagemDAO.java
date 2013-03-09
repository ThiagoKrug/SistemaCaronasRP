/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.Usuario;
import com.model.entity.Veiculo;
import com.model.entity.Viagem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Viagem viagem = (Viagem)entity;
        String sql = "insert into viagem "
                + "(id_autorizante, id_motorista, id_veiculo, "
                + "data_efetivacao) values (?,?,?,?)";
        
        return 0;
    }

    @Override
    public int alterar(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletar(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
