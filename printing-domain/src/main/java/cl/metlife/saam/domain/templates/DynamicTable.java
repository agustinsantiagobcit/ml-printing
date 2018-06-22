package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_DYNAMIC_TABLE)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_TABLA")
public class DynamicTable extends TemplateElement implements Serializable {

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

        if(parameterMap==null)
            return previewXML();

        List<Parameter> dynamicParameters = parseParameters();

        StringBuilder xml = new StringBuilder();

        xml.append("<Table>");
        if(getTitle()!=null && !getTitle().isEmpty()) {
            xml.append("<Title><![CDATA[");
            xml.append(fillParameterValues(getTitle(),parameterMap));
            xml.append("]]></Title>");
        }
        int colCount = 0;
        for (TableColumn column : getColumns()) {
            colCount++;
            xml.append("<Header").append(colCount).append("><![CDATA[");
            xml.append(fillParameterValues(column.getName(),parameterMap));
            xml.append("]]></Header").append(colCount).append(">");
        }

        String firstDynamicParam = getFirstDynamicParameter(parameterMap, dynamicParameters);


        if(firstDynamicParam != null && !dynamicParameters.isEmpty()&& !getRows().isEmpty()) {
            Object o = parameterMap.get(firstDynamicParam);
            TableRow elementoFila = getRows().get(0);


            if(o!=null && o instanceof List && elementoFila != null) {

                List<String> firstList = (List<String>) o;
                for (int rowCount = 0; rowCount < firstList.size(); rowCount++ ) {
                    renderRow(parameterMap, dynamicParameters, xml, elementoFila, rowCount);

                }
                if(firstList.isEmpty()){
                    renderEmptyRow(xml, elementoFila);
                }

            }
        }

        xml.append("</Table>");

        return xml.toString();
    }

    private void renderEmptyRow(StringBuilder xml, TableRow elementoFila) {
        xml.append("<Row>");
        int colCount2 = 0;
        for (TableCell elementoCelda : elementoFila.getCells()) {
            colCount2++;
            xml.append("<Column").append(colCount2).append("><![CDATA[ ]]></Column").append(colCount2).append(">");
        }
        xml.append("</Row>");
    }

    private void renderRow(Map<String, Object> parameterMap, List<Parameter> dynamicParameters, StringBuilder xml, TableRow elementoFila, int rowCount) {
        int colCount;
        xml.append("<Row>");
        colCount = 0;

        Map<String, Object> subParamsMap = new HashMap<String, Object>();
        for (Parameter dynamicParameter : dynamicParameters) {
            Object value = parameterMap.get(dynamicParameter.getNombre());
            if(value instanceof String)
                subParamsMap.put(dynamicParameter.getNombre(), value);
            if(value instanceof List)
                subParamsMap.put(dynamicParameter.getNombre(), ((List)value).get(rowCount));
        }

        for (TableCell elementoCelda : elementoFila.getCells()) {
            colCount++;
            xml.append("<Column").append(colCount).append("><![CDATA[");
            xml.append(
                    fillParameterValues(elementoCelda.getTexto(),subParamsMap)
            );
            xml.append("]]></Column").append(colCount).append(">");
        }
        xml.append("</Row>");
    }

    @Transient
    private String previewXML() {
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

        return xml.toString();
    }

    private String getFirstDynamicParameter(Map<String, Object> parameterMap, List<Parameter> dynamicParameters) {
        if(parameterMap==null)
            return null;
        String firstDynamicParam = null;
        for (Parameter dynamicParameter : dynamicParameters) {
            if(parameterMap.get(dynamicParameter.getNombre()) instanceof List) {
                firstDynamicParam = dynamicParameter.getNombre();
                break;
            }

        }

        return firstDynamicParam;
    }

    @Override
    @Transient
    public List<Parameter> parseParameters() {
        String aso = "";

        TableRow row = this.getRows().get(0);

        for (TableCell tableCell : row.getCells()) {
            aso += tableCell.getTexto();

        }
        for (TableColumn tableColumn : getColumns()) {
            aso+=tableColumn.getName();
        }
        aso+=getTitle();

        List<Parameter> parameters = parseTextForParameters(aso);

        if(parameters==null)
            return new ArrayList<Parameter>();

        for (Parameter parameter : parameters) {
            parameter.setMultiple(true);
        }

        return parameters;
    }

    @Override
    public TemplateElement copy() {
        DynamicTable clone = new DynamicTable();
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
