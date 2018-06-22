package cl.metlife.saam.domain.templates;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.util.Base64;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@javax.persistence.Table(name = "SAAM2_PLANTILLA")
public class Template implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String comentario;
    private String logoTR;
    private String logoBR;
    private String logoTL;
    private String logoBL;
    private String textoTL;
    private String textoBL;
    private String textoTR;
    private String textoBR;
    private String pageNumberPosition;

    private List<TemplateElement> elements;
    private List<Parameter> parameters;

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
    @Column(name = "NOMBRE", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    @JoinColumn(name = "PLANTILLA_ID")
    public List<TemplateElement> getElements() {
        return elements;
    }

    public void setElements(List<TemplateElement> elements) {
        this.elements = elements;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLANTILLA_ID")
    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parametros) {
        this.parameters = parametros;
    }

    @Basic
    @Column(name = "COMENTARIO", nullable = true)
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Basic
    @Column(name = "LOGO_TR", nullable = true)
    public String getLogoTR() {
        return logoTR;
    }

    public void setLogoTR(String logoTR) {
        this.logoTR = logoTR;
    }

    @Basic
    @Column(name = "LOGO_BR", nullable = true)
    public String getLogoBR() {
        return logoBR;
    }

    public void setLogoBR(String logoBR) {
        this.logoBR = logoBR;
    }

    @Basic
    @Column(name = "LOGO_TL", nullable = true)
    public String getLogoTL() {
        return logoTL;
    }

    public void setLogoTL(String logoTL) {
        this.logoTL = logoTL;
    }

    @Basic
    @Column(name = "LOGO_BL", nullable = true)
    public String getLogoBL() {
        return logoBL;
    }

    public void setLogoBL(String logoBL) {
        this.logoBL = logoBL;
    }

    @Basic
    @Column(name = "TEXTO_TL", nullable = true)
    public String getTextoTL() {
        return textoTL;
    }

    public void setTextoTL(String textoTL) {
        this.textoTL = textoTL;
    }

    @Basic
    @Column(name = "TEXTO_BL", nullable = true)
    public String getTextoBL() {
        return textoBL;
    }

    public void setTextoBL(String textoBL) {
        this.textoBL = textoBL;
    }

    @Basic
    @Column(name = "TEXTO_TR", nullable = true)
    public String getTextoTR() {
        return textoTR;
    }

    public void setTextoTR(String textoTR) {
        this.textoTR = textoTR;
    }

    @Basic
    @Column(name = "TEXTO_BR", nullable = true)
    public String getTextoBR() {
        return textoBR;
    }

    public void setTextoBR(String textoBR) {
        this.textoBR = textoBR;
    }

    @Basic
    @Column(name = "POSISION_PAGINA", nullable = true)
    public String getPageNumberPosition() {
        return pageNumberPosition;
    }

    public void setPageNumberPosition(String poPagina) {
        this.pageNumberPosition = poPagina;
    }


    @Transient
    public void handleFileUploadBL(FileUploadEvent event) {
        setLogoBL(Base64.encodeToString(event.getFile().getContents(),false));
    }
    @Transient
    public void handleFileUploadBR(FileUploadEvent event) {
        setLogoBR(Base64.encodeToString(event.getFile().getContents(),false));
    }
    @Transient
    public void handleFileUploadTL(FileUploadEvent event) {
        String logoTL = Base64.encodeToString(event.getFile().getContents(), false);
        setLogoTL(logoTL);
    }
    @Transient
    public void handleFileUploadTR(FileUploadEvent event) {
        setLogoTR(Base64.encodeToString(event.getFile().getContents(),false));
    }



}
