package com.convert;

import com.google.gson.Gson;
import com.model.dao.SolicitacaoViagemDAO;
import com.model.entity.Entity;
import com.model.entity.Passageiro;
import com.model.entity.SolicitacaoViagem;
import com.model.entity.Usuario;
import com.model.entity.Veiculo;
import com.model.entity.Viagem;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thiago
 */
public class ViagemConverter implements Converter {

    @Override
    public Entity convertEntity(HttpServletRequest request) {
        Viagem viagem = new Viagem();
        String id = request.getParameter("id_viagem");
        if (id.isEmpty() == false) {
            viagem.setIdViagem(Integer.parseInt(id));
        }
        Date dataRetorno = null;
        Date dataSaida = null;
        Date dataEfetivacao = null;
        Date horaSaida = null;
        Date horaRetorno = null;
        String dr = request.getParameter("data_retorno");
        String ds = request.getParameter("data_saida");
        String de = request.getParameter("data_efetivacao");
        String hr = request.getParameter("hora_retorno");
        String hs = request.getParameter("hora_saida");
        try {
            dataRetorno = new SimpleDateFormat("dd/MM/yyyy").parse(dr);
            viagem.setDataRetorno(dataRetorno);
            dataSaida = new SimpleDateFormat("dd/MM/yyyy").parse(ds);
            viagem.setDataSaida(dataSaida);
            dataEfetivacao = new SimpleDateFormat("dd/MM/yyyy").parse(de);
            viagem.setDataEfetivacao(dataEfetivacao);
            if (hr != null) {
                if (hr.isEmpty() == false) {
                    horaRetorno = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(dr + " " + hr);
                    viagem.setHoraRetorno(horaRetorno);
                }
            }
            if (hs != null) {
                if (hs.isEmpty() == false) {
                    horaSaida = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(ds + " " + hs);
                    viagem.setHoraSaida(horaSaida);
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        viagem.setLocalRetorno(request.getParameter("local_retorno"));
        viagem.setLocalSaida(request.getParameter("local_saida"));
        viagem.setObjetivo(request.getParameter("objetivo"));
        viagem.setPercurso(request.getParameter("percurso"));
        viagem.setAutorizante(request.getParameter("autorizante"));

        String idMotorista = request.getParameter("motorista");
        if (idMotorista.isEmpty() == false) {
            Usuario motorista = new Usuario();
            motorista.setIdUsuario(Integer.parseInt(idMotorista));
            viagem.setMotorista(motorista);
        }

        String idVeiculo = request.getParameter("veiculo");
        if (idVeiculo.isEmpty() == false) {
            Veiculo veiculo = new Veiculo();
            veiculo.setIdVeiculo(Integer.parseInt(idVeiculo));
            viagem.setVeiculo(veiculo);
        }

        viagem.setPassageiros(this.retrievePassageiros(request));

        viagem.setSolicitacoes(this.retrieveSolicitacoesViagem(request));
        
        return viagem;
    }

    private List<SolicitacaoViagem> retrieveSolicitacoesViagem(HttpServletRequest request) {
        Connection connection = (Connection) request.getAttribute("connection");
        List<SolicitacaoViagem> solicitacoes = new ArrayList<SolicitacaoViagem>();
        SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
        for (SolicitacaoViagem sv : svdao.getSolicitacoes()) {
            if (request.getParameter("solicitacaoViagem" + sv.getIdSolicitacaoViagem()) != null) {
                solicitacoes.add(sv);
            }
        }
        return solicitacoes;
    }

    public List<Passageiro> retrievePassageiros(HttpServletRequest request) {
        Passageiro[] passageiros = new Gson().fromJson(request.getParameter("passageiros"), Passageiro[].class);
        List<Passageiro> listaPassageiros = new ArrayList<Passageiro>();
        listaPassageiros.addAll(Arrays.asList(passageiros));
        return listaPassageiros;
    }
}
