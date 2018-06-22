package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_PARAMETRO_CONSULTA")
public class QueryParameter extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private String defaultValue;
    private Long extractionId;
    private Extraction extraction;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PARAMETRO_CONSULTA", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Basic
    @Column(name = "VALOR_INICIAL", nullable = true, length = 255)
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String valorInicial) {
        this.defaultValue = valorInicial;
    }

    @Basic
    @Column(name = "EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionId() {
        return extractionId;
    }

    public void setExtractionId(Long extractionId) {
        this.extractionId = extractionId;
    }

    @ManyToOne
    @JoinColumn(name = "EXTRACCION_ID")
    public Extraction getExtraction() {
        return extraction;
    }

    public void setExtraction(Extraction extraction) {
        this.extraction = extraction;
    }

}
