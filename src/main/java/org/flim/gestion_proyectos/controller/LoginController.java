package org.flim.gestion_proyectos.controller;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.service.IUsuarioService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ViewScoped
@Data
public class LoginController {
    @Autowired
    IUsuarioService usuarioService;
    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostConstruct
    public void init(){
        logger.info("Iniciando login controller");
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setClientes(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void verificarDatos(String nombreUsuario, String contrasenia ){
        if(!nombreUsuario.isBlank() && !(contrasenia.isBlank())){
            Usuario usuarioIngresado = usuarioService.buscarUsuarioporNombreYClave(nombreUsuario, contrasenia);
            if(usuarioIngresado != null){
                logger.info("Usuario: "+usuarioIngresado.toString());
                //Supongo que por aquí iría el cambio de vista o un boolean para verificarlo
            }
        }

    }

}
