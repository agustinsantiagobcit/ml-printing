package cl.blueprintsit.framework.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_CONFIGURACION")
public class Configuration extends BaseEntity implements Serializable {
    private Long id;
    private String llave;
    private String valor;
    private String descripcion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONFIGURACION", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LLAVE", nullable = true, length = 255)
    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    @Basic
    @Column(name = "VALOR", nullable = true, length = 255)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 1000)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
