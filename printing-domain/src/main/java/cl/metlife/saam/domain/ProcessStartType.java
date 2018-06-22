package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_TIPO_INICIO")
public class ProcessStartType extends BaseEntity implements Serializable{

    static final public Long PROCESS_START_TYPE_MANUAL = 1L;//	Manual
    static final public Long PROCESS_START_TYPE_SCHEDULED = 2L;//	Calendarizado
    static final public Long PROCESS_START_TYPE_AS_SUBPROCESS = 3L;//	Como Subproceso
    static final public Long PROCESS_START_TYPE_BY_SERVICE = 4L;//	Por Servicio


    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_INICIO", nullable = false, precision = 0)
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
