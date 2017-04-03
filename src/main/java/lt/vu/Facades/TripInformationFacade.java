package lt.vu.Facades;

import lt.vu.Models.SaveResult;
import lt.vu.entities.LocationPair;
import lt.vu.entities.TripInformation;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by minhmo on 17.3.19.
 */
@ApplicationScoped
public class TripInformationFacade extends AbstractFacade<TripInformation> implements lt.vu.Facades.Declarations.ITripInformationFacade {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public SaveResult validate(TripInformation entity) {
        return new SaveResult();
    }

    public TripInformationFacade () {
        super(TripInformation.class);
    }
}
