package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.Document;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class DocumentDAO {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public Document getById(Long id) {
        Document document = em.find(Document.class, id);
        document.getPrints();
        return document;
    }

    public List<Document> findAll() {
        List<Document> list = em.createQuery("select i from Document i ORDER BY i.id asc").getResultList();

        for (Document document : list) {
            em.detach(document);
        }

        return list;
    }

    public Document create(Document document) {

        em.persist(document);

        em.flush();

        return document;
    }

    public Document update(Document document) {
        if ( document == null )
            throw new IllegalArgumentException("document can't be null");

        Document updated = em.merge(document);
        em.flush();

        return updated;
    }

    public boolean delete(Document document) {

        Document toDelete = em.find(Document.class,document.getId());
        em.remove(toDelete);

        return true;
    }

    public List<Document> findDocumentsByFilters(String documentType, String policy, String rut, Date dateFrom, Date dateTo) {


        String queryS = makeQuery(documentType, policy, rut,dateFrom,dateTo);

        Query query = em.createQuery(queryS);

        setParameterFindDocuments(documentType,policy,rut,dateFrom,dateTo,query);


        return query.getResultList();
    }

    private String makeQuery(String documentType, String policy, String rut, Date dateFrom, Date dateTo) {
        String where = "";

        if (documentType != null && !documentType.isEmpty()) {
            where = "i.process.sponsor.name=:documentType";
        }
        if(policy != null && !policy.isEmpty()){
            if (!where.isEmpty())
                where += " and";
            where += " i.process.policyCode=:policyCode";
        }
        if(rut != null && !rut.isEmpty()){
            if (!where.isEmpty())
                where += " and";
            where += " i.clientRut=:rut";
        }

        if(dateFrom != null ){
            if (!where.isEmpty())
                where += " and";
            where += " i.creationDate >=:dateFrom";
        }

        if(dateTo != null ){
            if (!where.isEmpty())
                where += " and";
            where += " i.creationDate <=:dateTo";
        }

        if(!where.isEmpty())
            where = "where "+where;

        return "select i from Document i "+where+" ORDER BY i.id asc";
    }

    private void setParameterFindDocuments(String documentType, String policy, String rut, Date dateFrom, Date dateTo, Query query){
        if (documentType != null && !documentType.isEmpty()) {
            query.setParameter("documentType", documentType);
        }
        if(policy != null && !policy.isEmpty()){
            query.setParameter("policyCode",policy);
        }
        if(rut != null && !rut.isEmpty()){
            query.setParameter("rut",rut);
        }

        if(dateFrom != null ){
            query.setParameter("dateFrom",dateFrom);
        }

        if(dateTo != null ){
            query.setParameter("dateTo",dateTo);
        }
    }
}