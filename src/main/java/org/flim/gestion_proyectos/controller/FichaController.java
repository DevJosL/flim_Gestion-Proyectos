package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Ficha;
import org.flim.gestion_proyectos.service.IFichaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ViewScoped
@Data
public class FichaController {

    @Autowired
    IFichaService fichaService;
    private List<Ficha> fichas;
    private Ficha fichaSeleccionada;
    private static Logger logger = LoggerFactory.getLogger(FichaController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.fichas = this.fichaService.listarFichas();
        this.fichas.forEach(ficha -> logger.info(ficha.toString()));
    }

    public void agregarFicha(){
        this.fichaSeleccionada = new Ficha();
    }

    public void guardarFicha(){
        logger.info("Ficha a guardar" + this.fichaSeleccionada);

        if (this.fichaSeleccionada.getIdFicha()==null){
            this.fichaService.guardarFicha(this.fichaSeleccionada);
            this.fichas.add(this.fichaSeleccionada);
        } else{
            this.fichaService.guardarFicha(this.fichaSeleccionada);
        }

        this.fichaSeleccionada = null;
    }

    public void eliminarFicha(){
        logger.info("Ficha a eliminar: " + this.fichaSeleccionada);
        this.fichaService.eliminarFicha(this.fichaSeleccionada);
        this.fichas.remove(this.fichaSeleccionada);
        this.fichaSeleccionada=null;
    }
}