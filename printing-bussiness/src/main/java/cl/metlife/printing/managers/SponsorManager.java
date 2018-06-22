package cl.metlife.printing.managers;

import cl.metlife.printing.domain.Sponsor;
import cl.metlife.printing.persistence.dao.SponsorDAO;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class SponsorManager {

    @EJB
    private SponsorDAO sponsorDAO;


    public List<Sponsor> getAllSponsor(){
        return sponsorDAO.findAll();
    }

}
