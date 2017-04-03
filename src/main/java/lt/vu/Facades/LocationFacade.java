package lt.vu.Facades;

import com.google.maps.GeoApiContext;

import lt.vu.Models.SaveResult;
import lt.vu.Services.GoogleLocationAPIService;
import lt.vu.entities.Location;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@ApplicationScoped
public class LocationFacade extends AbstractFacade<Location> implements lt.vu.Facades.Declarations.ILocationFacade {

    @Inject
    private EntityManager em;

    @Inject
    private GoogleLocationAPIService googleAPI;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public SaveResult validate(Location entity) {
        SaveResult result = new SaveResult();

        return result;
    }

    public LocationFacade() {
        super(Location.class);
    }

    @Override
    @Transactional
    public SaveResult create(Location location) {
        try{
            googleAPI.fillLocationInfo(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            location.setAdditionDate(new Date());
            SaveResult result = super.create(location);
            return result;
        }

    }


}
