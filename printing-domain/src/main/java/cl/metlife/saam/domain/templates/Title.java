package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_TITLE)
@javax.persistence.Table(name = "SAAM2_ELEMENTO_TITULO")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Title extends TemplateElement implements Serializable {

    private String texto;

    @Basic
    @Column(name = "TEXTO", nullable = true, length = 1000)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Transient
    @Override
    public String getXML(Map<String, Object> parameterMap) {
        return "<Title><![CDATA["+getTexto()+"]]></Title>";
    }

    @Transient
    @Override
    public TemplateElement copy() {
        Title clone = new Title();
        baseCopyTo(clone);
        clone.setTexto(this.getTexto());
        return clone;
    }

}
