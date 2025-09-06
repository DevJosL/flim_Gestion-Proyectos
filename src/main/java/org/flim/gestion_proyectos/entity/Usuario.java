package org.flim.gestion_proyectos.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

// Definimos el nombre de la entidad igual a como esta en la base de datos
@Entity(name = "Usuarios")

//Lombok
// Generamos los setters y getters'
@Data

// Generamos el constructor vacio
@NoArgsConstructor

// Generamos el constructor lleno
@ToString

//Metodo para trabajar con HashCode | Id Interno
@EqualsAndHashCode

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    private String nombreUsuario;
    private String rol;
    private String correo;
    private String contraseña;
}