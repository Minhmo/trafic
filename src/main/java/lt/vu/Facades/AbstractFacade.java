package lt.vu.Facades;

import lt.vu.Models.SaveResult;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractFacade<T> {
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public abstract SaveResult validate(T entity);

    @Transactional
    public SaveResult create(T entity) {

        SaveResult result = validate(entity);

        if (result.getErrors().size() < 1)
        {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        }

        return result;
    }

    @Transactional
    public T edit(T entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T get(Object id) {
       return getEntityManager().find(entityClass, id);
    }

    public List<T> getAll(){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> pet = cq.from(entityClass);
        cq.select(pet);
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        List<T> allLocations = q.getResultList();

        return allLocations;
    }


    // some more find helper methods ...
}

