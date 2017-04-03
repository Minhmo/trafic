package lt.vu.Models.Locations;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by minhmo on 17.3.21.
 */
@Getter
@Setter
public class LocationModel {

    public Long id;

    public String adress;

    public Date additionDate;
}
