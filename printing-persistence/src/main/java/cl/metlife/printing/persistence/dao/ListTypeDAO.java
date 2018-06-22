package cl.metlife.printing.persistence.dao;

import cl.metlife.saam.domain.templates.ListType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ListTypeDAO {
    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public ListType getById(Long id) {
        return em.find(ListType.class, id);
    }

    public ListType create(ListType listType) {
        em.persist(listType);

        em.flush();

        return listType;
    }

    public ListType update(ListType listType){
        if (listType == null)
            throw new IllegalArgumentException("element type can't be null");

        ListType updated = em.merge(listType);
        em.flush();

        return updated;
    }

    public boolean delete(ListType listType){
        ListType toDelete = getById(listType.getId());
        em.remove(toDelete);

        return true;
    }

    public List<ListType> getAllListTypes(){
        return em.createQuery("select i from ListType i ORDER BY i.id asc").getResultList();
    }
}
