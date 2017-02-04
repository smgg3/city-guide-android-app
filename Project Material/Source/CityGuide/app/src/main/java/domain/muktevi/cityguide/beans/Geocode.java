
package domain.muktevi.cityguide.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Geocode implements Serializable{

    private static final long serialVersionUID = 1L;
    private String what;
    private String where;
    private Feature feature;
    private List<Object> parents = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The what
     */
    public String getWhat() {
        return what;
    }

    /**
     * 
     * @param what
     *     The what
     */
    public void setWhat(String what) {
        this.what = what;
    }

    /**
     * 
     * @return
     *     The where
     */
    public String getWhere() {
        return where;
    }

    /**
     * 
     * @param where
     *     The where
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * 
     * @return
     *     The feature
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * 
     * @param feature
     *     The feature
     */
    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    /**
     * 
     * @return
     *     The parents
     */
    public List<Object> getParents() {
        return parents;
    }

    /**
     * 
     * @param parents
     *     The parents
     */
    public void setParents(List<Object> parents) {
        this.parents = parents;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
