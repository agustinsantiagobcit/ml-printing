package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_SMS_SEND)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_ENVIO_SMS")
public class SMSSendStep extends Step implements Serializable{

    private String template;
    private List<SMSField> parameters;
    private ExtractionField numberExtractionField;
    private Long numberExtractionFieldId;

    @Basic
    @Column(name = "PLANTILLA", nullable = true, length = 20)
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ENVIO_SMS_ID")
    public List<SMSField> getParameters() {
        return parameters;
    }

    public void setParameters(List<SMSField> parametros) {
        this.parameters = parametros;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getNumberExtractionField() {
        return numberExtractionField;
    }

    public void setNumberExtractionField(ExtractionField numberExtractionField) {
        this.numberExtractionField = numberExtractionField;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_ID")
    public Long getNumberExtractionFieldId() {
        return numberExtractionFieldId;
    }

    public void setNumberExtractionFieldId(Long extractionFieldId) {
        this.numberExtractionFieldId = extractionFieldId;
    }

}
