package org.mindswap.utils.IMDBAPI;

import com.mashape.unirest.http.HttpResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindswap.exceptions.ImdbMovieModelNotFoundException;


import java.util.List;

@Service
public class ImdbService {

    private ImdbRepository imdbRepository;

    @Autowired
    public ImdbService(ImdbRepository imdbRepository) {
        this.imdbRepository = imdbRepository;
    }


    public void saveToDatabase(HttpResponse<String> response) {
        JSONObject responseBody = new JSONObject(response.getBody());

        String imdbId = responseBody.getString("id");
        String title = responseBody.getJSONObject("title").getString("title");
        double rating = responseBody.getJSONObject("ratings").getDouble("rating");
        String movieGenre = responseBody.getJSONArray("genres").getString(0);
        String imageUrl = responseBody.getJSONObject("title").getJSONObject("image").getString("url");


        ImdbMovieModel movie = ImdbMovieModel.builder()
                .imdbId(imdbId)
                .title(title)
                .imdbRating(rating)
                .movieGenre(movieGenre)
                .imageCoverUrl(imageUrl)
                .build();


        imdbRepository.save(movie);
    }

    public void saveTopRatedMovies(List<HttpResponse<String>> responseList) {
        for (HttpResponse<String> response : responseList) {
            JSONObject responseBody = new JSONObject(response.getBody());

            String imdbId = responseBody.getString("id");
            String title = responseBody.getJSONObject("title").getString("title");
            double rating = responseBody.getJSONObject("ratings").getDouble("rating");
            String movieGenre = responseBody.getJSONArray("genres").getString(0);
            String imageUrl = responseBody.getJSONObject("title").getJSONObject("image").getString("url");

            ImdbMovieModel movie = ImdbMovieModel.builder()
                    .imdbId(imdbId)
                    .title(title)
                    .imdbRating(rating)
                    .movieGenre(movieGenre)
                    .imageCoverUrl(imageUrl)
                    .build();
            imdbRepository.save(movie);
        }
    }

    public List<ImdbMovieModel> getAllImdbMovies() {
        return imdbRepository.findAll();
    }

    public ImdbMovieModel getImdbMovieById(Long id) {
        return imdbRepository.findById(id).orElseThrow(ImdbMovieModelNotFoundException::new);
    }

    public List<ImdbMovieModel> getMoviesById(List<Long> ids) {
        return imdbRepository.findAllById(ids);
    }
}
