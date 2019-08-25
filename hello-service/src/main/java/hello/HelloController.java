package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Client client;

    @Value("${message}")
    private String message;

    @RequestMapping("/")
    public String getData(@RequestHeader HttpHeaders headers) {
        try{
            return message + " "+client.getResponse(headers);
        }
        catch (Exception e){
            return "world-service not reachable!";
        }
    }
}
