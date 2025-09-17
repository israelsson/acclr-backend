package se.accelor.accelor.core.service;

import org.springframework.stereotype.Service;
import se.accelor.accelor.core.model.DataForFrontEnd;

@Service
public class FrontEndService {

    public DataForFrontEnd getDataForFrontEnd() {

        return new DataForFrontEnd();
    }
}
