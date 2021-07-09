package io.javabrains.springbootconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

    @Value("${my.greeting}")
    private String greetingMessage;

    @Value("${my.list.values}")
    private List<String> listOfValues;

    @Value("#{${dbValues}}")
    private Map<String, String> dbValues;

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment environment; //Avoid using it

    @GetMapping("/greeting")
    public String greeting(){
        return greetingMessage + listOfValues + dbValues + dbSettings.getHost() + dbSettings.getPort();
    }

    @GetMapping("/env/details")
    public String[] envDetails(){
        return environment.getActiveProfiles();
    }
}
