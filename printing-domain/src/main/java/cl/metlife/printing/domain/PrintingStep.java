package cl.metlife.printing.domain;

import cl.metlife.saam.domain.ExtractionField;
import cl.metlife.saam.domain.Step;
import cl.metlife.saam.domain.StepType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue(StepType.STEP_TYPE_PRINTING)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_PRINTING")
public class PrintingStep extends Step implements Serializable {

    private Long printingProcessId;

    private ExtractionField fieldRut;
    private Long fieldRutId;
    private ExtractionField fieldName;
    private Long fieldNameId;
    private ExtractionField fieldMail;
    private Long fieldMailId;
    private ExtractionField fieldPoliza;
    private Long fieldPolizaId;

    private List<PrintingMapping> mappings;
    private PrintingProcess printingProcess;


    @Basic
    @Column(name = "ID_PROCESO", nullable = true, precision = 0)
    public Long getPrintingProcessId() {
        return printingProcessId;
    }

    public void setPrintingProcessId(Long processPrintingId) {
        this.printingProcessId = processPrintingId;
    }

    @Basic
    @Column(name = "ID_CAMPO_RUT", nullable = true, precision = 0)
    public Long getFieldRutId() {
        return fieldRutId;
    }

    public void setFieldRutId(Long campoRutId) {
        this.fieldRutId = campoRutId;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CAMPO_RUT")
    public ExtractionField getFieldRut() {
        return fieldRut;
    }

    public void setFieldRut(ExtractionField fieldRut) {
        this.fieldRut = fieldRut;
    }

    @Basic
    @Column(name = "ID_CAMPO_NOMBRE", nullable = true, precision = 0)
    public Long getFieldNameId() {
        return fieldNameId;
    }

    public void setFieldNameId(Long campoNombreId) {
        this.fieldNameId = campoNombreId;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CAMPO_NOMBRE")
    public ExtractionField getFieldName() {
        return fieldName;
    }

    public void setFieldName(ExtractionField fieldName) {
        this.fieldName = fieldName;
    }

    @Basic
    @Column(name = "ID_CAMPO_MAIL", nullable = true, precision = 0)
    public Long getFieldMailId() {
        return fieldMailId;
    }

    public void setFieldMailId(Long campoMailId) {
        this.fieldMailId = campoMailId;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CAMPO_MAIL")
    public ExtractionField getFieldMail() {
        return fieldMail;
    }

    public void setFieldMail(ExtractionField fieldMail) {
        this.fieldMail = fieldMail;
    }

    @Basic
    @Column(name = "ID_CAMPO_POLIZA", nullable = true, precision = 0)
    public Long getFieldPolizaId() {
        return fieldPolizaId;
    }

    public void setFieldPolizaId(Long campoPolizaId) {
        this.fieldPolizaId = campoPolizaId;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CAMPO_POLIZA")
    public ExtractionField getFieldPoliza() {
        return fieldPoliza;
    }

    public void setFieldPoliza(ExtractionField fieldPoliza) {
        this.fieldPoliza = fieldPoliza;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASO_PRINTING_ID")
    public List<PrintingMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<PrintingMapping> mappingList) {
        this.mappings = mappingList;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PROCESO")
    public PrintingProcess getPrintingProcess() {
        return printingProcess;
    }

    public void setPrintingProcess(PrintingProcess processPrinting) {
        this.printingProcess = processPrinting;
    }
}
