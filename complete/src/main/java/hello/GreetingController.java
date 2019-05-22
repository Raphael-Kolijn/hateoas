package hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    ArrayList<String> greetings = new ArrayList<>();

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "world") String name,
            @RequestParam(value = "country", required = false, defaultValue = "NL") String country)
    {

        greetings.add(String.format(TEMPLATE, name + "from  "+ country));
        Greeting greeting = new Greeting(greetings);
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name, country)).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(String.format(name + "endpoint"), String.format("countryForEndpoint"))).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingController.class).greeting("respourceEndPoint","/POST")).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
