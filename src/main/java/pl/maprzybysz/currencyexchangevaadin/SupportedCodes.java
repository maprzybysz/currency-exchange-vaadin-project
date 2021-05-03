
package pl.maprzybysz.currencyexchangevaadin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "result",
    "supported_codes"
})
@Generated("jsonschema2pojo")
public class SupportedCodes {

    @JsonProperty("result")
    private String result;
    @JsonProperty("supported_codes")
    private List<List<String>> supportedCodes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("supported_codes")
    public List<List<String>> getSupportedCodes() {
        return supportedCodes;
    }

    @JsonProperty("supported_codes")
    public void setSupportedCodes(List<List<String>> supportedCodes) {
        this.supportedCodes = supportedCodes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "SupportedCodes{" +
                "result='" + result + '\'' +
                ", supportedCodes=" + supportedCodes +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
