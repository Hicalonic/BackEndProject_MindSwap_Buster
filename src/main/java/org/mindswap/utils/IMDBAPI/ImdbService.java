package org.mindswap.utils.IMDBAPI;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImdbService {

    private ImdbRepository imdbRepository;

    @Autowired
    public ImdbService(ImdbRepository imdbRepository) {
        this.imdbRepository = imdbRepository;
    }


    public void saveToDatabase(HttpResponse<String> response) {
        // Transform into a java object
        JSONObject responseBody = new JSONObject(response.getBody());
        System.out.println(responseBody);

        // Extract the relevant information
        String imdbId = responseBody.getString("id");
        String title = responseBody.getJSONObject("title").getString("title");
        double rating = responseBody.getJSONObject("ratings").getDouble("rating");
        String movieGenre = responseBody.getJSONArray("genres").getString(0);
        String imageUrl = responseBody.getJSONObject("title").getJSONObject("image").getString("url");


        // Creates a new movie instance from the API to our model
        ImdbMovieModel movie = ImdbMovieModel.builder()
                .imdbId(imdbId)
                .title(title)
                .rating(rating)
                .movieGenre(movieGenre)
                .imageCoverUrl(imageUrl)
                .build();

        // Save into our repository and db
        imdbRepository.save(movie);
    }
}
