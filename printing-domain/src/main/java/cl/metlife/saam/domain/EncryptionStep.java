package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_ENCRYPTION)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_ENCRIPTACION")
public class EncryptionStep extends Step implements Serializable {

    private ExtractionField pdfInExtractionField;
    private Long pdfInExtractionFieldId;
    private ExtractionField pdfOutExtractionField;
    private Long pdfOutExtractionFieldId;
    private ExtractionField pass1ExtractionField;
    private Long pass1ExtractionFieldId;
    private ExtractionField pass2ExtractionField;
    private Long pass2ExtractionFieldId;
    private ExtractionField fileGroupField;
    private Long fileGroupFieldId;

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_PDF_IN_ID")
    public ExtractionField getPdfInExtractionField() {
        return pdfInExtractionField;
    }

    public void setPdfInExtractionField(ExtractionField pdfInExtractionField) {
        this.pdfInExtractionField = pdfInExtractionField;
    }

    @Basic
    @JoinColumn(name = "CAMPO_EXTRACCION_PDF_IN_ID")
    public Long getPdfInExtractionFieldId() {
        return pdfInExtractionFieldId;
    }

    public void setPdfInExtractionFieldId(Long pdfInExtractionFieldId) {
        this.pdfInExtractionFieldId = pdfInExtractionFieldId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_PDF_OUT_ID")
    public ExtractionField getPdfOutExtractionField() {
        return pdfOutExtractionField;
    }

    public void setPdfOutExtractionField(ExtractionField pdfOutExtractionField) {
        this.pdfOutExtractionField = pdfOutExtractionField;
    }

    @Basic
    @JoinColumn(name = "CAMPO_EXTRACCION_PDF_OUT_ID")
    public Long getPdfOutExtractionFieldId() {
        return pdfOutExtractionFieldId;
    }

    public void setPdfOutExtractionFieldId(Long pdfOutExtractionFieldId) {
        this.pdfOutExtractionFieldId = pdfOutExtractionFieldId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_PASS1_ID")
    public ExtractionField getPass1ExtractionField() {
        return pass1ExtractionField;
    }

    public void setPass1ExtractionField(ExtractionField pass1ExtractionField) {
        this.pass1ExtractionField = pass1ExtractionField;
    }

    @Basic
    @JoinColumn(name = "CAMPO_EXTRACCION_PASS1_ID")
    public Long getPass1ExtractionFieldId() {
        return pass1ExtractionFieldId;
    }

    public void setPass1ExtractionFieldId(Long pass1ExtractionFieldId) {
        this.pass1ExtractionFieldId = pass1ExtractionFieldId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_PASS2_ID")
    public ExtractionField getPass2ExtractionField() {
        return pass2ExtractionField;
    }

    public void setPass2ExtractionField(ExtractionField pass2ExtractionField) {
        this.pass2ExtractionField = pass2ExtractionField;
    }

    @Basic
    @JoinColumn(name = "CAMPO_EXTRACCION_PASS2_ID")
    public Long getPass2ExtractionFieldId() {
        return pass2ExtractionFieldId;
    }

    public void setPass2ExtractionFieldId(Long pass2ExtractionFieldId) {
        this.pass2ExtractionFieldId = pass2ExtractionFieldId;
    }

    @Basic
    @JoinColumn(name = "CAMPO_AGRUP_ARCHIVO")
    public Long getFileGroupFieldId() {
        return fileGroupFieldId;
    }

    public void setFileGroupFieldId(Long fieldGroupFieldId) {
        this.fileGroupFieldId = fieldGroupFieldId;
    }


    @ManyToOne
    @JoinColumn(name = "CAMPO_AGRUP_ARCHIVO")
    public ExtractionField getFileGroupField() {
        return fileGroupField;
    }

    public void setFileGroupField(ExtractionField fieldGroupField) {
        this.fileGroupField = fieldGroupField;
    }
}
