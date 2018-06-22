package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_CAMPO_SMS")
public class SMSField extends BaseEntity implements Serializable {


    private Long id;
    private SMSSendStep smsSendStep;
    private Long smsSendStepId;
    private ExtractionField extractionField;
    private Long extractionFieldId;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPO_SMS", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ENVIO_SMS_ID")
    public SMSSendStep getSmsSendStep() {
        return smsSendStep;
    }

    public void setSmsSendStep(SMSSendStep smsSendStep) {
        this.smsSendStep = smsSendStep;
    }

    @Basic
    @Column(name = "ENVIO_SMS_ID", nullable = true, precision = 0)
    public Long getSmsSendStepId() {
        return smsSendStepId;
    }

    public void setSmsSendStepId(Long smsSendStepId) {
        this.smsSendStepId = smsSendStepId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getExtractionField() {
        return extractionField;
    }

    public void setExtractionField(ExtractionField extractionField) {
        this.extractionField = extractionField;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionFieldId() {
        return extractionFieldId;
    }

    public void setExtractionFieldId(Long extractionFieldId) {
        this.extractionFieldId = extractionFieldId;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

}
