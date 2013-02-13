package com.convert;

import com.model.entity.Entity;
import com.model.entity.TipoVeiculo;
import com.model.entity.Veiculo;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thiago
 */
public class VeiculoConverter implements Converter {

    @Override
    public Entity convertEntity(HttpServletRequest request) {
        Veiculo veiculo = new Veiculo();
        String id = request.getParameter("id_veiculo");
        if (id.isEmpty() == false) {
            veiculo.setIdVeiculo(Integer.parseInt(id));
        }
        veiculo.setCapacidadePassageiro(Integer.parseInt(request.getParameter("capacidade_passageiro")));
        veiculo.setCor(request.getParameter("cor"));
        veiculo.setPlaca(request.getParameter("placa"));
        veiculo.setQuilometragem(Float.parseFloat(request.getParameter("quilometragem")));
        TipoVeiculo tipoVeiculo = new TipoVeiculo();
        tipoVeiculo.setIdTipoVeiculo(Integer.parseInt(request.getParameter("tipo_veiculo")));
        veiculo.setTipoVeiculo(tipoVeiculo);

        return veiculo;
    }
}
