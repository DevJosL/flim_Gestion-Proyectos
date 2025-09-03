package org.flim.gestion_proyectos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Proyectos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProyecto")
    private Integer idProyecto;

    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private String visibilidad;

}
