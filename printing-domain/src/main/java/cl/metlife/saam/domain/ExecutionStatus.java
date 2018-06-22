package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_ESTADO_EJECUCION")
public class ExecutionStatus extends BaseEntity implements Serializable{


    static public final Long EXECUTION_STAUS_PRE_INIT = 1L;//	Pre Inicio
    static public final Long EXECUTION_STAUS_INIT = 2L;//	Iniciado
    static public final Long EXECUTION_STAUS_PAUSED = 3L;//	Pausado
    static public final Long EXECUTION_STAUS_CANCELED = 4L;//	Cancelado
    static public final Long EXECUTION_STAUS_WAITING_SUBPROCESS = 5L;//	Esperando Subproceso
    static public final Long EXECUTION_STAUS_ERROR = 6L;//	Error
    static public final Long EXECUTION_STAUS_FINALIZED = 7L;//	Finalizado



    private Long id;
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_EJECUCION", nullable = false)
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

    @Transient
    public String getPercentaje(){
        if(EXECUTION_STAUS_PRE_INIT.equals(getId()))
            return "0%";
        if(EXECUTION_STAUS_INIT.equals(getId()))
            return "5%";
        if(EXECUTION_STAUS_PAUSED.equals(getId()))
            return "Pausado";
        if(EXECUTION_STAUS_CANCELED.equals(getId()))
            return "Cancelado";
        if(EXECUTION_STAUS_ERROR.equals(getId()))
            return "Error";
        if(EXECUTION_STAUS_FINALIZED.equals(getId()))
            return "100%";

        return "--";
    }

}
