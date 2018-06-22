package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_PROCESS_CALL)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_PROCESO")
public class ProcessCallStep extends Step implements Serializable{


    private Long subProcessId;
    private Process subProcess;

    @Basic
    @Column(name = "SUBPROCESO_ID")
    public Long getSubProcessId() {
        return subProcessId;
    }

    public void setSubProcessId(Long subProcessId) {
        this.subProcessId = subProcessId;
    }

    @ManyToOne
    @JoinColumn(name = "SUBPROCESO_ID")
    public Process getSubProcess() {
        return subProcess;
    }

    public void setSubProcess(Process subProcess) {
        this.subProcess = subProcess;
    }
}
