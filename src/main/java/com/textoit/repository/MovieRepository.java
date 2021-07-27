package com.textoit.repository;

import com.textoit.entity.Movie;
import com.textoit.entity.dto.AwardsOutput;
import com.textoit.entity.dto.MovieDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@ApplicationScoped
public class MovieRepository {

    @Transactional
    public void persist(Movie movie) {
        if (movie != null)
            movie.persist();
    }

    @Transactional
    public void update(Movie movie) {
        Movie movieEntity = Movie.findById(movie.getId());
        movieEntity.setProduces(movie.getProduces());
        movieEntity.setStudios(movie.getStudios());
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setWinner(movie.getWinner());
        movieEntity.setYear(movie.getYear());

        movieEntity.persist();
    }

    @Transactional
    public void delete(Long id) {
        Movie movie = Movie.findById(id);

        if (movie != null)
            movie.delete();
    }

    @Transactional
    public void persistAll(List<Movie> movies) {
        if (movies != null) {
            movies.forEach(movie -> {
                movie.persist();
            });
        }
    }

    @Transactional
    public List<Movie> listAll() {
        return Movie.listAll();
    }

}
