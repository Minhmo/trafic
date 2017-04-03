package lt.vu.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhmo on 17.3.19.
 */
@Setter
@Getter
public class SaveResult {
    public SaveResult() {
        errors = new ArrayList<Error>();
        warnings = new ArrayList<Error>();
    }
    public List<Error> errors;
    public List<Error> warnings;

    public long entityId;
}
