package org.flim.gestion_proyectos.consola;

/*
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
*/

public class CrudUsuarioConsola{
/*
public class CrudUsuarioConsola implements CommandLineRunner {

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Gestión de Usuarios ---");
            System.out.println("1. Ver todos los usuarios");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Crear nuevo usuario");
            System.out.println("4. Actualizar usuario (por ID)");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        listarUsuarios();
                        break;
                    case 2:
                        buscarUsuario();
                        break;
                    case 3:
                        crearUsuario(scanner);
                        break;
                    case 4:
                        actualizarUsuario(scanner);
                        break;
                    case 5:
                        eliminarUsuario(scanner);
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
        scanner.close();
        System.out.println("Aplicación terminada.");
    }

    private void listarUsuarios() {
        System.out.println("\n--- Lista de Usuarios ---");
        List<Usuario> usuarios = usuarioService.listarUsuario();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getIdUsuario() + ", Nombre: " + usuario.getNombreUsuario() + ", Rol: " + usuario.getRol());
            }
        }
    }

    private void buscarUsuario() {
        System.out.print("Ingrese el ID del usuario a buscar: ");
        Scanner tempScanner = new Scanner(System.in);
        try {
            int id = tempScanner.nextInt();
            Usuario usuario = usuarioService.buscarUsuarioporId(id);
            if (usuario != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + usuario.getIdUsuario());
                System.out.println("Nombre: " + usuario.getNombreUsuario());
                System.out.println("Rol: " + usuario.getRol());
                System.out.println("Correo: " + usuario.getCorreo());
            } else {
                System.out.println("Usuario con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            tempScanner.nextLine();
        }
    }

    private void crearUsuario(Scanner scanner) {
        System.out.println("\n--- Crear Nuevo Usuario ---");
        Usuario nuevoUsuario = new Usuario();
        System.out.print("Nombre de usuario: ");
        nuevoUsuario.setNombreUsuario(scanner.nextLine());
        System.out.print("Rol: ");
        nuevoUsuario.setRol(scanner.nextLine());
        System.out.print("Correo: ");
        nuevoUsuario.setCorreo(scanner.nextLine());
        System.out.print("Contraseña: ");
        nuevoUsuario.setContraseña(scanner.nextLine());

        usuarioService.guardarUsuario(nuevoUsuario);
        System.out.println("Usuario creado exitosamente.");
    }

    private void actualizarUsuario(Scanner scanner) {
        System.out.println("\n--- Actualizar Usuario ---");
        System.out.print("Ingrese el ID del usuario a actualizar: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            Usuario usuarioExistente = usuarioService.buscarUsuarioporId(id);

            if (usuarioExistente != null) {
                System.out.print("Nuevo nombre de usuario (deje en blanco para no cambiar): ");
                String nuevoNombre = scanner.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    usuarioExistente.setNombreUsuario(nuevoNombre);
                }

                System.out.print("Nuevo rol (deje en blanco para no cambiar): ");
                String nuevoRol = scanner.nextLine();
                if (!nuevoRol.isEmpty()) {
                    usuarioExistente.setRol(nuevoRol);
                }

                System.out.print("Nuevo correo (deje en blanco para no cambiar): ");
                String nuevoCorreo = scanner.nextLine();
                if (!nuevoCorreo.isEmpty()) {
                    usuarioExistente.setCorreo(nuevoCorreo);
                }

                System.out.print("Nueva contraseña (deje en blanco para no cambiar): ");
                String nuevaContraseña = scanner.nextLine();
                if (!nuevaContraseña.isEmpty()) {
                    usuarioExistente.setContraseña(nuevaContraseña);
                }

                usuarioService.guardarUsuario(usuarioExistente);
                System.out.println("Usuario actualizado exitosamente.");
            } else {
                System.out.println("Usuario con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            scanner.nextLine();
        }
    }

    private void eliminarUsuario(Scanner scanner) {
        System.out.println("\n--- Eliminar Usuario ---");
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        try {
            int id = scanner.nextInt();
            Usuario usuario = usuarioService.buscarUsuarioporId(id);
            if (usuario != null) {
                System.out.print("¿Está seguro de que desea eliminar al usuario " + usuario.getNombreUsuario() + "? (s/n): ");
                String confirmacion = scanner.next();
                if (confirmacion.equalsIgnoreCase("s")) {
                    usuarioService.eliminarUsuario(usuario);
                    System.out.println("Usuario eliminado exitosamente.");
                } else {
                    System.out.println("Operación cancelada.");
                }
            } else {
                System.out.println("Usuario con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            scanner.nextLine();
        }
    }

     */
}