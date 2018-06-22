package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_FILENET_FETCH)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_FILENET")
public class FileNetFetchStep extends Step implements Serializable{

    private ExtractionField fileAgrupatorExtractionField;
    private Long fileAgrupatorExtractionFieldId;

    private List<FileNetFetchField> parameters;

    private ExtractionField fileNameExtractionField;
    private Long fileNameExtractionFieldId;

    private String applicationId;

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
    @JoinColumn(name = "PASO_FILENET_ID")
    public List<FileNetFetchField> getParameters() {
        return parameters;
    }

    public void setParameters(List<FileNetFetchField> parametros) {
        this.parameters = parametros;
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

    @Basic
    @Column(name = "APPLICATION_ID")
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
