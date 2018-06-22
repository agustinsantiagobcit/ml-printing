package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "SAAM2_PARAMETRO")
public class Parameter extends BaseEntity implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean multiple;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 200)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 1000)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "MULTIPLE")
    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }
}
