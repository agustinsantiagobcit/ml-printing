package cl.metlife.saam.domain.templates;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_PAGE_BREAK)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_GENERICO")
public class PageBreak extends TemplateElement implements Serializable {


    @Override
    @Transient
    public String getXML(Map<String, Object> parameterMap) {

        return "<PageBreak>pageBreak</PageBreak>";
    }


    @Override
    @Transient
    public TemplateElement copy() {
        PageBreak clone = new PageBreak();
        baseCopyTo(clone);

        return clone;
    }

}
