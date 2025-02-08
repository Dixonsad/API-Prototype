package csc340SD.API.Prototype.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dog {
    private String id;
    private String url;

    public Dog() {}

    public Dog(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
