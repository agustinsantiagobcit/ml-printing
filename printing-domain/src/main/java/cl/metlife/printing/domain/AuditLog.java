package cl.metlife.printing.domain;

import cl.blueprintsit.framework.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PRINTING_AUDIT_LOG")
public class AuditLog extends BaseEntity implements Serializable {

    private Long id;
    private Long typeAuditLogId;
    private String detail;
    private Date date;
    private String userName;
    private AuditLogType type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIPO_LOG")
    public Long getTypeAuditLogId() {
        return typeAuditLogId;
    }

    public void setTypeAuditLogId(Long typeAuditLogId) {
        this.typeAuditLogId = typeAuditLogId;
    }

    @Basic
    @Column(name = "DETALLE")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "FECHA")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Basic
    @Column(name = "USUARIO")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ManyToOne
    @JoinColumn(name = "TIPO_LOG", referencedColumnName = "ID")
    public AuditLogType getType() {
        return type;
    }

    public void setType(AuditLogType typeAuditLog) {
        this.type = typeAuditLog;
    }
}
