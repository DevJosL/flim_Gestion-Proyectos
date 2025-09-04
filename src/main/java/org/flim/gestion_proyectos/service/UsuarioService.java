package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    //Inyeccion de dependencias
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario buscarUsuarioporId(Integer codigo){
        Usuario usuario = usuarioRepository.findById(codigo).orElse(null);
        return usuario;
    }

    public void guardarUsuario(Usuario usuario){
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraseñaEncriptada);
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
