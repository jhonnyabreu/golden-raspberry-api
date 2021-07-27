package com.textoit.factory;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.textoit.entity.Movie;
import com.textoit.repository.MovieRepository;
import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Startup
public class DatabaseFactory {

    private static final String FILE_NAME = "movielist.csv";
    private static final String RESOURCE_DIRECTORY_NAME = "data";
    private static final String TMP_FILE_NAME = "/tmp";

    private static final String HEADER_YEAR = "year";
    private static final String HEADER_TITLE = "title";
    private static final String HEADER_STUDIOS = "studios";
    private static final String HEADER_PRODUCES = "producers";
    private static final String HEADER_WINNER = "winner";

    private static int INDEX_YEAR;
    private static int INDEX_TITLE;
    private static int INDEX_STUDIOS;
    private static int INDEX_PRODUCES;
    private static int INDEX_WINNER;

    @Inject
    MovieRepository repository;

    @PostConstruct
    public void generateDatabaseInformation() {
        try {
            InputStream loadData = getClass().getClassLoader().getResourceAsStream(RESOURCE_DIRECTORY_NAME + File.separator + FILE_NAME);
            File movieFile = new File(TMP_FILE_NAME + File.separator + FILE_NAME);
            Files.copy(loadData, movieFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            readerCsvFile(movieFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readerCsvFile(File movieFile) throws IOException, CsvValidationException {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(movieFile)).withCSVParser(parser).build();
        String[] rows;

        List<Movie> movies = new ArrayList<>();

        boolean readerHeader = false;
        while ((rows = csvReader.readNext()) != null) {
            if (!readerHeader) {
                headerIndex(rows);
                readerHeader = true;
                continue;
            }

            Movie movie = new Movie();
            movie.setYear(Integer.parseInt(rows[INDEX_YEAR]));
            movie.setTitle(rows[INDEX_TITLE]);
            movie.setStudios(rows[INDEX_STUDIOS]);
            movie.setProduces(rows[INDEX_PRODUCES]);
            movie.setWinner(rows[INDEX_WINNER]);

            movies.add(movie);
        }

        repository.persistAll(movies);
    }

    private void headerIndex(String[] rows){
        for (int i = 0; i < rows.length; i++) {
            String cell = rows[i];

            switch (cell) {
                case HEADER_YEAR:
                    INDEX_YEAR = i;
                case HEADER_PRODUCES:
                    INDEX_PRODUCES = i;
                case HEADER_STUDIOS:
                    INDEX_STUDIOS = i;
                case HEADER_TITLE:
                    INDEX_TITLE = i;
                case HEADER_WINNER:
                    INDEX_WINNER = i;
            }
        }
    }
}
