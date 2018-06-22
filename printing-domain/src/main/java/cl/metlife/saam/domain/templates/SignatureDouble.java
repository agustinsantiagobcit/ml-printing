package cl.metlife.saam.domain.templates;


import org.primefaces.event.FileUploadEvent;
import org.primefaces.util.Base64;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_DOUBLE_SIGNING)
@javax.persistence.Table(name = "SAAM2_ELEMENTO_FIRMA_DOBLE")
@PrimaryKeyJoinColumn(name = "ID_FIRMA", referencedColumnName = "ID")
public class SignatureDouble extends TemplateElement implements Serializable {

    private String image1;
    private String name1;
    private String position1;
    private String institution1;
    private String contentType1;

    private String image2;
    private String name2;
    private String position2;
    private String institution2;
    private String contentType2;

    @Basic
    @Column(name = "FOTO_1", nullable = true, length = 1000)
    public String getImage1() {
        return image1;
    }

    public void setImage1(String imagen1) {
        this.image1 = imagen1;
    }

    @Basic
    @Column(name = "NOMBRE_1", nullable = true, length = 1000)
    public String getName1() {
        return name1;
    }

    public void setName1(String nombre1) {
        this.name1 = nombre1;
    }

    @Basic
    @Column(name = "CARGO_1", nullable = true, length = 1000)
    public String getPosition1() {
        return position1;
    }

    public void setPosition1(String cargo1) {
        this.position1 = cargo1;
    }

    @Basic
    @Column(name = "INSTITUCION_1", nullable = true, length = 1000)
    public String getInstitution1() {
        return institution1;
    }

    public void setInstitution1(String institucion1) {
        this.institution1 = institucion1;
    }

    @Basic
    @Column(name = "CONTENT_TYPE_1", nullable = true, length = 1000)
    public String getContentType1() {
        return contentType1;
    }

    public void setContentType1(String contentType1) {
        this.contentType1 = contentType1;
    }

    @Basic
    @Column(name = "FOTO_2", nullable = true, length = 1000)
    public String getImage2() {
        return image2;
    }

    public void setImage2(String imagen2) {
        this.image2 = imagen2;
    }

    @Basic
    @Column(name = "NOMBRE_2", nullable = true, length = 1000)
    public String getName2() {
        return name2;
    }

    public void setName2(String nombre2) {
        this.name2 = nombre2;
    }

    @Basic
    @Column(name = "CARGO_2", nullable = true, length = 1000)
    public String getPosition2() {
        return position2;
    }

    public void setPosition2(String cargo2) {
        this.position2 = cargo2;
    }

    @Basic
    @Column(name = "INSTITUCION_2", nullable = true, length = 1000)
    public String getInstitution2() {
        return institution2;
    }

    public void setInstitution2(String institucion2) {
        this.institution2 = institucion2;
    }

    @Basic
    @Column(name = "CONTENT_TYPE_2", nullable = true, length = 1000)
    public String getContentType2() {
        return contentType2;
    }

    public void setContentType2(String contentType2) {
        this.contentType2 = contentType2;
    }

    @Override
    @Transient
    public String getXML(Map<String, Object> parameterMap) {
        return "<Signature1>" +
                "<Name><![CDATA["+getName1()+"]]></Name>" +
                "<Position><![CDATA["+getPosition1()+"]]></Position>" +
                "<Institution><![CDATA["+getInstitution1()+"]]></Institution>" +
                "<Image><![CDATA["+getImage1()+"]]></Image>" +
                "</Signature1>\n"+
                "<Signature2>" +
                "<Name><![CDATA["+getName2()+"]]></Name>" +
                "<Position><![CDATA["+getPosition2()+"]]></Position>" +
                "<Institution><![CDATA["+getInstitution2()+"]]></Institution>" +
                "<Image><![CDATA["+getImage2()+"]]></Image>" +
                "</Signature2>\n";
    }


    @Transient
    public void handleFileUpload1(FileUploadEvent event) {

        setContentType1(event.getFile().getContentType());
        setImage1(Base64.encodeToString(event.getFile().getContents(),false));
    }

    @Transient
    public void handleFileUpload2(FileUploadEvent event) {

        setContentType2(event.getFile().getContentType());
        setImage2(Base64.encodeToString(event.getFile().getContents(),false));
    }


    @Transient
    @Override
    public TemplateElement copy() {
        SignatureDouble clone = new SignatureDouble();
        baseCopyTo(clone);

        clone.setImage1(this.getImage1());
        clone.setName1(this.getName1());
        clone.setPosition1(this.getPosition1());
        clone.setInstitution1(this.getInstitution1());
        clone.setContentType1(this.getContentType1());

        clone.setImage2(this.getImage2());
        clone.setName2(this.getName2());
        clone.setPosition2(this.getPosition2());
        clone.setInstitution2(this.getInstitution2());
        clone.setContentType2(this.getContentType2());

        return clone;
    }
}
