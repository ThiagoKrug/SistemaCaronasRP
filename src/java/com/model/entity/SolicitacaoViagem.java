package com.model.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class SolicitacaoViagem implements Entity {

    private Integer id;
    private Calendar dataSaida;
    private Calendar dataRetorno;
    private Calendar horaSaida;
    private Calendar horaRetorno;
    private String objetivo;
    private String percurso;
    private String localSaida;
    private String localRetorno;
    private Usuario autorizante;
    private Usuario solicitante;
    private Integer numero;
    private Boolean servidores;
    private Veiculo veiculo;
    private String status;
    private List<Passageiro> passageiros;

    public SolicitacaoViagem() {
        this.passageiros = new ArrayList<Passageiro>();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dataSaida
     */
    public Date getDataSaida() {
        if (dataSaida != null) {
            return dataSaida.getTime();
        }
        return null;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = new GregorianCalendar();
        this.dataSaida.setTime(dataSaida);
    }

    /**
     * @return the dataRetorno
     */
    public Date getDataRetorno() {
        if (dataRetorno != null) {
            return dataRetorno.getTime();
        }
        return null;
    }

    /**
     * @param dataRetorno the dataRetorno to set
     */
    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = new GregorianCalendar();
        this.dataRetorno.setTime(dataRetorno);
    }

    /**
     * @return the horaSaida
     */
    public Date getHoraSaida() {
        if (horaSaida != null) {
            return horaSaida.getTime();
        }
        return null;
    }

    /**
     * @param horaSaida the horaSaida to set
     */
    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = new GregorianCalendar();
        this.horaSaida.setTime(horaSaida);
    }

    /**
     * @return the horaRetorno
     */
    public Date getHoraRetorno() {
        if (horaRetorno != null) {
            return horaRetorno.getTime();
        }
        return null;
    }

    /**
     * @param horaRetorno the horaRetorno to set
     */
    public void setHoraRetorno(Date horaRetorno) {
        this.horaRetorno = new GregorianCalendar();
        this.horaRetorno.setTime(horaRetorno);
    }

    /**
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * @return the percurso
     */
    public String getPercurso() {
        return percurso;
    }

    /**
     * @param percurso the percurso to set
     */
    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

    /**
     * @return the localSaida
     */
    public String getLocalSaida() {
        return localSaida;
    }

    /**
     * @param localSaida the localSaida to set
     */
    public void setLocalSaida(String localSaida) {
        this.localSaida = localSaida;
    }

    /**
     * @return the localRetorno
     */
    public String getLocalRetorno() {
        return localRetorno;
    }

    /**
     * @param localRetorno the localRetorno to set
     */
    public void setLocalRetorno(String localRetorno) {
        this.localRetorno = localRetorno;
    }

    /**
     * @return the autorizante
     */
    public Usuario getAutorizante() {
        return autorizante;
    }

    /**
     * @param autorizante the autorizante to set
     */
    public void setAutorizante(Usuario autorizante) {
        this.autorizante = autorizante;
    }

    /**
     * @return the solicitante
     */
    public Usuario getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante the solicitante to set
     */
    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the servidores
     */
    public Boolean getServidores() {
        return servidores;
    }

    /**
     * @param servidores the servidores to set
     */
    public void setServidores(Boolean servidores) {
        this.servidores = servidores;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the passageiros
     */
    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    /**
     * @param passageiros the passageiros to set
     */
    public void setPassageiros(List<Passageiro> passageirosp) {
        this.passageiros = passageirosp;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) throws Exception {
        if (status == null) {
            this.status = status;
        } else {
            if (status.equalsIgnoreCase(StatusSolicitacaoViagem.CANCELADO.toString())
                    || status.equalsIgnoreCase(StatusSolicitacaoViagem.SOLICITADO.toString())
                    || status.equalsIgnoreCase("")) {
                this.status = status;
            } else {
                throw new Exception("Falha ao setar status da solicitação viagem: status inválido. Status: " + status);
            }
        }
    }
}
