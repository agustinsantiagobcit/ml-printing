package cl.blueprintsit.framework.config;

import cl.blueprintsit.framework.domain.Configuration;
import cl.metlife.printing.persistence.dao.ConfigurationDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
@Stateless
public class ConfigurationManager {

    @EJB
    ConfigurationDAO dao;

    public Configuration getByKey(String key) {
        return dao.getByKey(key);
    }

    public List<Configuration> findAll() {
        return dao.findAll();
    }

    public Configuration create(Configuration configuration) {
        return dao.create(configuration);
    }

    public Configuration update(Configuration configuration) {
        return dao.update(configuration);
    }

    public void delete(Configuration configuration) {
        dao.delete(configuration);
    }


}
