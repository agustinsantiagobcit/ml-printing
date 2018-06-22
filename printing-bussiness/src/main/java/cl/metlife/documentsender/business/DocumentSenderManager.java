package cl.metlife.documentsender.business;

import cl.metlife.documentsender.persistence.dao.*;
import cl.metlife.printing.pojos.ReportData;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class DocumentSenderManager {

    private static final Logger LOGGER = Logger.getLogger(DocumentSenderManager.class);

    @EJB
    private DocumentEntityDAO documentEntityDAO;

    @EJB
    private ContentEntityDAO contentEntityDAO;

    @EJB
    private LotEntityDAO lotEntityDAO;


    public List<ReportData> findReportData() {
        return null;
    }
}