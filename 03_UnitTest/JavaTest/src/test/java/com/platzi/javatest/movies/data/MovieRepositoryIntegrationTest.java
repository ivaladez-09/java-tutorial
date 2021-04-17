package com.platzi.javatest.movies.data;

import com.platzi.javatest.movies.model.Genre;
import com.platzi.javatest.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        // Crear una base de datos en memoria RAM
        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        // Manda llamar el script sql
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {
        Collection<Movie> movies = movieRepository.findAll();

        assertEquals( movies,  Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        ));
    }

    @Test
    public void load_movie_by_id() {
        Movie movie = movieRepository.findById(2);

        assertEquals( movie,  new Movie(2, "Memento", 113, Genre.THRILLER));
    }

    @Test
    public void insert_a_movie() {
        Movie movie = new Movie(4,"Super 8", 112, Genre.THRILLER);
        movieRepository.saveOrUpdate(movie);
        Movie movieFromDB = movieRepository.findById(4);

        assertEquals(movieFromDB, movie);
    }

    @After
    public void tearDown() throws Exception {
        // Remove H2 files after each execution of the DB
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}