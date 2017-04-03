package lt.vu.Providers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by minhmo on 17.3.21.
 */
@ApplicationScoped
public class MapperProvider {

    @Produces
    @ApplicationScoped
    private ModelMapper getEntityMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
    }
}
