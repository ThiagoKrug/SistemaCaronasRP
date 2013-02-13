package com.model.entity;

/**
 *
 * @author Usuario
 */
public class TipoUsuario implements Entity {

    private Integer idTipoUsuario;
    private String tipoUsuario;

    /**
     * @return the idTipoUsuario
     */
    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * @param idTipoUsuario the id to set
     */
    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
