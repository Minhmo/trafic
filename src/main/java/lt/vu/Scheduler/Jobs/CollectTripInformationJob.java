package lt.vu.Scheduler.Jobs;

import lt.vu.Scheduler.Collectors.DataCollector;
import lt.vu.Scheduler.Collectors.Declarations.IDataCollector;

import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by minhmo on 17.3.19.
 */
@Local
@Singleton
public class CollectTripInformationJob {

    @Inject
    IDataCollector collector;

    @Schedule(second = "20", minute = "*", hour = "*")
    public void collectTripInformation(Timer timer) {

        System.out.println("Test");
        try {
            collector.CollectData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
