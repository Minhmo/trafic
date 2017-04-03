package lt.vu.Models.Locations;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by minhmo on 17.3.22.
 */
@Getter
@Setter
public class LocationPairModel {

    private Integer id;

    private String locationFromAdress;

    private Integer locationFromId;

    private String locationToAdress;

    private Integer locationToId;

    private List<TripInformationModel> tripInformations;

}
