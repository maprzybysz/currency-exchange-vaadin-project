package pl.maprzybysz.currencyexchangevaadin;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService {
    @Value("${API-KEY}")
    private String key;


    private RestTemplate restTemplate = new RestTemplate();

    public String getConversionRate(String from, String to){
        String url = "https://v6.exchangerate-api.com/v6/"+key+"/pair/"+from+"/"+to;
        JsonNode conversionRate = restTemplate.getForObject(url, JsonNode.class).get("conversion_rate");
        return conversionRate.toString();
    }


    public List<String> getAvailableCurrencies(){
        String url = "https://v6.exchangerate-api.com/v6/"+key+"/codes";
        ArrayList<String> supportedCurrencies = new ArrayList<>();
        SupportedCodes supportedCodes = restTemplate.getForObject(url, SupportedCodes.class);
        supportedCodes.getSupportedCodes().stream().forEach(e -> supportedCurrencies.add(e.get(0)));
        return supportedCurrencies;
    }
}
