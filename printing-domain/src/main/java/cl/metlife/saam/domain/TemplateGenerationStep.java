package cl.metlife.saam.domain;

import cl.metlife.saam.domain.templates.Template;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_TEMPLATE_GENERATION)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_GENERICO")
public class TemplateGenerationStep extends Step implements Serializable{

    private Long templateId;
    private Template template;
    private Long fileAgrupatorExtractionFieldId;
    private ExtractionField fileAgrupatorExtractionField;
    private List<TemplateMapping> mappings;

    @Basic
    @Column(name = "PLANTILLA_ID")
    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLANTILLA_ID")
    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_AGRUPADOR_ID")
    public ExtractionField getFileAgrupatorExtractionField() {
        return fileAgrupatorExtractionField;
    }

    public void setFileAgrupatorExtractionField(ExtractionField fileAgrupatorExtractionField) {
        this.fileAgrupatorExtractionField = fileAgrupatorExtractionField;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_AGRUPADOR_ID")
    public Long getFileAgrupatorExtractionFieldId() {
        return fileAgrupatorExtractionFieldId;
    }

    public void setFileAgrupatorExtractionFieldId(Long fileAgrupatorExtractionFieldId) {
        this.fileAgrupatorExtractionFieldId = fileAgrupatorExtractionFieldId;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASO_ID")
    public List<TemplateMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<TemplateMapping> mappings) {
        this.mappings = mappings;
    }


}
