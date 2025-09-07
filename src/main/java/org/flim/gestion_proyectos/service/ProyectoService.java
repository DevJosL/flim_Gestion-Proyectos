package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Proyecto;
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService implements IProyectoService{

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> listarProyectos() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos;
    }

    @Override
    public List<Proyecto> buscarProyectoPorIdUser(Usuario idUser) {
        List<Proyecto> proyectos = proyectoRepository.findByIdUsuario(idUser);
        return proyectos;
    }

    @Override
    public Proyecto buscarProyectoPorId(Integer id) {
        Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
        return proyecto;
    }

    @Override
    public void guardarContacto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminarContacto(Proyecto proyecto) {
        proyectoRepository.delete(proyecto);
    }
}
