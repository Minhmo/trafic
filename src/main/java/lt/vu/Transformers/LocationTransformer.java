package lt.vu.Transformers;

import lt.vu.Facades.LocationFacade;
import lt.vu.Models.Locations.LocationModel;
import lt.vu.Models.Locations.LocationPairModel;
import lt.vu.entities.Location;
import lt.vu.entities.LocationPair;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by minhmo on 17.3.21.
 */
@ApplicationScoped
public class LocationTransformer {
    @Inject
    private ModelMapper modelMapper;

    @Inject
    private LocationFacade locationFacade;

    public Location transformToLocationEntity(LocationModel model) {
        return modelMapper.map(model, Location.class);
    }

    public LocationPair transformToLocationPairEntity(LocationPairModel model) {
        LocationPair pair = new LocationPair();
        Location from = locationFacade.get((long)model.getLocationFromId());
        Location to = locationFacade.get((long) model.getLocationToId());

        pair.setLocationFrom(from);
        pair.setLocationTo(to);
        pair.setId(model.getId());

        return pair;
    }


    public LocationModel transformToLocationModel(Location entity) {
        return modelMapper.map(entity, LocationModel.class);
    }

    public LocationPairModel transformToLocationPairModel(LocationPair entity) {
        return modelMapper.map(entity, LocationPairModel.class);
    }

    public List<LocationModel> transformToLocationModelList(List<Location> entityList) {

        return entityList.stream().map(this::transformToLocationModel).collect(Collectors.toList());
    }

    public List<LocationPairModel> transformToLocationPairModelList(List<LocationPair> entityList) {

        return entityList.stream().map(this::transformToLocationPairModel).collect(Collectors.toList());
    }

}
