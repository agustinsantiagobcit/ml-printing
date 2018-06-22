package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_DATOS_EXTRA")
public class ExtraData extends BaseEntity implements Serializable {

    public static final String REQ_PARAMETER_NAME = "Nombre destinatario";
    public static final String REQ_PARAMETER_CI = "RUT destinatario";
    public static final String REQ_PARAMETER_MAIL = "Mail destinatario";
    public static final String REQ_PARAMETER_DATE = "Fecha inicio vigencia";
    public static final String REQ_PARAMETER_POLICY = "Numero poliza";

    private Long id;
    private MailSendStep mailSendStep;
    private Long mailSendStepId;
    private ExtractionField extractionField;
    private Long extractionFieldId;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DATOS_EXTRA", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ENVIO_MAIL_ID")
    public MailSendStep getMailSendStep() {
        return mailSendStep;
    }

    public void setMailSendStep(MailSendStep mailSendStep) {
        this.mailSendStep = mailSendStep;
    }

    @Basic
    @Column(name = "ENVIO_MAIL_ID", nullable = true, precision = 0)
    public Long getMailSendStepId() {
        return mailSendStepId;
    }

    public void setMailSendStepId(Long mailSendStepId) {
        this.mailSendStepId = mailSendStepId;
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
