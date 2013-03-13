package com.jdbc.fixtures;

import com.model.dao.PassageiroDAO;
import com.model.dao.SolicitacaoViagemDAO;
import com.model.dao.TipoUsuarioDAO;
import com.model.dao.UsuarioDAO;
import com.model.dao.VeiculoDAO;
import com.model.dao.ViagemDAO;
import com.model.entity.Passageiro;
import com.model.entity.SolicitacaoViagem;
import com.model.entity.StatusSolicitacaoViagem;
import com.model.entity.TipoUsuario;
import com.model.entity.TipoVeiculo;
import com.model.entity.Usuario;
import com.model.entity.Veiculo;
import com.model.entity.Viagem;
import java.sql.Connection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author thiago
 */
public class Fixtures {

    public void loadFixtures(Connection connection) {
        UsuarioDAO udao = new UsuarioDAO(connection);
        TipoUsuarioDAO tdao = new TipoUsuarioDAO(connection);
        List<TipoUsuario> tiposUsuarios = tdao.getTiposUsuarios();
        Usuario motorista = new Usuario();
        Usuario servidor = new Usuario();
        Usuario admin = new Usuario();
        for (TipoUsuario tipoUsuario : tiposUsuarios) {
            if (tipoUsuario.getTipoUsuario().equalsIgnoreCase("motorista")) {
                motorista.setEmail("motorista@email.com");
                motorista.setNome("Motorista");
                motorista.setNumeroServidor("11111");
                motorista.setRg("1234567890");
                motorista.setSenha("11111");
                motorista.setSituacao(Usuario.ATIVO);
                motorista.setTelefone("55 99996666");
                motorista.setTipoUsuario(tipoUsuario);
                motorista.setUsername("motorista");
                udao.inserir(motorista);
            } else if (tipoUsuario.getTipoUsuario().equalsIgnoreCase("Servidor Solicitante")) {
                servidor.setEmail("servidor@email.com");
                servidor.setNome("Servidor");
                servidor.setNumeroServidor("22222");
                servidor.setRg("0987654321");
                servidor.setSenha("22222");
                servidor.setSituacao(Usuario.ATIVO);
                servidor.setTelefone("5599669966");
                servidor.setTipoUsuario(tipoUsuario);
                servidor.setUsername("servidor");
                udao.inserir(servidor);
            }
        }

        List<Usuario> usuarios = udao.getUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(motorista.getNome())) {
                motorista = usuario;
            } else if (usuario.getNome().equalsIgnoreCase(servidor.getNome())) {
                servidor = usuario;
            } else if (usuario.getUsername().equalsIgnoreCase("admin")) {
                admin = usuario;
            }
        }

        TipoVeiculo tvOnibus = new TipoVeiculo();
        tvOnibus.setIdTipoVeiculo(1);
        TipoVeiculo tvMicroOnibus = new TipoVeiculo();
        tvMicroOnibus.setIdTipoVeiculo(2);
        TipoVeiculo tvPassageiro = new TipoVeiculo();
        tvPassageiro.setIdTipoVeiculo(3);
        TipoVeiculo tvUtilitario = new TipoVeiculo();
        tvUtilitario.setIdTipoVeiculo(4);
        TipoVeiculo tvCarga = new TipoVeiculo();
        tvCarga.setIdTipoVeiculo(5);

        VeiculoDAO vdao = new VeiculoDAO(connection);

        Veiculo onibus = new Veiculo();
        onibus.setCapacidadePassageiro(54);
        onibus.setCor("Branco");
        onibus.setPlaca("abc-1234");
        onibus.setQuilometragem(20000f);
        onibus.setTipoVeiculo(tvOnibus);
        vdao.inserir(onibus);

        System.out.println("passo aqui");

        Veiculo microOnibus = new Veiculo();
        microOnibus.setCapacidadePassageiro(24);
        microOnibus.setCor("Verde");
        microOnibus.setPlaca("xyz-0987");
        microOnibus.setQuilometragem(35258f);
        microOnibus.setTipoVeiculo(tvMicroOnibus);
        vdao.inserir(microOnibus);

        Veiculo passageiro = new Veiculo();
        passageiro.setCapacidadePassageiro(5);
        passageiro.setCor("Preto");
        passageiro.setPlaca("asd-3456");
        passageiro.setQuilometragem(18654f);
        passageiro.setTipoVeiculo(tvPassageiro);
        vdao.inserir(passageiro);

        Veiculo utilitario = new Veiculo();
        utilitario.setCapacidadePassageiro(2);
        utilitario.setCor("Vermelho");
        utilitario.setPlaca("ian-1097");
        utilitario.setQuilometragem(34871f);
        utilitario.setTipoVeiculo(tvUtilitario);
        vdao.inserir(utilitario);

        Veiculo carga = new Veiculo();
        carga.setCapacidadePassageiro(2);
        carga.setCor("Azul");
        carga.setPlaca("ier-9473");
        carga.setQuilometragem(12362f);
        carga.setTipoVeiculo(tvCarga);
        vdao.inserir(carga);
        
        for (Veiculo veiculo : vdao.getVeiculos()) {
            if (veiculo.getPlaca().equalsIgnoreCase(onibus.getPlaca())) {
                onibus = veiculo;
            } else if (veiculo.getPlaca().equalsIgnoreCase(microOnibus.getPlaca())) {
                microOnibus = veiculo;
            } else if (veiculo.getPlaca().equalsIgnoreCase(passageiro.getPlaca())) {
                passageiro = veiculo;
            } else if (veiculo.getPlaca().equalsIgnoreCase(utilitario.getPlaca())) {
                utilitario = veiculo;
            } else if (veiculo.getPlaca().equalsIgnoreCase(carga.getPlaca())) {
                carga = veiculo;
            }
        }

        PassageiroDAO pdao = new PassageiroDAO(connection);

        Passageiro joao = new Passageiro();
        joao.setEndereco("Rua Barão do Amazonas, 125");
        joao.setNome("João da Silva");
        joao.setRg("7575757575");
        joao.setTelefone("55 91929394");
        pdao.inserir(joao);

        Passageiro maria = new Passageiro();
        maria.setEndereco("Rua Barão do Cerro Largo, 123");
        maria.setNome("Maria da Silva");
        maria.setRg("4242424242");
        maria.setTelefone("55 98979695");
        pdao.inserir(maria);

        Passageiro jose = new Passageiro();
        jose.setEndereco("Avenida Tiarajú, 723");
        jose.setNome("José da Silva");
        jose.setRg("3232323232");
        jose.setTelefone("55 99919892");
        pdao.inserir(jose);

        Passageiro ana = new Passageiro();
        ana.setEndereco("Rua General Sampaio, 321");
        ana.setNome("Ana da Silva");
        ana.setRg("6565656565");
        ana.setTelefone("55 95949693");
        pdao.inserir(ana);
        
        for (Passageiro p : pdao.getPassageiros()) {
            if (p.getNome().equalsIgnoreCase(joao.getNome())) {
                joao = p;
            } else if (p.getNome().equalsIgnoreCase(maria.getNome())) {
                maria = p;
            } else if (p.getNome().equalsIgnoreCase(jose.getNome())) {
                jose = p;
            } else if (p.getNome().equalsIgnoreCase(ana.getNome())) {
                ana = p;
            }
        }

        SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);

        SolicitacaoViagem sv1 = new SolicitacaoViagem();
        sv1.setDataRetorno(new Date(System.currentTimeMillis()));
        sv1.setDataSaida(new Date(System.currentTimeMillis()));
        sv1.setHoraRetorno(new GregorianCalendar(2013, 10, 10, 12, 0).getTime());
        sv1.setHoraSaida(new GregorianCalendar(2013, 10, 10, 20, 0).getTime());
        sv1.setLocalRetorno("Uruguaiana");
        sv1.setLocalSaida("Alegrete");
        sv1.setNumeroPedido(1);
        sv1.setNumeroTransportados(4);
        sv1.setObjetivo("SIEPE");
        sv1.setPercurso("Alegrete - Uruguaiana - Alegrete");
        sv1.setServidores(true);
        sv1.setSolicitante(servidor);
        try {
            sv1.setStatus(StatusSolicitacaoViagem.SOLICITADO.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sv1.setTipoVeiculo(tvUtilitario);
        svdao.inserir(sv1);
        
        for (SolicitacaoViagem solicitacaoViagem : svdao.getSolicitacoes()) {
            if (solicitacaoViagem.getLocalSaida().equalsIgnoreCase(sv1.getLocalSaida())) {
                sv1 = solicitacaoViagem;
            }
        }
        
        ViagemDAO vidao = new ViagemDAO(connection);
        
        Viagem v = new Viagem();
        v.setAutorizante(admin);
        v.setDataRetorno(new Date(System.currentTimeMillis()));
        v.setDataSaida(new Date(System.currentTimeMillis()));
        v.setHoraRetorno(new GregorianCalendar(0, 0, 0, 12, 0).getTime());
        v.setHoraSaida(new GregorianCalendar(0, 0, 0, 20, 0).getTime());
        v.setLocalRetorno("Uruguaiana");
        v.setLocalSaida("Alegrete");
        v.setObjetivo("SIEPE");
        v.setPercurso("Alegrete - Uruguaiana - Alegrete");
        v.setVeiculo((Veiculo)vdao.getById(4));
        v.setDataEfetivacao(new Date(System.currentTimeMillis()));
        v.setMotorista(motorista);
        v.addSolicitacao(sv1);
        v.addPassageiro(ana);
        v.addPassageiro(joao);
        v.addPassageiro(jose);
        vidao.inserir(v);
    }
}
