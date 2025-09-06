package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        // Evitamos que la contraseña se muestre definiendola como null, de esta forma nunca llegaria la respuesta POST con datos sencibles
        usuarios.forEach(usuario -> usuario.setContraseña(null));
        return usuarios;
    }

    public Usuario buscarUsuarioporId(Integer codigo){
        Usuario usuario = usuarioRepository.findById(codigo).orElse(null);
        if (usuario != null) {
            // Lo mismo, limpiamos la contraseña como null para evitar transferir o mostrar datos sencibles
            usuario.setContraseña(null);
        }
        return usuario;
    }

    public void guardarUsuario(Usuario usuario){
        if (usuario.getIdUsuario() == null) {
            // Encriptamos la contraseña para el nuevo usuario
            String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
            usuario.setContraseña(contraseñaEncriptada);
            logger.info("Usuario nuevo guardado: " + usuario.getNombreUsuario());
        } else {
            // Mantenemos contraseñas actuales para los usuarios que se actualicen
            Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getIdUsuario());
            if (usuarioExistente.isPresent()) {
                usuario.setContraseña(usuarioExistente.get().getContraseña());
                logger.info("Usuario existente actualizado: " + usuario.getNombreUsuario());
            }
        }
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
        logger.info("Usuario eliminado: " + usuario.getNombreUsuario());
    }

    /*
        El punto de crear este proceso es que un administrador pueda reestablecer la contraseña, de modo que
        no podra verla mas si actualizarla y a su vez encriptarla, mejora seguridad y evita que se traspasen datos sencibles
        ademas de hacer el proyecto mas escalable en relacion a servicio a cliente
    */
    public void reestablecerContrasena(Integer idUsuario, String nuevaContrasena){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // volvemos a encriptar y guardar contraseña actualizada o restablecida
            String contraseñaEncriptada = passwordEncoder.encode(nuevaContrasena);
            usuario.setContraseña(contraseñaEncriptada);
            usuarioRepository.save(usuario);
            logger.info("Contraseña reestablecida para el usuario con ID: " + idUsuario);
        }
    }
}