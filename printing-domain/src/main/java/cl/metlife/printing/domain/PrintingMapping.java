package cl.metlife.printing.domain;

import cl.metlife.saam.domain.BaseEntity;
import cl.metlife.saam.domain.ExtractionField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SAAM2_MAPEO_PASO_PRINTING")
public class PrintingMapping extends BaseEntity implements Serializable {

    private Long id;
    private Long templateParameterId;
    private Long extractionFieldId;


    private cl.metlife.saam.domain.templates.Parameter parameter;
    private ExtractionField extractionField;

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
    @Column(name = "PARAMETRO_PLANTILLA_ID", nullable = true, precision = 0)
    public Long getTemplateParameterId() {
        return templateParameterId;
    }

    public void setTemplateParameterId(Long parameterTemplateId) {
        this.templateParameterId = parameterTemplateId;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionFieldId() {
        return extractionFieldId;
    }

    public void setExtractionFieldId(Long extractionFieldId) {
        this.extractionFieldId = extractionFieldId;
    }

    @ManyToOne
    @JoinColumn(name = "PARAMETRO_PLANTILLA_ID")
    public cl.metlife.saam.domain.templates.Parameter  getParameter() {
        return parameter;
    }

    public void setParameter(cl.metlife.saam.domain.templates.Parameter parameter) {
        this.parameter = parameter;
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
