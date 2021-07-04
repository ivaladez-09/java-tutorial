package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.Catalog;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/{userId}")
    public List<Catalog> getCatalog(@PathVariable("userId") String userId){

        // Get all rated movie ID from rating-data-service
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/rating/user/" + userId, UserRating.class);

        return userRating.getRatings().stream().map(rating -> {
            // For each movie ID, call movie-info-service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);
            return new Catalog(movie.getName(), "Test", rating.getRating());
        }).collect(Collectors.toList());

    }

}
