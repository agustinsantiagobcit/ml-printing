package cl.metlife.printing.managers;

import cl.metlife.documentsender.business.DocumentSenderManager;
import cl.metlife.printing.domain.Document;
import cl.metlife.printing.domain.Print;
import cl.metlife.printing.persistence.dao.DocumentDAO;
import cl.metlife.printing.pojos.ReportData;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class ReportsManager {

    private static final Logger LOGGER = Logger.getLogger(ReportsManager.class);

    @EJB
    DocumentSenderManager documentSenderManager;

    @EJB
    DocumentDAO documentDAO;

    public List<Document> findDocumentListByFilters(String documentType, String policy, String rut, Date dateFrom, Date dateTo) {
        List<Document> documentsByFilters =null;
        documentsByFilters = documentDAO.findDocumentsByFilters(documentType, policy, rut, dateFrom, dateTo);// TODO
        return documentsByFilters;
    }

    public List<ReportData> findReportDataListByFilters(String documentType, String policy, String rut, Date dateFrom, Date dateTo) {
        List<Document> documentsByFilters = documentDAO.findDocumentsByFilters(documentType, policy, rut, dateFrom, dateTo);// TODO
        List<ReportData> returnList = new ArrayList<ReportData>();

        for (Document doc : documentsByFilters) {
            ReportData reportData = new ReportData();

            reportData.setId(doc.getId());
            reportData.setInsuredName(doc.getClientName());
            reportData.setRut(doc.getClientRut());
            reportData.setEmail("?");// FIXME
            reportData.setPolicy("?");// FIXME
            reportData.setOpeningDate(new Date());// FIXME
            reportData.setSendDate(new Date());// FIXME
            reportData.setStatus("?");// FIXME

            returnList.add(reportData);
        }

        return returnList;
    }

    public List<Document> getAllDocuments(){
        return documentDAO.findAll();
    }

    public List<Print> getDocumentPrints(Document document){
        return documentDAO.getById(document.getId()).getPrints();
    }
}