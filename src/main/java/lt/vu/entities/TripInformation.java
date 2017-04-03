package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by minhmo on 17.2.28.
 */
@Entity
@Getter
@Setter
public class TripInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_from_id")
//    public Location from;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_to_id")
//    public Location to;

    @ManyToOne
    @JoinColumn(name = "location_pair_id")
    private LocationPair locationPair;

    public int estimatedHours;

    public int estimatedSeconds;

    public int estimatedMinutes;

    public Date estimationTime;

}
