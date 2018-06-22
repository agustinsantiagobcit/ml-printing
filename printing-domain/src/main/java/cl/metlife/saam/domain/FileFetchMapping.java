package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_MAPEO_PASO_RECUP_ARCHIVO")
public class FileFetchMapping extends BaseEntity implements Serializable {
    private Long id;
    private String nombreImt;
    private String ruta;
    private Long pasoId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPEO", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE_IMT", nullable = true, precision = 0)
    public String getNombreImt() {
        return nombreImt;
    }

    public void setNombreImt(String nombreImt) {
        this.nombreImt = nombreImt;
    }

    @Basic
    @Column(name = "RUTA", nullable = true, precision = 0)
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Basic
    @Column(name = "PASO_ID", nullable = true, precision = 0)
    public Long getPasoId() {
        return pasoId;
    }

    public void setPasoId(Long pasoId) {
        this.pasoId = pasoId;
    }

}
