/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.Entity;
import com.model.entity.Viagem;
import java.sql.Connection;

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
    
    
}
