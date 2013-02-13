package com.convert;

import com.model.entity.Entity;
import com.model.entity.Passageiro;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thiago
 */
public class PassageiroConverter implements Converter {

    @Override
    public Entity convertEntity(HttpServletRequest request) {
        Passageiro passageiro = new Passageiro();
        String id = request.getParameter("id_passageiro");
        if (id.isEmpty() == false) {
            passageiro.setIdPassageiro(Integer.parseInt(id));
        }
        passageiro.setNome(request.getParameter("nome"));
        passageiro.setRg(request.getParameter("rg"));
        passageiro.setTelefone(request.getParameter("telefone"));
        passageiro.setEndereco(request.getParameter("endereco"));
        return passageiro;
    }
}
