package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@javax.persistence.Table(name = "SAAM2_ELEMENTO_FILA")
public class TableRow extends BaseEntity implements Serializable {
    private Long id;
    private String nombre;
    private int orden;
    private List<TableCell> cells;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    @JoinColumn(name = "FILA_ID")
    public List<TableCell> getCells() {
        return cells;
    }

    public void setCells(List<TableCell> celdas) {
        this.cells = celdas;
    }

    @Transient
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
