package com.model.entity;

/**
 *
 * @author Usuario
 */
public class TipoVeiculo implements Entity {

    private Integer idTipoVeiculo;
    private String tipoVeiculo;

    /**
     * @return the idTipoVeiculo
     */
    public Integer getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    /**
     * @param idTipoVeiculo the id to set
     */
    public void setIdTipoVeiculo(Integer idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    /**
     * @return the tipoVeiculo
     */
    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    /**
     * @param tipoVeiculo the tipoVeiculo to set
     */
    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
}
