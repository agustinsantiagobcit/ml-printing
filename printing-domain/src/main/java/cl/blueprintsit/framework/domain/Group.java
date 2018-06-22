package cl.blueprintsit.framework.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "SAAM2_GRUPO")
public class Group implements Serializable{
    private long id;
    private String name;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

}
