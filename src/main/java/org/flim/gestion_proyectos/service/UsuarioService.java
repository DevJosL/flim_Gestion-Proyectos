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

    public Usuario buscarUsuarioporNombreYClave(String nombre, String clave){
        for (Usuario usuario : listarUsuario()) {
            /*Aquí usamos una función especial del PasswordEncoder, ya que como la clave está
            encriptada, no es posible que lo comparemos solo con equals lo estaríamos comparando
            con la clave encriptada, y entonces nunca coincidirá*/
            //Entonces, si el nombre ingresado es igual al registrado
            //Y la clave coincide entonces, devolverá ese usuario
            if (usuario.getNombreUsuario().equals(nombre) && passwordEncoder.matches(clave, usuario.getContrasena())) {
                return usuario;
            }
        }
        return null;
    }

    public void guardarUsuario(Usuario usuario){
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contraseñaEncriptada);
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
