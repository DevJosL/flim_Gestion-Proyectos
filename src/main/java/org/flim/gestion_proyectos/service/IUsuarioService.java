package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listarUsuario();
    public Usuario buscarUsuarioporId(Integer codigo);
    public Usuario buscarUsuarioporNombreYClave(String nombre, String clave);
    public void guardarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
}
