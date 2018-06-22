package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_FILE_FETCH)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_RECUP_ARCHIVO")
public class FileFetchStep extends Step implements Serializable{


    private Long fileFetchTypeId;
    private FileFetchType fileFetchType;
    private String smbHost;
    private String smbUser;
    private String smbPass;
    private Boolean fetchWithSMB;
    private List<FileFetchMapping> mappings;

    private ExtractionField fileAgrupatorExtractionField;
    private Long fileAgrupatorExtractionFieldId;

    private ExtractionField fileNameExtractionField;
    private Long fileNameExtractionFieldId;



    @Basic
    @Column(name = "TIPO_PASO_ID", nullable = true)
    public Long getFileFetchTypeId() {
        return fileFetchTypeId;
    }

    public void setFileFetchTypeId(Long fileFetchTypeId) {
        this.fileFetchTypeId = fileFetchTypeId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TIPO_PASO_ID")
    public FileFetchType getFileFetchType() {
        return fileFetchType;
    }

    public void setFileFetchType(FileFetchType fileFetchType) {
        this.fileFetchType = fileFetchType;
    }

    @Basic
    @Column(name = "SMB_HOST", nullable = true, length = 255)
    public String getSmbHost() {
        return smbHost;
    }

    public void setSmbHost(String smbHost) {
        this.smbHost = smbHost;
    }

    @Basic
    @Column(name = "SMB_USER", nullable = true, length = 255)
    public String getSmbUser() {
        return smbUser;
    }

    public void setSmbUser(String smbUser) {
        this.smbUser = smbUser;
    }

    @Basic
    @Column(name = "SMB_PASS", nullable = true, length = 255)
    public String getSmbPass() {
        return smbPass;
    }

    public void setSmbPass(String smbPass) {
        this.smbPass = smbPass;
    }

    @Basic
    @Column(name ="USA_SMB")
    public Boolean getFetchWithSMB() {
        return fetchWithSMB;
    }

    public void setFetchWithSMB(Boolean fetchWithSMB) {
        this.fetchWithSMB = fetchWithSMB;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASO_ID")
    public List<FileFetchMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<FileFetchMapping> mappingList) {
        this.mappings = mappingList;
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
    @JoinColumn(name = "CAMPO_EXTRACCION_NOMARCH_ID")
    public ExtractionField getFileNameExtractionField() {
        return fileNameExtractionField;
    }

    public void setFileNameExtractionField(ExtractionField fileNameExtractionField) {
        this.fileNameExtractionField = fileNameExtractionField;
    }


    @Basic
    @Column(name = "CAMPO_EXTRACCION_NOMARCH_ID")
    public Long getFileNameExtractionFieldId() {
        return fileNameExtractionFieldId;
    }

    public void setFileNameExtractionFieldId(Long fileNameExtractionFieldId) {
        this.fileNameExtractionFieldId = fileNameExtractionFieldId;
    }
}
