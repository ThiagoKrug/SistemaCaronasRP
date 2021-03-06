package com.model.entity;

import com.model.dao.ViagemDAO;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Usuario implements Entity {
    
    public static String ATIVO = "ativo";
    public static String INATIVO = "inativo";

    private Integer idUsuario;
    private TipoUsuario tipoUsuario;
    private String nome;
    private String rg;
    private String username;
    private String numeroServidor;
    private String telefone;
    private String email;
    private String senha;
    private String situacao;

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the id to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipo to set
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the numeroServidor
     */
    public String getNumeroServidor() {
        return numeroServidor;
    }

    /**
     * @param numeroServidor the numeroServidor to set
     */
    public void setNumeroServidor(String numeroServidor) {
        this.numeroServidor = numeroServidor;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

                    evento.setTitulo(this.getNome() + " - " + viagem.getPercurso());

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
        List<Viagem> viagens = vdao.getByIdMotorista(this.getIdUsuario());
        return viagens;
    }
}
