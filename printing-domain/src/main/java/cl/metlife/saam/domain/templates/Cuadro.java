package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_CUADRO)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_PARRAFO")
public class Cuadro extends TemplateElement {

    private String texto;

    @Basic
    @Column(name = "TEXTO", nullable = true, length = 1000)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String getXML(Map<String, Object> parameterMap) {
        return createTextElement("Frame",fillParameterValues(getTexto(),parameterMap));
    }

    @Override
    public TemplateElement copy() {
        Cuadro clone = new Cuadro();
        baseCopyTo(clone);
        clone.setTexto(this.getTexto());
        return clone;
    }
}
