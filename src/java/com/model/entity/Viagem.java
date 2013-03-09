package com.model.entity;

import com.model.dao.PassageiroDAO;
import com.model.dao.SolicitacaoViagemDAO;
import com.model.dao.ViagemDAO;
import java.sql.Connection;
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
public class Viagem implements Entity {
    
    private Integer idViagem;
    private Calendar dataEfetivacao;
    private Usuario autorizante;
    private Usuario motorista;
    private Veiculo veiculo;
    private Calendar dataSaida;
    private Calendar dataRetorno;
    private Calendar horaSaida;
    private Calendar horaRetorno;
    private String objetivo;
    private String percurso;
    private String localSaida;
    private String localRetorno;
    private List<Passageiro> passageiros;
    private List<SolicitacaoViagem> solicitacoes;

    /**
     * @return the idViagem
     */
    public Integer getIdViagem() {
        return idViagem;
    }

    /**
     * @param idViagem the idViagem to set
     */
    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    /**
     * @return the dataEfetivacao
     */
    public Date getDataEfetivacao() {
        if (dataEfetivacao != null) {
            return dataEfetivacao.getTime();
        }
        return null;
    }
    
    /**
     * @return the dataEfetivacao
     */
    public String getDataEfetivacaoFormatada() {
        if (dataEfetivacao != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dataEfetivacao.getTime());
        }
        return null;
    }

    /**
     * @param dataEfetivacao the dataEfetivacao to set
     */
    public void setDataEfetivacao(Date dataEfetivacao) {
        if (dataEfetivacao != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(dataEfetivacao);
            this.dataEfetivacao = new GregorianCalendar(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DAY_OF_MONTH));
        } else {
            this.dataEfetivacao = null;
        }
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
     * @return the motorista
     */
    public Usuario getMotorista() {
        return motorista;
    }

    /**
     * @param motorista the motorista to set
     */
    public void setMotorista(Usuario motorista) {
        this.motorista = motorista;
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
    public List<Passageiro> getPassageiros(Connection connection) {
        if (this.passageiros == null) {
            this.passageiros = new ArrayList<Passageiro>();
            PassageiroDAO pdao = new PassageiroDAO(connection);
            for (Integer id: new ViagemDAO().getPassIds(this.idViagem)) {
                this.passageiros.add(pdao.getById(id));
            }
        }
        return passageiros;
    }

    /**
     * @param passageiros the passageiros to set
     */
    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }

    /**
     * @return the solicitacoes
     */
    public List<SolicitacaoViagem> getSolicitacoes(Connection connection) {
        if (solicitacoes == null) {
            solicitacoes = new ArrayList<SolicitacaoViagem>();
            SolicitacaoViagemDAO sdao = new SolicitacaoViagemDAO(connection);
            for (Integer sv: new ViagemDAO().getSolIds(this.idViagem)) {
                solicitacoes.add(sdao.getById(sv));
            }
        }
        return solicitacoes;
    }

    /**
     * @param solicitacoes the solicitacoes to set
     */
    public void setSolicitacoes(List<SolicitacaoViagem> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    /**
     * @return the dataSaida
     */
    public Calendar getDataSaida() {
        return dataSaida;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(Calendar dataSaida) {
        this.dataSaida = dataSaida;
    }

    /**
     * @return the dataRetorno
     */
    public Calendar getDataRetorno() {
        return dataRetorno;
    }

    /**
     * @param dataRetorno the dataRetorno to set
     */
    public void setDataRetorno(Calendar dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    /**
     * @return the horaSaida
     */
    public Calendar getHoraSaida() {
        return horaSaida;
    }

    /**
     * @param horaSaida the horaSaida to set
     */
    public void setHoraSaida(Calendar horaSaida) {
        this.horaSaida = horaSaida;
    }

    /**
     * @return the horaRetorno
     */
    public Calendar getHoraRetorno() {
        return horaRetorno;
    }

    /**
     * @param horaRetorno the horaRetorno to set
     */
    public void setHoraRetorno(Calendar horaRetorno) {
        this.horaRetorno = horaRetorno;
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
    
    
    
}
