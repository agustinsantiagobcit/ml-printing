package cl.metlife.saam.domain;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SAAM2_CAMPO_CSV_OUT")
public class CSVOutField extends BaseEntity implements Serializable{

    private Long id;
    private Long csvOutStepId;
    private String nombre;
    private Long extractionFieldId;


    private ExtractionField extractionField;
    private CSVOutStep csvOutStep;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPO_CSV_OUT", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CSV_OUT_ID", nullable = true, precision = 0)
    public Long getCsvOutStepId() {
        return csvOutStepId;
    }

    public void setCsvOutStepId(Long csvOutStepId) {
        this.csvOutStepId = csvOutStepId;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, precision = 0)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getExtractionField() {
        return extractionField;
    }

    public void setExtractionField(ExtractionField extractionField) {
        this.extractionField = extractionField;
    }

    @ManyToOne
    @JoinColumn(name = "CSV_OUT_ID")
    public CSVOutStep getCsvOutStep() {
        return csvOutStep;
    }

    public void setCsvOutStep(CSVOutStep csvOutStep) {
        this.csvOutStep = csvOutStep;
    }
}
