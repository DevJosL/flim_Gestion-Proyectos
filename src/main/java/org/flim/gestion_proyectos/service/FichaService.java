package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Ficha;
import org.flim.gestion_proyectos.repository.IFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService implements IFichaService{
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
    public Ficha buscarFichaPorNombre(String nombreFicha) {
        List<Ficha> fichas = fichaRepository.findByNombreFicha(nombreFicha);
        if(fichas != null){
            return fichas.getFirst();
        }
        return null;
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
