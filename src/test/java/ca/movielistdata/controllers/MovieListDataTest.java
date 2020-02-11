package ca.movielistdata.controllers;

import ca.movielistdata.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MovieListDataTest {
    @InjectMocks
    MovieListData movieListData;
    String movieName = "The%20Matrix";

    @Test
    void getMovies() {
        ArrayList<String> Movies = new ArrayList<String>();
        movieListData = new MovieListData();
        Movies = movieListData.getMovies();
        System.out.println(Movies);
    }

    @Test
    void getMoviesDec() {
        ArrayList<String> Movies = new ArrayList<String>();
        movieListData = new MovieListData();
        Movies = movieListData.getMoviesDec();
        System.out.println(Movies);
    }

    @Test
    void getMoviesReleaseAsc() {
        ArrayList<String> release = new ArrayList<String>();
        movieListData = new MovieListData();
        release = movieListData.getMoviesReleaseAsc();
        System.out.println(release);
    }

    @Test
    void getMoviesReleaseDesc() {
        ArrayList<String> reverseOrder = new ArrayList<String>();
        movieListData = new MovieListData();
        reverseOrder = movieListData.getMoviesReleaseDesc();
        System.out.println(reverseOrder);
    }

    @Test
    void scanSingleItem() throws IOException {
        Movie movie = new Movie();
        movieListData = new MovieListData();
        movie = movieListData.scanSingleItem(movieName);
        System.out.println(movie);
    }
}