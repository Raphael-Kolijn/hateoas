package hello;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Greeting extends ResourceSupport {

    private final ArrayList content;

    @JsonCreator
    public Greeting(@JsonProperty("content") ArrayList content) {
        this.content = content;
    }

    public ArrayList getContent() {
        return content;
    }
}
