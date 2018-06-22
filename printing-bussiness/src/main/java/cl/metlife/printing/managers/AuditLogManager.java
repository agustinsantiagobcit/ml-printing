package cl.metlife.printing.managers;

import cl.metlife.printing.domain.AuditLog;
import cl.metlife.printing.persistence.dao.AuditLogDAO;
import org.apache.log4j.Logger;

import javax.ejb.*;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class AuditLogManager {

    private static final Logger LOGGER = Logger.getLogger(AuditLogManager.class);

    @EJB
    AuditLogDAO auditLogDAO;

    public List<AuditLog> getAllAuditData(){
        return auditLogDAO.findAll(); //TODO: Completar
    }

    public List<AuditLog> findLogDataListByFilters(Date dateFrom, Date dateTo) {

        if(dateFrom == null && dateTo == null){
            return getAllAuditData();
        }else{

        }

        return null;//TODO
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void log(Long typeAuditLogId, String detail, String userName){
        AuditLog auditLog = new AuditLog();
        auditLog.setTypeAuditLogId(typeAuditLogId);
        auditLog.setDetail(detail);
        auditLog.setDate(new Date());
        auditLog.setUserName(userName);
        auditLogDAO.create(auditLog);
    }

    public void updateAuditLog(AuditLog auditLog){
        auditLogDAO.update(auditLog);
    }


}