package cl.metlife.printing.persistence.dao;

import cl.metlife.saam.domain.templates.ElementType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ElementTypeDAO {
    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public ElementType getById(Long id) {
        return em.find(ElementType.class, id);
    }

    public ElementType create(ElementType elementType) {
        em.persist(elementType);

        em.flush();

        return elementType;
    }

    public ElementType update(ElementType elementType){
        if (elementType == null)
            throw new IllegalArgumentException("element type can't be null");

        ElementType updated = em.merge(elementType);
        em.flush();

        return updated;
    }

    public boolean delete(ElementType elementType){
        ElementType toDelete = getById(elementType.getId());
        em.remove(toDelete);

        return true;
    }

    public List<ElementType> getAllElementTypes(){
        return em.createQuery("select i from ElementType i ORDER BY i.id asc").getResultList();
    }
}
