package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_CAMPO_FILENET")
public class FileNetFetchField extends BaseEntity implements Serializable {


    private Long id;
    private FileNetFetchStep fileNetFetchStep;
    private Long filenetStepId;
    private ExtractionField extractionField;
    private Long extractionFieldId;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPO_FILENET", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "PASO_FILENET_ID")
    public FileNetFetchStep getFileNetFetchStep() {
        return fileNetFetchStep;
    }

    public void setFileNetFetchStep(FileNetFetchStep fileNetFetchStep) {
        this.fileNetFetchStep = fileNetFetchStep;
    }

    @Basic
    @Column(name = "PASO_FILENET_ID", nullable = true, precision = 0)
    public Long getFilenetStepId() {
        return filenetStepId;
    }

    public void setFilenetStepId(Long filenetStepId) {
        this.filenetStepId = filenetStepId;
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
