package org.mindswap.utils.IMDBAPI;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/imdb")
public class ImdbController {

    private ImdbService imdbService;

    @Autowired
    public ImdbController(ImdbService imdbService) {
        this.imdbService = imdbService;
    }

    @GetMapping("/manager/get-movie-details/{titleId}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public String requestMovieDetails(@PathVariable String titleId) throws Exception {
        String apiKey = "010d3e5bc7msha728d029490fd2cp118d80jsnc074a334fa3a"; // Replace with your RapidAPI API key


        HttpResponse<String> response = Unirest.get("https://online-movie-database.p.rapidapi.com/title/get-overview-details?tconst="+titleId+"&currentCountry=US")
                .header("X-RapidAPI-Key", "010d3e5bc7msha728d029490fd2cp118d80jsnc074a334fa3a")
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .asString();

        imdbService.saveToDatabase(response);

        return response.getBody();
    }


    @GetMapping("/manager/get-top-rated")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public String requestTopRatedMovies() throws Exception {
        String apiKey = "010d3e5bc7msha728d029490fd2cp118d80jsnc074a334fa3a";
        HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-top-rated-movies")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                .asString();
        List<String> listOfIds = new ArrayList<>();
        JSONArray responseArray = new JSONArray(response.getBody());

        System.out.println("response body: " + responseArray);

        for (int i = 0; i < 5; i++) {
            JSONObject obj = responseArray.getJSONObject(i);
            String id = obj.getString("id");
            String titleId = id.replace("title", "").replace("/","");
            listOfIds.add(titleId);
        }
        List<HttpResponse<String>> responseList  = new ArrayList<>();
        listOfIds.forEach(title -> {
            try {
                responseList.add(Unirest.get("https://online-movie-database.p.rapidapi.com/title/get-overview-details?tconst="
                                .concat(title).concat("&currentCountry=US"))
                        .header("X-RapidAPI-Key", "010d3e5bc7msha728d029490fd2cp118d80jsnc074a334fa3a")
                        .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                        .asString());
                System.out.println("For each iteration: " + responseList);
            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(listOfIds);
        System.out.println("-".repeat(50));
        System.out.println(responseList);
        imdbService.saveTopRatedMovies(responseList);
        return "Top Rated Movies have been Added";
    }
    @GetMapping("/manager/get-movies")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<List<ImdbMovieModel>> viewMovies() {
        return new ResponseEntity<>(imdbService.getAllImdbMovies(), HttpStatus.OK);
    }
}



