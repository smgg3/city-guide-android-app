
package domain.muktevi.cityguide.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response implements Serializable{

    private static final long serialVersionUID = 1L;
    private List<Venue> venues = new ArrayList<Venue>();
    private Geocode geocode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The venues
     */
    public List<Venue> getVenues() {
        return venues;
    }

    /**
     * 
     * @param venues
     *     The venues
     */
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    /**
     * 
     * @return
     *     The geocode
     */
    public Geocode getGeocode() {
        return geocode;
    }

    /**
     * 
     * @param geocode
     *     The geocode
     */
    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
