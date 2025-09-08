package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ViewScoped
@Data
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;
    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.usuarios = this.usuarioService.listarUsuario();
        this.usuarios.forEach(usuario -> logger.info(usuario.toString()));
    }

    public void agregarUsuario(){
        this.usuarioSeleccionado = new Usuario();
    }

    public void guardarUsuario(){
        logger.info("Usuario a guardar" + this.usuarioSeleccionado);

        if (this.usuarioSeleccionado.getIdUsuario()==null){
            this.usuarioService.guardarUsuario(this.usuarioSeleccionado);
            this.usuarios.add(this.usuarioSeleccionado);
        } else{
            this.usuarioService.guardarUsuario(this.usuarioSeleccionado);
        }

        this.usuarioSeleccionado = null;
    }

    public void eliminarUsuario(){
        logger.info("Usuario a eliminar: " + this.usuarioSeleccionado);
        this.usuarioService.eliminarUsuario(this.usuarioSeleccionado);
        this.usuarios.remove(this.usuarioSeleccionado);
        this.usuarioSeleccionado = null;
    }
}
