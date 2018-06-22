package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_CAMPO_ALTIUZ")
public class AltiuzField extends BaseEntity implements Serializable {
    private Long id;
    private Long templateId;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPO_ALTIUZ", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PLANTILLA_ALTIUZ_ID", nullable = true, precision = 0)
    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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
