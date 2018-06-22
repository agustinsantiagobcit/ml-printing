package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_EXTRACTION)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_EXTRACCION")
public class ExtractionStep extends Step implements Serializable{

    private Long extractionId;
    private Extraction extraction;

    @Basic
    @Column(name = "EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionId() {
        return extractionId;
    }

    public void setExtractionId(Long extractionId) {
        this.extractionId = extractionId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "EXTRACCION_ID")
    public Extraction getExtraction() {
        return extraction;
    }

    public void setExtraction(Extraction extraction) {
        this.extraction = extraction;
    }

}
