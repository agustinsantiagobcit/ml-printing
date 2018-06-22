package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_SUB_EXTRACCION")
public class SubExtraction extends BaseEntity implements Serializable {
    private Long id;
    private Extraction parentExtraction;
    private Long parentExtractionId;
    private Extraction childExtraction;
    private Long childExtractionId;
    private List<SubExtractionMapping> mappings;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUB_EXTRACCION", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EXTRACCION_ID_PADRE", nullable = true, precision = 0)
    public Long getParentExtractionId() {
        return parentExtractionId;
    }

    public void setParentExtractionId(Long parentExtractionId) {
        this.parentExtractionId = parentExtractionId;
    }

    @ManyToOne
    @JoinColumn(name = "EXTRACCION_ID_PADRE", referencedColumnName = "ID_EXTRACCION", nullable = false)
    public Extraction getParentExtraction() {
        return parentExtraction;
    }

    public void setParentExtraction(Extraction sqlExtractor) {
        this.parentExtraction = sqlExtractor;
    }

    @Basic
    @Column(name = "EXTRACCION_ID_HIJO", nullable = true, precision = 0)
    public Long getChildExtractionId() {
        return childExtractionId;
    }

    public void setChildExtractionId(Long childExtractionId) {
        this.childExtractionId = childExtractionId;
    }

    @ManyToOne
    @JoinColumn(name = "EXTRACCION_ID_HIJO", referencedColumnName = "ID_EXTRACCION", nullable = false)
    public Extraction getChildExtraction() {
        return childExtraction;
    }

    public void setChildExtraction(Extraction sqlExtractor) {
        this.childExtraction = sqlExtractor;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "SUB_EXTRACCION_ID")
    public List<SubExtractionMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<SubExtractionMapping> mappings) {
        this.mappings = mappings;
    }
}
