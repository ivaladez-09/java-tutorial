package io.javabrains.moviecatalogservice.resources;


import io.javabrains.moviecatalogservice.models.Catalog;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@EnableHystrixDashboard
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;


    @GetMapping("/{userId}")
    public List<Catalog> getCatalog(@PathVariable("userId") String userId){

        // Get all rated movie ID from rating-data-service
        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getRatings().stream()
                .map(rating -> movieInfo.getCatalog(rating))
                .collect(Collectors.toList());
    }
}
