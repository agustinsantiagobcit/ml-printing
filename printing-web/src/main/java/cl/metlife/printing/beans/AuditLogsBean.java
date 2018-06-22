package cl.metlife.printing.beans;

import cl.metlife.printing.domain.AuditLog;
import cl.metlife.printing.managers.AuditLogManager;
import cl.metlife.printing.pojos.AuditData;
import cl.metlife.saam.web.controller.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 1/31/3500.
 */

@ManagedBean(name="auditBean")
@ViewScoped
public class AuditLogsBean extends BaseBean implements Serializable {

    /* Init params */
    private static final long serialVersionUID = 1L;
    static final Logger logger = LoggerFactory.getLogger(AuditLogsBean.class);

    /* Form params */
    private Date dateFrom;
    private Date dateTo;

    /* Table List */
    public List<AuditLog> dataList;
    private AuditData editItem;

    /* Persistence Objects */
    @EJB
    private AuditLogManager auditLogManager;


    public void search(){
        this.dataList = auditLogManager.findLogDataListByFilters(dateFrom, dateTo);
    }

    public List<AuditLog> getAllLogs(){
        if (this.dataList == null) {
            this.dataList = auditLogManager.getAllAuditData();
        }

        return this.dataList;
    }

    public AuditData getEditItem() {
        if(this.editItem == null){
            this.editItem = new AuditData();
        }

        return editItem;
    }

    public void setEditItem(AuditData item) {
        this.editItem = item;
    }

    private void resetList() {
        this.dataList = null;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}

