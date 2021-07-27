package com.textoit.repository;

import com.textoit.entity.Movie;
import com.textoit.entity.dto.AwardsOutput;
import com.textoit.entity.dto.MovieDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.*;

@ApplicationScoped
public class AwardsRepository {

    @Inject
    EntityManager emNoTenant;

    public AwardsOutput searchAwards() {
        List<MovieDTO> moviesDTO = new ArrayList<>();

        this.findAllProduces().forEach(produces -> {
            if (!produces.isEmpty())
                createMovieDTO(executeQuery(produces), produces, moviesDTO);
        });

        Collections.sort(moviesDTO, Collections.reverseOrder());

        AwardsOutput output = new AwardsOutput();
        output.setMax(List.of(moviesDTO.get(0)));
        output.setMin(List.of(moviesDTO.get(moviesDTO.size() - 1)));

        return output;
    }

    private ArrayList<String> findAllProduces() {
        String queryProduces = "SELECT produces FROM Movie WHERE winner = 'yes' GROUP BY produces ORDER BY produces";
        List<?> producesAll = Movie.list(queryProduces);

        Set<String> producesName = new HashSet<>();
        producesAll.forEach(obj -> {
            String value = (String) obj;

            String[] nameSplitProduces = value.split("\\,|\\h+and\\h+");
            for (String name : nameSplitProduces)
                producesName.add(name.trim());
        });

        return new ArrayList<>(producesName);
    }

    private List<?> executeQuery(String produces) {
        String query = "SELECT MAX(year) as followingWin, MIN(year) as previousWin, (MAX(year) - MIN(year) ) as year FROM Movie WHERE winner = 'yes'  AND REGEXP_LIKE( PRODUCES , :produces) is true  ORDER BY year DESC";

        return emNoTenant.createQuery(query)
                .setParameter("produces", produces)
                .getResultList();
    }

    private void createMovieDTO(List<?> movies, String producer, List<MovieDTO> moviesReturn) {
        for (Object obj : movies) {
            Object[] value = (Object[]) obj;

            MovieDTO movie = new MovieDTO();
            movie.setFollowingWin((Integer) value[0]);
            movie.setPreviousWin((Integer) value[1]);
            movie.setInterval((Integer) value[2]);
            movie.setProducer(producer);

            if (movie.getInterval() > 0)
                moviesReturn.add(movie);
        }
    }

}
