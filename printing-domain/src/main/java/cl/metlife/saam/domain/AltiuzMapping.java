package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_MAPEO_ALTIUZ")
public class AltiuzMapping extends BaseEntity implements Serializable{
    private Long id;
    private Long extractionFieldId;
    private Long altiuzFieldId;
    private Long stepId;

    private ExtractionField extractionField;
    private AltiuzField altiuzField;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPEO_ALTIUZ", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "CAMPO_ALTIUZ_ID", nullable = true, precision = 0)
    public Long getAltiuzFieldId() {
        return altiuzFieldId;
    }

    public void setAltiuzFieldId(Long campoAltiuzId) {
        this.altiuzFieldId = campoAltiuzId;
    }

    @Basic
    @Column(name = "PASO_ID", nullable = true, precision = 0)
    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long pasoId) {
        this.stepId = pasoId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getExtractionField() {
        return extractionField;
    }

    public void setExtractionField(ExtractionField extractionField) {
        this.extractionField = extractionField;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_ALTIUZ_ID")
    public AltiuzField getAltiuzField() {
        return altiuzField;
    }

    public void setAltiuzField(AltiuzField altiuzField) {
        this.altiuzField = altiuzField;
    }
}
