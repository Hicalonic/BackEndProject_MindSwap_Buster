package org.mindswap.utils.IMDBAPI;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/imdb")
public class ImdbController {

    private ImdbService imdbService;

    @Autowired
    public ImdbController(ImdbService imdbService) {
        this.imdbService = imdbService;
    }

    @GetMapping("/manager/get-movie-details/{titleId}")
    //@PreAuthorize("hasRole('MANAGER')")
    public String getMovieDetails(@PathVariable String titleId) throws Exception {
        String apiKey = "010d3e5bc7msha728d029490fd2cp118d80jsnc074a334fa3a"; // Replace with your RapidAPI API key


        HttpResponse<String> response = Unirest.get("https://online-movie-database.p.rapidapi.com/title/get-overview-details?tconst=tt0944947&currentCountry=US")
                .header("X-RapidAPI-Key", "010d3e5bc7msha728d029490fd2cp118d80jsnc074a334fa3a")
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .asString();

        imdbService.saveToDatabase(response);

        return response.getBody();
    }


}

