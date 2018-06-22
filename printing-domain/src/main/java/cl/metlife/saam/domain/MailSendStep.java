package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_MAIL_SEND)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_ENVIO_MAIL")
public class MailSendStep extends Step implements Serializable{

    private String template;
    private String businessLine;
    private String tipoDocumento;
    private String encriptaPdf;
    private List<ExtraData> parametros;
    private ExtractionField fileAgrupatorExtractionField;
    private Long fileAgrupatorExtractionFieldId;

    @Basic
    @Column(name = "PLANTILLA", nullable = true, length = 20)
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Basic
    @Column(name = "LINEA_NEGOCIO", nullable = true, length = 20)
    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    @Basic
    @Column(name = "TIPO_DOCUMENTO", nullable = true, length = 20)
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Basic
    @Column(name = "ENCRIPTA_PDF", nullable = true, length = 1, columnDefinition = "char(1) default '0'")
    public String getEncriptaPdf() {
        return encriptaPdf;
    }

    public void setEncriptaPdf(String encriptaPdf) {
        this.encriptaPdf = encriptaPdf;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ENVIO_MAIL_ID")
    public List<ExtraData> getParametros() {
        return parametros;
    }

    public void setParametros(List<ExtraData> parametros) {
        this.parametros = parametros;
    }

    @Transient
    public boolean isEncripta() {
        return !getEncriptaPdf().equals("0");
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

}
