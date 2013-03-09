package com.model.entity;

import com.model.dao.ViagemDAO;
import java.util.List;

/**
 *
 * @author Usuario
 *
 */
public class Veiculo implements Entity {

    private Integer idVeiculo;
    private TipoVeiculo tipoVeiculo;
    private String placa;
    private Float quilometragem;
    private Integer capacidadePassageiro;
    private String cor;

    public Veiculo() {
    }

    /**
     * @return the idVeiculo
     */
    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * @param idVeiculo the id to set
     */
    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the quilometragem
     */
    public Float getQuilometragem() {
        return quilometragem;
    }

    /**
     * @param quilometragem the quilometragem to set
     */
    public void setQuilometragem(Float quilometragem) {
        this.quilometragem = quilometragem;
    }

    /**
     * @return the capacidade
     */
    public Integer getCapacidadePassageiro() {
        return capacidadePassageiro;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidadePassageiro(Integer capacidade) {
        this.capacidadePassageiro = capacidade;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the tipoVeiculo
     */
    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    /**
     * @param tipoVeiculo the tipoVeiculo to set
     */
    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipo() {
        return this.tipoVeiculo.getTipoVeiculo();
    }

    public void setTipo(String tipo) {
        this.tipoVeiculo.setTipoVeiculo(tipo);
    }
    
    public void getAgenda() {
        ViagemDAO vdao = new ViagemDAO();
        List<Viagem> viagens = vdao.getByIdVeiculo(this.getIdVeiculo());
        
    }
}
