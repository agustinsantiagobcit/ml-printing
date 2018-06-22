package cl.metlife.saam.domain;

import cl.metlife.saam.domain.templates.Parameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_MAPEO_PLANTILLA")
public class TemplateMapping extends BaseEntity implements Serializable {
    private Long id;
    private Long parameterId;
    private Long extractionFieldId;
    private Long pasoId;
    private Parameter parameter;
    private ExtractionField extractionField;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPEO_PLANTILLA", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PARAMETRO_ID", nullable = true, precision = 0)
    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    @ManyToOne
    @JoinColumn(name = "PARAMETRO_ID")
    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
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
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getExtractionField() {
        return extractionField;
    }

    public void setExtractionField(ExtractionField extractionField) {
        this.extractionField = extractionField;
    }
}
