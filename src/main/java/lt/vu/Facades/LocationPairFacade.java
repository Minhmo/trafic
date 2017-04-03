package lt.vu.Facades;

import lt.vu.Models.SaveResult;
import lt.vu.entities.LocationPair;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by minhmo on 17.3.19.
 */
@ApplicationScoped
public class LocationPairFacade extends AbstractFacade<LocationPair> implements lt.vu.Facades.Declarations.ILocationPairFacade {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public SaveResult validate(LocationPair entity) {
        return new SaveResult();
    }

    public LocationPairFacade () {
        super(LocationPair.class);
    }
}
