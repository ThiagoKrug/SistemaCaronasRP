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
    
    
    
}
