package org.flim.gestion_proyectos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Fichas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFicha")
    private Integer idFicha;
    private String nombreFicha;
    @ManyToOne
    @JoinColumn(name = "idProyecto", referencedColumnName = "idProyecto")
    private Proyecto proyecto;
}
