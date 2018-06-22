package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_TABLE)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_TABLA")
public class Table extends TemplateElement implements Serializable {

    private String title;
    private List<TableRow> rows;
    private List<TableColumn> columns;


    @Basic
    @Column(name = "TITULO", nullable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    @JoinColumn(name = "TABLA_ID")
    public List<TableRow> getRows() {
        return rows;
    }
    public void setRows(List<TableRow> elementoFilas) {
        this.rows = elementoFilas;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    @JoinColumn(name = "TABLA_ID")
    public List<TableColumn> getColumns() {
        return columns;
    }
    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }


    @Override
    @Transient
    public String getXML(Map<String, Object> parameterMap) {
        StringBuilder xml = new StringBuilder();

        xml.append("<Table>");

        if(getTitle()!=null && !getTitle().isEmpty()) {
            xml.append("<Title><![CDATA[");
            xml.append(getTitle());
            xml.append("]]></Title>");
        }
        int colCount = 0;
        for (TableColumn column : getColumns()) {
            colCount++;
            xml.append("<Header").append(colCount).append("><![CDATA[");
            xml.append(column.getName());
            xml.append("]]></Header").append(colCount).append(">");
        }

        for (TableRow elementoFila : getRows()) {
            xml.append("<Row>");
            colCount = 0;
            for (TableCell elementoCelda : elementoFila.getCells()) {
                colCount++;
                xml.append("<Column").append(colCount).append("><![CDATA[");
                xml.append(elementoCelda.getTexto());
                xml.append("]]></Column").append(colCount).append(">");
            }
            xml.append("</Row>");
        }

        xml.append("</Table>");

        return fillParameterValues(xml.toString(),parameterMap);
    }


    @Override
    public TemplateElement copy() {
        Table clone = new Table();
        baseCopyTo(clone);
        clone.setTitle(this.getTitle());

        clone.setColumns(new ArrayList<TableColumn>());
        for (TableColumn origColumn : getColumns()) {
            TableColumn cloneColumn = new TableColumn();
            cloneColumn.setName(origColumn.getName());
            cloneColumn.setOrden(origColumn.getOrden());
            clone.getColumns().add(cloneColumn);
        }

        clone.setRows(new ArrayList<TableRow>());
        for (TableRow origRow : this.getRows()) {
            TableRow cloneRow = new TableRow();
            cloneRow.setNombre(origRow.getNombre());
            cloneRow.setOrden(origRow.getOrden());
            cloneRow.setCells(new ArrayList<TableCell>());

            for (TableCell origCell : origRow.getCells()) {
                TableCell cloneCell = new TableCell();
                cloneCell.setOrden(origCell.getOrden());
                cloneCell.setTexto(origCell.getTexto());
                cloneRow.getCells().add(cloneCell);
            }

            clone.getRows().add(cloneRow);
        }


        return clone;
    }

}
