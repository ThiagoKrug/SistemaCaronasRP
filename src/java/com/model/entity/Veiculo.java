package com.model.entity;

import com.model.dao.ViagemDAO;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 *
 */
public class Veiculo implements Entity, Comparable {

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

    public List<Evento> getAgenda(Connection connection) {
        List<Viagem> viagens = this.getViagens(connection);
        List<Evento> eventos = new ArrayList<Evento>();
        if (viagens != null) {
            if (viagens.size() > 0) {
                String cor = Evento.getCores();
                for (Viagem viagem : viagens) {
                    Evento evento = new Evento();
                    
                    String data = new SimpleDateFormat("yyyy-MM-dd").format(viagem.getDataSaida());
                    String hora = new SimpleDateFormat("HH:mm").format(viagem.getHoraSaida());
                    evento.setInicio(data + " " + hora);

                    data = new SimpleDateFormat("yyyy-MM-dd").format(viagem.getDataRetorno());
                    hora = new SimpleDateFormat("HH:mm").format(viagem.getHoraRetorno());
                    evento.setFim(data + " " + hora);

                    evento.setTitulo(this.getTipo() + " " + this.getPlaca() + " - " + viagem.getPercurso());

                    evento.setCor(cor);
                    
                    eventos.add(evento);
                }
                return eventos;
            }
        }
        return null;
    }

    public List<Viagem> getViagens(Connection connection) {
        ViagemDAO vdao = new ViagemDAO(connection);
        List<Viagem> viagens = vdao.getByIdVeiculo(this.getIdVeiculo());
        return viagens;
    }

    @Override
    public int compareTo(Object o) {
        Veiculo comp = (Veiculo)o;
        if (comp.getQuilometragem() > this.getQuilometragem()) {
            return -1;
        }
        
        else if (comp.getQuilometragem() < this.getQuilometragem()){
            return 1;
        }
        
        return 0;
    }
}
