package lt.vu.Scheduler.Collectors;

import java.util.Date;
import java.util.List;

import lt.vu.Facades.LocationPairFacade;
import lt.vu.Facades.TripInformationFacade;
import lt.vu.Services.GoogleLocationAPIService;
import lt.vu.entities.LocationPair;
import lt.vu.entities.TripInformation;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by minhmo on 12/8/16.
 */
@ApplicationScoped
public class DataCollector implements lt.vu.Scheduler.Collectors.Declarations.IDataCollector {

    @Inject
    private LocationPairFacade locationPairFacade;

    @Inject
    private TripInformationFacade tripInformationFacade;

    @Inject
    private GoogleLocationAPIService googleAPI;


    public DataCollector(){
    }

    @Lock(LockType.READ)
    @Schedule(hour = "*/1")
    public void CollectData() throws Exception {
        List<LocationPair> allLocations = locationPairFacade.getAll();

        for (LocationPair location : allLocations)
        {
            tripInformationFacade.create(googleAPI.getTripInformation(location));
        }
    }
}

