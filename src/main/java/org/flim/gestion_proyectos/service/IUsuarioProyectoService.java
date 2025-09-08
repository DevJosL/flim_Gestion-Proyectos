package org.flim.gestion_proyectos.service;


import org.flim.gestion_proyectos.entity.UsuarioProyecto;

import java.util.List;

public interface IUsuarioProyectoService {
    public List<UsuarioProyecto> listarUsuariosProyectos();
    public void guardarUsuarioProyecto(UsuarioProyecto usuarioProyecto);
    public void eliminarContacto(UsuarioProyecto usuarioProyecto);
}
