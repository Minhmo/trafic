package lt.vu.Providers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;

/**
 * Created by minhmo on 17.3.9.
 */

@ApplicationScoped
public class Resources {

    @PersistenceUnit
    private EntityManagerFactory emf;


    @Produces
    @RequestScoped
    private EntityManager createJTAEntityManager() {

        return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
    }

    private void closeUnsynchronizedEntityManager(@Disposes EntityManager em) {
        em.close();
    }
}
