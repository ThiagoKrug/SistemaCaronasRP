package com.model.dao;

import com.model.entity.Entity;

/**
 *
 * @author thiago
 */
public interface Dao {
    
    public int inserir(Entity entity);
    public int alterar(Entity entity);
    public int deletar(Entity entity);
    public Entity getById(Integer id);
    
}
