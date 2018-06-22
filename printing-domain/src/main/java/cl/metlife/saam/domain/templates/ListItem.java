package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "SAAM2_ELEMENTOS_LISTA")
public class ListItem extends BaseEntity implements Serializable {

    private Long id;
    private String text;
    private Long order;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEXTO", nullable = true, length = 4000)
    public String getText() {
        return text;
    }

    public void setText(String texto) {
        this.text = texto;
    }

    @Basic
    @Column(name = "ORDEN")
    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }
}
