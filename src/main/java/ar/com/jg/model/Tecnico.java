package ar.com.jg.model;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tecnicos")
@AttributeOverride(name = "id", column = @Column(name = "id_tecnico"))
//@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Tecnico extends Empleado{

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Especialidad.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tecnico_especialidad", joinColumns = @JoinColumn(name = "id_tecnico"), inverseJoinColumns = @JoinColumn(name = "id_especialidad"), uniqueConstraints = @UniqueConstraint(columnNames = {"id_tecnico", "id_especialidad"}))
    @EqualsAndHashCode.Exclude
    private List<Especialidad> especialidades; //Many2Many
    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<ReporteIncidencia> reportesIncidencia; //One2Many

    public Tecnico() {

        this.especialidades = new ArrayList<>();
        this.reportesIncidencia = new ArrayList<>();

    }

    public Tecnico(Integer legajo, @NonNull String nombre, @NonNull String apellido, @NonNull String usuario, @NonNull String password) {

        this();
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;

    }

    public Tecnico addReporteIncidencia(ReporteIncidencia reporte){

        this.reportesIncidencia.add(reporte);
        reporte.setTecnico(this);
        return this;

    }

    public Tecnico removeReporteIncidencia(ReporteIncidencia reporte){

        this.reportesIncidencia.remove(reporte);
        reporte.setTecnico(null);
        return this;

    }

    public Tecnico addEspecialidad(Especialidad especialidad){

        if(!this.especialidades.contains(especialidad)) {

            this.especialidades.add(especialidad);
            especialidad.getTecnicos().add(this);

        }else{

            JOptionPane.showMessageDialog(null, "La Especialidad ya fue agregada con anterioridad.");

        }

        return this;

    }

    public Tecnico removeEspecialidad(Especialidad especialidad){

        if(this.especialidades.contains(especialidad)) {

            this.especialidades.remove(especialidad);
            especialidad.getTecnicos().remove(this);

        }else{

            JOptionPane.showMessageDialog(null, "La Especialidad ya fue quitada con anterioridad.");

        }
        return this;

    }

}
