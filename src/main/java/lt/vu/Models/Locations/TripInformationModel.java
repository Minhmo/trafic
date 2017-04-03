package lt.vu.Models.Locations;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by minhmo on 17.4.3.
 */

@Getter
@Setter
public class TripInformationModel {

    public Integer estimatedHours;

    public Integer estimatedSeconds;

    public Integer id;

    public Date additionDate;

}
