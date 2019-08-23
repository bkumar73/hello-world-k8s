package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Client {

    @Value("${world-service-url}")
    private String worldServiceUrl;

    public String getResponse() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(worldServiceUrl, String.class);
        return response.getBody();
    }
}
