package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_CAMPO_EXTRACCION")
public class ExtractionField extends BaseEntity implements Serializable{
    private Long id;
    private Long extractionId;
    private String name;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPO_EXTRACCION", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EXTRACCION_ID", nullable = true, precision = 0)
    public Long getExtractionId() {
        return extractionId;
    }

    public void setExtractionId(Long extractionId) {
        this.extractionId = extractionId;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
