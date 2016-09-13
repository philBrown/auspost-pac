package au.id.philipbrown.auspost.pac;

import au.id.philipbrown.auspost.pac.model.Country;
import au.id.philipbrown.auspost.pac.model.Service;
import au.id.philipbrown.auspost.pac.request.DomesticLetterServiceRequest;
import au.id.philipbrown.auspost.pac.request.DomesticParcelServiceRequest;
import au.id.philipbrown.auspost.pac.request.InternationalServiceRequest;

import java.util.List;

public interface PostageAssessmentCalculator {
    /**
     * @return a list of countries which are accepted by the PAC API.
     */
    List<Country> getCountries();

    /**
     * @param request    request parameter object.
     * @return the available services and additional options for a domestic letter.
     */
    List<Service> getDomesticLetterServices(DomesticLetterServiceRequest request);

    /**
     * @param request    request parameter object.
     * @return the available services and additional options for an international letter.
     */
    List<Service> getInternationalLetterServices(InternationalServiceRequest request);

    /**
     * @param request    request parameter object.
     * @return the available services and additional options for a domestic parcel.
     */
    List<Service> getDomesticParcelServices(DomesticParcelServiceRequest request);

    /**
     * @param request    request parameter object.
     * @return the available services and additional options for an international parcel.
     */
    List<Service> getInternationalParcelServices(InternationalServiceRequest request);
}
