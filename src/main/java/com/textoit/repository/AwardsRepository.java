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

    private static final String QUERY_SEARCH_PRODUCES = "SELECT produces FROM Movie WHERE winner = 'yes' GROUP BY produces ORDER BY produces";
    private static final String QUERY_SEARCH_YEARS_BY_PRODUCES = "SELECT year FROM Movie WHERE winner = 'yes' AND REGEXP_LIKE( PRODUCES , :produces) is true ORDER BY year DESC";

    @Inject
    EntityManager entityManager;

    public AwardsOutput searchAwards() {
        List<MovieDTO> moviesDTO = new ArrayList<>();
        this.findAllProduces().forEach(produces -> {
            if (!produces.isEmpty()) {
                List<Integer> years = (List<Integer>) executeQueryByProduces(QUERY_SEARCH_YEARS_BY_PRODUCES, produces);

                if (years.size() > 1) {
                    MovieDTO movie = new MovieDTO();

                    Integer max = years.get(0);
                    Integer min = years.get(1);

                    movie.setPreviousWin(min);
                    movie.setFollowingWin(max);
                    movie.setInterval(max - min);
                    movie.setProducer(produces);

                    moviesDTO.add(movie);
                }
            }
        });

        Collections.sort(moviesDTO, Collections.reverseOrder());

        AwardsOutput output = new AwardsOutput();
        output.setMax(List.of(moviesDTO.get(0)));
        output.setMin(List.of(moviesDTO.get(moviesDTO.size() - 1)));

        return output;
    }

    private ArrayList<String> findAllProduces() {
        List<?> producesAll = Movie.list(QUERY_SEARCH_PRODUCES);

        Set<String> producesName = new HashSet<>();
        producesAll.forEach(obj -> {
            String value = (String) obj;

            String[] nameSplitProduces = value.split("\\,|\\h+and\\h+");
            for (String name : nameSplitProduces)
                producesName.add(name.trim());
        });

        return new ArrayList<>(producesName);
    }

    private List<?> executeQueryByProduces(String query, String produces) {
        return entityManager.createQuery(query)
                .setParameter("produces", produces)
                .getResultList();
    }

}
