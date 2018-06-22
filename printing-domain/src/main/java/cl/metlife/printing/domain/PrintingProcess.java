package cl.metlife.printing.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "PRINTING_PROCESOS")
public class PrintingProcess extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String policyCode;
    private Long saamProcessId;
    private Long templateId;
    private Long sponsorId;
    private Sponsor sponsor;

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
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CODIGO_POLIZA")
    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    @Basic
    @Column(name = "PROCESO_ID")
    public Long getSaamProcessId() {
        return saamProcessId;
    }

    public void setSaamProcessId(Long saamProcessId) {
        this.saamProcessId = saamProcessId;
    }

    @Basic
    @Column(name = "PLANTILLA_ID", nullable = true, precision = 0)
    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    @Basic
    @Column(name = "PRINTING_SPONSORS_ID", nullable = true, precision = 0)
    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRINTING_SPONSORS_ID")
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

}
