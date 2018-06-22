package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_LIST)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_LISTA")
public class ListElement extends TemplateElement implements Serializable {

    private List<ListItem> items;
    private ListType listType;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("order ASC")
    @JoinColumn(name = "ELEMENTO_LISTA_ID")
    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(List<ListItem> elementosListaList) {
        this.items = elementosListaList;
    }

    @ManyToOne
    @JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {

        //fix para error exotico
        if(listType == null) {
            return;
        }

        this.listType = listType;
    }

    @Override
    @Transient
    public String getXML(Map<String, Object> parameterMap) {
        StringBuilder xml = new StringBuilder();

        xml.append("<List>");

        xml.append("<Type>");
        xml.append(getListType().getName());
        xml.append("</Type>");


        int colCount = 0;
        for (ListItem item : getItems()) {
            colCount++;
            xml.append("<Item><Value><![CDATA[");
            xml.append(item.getText());
            xml.append("]]></Value></Item>");
        }

        xml.append("</List>");

        return xml.toString();
    }

    @Transient
    @Override
    public TemplateElement copy() {
        ListElement clone = new ListElement();
        baseCopyTo(clone);

        clone.setListType(this.getListType());
        clone.setItems(new ArrayList<ListItem>());

        for (ListItem origItem : this.getItems()) {
            ListItem cloneItem= new ListItem();
            cloneItem.setText(origItem.getText());
            cloneItem.setOrder(origItem.getOrder());
            clone.getItems().add(cloneItem);
        }

        return clone;
    }


}
