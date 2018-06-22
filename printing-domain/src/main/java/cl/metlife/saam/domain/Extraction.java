package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_EXTRACCION_ID",discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "SAAM2_EXTRACCION")
public abstract class Extraction extends BaseEntity implements Serializable{

    private Long id;
    private Long extractionTypeId;
    private String name;
    private ExtractionType extractionType;
    private List<QueryParameter> parameters;
    private List<ExtractionField> fields;
    private List<SubExtraction> subExtractions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXTRACCION", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIPO_EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionTypeId() {
        return extractionTypeId;
    }

    public void setExtractionTypeId(Long extractionTypeId) {
        this.extractionTypeId = extractionTypeId;
}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TIPO_EXTRACCION_ID")
    public ExtractionType getExtractionType() {
        return extractionType;
    }

    public void setExtractionType(ExtractionType extractionType) {
        this.extractionType = extractionType;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EXTRACCION_ID")
    public List<QueryParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<QueryParameter> parameters) {
        this.parameters = parameters;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EXTRACCION_ID")
    public List<ExtractionField> getFields() {
        return fields;
    }

    public void setFields(List<ExtractionField> fields) {
        this.fields = fields;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentExtraction" , cascade = CascadeType.ALL)
    public List<SubExtraction> getSubExtractions() {
        return subExtractions;
    }

    public void setSubExtractions(List<SubExtraction> subExtractions) {
        this.subExtractions = subExtractions;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
