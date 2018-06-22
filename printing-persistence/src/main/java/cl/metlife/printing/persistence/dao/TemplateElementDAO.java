package cl.metlife.printing.persistence.dao;


import cl.metlife.saam.domain.templates.TemplateElement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TemplateElementDAO {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public TemplateElement getById(Long id) {
        return em.find(TemplateElement.class, id);
    }

    public TemplateElement create(TemplateElement templateElement) {
        em.persist(templateElement);
        em.flush();

        return templateElement;
    }

    public TemplateElement update(TemplateElement templateElement){
        if (templateElement == null)
            throw new IllegalArgumentException("template element can't be null");

        TemplateElement updated = em.merge(templateElement);
        em.flush();
        return updated;
    }

    public boolean delete(TemplateElement templateElement){
        TemplateElement toDelete = em.find(TemplateElement.class,templateElement.getId());
        em.remove(toDelete);
        return true;
    }

}
