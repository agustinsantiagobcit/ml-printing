package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue(StepType.STEP_TYPE_EXTRACTION_QUERY)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_EJECUCION_QUERY")
public class ExecuteQueryStep extends Step implements Serializable {

    private Long extractionId;
    private SQLExtraction extraction;
    private List<ExecuteQueryMap> executeQueryMaps;

    @Basic
    @JoinColumn(name = "EXTRACTION_ID")
    public Long getExtractionId() {
        return extractionId;
    }

    public void setExtractionId(Long extraction_id) {
        this.extractionId = extraction_id;
    }

    @ManyToOne
    @JoinColumn(name = "EXTRACTION_ID")
    public SQLExtraction getExtraction() {
        return extraction;
    }

    public void setExtraction(SQLExtraction extraction) {
        this.extraction = extraction;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EJECUCION_QUERY_ID")
    public List<ExecuteQueryMap> getExecuteQueryMaps() {
        return executeQueryMaps;
    }

    public void setExecuteQueryMaps(List<ExecuteQueryMap> executeQueryMaps) {
        this.executeQueryMaps = executeQueryMaps;
    }
}
