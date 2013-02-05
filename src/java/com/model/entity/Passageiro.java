package com.model.entity;

/**
 *
 * @author Usuario
 */
public class Passageiro implements Entity {

    private Integer idPassageiro;
    private String rg;
    private String nome;
    private String telefone;
    private Boolean disabled;

    public Passageiro() {
        this.disabled = false;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the idPassageiro
     */
    public Integer getIdPassageiro() {
        return idPassageiro;
    }

    /**
     * @param idPassageiro the idPassageiro to set
     */
    public void setIdPassageiro(Integer idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    /**
     * @return the disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
