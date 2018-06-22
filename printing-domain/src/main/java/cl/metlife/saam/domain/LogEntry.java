package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_BITACORA")
public class LogEntry extends BaseEntity implements Serializable {
    private Long id;
    private Long processExecutionId;
    private Long logLeveld;
    private LogLevel logLevel;
    private Long stepId;
    private Step step;
    private String message;
    private Date timestamp;
    private String stackTrace;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BITACORA", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EJECUCION_PROCESO_ID", nullable = true, precision = 0)
    public Long getProcessExecutionId() {
        return processExecutionId;
    }

    public void setProcessExecutionId(Long ejecucionProcesoId) {
        this.processExecutionId = ejecucionProcesoId;
    }

    @Basic
    @Column(name = "TIPO_BITACORA_ID", nullable = true, precision = 0)
    public Long getLogLeveld() {
        return logLeveld;
    }

    public void setLogLeveld(Long tipoBitacoraId) {
        this.logLeveld = tipoBitacoraId;
    }

    @ManyToOne
    @JoinColumn(name = "TIPO_BITACORA_ID")
    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Basic
    @Column(name = "PASO_ID", nullable = true, precision = 0)
    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long pasoId) {
        this.stepId = pasoId;
    }

    @ManyToOne
    @JoinColumn(name = "PASO_ID")
    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String descripcion) {
        this.message = descripcion;
    }

    @Basic
    @Column(name = "FECHA", nullable = true)
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date fecha) {
        this.timestamp = fecha;
    }

    @Basic
    @Column(name = "TRAZA")
    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Transient
    public String getIcon(){
        switch (getLogLeveld().intValue()){
            case 1:
                return "fa-flag";
            case 2:
                return "fa-info-circle";
            case 3:
                return "fa-warning";
            case 4:
                return "fa-times-circle";
            case 5:
                return "fa-times-circle";
            default:
                return "fa-info";

        }

    }

    @Transient
    public String getColor(){
        switch (getLogLeveld().intValue()) {
            case 1:
                return "Gray";
            case 2:
                return "Blue";
            case 3:
                return "Orange";
            case 4:
                return "Red";
            case 5:
                return "Pink";
            default:
                return "Gray";
        }

    }
}
