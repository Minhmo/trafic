package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by minhmo on 17.2.28.
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "adress")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Size(max = 80)
    public String adress;

    public String cityName;

    public String districtName;

    public Float xCoordinate;

    public Float yCoordinate;

    public Date additionDate;
}
