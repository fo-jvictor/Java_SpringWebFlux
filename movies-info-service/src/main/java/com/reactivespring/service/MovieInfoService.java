package com.reactivespring.service;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.repository.MovieInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieInfoService {

    private MovieInfoRepository movieInfoRepository;

    public MovieInfoService(MovieInfoRepository movieInfoRepository){
        this.movieInfoRepository = movieInfoRepository;
    }
    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
       return this.movieInfoRepository.save(movieInfo);
    }

    public Flux<MovieInfo> getAllMovies(){
        return movieInfoRepository.findAll();
    }

    public Mono<MovieInfo> getMovieById(String id) {
        return movieInfoRepository.findById(id);
    }

    public Mono<MovieInfo> updateMovieInfo(MovieInfo movieInfo, String id) {
       return movieInfoRepository.findById(id)
                .flatMap(movie -> {
                    movie.setCast(movieInfo.getCast());
                    movie.setName(movieInfo.getName());
                    movie.setYear(movie.getYear());
                    movie.setRelease_date(movie.getRelease_date());
                    return movieInfoRepository.save(movie);
                });
    }

    public Mono<Void> deleteMovie(String id){
        return movieInfoRepository.deleteById(id);
    }
}
