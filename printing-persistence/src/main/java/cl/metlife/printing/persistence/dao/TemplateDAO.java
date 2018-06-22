package cl.metlife.printing.persistence.dao;

import cl.metlife.saam.domain.templates.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TemplateDAO {
    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public Template getById(Long id) {
        Template template = em.find(Template.class, id);

        template.setElements(new ArrayList<TemplateElement>(template.getElements()));
        template.setParameters(new ArrayList<Parameter>(template.getParameters()));

        return template;
    }

    public Template create(Template template) {
        em.persist(template);

        // Fix Orden for TABLES.
        tableOrderFix(template);

        em.flush();

        return template;
    }

    public Template update(Template template){
        if (template == null)
            throw new IllegalArgumentException("template can't be null");

        // Fix Orden for TABLES.
        tableOrderFix(template);

        Template updated = em.merge(template);
        em.flush();

        return updated;
    }

    private void tableOrderFix(Template template) {

        for (TemplateElement templateElement : template.getElements()) {
            if(templateElement instanceof Table){
                Table table = (Table) templateElement;
                List<TableRow> rows = table.getRows();
                List<TableColumn> columnList = table.getColumns();

                fixRowsOrders(rows);
                fixColumnsOrders(columnList);

            }

            if(templateElement instanceof DynamicTable){
                DynamicTable table = (DynamicTable) templateElement;
                List<TableRow> rows = table.getRows();
                List<TableColumn> columnList = table.getColumns();

                fixRowsOrders(rows);
                fixColumnsOrders(columnList);

            }
        }
    }

    private void fixColumnsOrders(List<TableColumn> columnList) {
        int colIndex = 1;

        for (TableColumn tableCol : columnList) {
            tableCol.setOrden(colIndex);
            colIndex++;
        }
    }

    private void fixRowsOrders( List<TableRow> rows) {
        int rowIndex = 1;

        for (TableRow tableRow : rows) {
            tableRow.setOrden(rowIndex);
            rowIndex++;

            int cellIndex = 1;
            for (TableCell tableCell : tableRow.getCells()) {
                tableCell.setOrden(cellIndex);
                cellIndex++;
            }
        }


    }

    public boolean delete(Template template){
        Template toDelete = em.find(Template.class,template.getId());
        em.remove(toDelete);

        return true;
    }

    public List<Template> getAllTemplates(){
        return em.createQuery("select i from Template i").getResultList();
    }
}
