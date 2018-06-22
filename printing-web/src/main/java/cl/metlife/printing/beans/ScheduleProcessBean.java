package cl.metlife.printing.beans;

import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.metlife.printing.managers.ProcessManager;
import cl.metlife.saam.web.controller.BaseBean;
import cl.metlife.ws.clients.timer.SimpleDisplayableTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;


@ManagedBean(name="scheduleProcessBean")
@ViewScoped
public class ScheduleProcessBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    static private final Logger LOGGER = LoggerFactory.getLogger(ScheduleProcessBean.class);

    private Long processId;

    private String second = "0";
    private String minute = "0";
    private String hour ="0";
    private String dayOfMonth = "*";
    private String month ="*";
    private String dayOfWeek ="*";
    private String year = "*";

    @EJB
    ProcessManager processManager;

    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;

    public void init(){

        SimpleDisplayableTimer simpleDisplayableTimer = processManager.getSchedulerTimerByProcessSAAM(processId);

        if(simpleDisplayableTimer != null){
            this.second = simpleDisplayableTimer.getSecond();
            this.minute = simpleDisplayableTimer.getMinute();
            this.hour = simpleDisplayableTimer.getHour();
            this.dayOfMonth = simpleDisplayableTimer.getDayOfMonth();
            this.month = simpleDisplayableTimer.getDayOfMonth();
            this.dayOfWeek = simpleDisplayableTimer.getDayOfWeek();
            this.year = simpleDisplayableTimer.getYear();
        }
    }


    public String save(){

        SimpleDisplayableTimer simpleDisplayableTimer = new SimpleDisplayableTimer();

        simpleDisplayableTimer.setSecond(this.second);
        simpleDisplayableTimer.setMinute(this.minute);
        simpleDisplayableTimer.setHour(this.hour);
        simpleDisplayableTimer.setDayOfMonth(this.dayOfMonth);
        simpleDisplayableTimer.setMonth(this.month);
        simpleDisplayableTimer.setDayOfWeek(this.dayOfWeek);
        simpleDisplayableTimer.setYear(this.year);


        processManager.setProcessTimerService(simpleDisplayableTimer,processId,authenticationBean.getLoggedUser().getName());

        showInfo("Éxito","Cambios guardados exitósamente.");
        return "list.xhtml";

    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

}