package ar.com.jg.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "clientes")
@AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
@Getter @Setter//(value = AccessLevel.PRIVATE)
//@NoArgsConstructor @RequiredArgsConstructor //@AllArgsConstructor //(staticName = "createCliente") //(access = AccessLevel.PRIVATE)
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString(callSuper = true, includeFieldNames = false, of = {"razonSocial", "cuit"})//(exclude = {"servicios", "reportesIncidencia"})
@EqualsAndHashCode(callSuper = true)
public class Cliente extends EntidadId{


    @Column(name = "razon_social", nullable = false)
    @NonNull
    @EqualsAndHashCode.Exclude
    private String razonSocial;
    @Column(nullable = false, unique = true) //nullable y unique juntas hacen que se comporte como si fuera una PK
    @EqualsAndHashCode.Exclude
    private Long cuit;
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Servicio.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cliente_servicio", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_servicio"), uniqueConstraints = @UniqueConstraint(columnNames = {"id_cliente", "id_servicio"}))
    @EqualsAndHashCode.Exclude
    private List<Servicio> servicios; //Many2Many
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<ReporteIncidencia> reportesIncidencia; //One2Many
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_datos_contacto", nullable = false)
    @EqualsAndHashCode.Exclude
    private DatosContacto datosContacto; //One2One

    public Cliente() {

        this.servicios = new ArrayList<>();
        this.reportesIncidencia = new ArrayList<>();

    }

    public Cliente(@NonNull String razonSocial, Long cuit) {

        this();
        this.razonSocial = razonSocial;
        this.cuit = cuit;

    }



    public Cliente addReporteIncidencia(ReporteIncidencia reporte){

        this.reportesIncidencia.add(reporte);
        reporte.setCliente(this);
        return this;

    }

    public Cliente removeReporteIncidencia(ReporteIncidencia reporte){

        this.reportesIncidencia.remove(reporte);
        reporte.setCliente(null);
        return this;

    }

    public Cliente addServicio(Servicio servicio){

        this.servicios.add(servicio);
        servicio.getClientes().add(this);
        return this;

    }

    public Cliente removeServicio(Servicio servicio){

        this.servicios.remove(servicio);
        servicio.getClientes().remove(this);
        return this;

    }

}
