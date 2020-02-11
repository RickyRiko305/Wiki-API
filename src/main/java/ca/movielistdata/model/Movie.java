package ca.movielistdata.model;

import java.time.LocalDate;

public class Movie {
    private long id;
    private String movieName;
    private String actorName;
    private String director;
    private String producer;
    private String musicComposer;
    private String productionHouse;
    private LocalDate releaseDate;
    private String duration;
    private String language;
    private String budget;
    private String boxOfficeCollection;
    private String paragraph;

    public Movie(){}

    public Movie(long id, String movieName, String actorName, String director, String producer, String musicComposer, String productionHouse, LocalDate releaseDate, String duration, String language, String budget, String boxOfficeCollection, String paragraph) {
        this.id = id;
        this.movieName = movieName;
        this.actorName = actorName;
        this.director = director;
        this.producer = producer;
        this.musicComposer = musicComposer;
        this.productionHouse = productionHouse;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.language = language;
        this.budget = budget;
        this.boxOfficeCollection = boxOfficeCollection;
        this.paragraph = paragraph;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getMusicComposer() {
        return musicComposer;
    }

    public void setMusicComposer(String musicComposer) {
        this.musicComposer = musicComposer;
    }

    public String getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(String productionHouse) {
        this.productionHouse = productionHouse;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBoxOfficeCollection() {
        return boxOfficeCollection;
    }

    public void setBoxOfficeCollection(String boxOfficeCollection) {
        this.boxOfficeCollection = boxOfficeCollection;
    }
}
