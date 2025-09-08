package org.flim.gestion_proyectos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name="Tareas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTarea")
    private Integer idTarea;
    private String nombreTarea;
    private String descripcionTarea;
    private Date fechaInicio;
    private Date fechaFin;
    @ManyToOne
    @JoinColumn(name = "idFicha", referencedColumnName = "idFicha")
    private Ficha ficha;

}
