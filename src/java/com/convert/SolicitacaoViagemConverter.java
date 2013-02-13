package com.convert;

import com.google.gson.Gson;
import com.model.entity.Entity;
import com.model.entity.Passageiro;
import com.model.entity.SolicitacaoViagem;
import com.model.entity.StatusSolicitacaoViagem;
import com.model.entity.Usuario;
import com.model.entity.Veiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thiago
 */
public class SolicitacaoViagemConverter implements Converter {

    @Override
    public Entity convertEntity(HttpServletRequest request) {
        SolicitacaoViagem solicitacaoViagem = new SolicitacaoViagem();
        String id = request.getParameter("id_solicitacao_viagem");
        if (id.isEmpty() == false) {
            solicitacaoViagem.setIdSolicitacaoViagem(Integer.parseInt(id));
        }
        String idAutorizante = request.getParameter("autorizante");
        if (idAutorizante != null) {
            if (idAutorizante.isEmpty() == false) {
                Usuario autorizante = new Usuario();
                autorizante.setIdUsuario(Integer.parseInt(idAutorizante));
                solicitacaoViagem.setAutorizante(autorizante);
            }
        }
        Date dataRetorno = null;
        Date dataSaida = null;
        Date horaSaida = null;
        Date horaRetorno = null;
        String dr = request.getParameter("data_retorno");
        String ds = request.getParameter("data_saida");
        String hr = request.getParameter("hora_retorno");
        String hs = request.getParameter("hora_saida");
        try {
            dataRetorno = new SimpleDateFormat("dd/MM/yyyy").parse(dr);
            solicitacaoViagem.setDataRetorno(dataRetorno);
            dataSaida = new SimpleDateFormat("dd/MM/yyyy").parse(ds);
            solicitacaoViagem.setDataSaida(dataSaida);
            if (hr != null) {
                if (hr.isEmpty() == false) {
                    horaRetorno = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(dr + " " + hr);
                    solicitacaoViagem.setHoraRetorno(horaRetorno);
                }
            }
            if (hs != null) {
                if (hs.isEmpty() == false) {
                    horaSaida = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(ds + " " + hs);
                    solicitacaoViagem.setHoraSaida(horaSaida);
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        solicitacaoViagem.setLocalRetorno(request.getParameter("local_retorno"));
        solicitacaoViagem.setLocalSaida(request.getParameter("local_saida"));
        String numeroPedido = request.getParameter("numero_pedido");
        if (numeroPedido.isEmpty() == false) {
            solicitacaoViagem.setNumeroPedido(Integer.parseInt(numeroPedido));
        }
        String numeroTransportados = request.getParameter("numero_transportados");
        if (numeroTransportados.isEmpty() == false) {
            solicitacaoViagem.setNumeroTransportados(Integer.parseInt(numeroTransportados));
        }
        solicitacaoViagem.setObjetivo(request.getParameter("objetivo"));
        solicitacaoViagem.setPercurso(request.getParameter("percurso"));
        solicitacaoViagem.setServidores(Boolean.parseBoolean(request.getParameter("servidores")));
        String idSolicitante = request.getParameter("solicitante");
        if (idSolicitante.isEmpty() == false) {
            Usuario solicitante = new Usuario();
            solicitante.setIdUsuario(Integer.parseInt(idSolicitante));
            solicitacaoViagem.setSolicitante(solicitante);
        }
        try {
            solicitacaoViagem.setStatus(StatusSolicitacaoViagem.SOLICITADO.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String idVeiculo = request.getParameter("veiculo");
        if (idVeiculo.isEmpty() == false) {
            Veiculo v = new Veiculo();
            v.setIdVeiculo(Integer.parseInt(idVeiculo));
            solicitacaoViagem.setVeiculo(v);
        }
        solicitacaoViagem.setPassageiros(this.retrievePassageiros(request));
        return solicitacaoViagem;
    }
    
    public List<Passageiro> retrievePassageiros(HttpServletRequest request) {
        Passageiro[] passageiros = new Gson().fromJson(request.getParameter("passageiros"), Passageiro[].class);
        List<Passageiro> listaPassageiros = new ArrayList<Passageiro>();
        listaPassageiros.addAll(Arrays.asList(passageiros));
        return listaPassageiros;
    }
}
