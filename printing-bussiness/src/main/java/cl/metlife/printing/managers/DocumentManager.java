package cl.metlife.printing.managers;

import cl.metlife.printing.domain.Document;
import cl.metlife.printing.persistence.dao.DocumentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
public class DocumentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentManager.class);

    @EJB
    DocumentDAO documentDAO;

    public Document getById(Long id){
        return documentDAO.getById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Document create(Document plantilla){
        Document document = documentDAO.create(plantilla);
        return document;
    }

    public boolean delete(Document plantilla){
        return documentDAO.delete(plantilla);
    }

    public Document update(Document plantilla){
        plantilla = documentDAO.update(plantilla);
        return plantilla;
    }

}
