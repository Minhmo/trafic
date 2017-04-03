package lt.vu.Services;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import lt.vu.entities.Location;
import lt.vu.entities.LocationPair;
import lt.vu.entities.TripInformation;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.util.Date;

/**
 * Created by minhmo on 17.3.19.
 */
@ApplicationScoped
public class GoogleLocationAPIService {
    public static final String API_KEY = "AIzaSyA161XK-wPSnhxZWN_1k6V7eFIVrfQy8n4";
    private final GeoApiContext context;

    public GoogleLocationAPIService() {
        context = new GeoApiContext().setApiKey(API_KEY);
    }
    public void fillLocationInfo(Location location) throws Exception {
        if (location.getAdress() != null){
            GeocodingResult[] results =  GeocodingApi.geocode(context, location.getAdress()).await();
            if (results.length > 0){
                location.setAdress(results[0].formattedAddress);
                location.setXCoordinate((float) results[0].geometry.location.lat);
                location.setYCoordinate((float) results[0].geometry.location.lng);
            }
        }
    }

    public TripInformation getTripInformation(LocationPair location) throws Exception {
        DistanceMatrixApiRequest request = initRequest(location);
        DistanceMatrix response = request.await();

        DistanceMatrixRow row = response.rows[0];

        TripInformation tripInformation = new TripInformation();
        tripInformation.setLocationPair(location);

        tripInformation.setEstimatedSeconds((int) row.elements[0].duration.inSeconds);
        tripInformation.setEstimationTime(new Date());
        tripInformation.setEstimatedHours((int) row.elements[0].duration.inSeconds % 3600);
        tripInformation.setEstimatedMinutes((int) row.elements[0].duration.inSeconds % 60);

        return tripInformation;
    }

    private DistanceMatrixApiRequest initRequest(LocationPair location) {
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context);
        request.destinations(location.getLocationTo().getAdress());
        request.origins(location.getLocationFrom().getAdress());
        request.mode(TravelMode.DRIVING);
        request.units(Unit.METRIC);
        return request;
    }


}
