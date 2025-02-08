package csc340SD.API.Prototype.controller;

import csc340SD.API.Prototype.model.Dog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DogController {

    @GetMapping("/dog")
    public ResponseEntity<Dog> getDog() {
        String apiUrl = "https://api.thedogapi.com/v1/images/search";

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        // Debugging: Print the raw JSON response in IntelliJ
        System.out.println("Raw JSON Response: " + jsonResponse);

        try {
            // Convert JSON String into a Dog list (since API returns a JSON array)
            ObjectMapper objectMapper = new ObjectMapper();
            List<Dog> dogs = Arrays.asList(objectMapper.readValue(jsonResponse, Dog[].class));

            // Return the first dog in the array
            return dogs.isEmpty()
                    ? ResponseEntity.ok(new Dog("N/A", "No Image Available"))
                    : ResponseEntity.ok(dogs.get(0));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Dog("error", "Failed to fetch data"));
        }
    }
}

