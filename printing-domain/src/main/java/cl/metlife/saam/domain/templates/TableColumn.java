package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "SAAM2_ELEMENTO_COLUMNA_TABLA")
public class TableColumn extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private int orden;

    private static final long serialVersionUID = 6939530744756159455L;

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
    @Column(name = "ORDEN")
    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}




