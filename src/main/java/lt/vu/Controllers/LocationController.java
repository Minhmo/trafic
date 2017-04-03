package lt.vu.Controllers;

import lt.vu.Facades.LocationFacade;
import lt.vu.Facades.LocationPairFacade;
import lt.vu.Models.Locations.LocationModel;
import lt.vu.Models.Locations.LocationPairModel;
import lt.vu.Transformers.LocationTransformer;
import lt.vu.entities.Location;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by minhmo on 17.3.2.
 */
@Named(value = "locationController")
@Path("/location")
@ApplicationScoped
public class LocationController{

    @Inject
    private LocationFacade locationFacade;
    @Inject
    private LocationTransformer locationTransformer;

    @GET
    @Path("/locations")
    @Produces({ "application/json" })
    public List<LocationModel> allLocations(){

        return locationTransformer.transformToLocationModelList(locationFacade.getAll());
    }


//    @GET
//    @Path("/{id}")
//    public Location location(@PathParam("id") long id) {
//
//        return locationFacade.get(id);
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LocationModel createOrUpdate(LocationModel locationModel) {
        Location location = locationTransformer.transformToLocationEntity(locationModel);

        if (location.id != null) {
            location = locationFacade.edit(location);
             locationModel = locationTransformer.transformToLocationModel(location);
        }
        else {
            locationFacade.create(location);
            locationModel = locationTransformer.transformToLocationModel(location);
        }

        return locationModel;
    }
}
