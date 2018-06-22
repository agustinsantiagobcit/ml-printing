package cl.metlife.saam.domain.templates;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.util.Base64;

import javax.persistence.*;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_IMAGE)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_IMAGEN")
public class Image extends TemplateElement {

    private String image;
    private String contentType;

    @Basic
    @Column(name = "IMAGEN", nullable = true)
    public String getImage() {
        return image;
    }

    public void setImage(String text) {
        this.image = text;
    }

    @Basic
    @Column(name = "CONTENT_TYPE", nullable = true)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    @Transient
    public String getXML(Map<String, Object> parameterMap) {
        return "<SizeY>"+getTamanioY()+"</SizeY>\n"+
        "<Image>"+getImage()+"</Image>\n";
    }

    @Transient
    public void handleFileUpload(FileUploadEvent event) {

        setContentType(event.getFile().getContentType());
        setImage(Base64.encodeToString(event.getFile().getContents(),false));
    }

    @Override
    public TemplateElement copy() {
        Image clone = new Image();
        baseCopyTo(clone);
        clone.setContentType(this.getContentType());
        clone.setImage(this.getImage());
        return clone;
    }

}
