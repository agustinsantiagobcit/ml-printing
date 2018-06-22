package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_MAPEO_OD")
public class ODMapping  extends BaseEntity implements Serializable {
    private Long id;
    private Long odFieldId;
    private Long extractionFieldId;
    private Long pasoId;
    private ODField odField;
    private ExtractionField extractionField;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPEO_OD", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CAMPO_OD_ID", nullable = true, precision = 0)
    public Long getOdFieldId() {
        return odFieldId;
    }

    public void setOdFieldId(Long campoOdId) {
        this.odFieldId = campoOdId;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionFieldId() {
        return extractionFieldId;
    }

    public void setExtractionFieldId(Long campoExtraccionId) {
        this.extractionFieldId = campoExtraccionId;
    }

    @Basic
    @Column(name = "PASO_ID", nullable = true, precision = 0)
    public Long getPasoId() {
        return pasoId;
    }

    public void setPasoId(Long pasoId) {
        this.pasoId = pasoId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_OD_ID")
    public ODField getOdField() {
        return odField;
    }

    public void setOdField(ODField odField) {
        this.odField = odField;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getExtractionField() {
        return extractionField;
    }

    public void setExtractionField(ExtractionField extractionField) {
        this.extractionField = extractionField;
    }
}
