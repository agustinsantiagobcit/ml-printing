package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_ALTIUZ_GENERATIION)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_ALTIUZ")
public class AltiuzStep extends Step implements Serializable{

    private Long templateId;
    private AltiuzTemplate template;
    private List<AltiuzMapping> mappings;

    private ExtractionField fileAgrupatorExtractionField;
    private Long fileAgrupatorExtractionFieldId;

    private ExtractionField fileNameExtractionField;
    private Long fileNameExtractionFieldId;

    @Basic
    @Column(name = "PLANTILLA_ALTIUZ_ID")
    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long plantillaAltiuzId) {
        this.templateId = plantillaAltiuzId;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLANTILLA_ALTIUZ_ID")
    public AltiuzTemplate getTemplate() {
        return template;
    }

    public void setTemplate(AltiuzTemplate altiuzTemplate) {
        this.template = altiuzTemplate;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASO_ID")
    public List<AltiuzMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<AltiuzMapping> mappings) {
        this.mappings = mappings;
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


    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_NOMBRE_ID")
    public ExtractionField getFileNameExtractionField() {
        return fileNameExtractionField;
    }

    public void setFileNameExtractionField(ExtractionField fileNameExtractionField) {
        this.fileNameExtractionField = fileNameExtractionField;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_NOMBRE_ID")
    public Long getFileNameExtractionFieldId() {
        return fileNameExtractionFieldId;
    }

    public void setFileNameExtractionFieldId(Long fileNameExtractionFieldId) {
        this.fileNameExtractionFieldId = fileNameExtractionFieldId;
    }
}
