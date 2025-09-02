package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    @Override
    public List<Usuario> listarUsuario() {
        return List.of();
    }

    @Override
    public Usuario buscarUsuarioporId(Integer id) {
        return null;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {

    }

    @Override
    public void eliminarUsuario(Usuario usuario) {

    }
}
