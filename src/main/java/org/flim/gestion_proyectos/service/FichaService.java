package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Ficha;
import org.flim.gestion_proyectos.repository.IFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService implements IFichaService{

    @Override
    public List<Ficha> buscarFichaPorIdProyecto(Integer idProyecto) {
        List<Ficha> proyectos = fichaRepository.findByProyecto_idProyecto(idProyecto);
        return proyectos;
    }

    @Autowired
    private IFichaRepository fichaRepository;

    @Override
    public List<Ficha> listarFichas() {
        List<Ficha> fichas = fichaRepository.findAll();
        return fichas;
    }

    @Override
    public Ficha buscarFichaPorId(Integer codigo) {
        Ficha ficha = fichaRepository.findById(codigo).orElse(null);
        return ficha;
    }

    @Override
    public void guardarFicha(Ficha ficha) {
        fichaRepository.save(ficha);
    }

    @Override
    public void eliminarFicha(Ficha ficha) {
        fichaRepository.delete(ficha);
    }
}
