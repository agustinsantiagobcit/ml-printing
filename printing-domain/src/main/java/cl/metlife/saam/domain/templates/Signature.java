package cl.metlife.saam.domain.templates;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.util.Base64;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_SIGNING)
@javax.persistence.Table(name = "SAAM2_ELEMENTO_FIRMA")
@PrimaryKeyJoinColumn(name = "ID_FIRMA", referencedColumnName = "ID")
public class Signature extends TemplateElement implements Serializable {


    private String image;
    private String name;
    private String position;
    private String institution;
    private String contentType;

    @Basic
    @Column(name = "FOTO", nullable = true, length = 1000)
    public String getImage() {
        return image;
    }

    public void setImage(String imagen) {
        this.image = imagen;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 1000)
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Basic
    @Column(name = "CARGO", nullable = true, length = 1000)
    public String getPosition() {
        return position;
    }

    public void setPosition(String cargo) {
        this.position = cargo;
    }

    @Basic
    @Column(name = "INSTITUCION", nullable = true, length = 1000)
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institucion) {
        this.institution = institucion;
    }

    @Basic
    @Column(name = "CONTENT_TYPE", nullable = true, length = 1000)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    @Override
    @Transient
    public String getXML(Map<String, Object> parameterMap) {
        return "<Signature>" +
                "<Name><![CDATA["+getName()+"]]></Name>" +
                "<Position><![CDATA["+getPosition()+"]]></Position>" +
                "<Institution><![CDATA["+getInstitution()+"]]></Institution>" +
                "<Image><![CDATA["+getImage()+"]]></Image>" +
                "</Signature>\n";
    }


    @Transient
    public void handleFileUpload(FileUploadEvent event) {

        setContentType(event.getFile().getContentType());
        setImage(Base64.encodeToString(event.getFile().getContents(),false));
    }


    @Transient
    @Override
    public TemplateElement copy() {
        Signature clone = new Signature();
        baseCopyTo(clone);

        clone.setImage(this.getImage());
        clone.setName(this.getName());
        clone.setPosition(this.getPosition());
        clone.setInstitution(this.getInstitution());
        clone.setContentType(this.getContentType());

        return clone;
    }


}
