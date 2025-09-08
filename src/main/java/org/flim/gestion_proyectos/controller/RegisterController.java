package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
@Data
public class RegisterController implements Serializable {

    // Inyeccion de dependencia
    @Autowired
    private IUsuarioService usuarioService;

    // El nuevo usuario que se registrara
    private Usuario nuevoUsuario;

    // Iniciamos el objeto de usuario
    @PostConstruct
    public void init() {
        this.nuevoUsuario = new Usuario();
    }

    // Metodo que maneja el registro
    public String registrar() {
        try {
            // Todos los usuarios tendran el rol de usuarios al crear la cuenta
            nuevoUsuario.setRol("usuario");

            // llamamos al servicio para guardar al usuario (ya esta encriptada la contra ahi)
            usuarioService.guardarUsuario(nuevoUsuario);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Te registraste con éxito."));

            // Si el usuario se creo correctamente se redirige al xhtml del login
            //return "/login.xhtml?faces-redirect=true";
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            // Si falla entonces muestra un mensaje de error
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al intentar registrarte."));
            // Mostramos el error en la consola para facilitar lectura de errores
            e.printStackTrace();

            // Se va a mantener en la misma pagina de registro
            return "/register.xhtml?faces-redirect=true";
        }
    }
}
