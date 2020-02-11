package ca.movielistdata.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private Scanner scan;
    private ArrayList<String> movies = new ArrayList<String>();
    public void openFile(){
        try{
            scan = new Scanner(new File("F:/Program Files/MovieListData/src/main/java/ca/movielistdata/controllers/Movie_List.txt"));//../Resource/
        }catch (Exception e){
            System.out.println("Could not find the movie-list file");
        }
    }

    public ArrayList<String> readFile(){
        while (scan.hasNextLine()){
            String movie = scan.nextLine();
            movies.add(movie);
        }
        return movies;
    }

    public void closeFile(){
        scan.close();
    }

}
