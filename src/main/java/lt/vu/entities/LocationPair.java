package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by minhmo on 17.3.19.
 */
@Entity
@Table(name = "location_pairs", schema = "public", catalog = "traffic")
@Getter
@Setter
@EqualsAndHashCode
public class LocationPair implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "locationPair", cascade = CascadeType.ALL)
    private List<TripInformation> tripInformations;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_from")
    private Location locationFrom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_to")
    private Location locationTo;

    @Column(name = "additiondate")
    private Date additionDate;

    public void addTripInfo(TripInformation tripInformation) {
        if (tripInformation != null){
            tripInformations.add(tripInformation);
            tripInformation.setLocationPair(this);
        }
    }
}
