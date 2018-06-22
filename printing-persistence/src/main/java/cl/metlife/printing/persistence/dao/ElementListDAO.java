package cl.metlife.printing.persistence.dao;

import cl.metlife.saam.domain.templates.ListElement;
import cl.metlife.saam.domain.templates.ListItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ElementListDAO {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public ListElement getById(Long id) {
        return em.find(ListElement.class, id);
    }

    public ListElement create(ListElement listElement) {
        em.persist(listElement);

        em.flush();

        return listElement;
    }

    public ListElement update(ListElement listElement){
        if (listElement == null)
            throw new IllegalArgumentException("list element can't be null");

        ListElement updated = em.merge(listElement);
        em.flush();

        return updated;
    }

    public boolean delete(ListElement listElement) {
        ListElement toDelete = getById(listElement.getId());
        em.remove(toDelete);

        return true;
    }

    public ListItem createElementsList(ListItem listElement){
        em.persist(listElement);

        em.flush();

        return listElement;
    }
}
