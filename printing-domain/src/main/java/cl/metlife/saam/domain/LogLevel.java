package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_TIPO_BITACORA")
public class LogLevel extends BaseEntity implements Serializable {

    static public final Long LOG_LEVEL_TRACE = 1L;
    static public final Long LOG_LEVEL_INFO = 2L;
    static public final Long LOG_LEVEL_WARNING = 3L;
    static public final Long LOG_LEVEL_ERROR = 4L;
    static public final Long LOG_LEVEL_FATAL = 5L;


    private Long id;
    private String name;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_BITACORA", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

}
