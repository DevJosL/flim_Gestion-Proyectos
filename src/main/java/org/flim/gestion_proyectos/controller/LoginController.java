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

import java.io.IOException;
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

    private String nombreUsuario;
    private String contrasenia;

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

    public void verificarDatos(){
        if(!nombreUsuario.isBlank() && !(contrasenia.isBlank())){
            Usuario usuarioIngresado = usuarioService.buscarUsuarioporNombreYClave(nombreUsuario, contrasenia);
            if(usuarioIngresado != null){
                logger.info("Usuario: "+usuarioIngresado.toString());

                //Guarda el usuario que ingreso.
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("usuarioLogueado", usuarioIngresado);

                try {
                    if ("admin".equalsIgnoreCase(usuarioIngresado.getRol())){
                        FacesContext.getCurrentInstance().getExternalContext().redirect("administracion.xhtml");
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoView.xhtml");
                    }
                } catch (IOException e){
                    logger.info("" + e);
                    e.printStackTrace();
                }

            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Credenciales inválidas",
                                "Usuario o contraseña incorrectos"));
            }
        }else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Campos vacíos",
                            "Ingrese usuario y contraseña"));
        }

    }

}
