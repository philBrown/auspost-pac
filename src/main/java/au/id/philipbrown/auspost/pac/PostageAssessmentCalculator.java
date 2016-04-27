package au.id.philipbrown.auspost.pac;

import au.id.philipbrown.auspost.pac.model.Country;
import au.id.philipbrown.auspost.pac.model.Service;

import java.util.List;

public interface PostageAssessmentCalculator {
    /**
     * @return a list of countries which are accepted by the PAC API.
     */
    List<Country> getCountries();

    /**
     * @param length       letter length in mm.
     * @param width        letter width in mm.
     * @param thickness    letter thickness in mm.
     * @param weight       letter weight in grams.
     * @return the available services and additional options for a domestic letter.
     */
    List<Service> getDomesticLetterServices(int length, int width, int thickness, int weight);

    /**
     * @param country    destination country.
     * @param weight     letter weight in grams.
     * @return the available services and additional options for an international letter.
     */
    List<Service> getInternationalLetterServices(Country country, int weight);

    /**
     * @param fromPostcode    postcode the parcel will be sent from.
     * @param toPostcode      postcode the parcel will be sent to.
     * @param length          parcel length in cm.
     * @param width           parcel width in cm.
     * @param height          parcel height in cm.
     * @param weight          parcel weight in kg.
     * @return the available services and additional options for a domestic parcel.
     */
    List<Service> getDomesticParcelServices(String fromPostcode, String toPostcode, int length, int width, int height, double weight);

    /**
     * @param country    destination country.
     * @param weight     parcel weight in kg.
     * @return the available services and additional options for an international parcel.
     */
    List<Service> getInternationalParcelServices(Country country, double weight);
}
