package com.convert;

import com.model.entity.Entity;
import com.model.entity.TipoUsuario;
import com.model.entity.Usuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thiago
 */
public class UsuarioConverter implements Converter {

    @Override
    public Entity convertEntity(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        String id = request.getParameter("id_usuario");
        if (id.isEmpty() == false) {
            usuario.setIdUsuario(Integer.parseInt(id));
        }
        usuario.setNome(request.getParameter("nome"));
        usuario.setRg(request.getParameter("rg"));
        usuario.setTelefone(request.getParameter("telefone"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setNumeroServidor(request.getParameter("numero_servidor"));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setUsername(request.getParameter("username"));
        usuario.setSituacao("ativo");
        
        TipoUsuario tu = new TipoUsuario();
        tu.setIdTipoUsuario(Integer.parseInt(request.getParameter("tipo_usuario")));
        usuario.setTipoUsuario(tu);
        
        return usuario;
    }
}
