
package domain.muktevi.cityguide.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Contact implements Serializable{

    private static final long serialVersionUID = 1L;
    private String phone;
    private String formattedPhone;

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
