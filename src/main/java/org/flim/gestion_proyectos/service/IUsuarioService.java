package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioporId(Integer id);
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
}
