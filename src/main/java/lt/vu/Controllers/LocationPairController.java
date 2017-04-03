package lt.vu.Controllers;

import lt.vu.Facades.LocationPairFacade;
import lt.vu.Models.Locations.LocationModel;
import lt.vu.Models.Locations.LocationPairModel;
import lt.vu.Transformers.LocationTransformer;
import lt.vu.entities.Location;
import lt.vu.entities.LocationPair;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by minhmo on 17.3.22.
 */
@Named
@Path("/location-pair")
@ApplicationScoped
public class LocationPairController{
    @Inject
    private LocationPairFacade locationPairFacade;

    @Inject
    private LocationTransformer locationTransformer;

    @GET
    @Produces({ "application/json" })
    public List<LocationPairModel> allLocationPairs(){
        return locationTransformer.transformToLocationPairModelList(locationPairFacade.getAll());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LocationPairModel createOrUpdate(LocationPairModel locationModel) {
        LocationPair location = locationTransformer.transformToLocationPairEntity(locationModel);

        if (location.getId() != null) {
            location = locationPairFacade.edit(location);
            locationModel = locationTransformer.transformToLocationPairModel(location);
        }
        else {
            locationPairFacade.create(location);
            locationModel = locationTransformer.transformToLocationPairModel(location);
        }

        return locationModel;
    }

}
