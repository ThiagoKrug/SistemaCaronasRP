package com.model.entity;

import java.text.SimpleDateFormat;
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

    private Integer idSolicitacaoViagem;
    private Calendar dataSaida;
    private Calendar dataRetorno;
    private Calendar horaSaida;
    private Calendar horaRetorno;
    private String objetivo;
    private String percurso;
    private String localSaida;
    private String localRetorno;
    private Usuario solicitante;
    private Integer numeroPedido;
    private Integer numeroTransportados;
    private Boolean servidores;
    private TipoVeiculo tipoVeiculo;
    private String status;
    private List<Passageiro> passageiros;
    private Viagem viagem;

    public SolicitacaoViagem() {
        this.passageiros = new ArrayList<Passageiro>();
    }

    /**
     * @return the idSolicitacaoViagem
     */
    public Integer getIdSolicitacaoViagem() {
        return idSolicitacaoViagem;
    }

    /**
     * @param idSolicitacaoViagem the id to set
     */
    public void setIdSolicitacaoViagem(Integer idSolicitacaoViagem) {
        this.idSolicitacaoViagem = idSolicitacaoViagem;
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
        if (dataSaida != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(dataSaida);
            this.dataSaida = new GregorianCalendar(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DAY_OF_MONTH));
        } else {
            this.dataSaida = null;
        }
    }
    
    /**
     * @return the dataSaida
     */
    public String getDataSaidaFormatada() {
        if (dataSaida != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dataSaida.getTime());
        }
        return null;
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
        if (dataRetorno != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(dataRetorno);
            this.dataRetorno = new GregorianCalendar(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DAY_OF_MONTH));
        } else {
            this.dataRetorno = null;
        }
    }
    
    /**
     * @return the dataRetorno
     */
    public String getDataRetornoFormatada() {
        if (dataRetorno != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dataRetorno.getTime());
        }
        return null;
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
        if (horaSaida != null) {
            this.horaSaida = new GregorianCalendar();
            this.horaSaida.setTime(horaSaida);
        } else {
            this.horaSaida = null;
        }
    }
    
    /**
     * @return the horaSaida
     */
    public String getHoraSaidaFormatada() {
        if (horaSaida != null) {
            return new SimpleDateFormat("hh:mm").format(horaSaida.getTime());
        }
        return null;
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
        if (horaRetorno != null) {
            this.horaRetorno = new GregorianCalendar();
            this.horaRetorno.setTime(horaRetorno);
        } else {
            this.horaRetorno = null;
        }
    }
    
    /**
     * @return the horaRetorno
     */
    public String getHoraRetornoFormatada() {
        if (horaRetorno != null) {
            return new SimpleDateFormat("hh:mm").format(horaRetorno.getTime());
        }
        return null;
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
     * @return the numeroPedido
     */
    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    /**
     * @param numeroPedido the numero to set
     */
    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Integer getNumeroTransportados() {
        return numeroTransportados;
    }

    public void setNumeroTransportados(Integer numeroTransportados) {
        this.numeroTransportados = numeroTransportados;
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
     * @return the tipoVeiculo
     */
    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    /**
     * @param tipoVeiculo the veiculo to set
     */
    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
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
                    || status.equalsIgnoreCase(StatusSolicitacaoViagem.EFETIVADO.toString())
                    || status.equalsIgnoreCase("")) {
                this.status = status;
            } else {
                throw new Exception("Falha ao setar status da solicitação viagem: status inválido. Status: " + status);
            }
        }
    }

    /**
     * @return the viagem
     */
    public Viagem getViagem() {
        return viagem;
    }

    /**
     * @param viagem the viagem to set
     */
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
