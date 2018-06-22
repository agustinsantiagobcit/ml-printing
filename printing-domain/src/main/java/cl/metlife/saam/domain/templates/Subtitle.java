package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_SUBTITLE)
@javax.persistence.Table(name = "SAAM2_ELEMENTO_GENERICO")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Subtitle extends TemplateElement implements Serializable {

    private String text;

    @Basic
    @Column(name = "CONTENIDO", nullable = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;

    }

    @Override
    public String getXML(Map<String, Object> parameterMap) {
        return "<Title><![CDATA["+ getText()+"]]></Title>";
    }

    @Override
    public TemplateElement copy() {
        Subtitle clone = new Subtitle();
        baseCopyTo(clone);
        clone.setText(this.getText());
        return clone;
    }

}
