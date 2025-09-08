package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.UsuarioProyecto;
import org.flim.gestion_proyectos.repository.UsuarioProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioProyectoService implements IUsuarioProyectoService{

    @Autowired
    private UsuarioProyectoRepository usuarioProyectoRepository;

    @Override
    public List<UsuarioProyecto> listarUsuariosProyectos() {
        List<UsuarioProyecto> usuarioProyectos = usuarioProyectoRepository.findAll();
        return usuarioProyectos;
    }

    @Override
    public void guardarUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        usuarioProyectoRepository.save(usuarioProyecto);
    }

    @Override
    public void eliminarContacto(UsuarioProyecto usuarioProyecto) {
        usuarioProyectoRepository.delete(usuarioProyecto);
    }
}
