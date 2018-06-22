package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "SAAM2_ELEMENTO_CELDA")
public class TableCell extends BaseEntity implements Serializable {
    private Long id;
    private String texto;
    private int orden;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = true, precision = 0)
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

    @Basic
    @Column(name = "TEXTO", nullable = true, length = 1000)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
