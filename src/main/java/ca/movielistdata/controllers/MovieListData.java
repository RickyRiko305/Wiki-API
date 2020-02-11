package ca.movielistdata.controllers;

import ca.movielistdata.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@Service
public class MovieListData {
    HashMap<String,String> Item;
    ArrayList<Movie> allMovies = new ArrayList<Movie>();
    Movie movie;
    String url = "https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=";//The%20Matrix";
    private AtomicLong nextID = new AtomicLong();


    @GetMapping("/hello")
    public String getHelloMessage(){
        return "Hello Spring Boot";
    }


    public void preProcess() throws IOException {
        ArrayList<String> Movies = new ArrayList<String>();
        Movies = getMovies();
        for (String i : Movies){
            //String targetURL = url + i;
            allMovies.add(scanSingleItem(i));//targetURL
            System.out.println("done");
        }
    }
    @Async
    public void sync() throws IOException {
        if(allMovies.isEmpty()){
            preProcess();
        }
    }

    @GetMapping("/movies/name/ASCENDING_ORDER")
    public ArrayList<String> getMovies(){
        ArrayList<String> Movies = new ArrayList<String>();

        FileReader fileReader = new FileReader();
        fileReader.openFile();
        Movies = fileReader.readFile();
        fileReader.closeFile();
        Collections.sort(Movies);
        return Movies;
    }

    @GetMapping("/movies/name/DESCENDING_ORDER")
    public ArrayList<String> getMoviesDec(){
        ArrayList<String> Movies = new ArrayList<String>();

        FileReader fileReader = new FileReader();
        fileReader.openFile();
        Movies = fileReader.readFile();
        fileReader.closeFile();
        Collections.sort(Movies, Collections.reverseOrder());
        return Movies;
    }


    @GetMapping("/movies/release/ASCENDING_ORDER")
    public ArrayList<String> getMoviesReleaseAsc(){
        ArrayList<String> Movies = new ArrayList<String>();

        FileReader fileReader = new FileReader();
        fileReader.openFile();
        Movies = fileReader.readFile();
        fileReader.closeFile();
        //Collections.sort(Movies, Collections.reverseOrder());
        HashMap<LocalDate,String> release = new HashMap<LocalDate, String>();
        release = releaseOrder(Movies);

        ArrayList<String> movieNameByRelease = new ArrayList<String>();
        for(HashMap.Entry<LocalDate,String> i : release.entrySet()){
            i.getKey();
            i.getValue();
            movieNameByRelease.add(i.getValue());
        }
        //Collections.reverse(v);
        //System.out.println(v);
        return movieNameByRelease;
    }

    @GetMapping("/movies/release/DESCENDING_ORDER")
    public ArrayList<String> getMoviesReleaseDesc(){
        ArrayList<String> Movies = new ArrayList<String>();

        FileReader fileReader = new FileReader();
        fileReader.openFile();
        Movies = fileReader.readFile();
        fileReader.closeFile();
        //Collections.sort(Movies, Collections.reverseOrder());
        HashMap<LocalDate,String> release = new HashMap<LocalDate, String>();
        release = releaseOrder(Movies);

        ArrayList<String> reverseOrder = new ArrayList<String>();
        for(HashMap.Entry<LocalDate,String> i : release.entrySet()){
            i.getKey();
            i.getValue();
            reverseOrder.add(i.getValue());
        }
        Collections.reverse(reverseOrder);
        //System.out.println(reverseOrder);
        return reverseOrder;
    }

    public HashMap<LocalDate,String> releaseOrder(ArrayList<String> movies){

        //ArrayList<LocalDate,String> release =
        HashMap<LocalDate,String> release = new HashMap<LocalDate, String>();

        for(int i=0; i<movies.size(); i++){
            //System.out.println(i);
            String targetURL = url + movies.get(i);
            Document document;

            try{
                document = Jsoup.connect(targetURL).ignoreContentType(true).get();
                //System.out.println(document);///
            }catch (IOException ignored){
                System.out.println("Could not scan document");
                return null;
            }


            Element table = document.select("table").first();
            Elements rows = table.select("tr");
            boolean flag = true;
            int j = 0;
            while(flag){
                Element row = rows.get(j++);
                Elements cols = row.select("th");
                String Value = cols.text();
                if(Value.equals("Release date")){
                    flag = false;
                    Elements data2 = row.select("td");
                    Elements li = data2.select("li");
                    String column2 = "";
                    if(li.isEmpty()){
                        column2 = column2 + data2.text();
                    }
                    for(Element column : li){
                        String liData = column.text();
                        column2 = column2 + liData + ", ";
                    }

                    int startPoint = 0;
                    int endPoint = 0;
                    for(int k=0; k<column2.length(); k++){
                        char var = column2.charAt(k);
                        if( var == '('){
                            startPoint = k;
                        }

                        if(var == ')'){
                            endPoint = k;
                            k = column2.length();
                        }

                    }
                    String date = column2.substring(startPoint+1,endPoint);

                    LocalDate localDate = LocalDate.parse(date);
                    release.put(localDate,movies.get(i));
                }
            }
            //release.add(localDate,)
        }
        return release;
    }
    //netstat -ano | findstr :8080
    //taskkill/PID 24833 /F

    @GetMapping("/storeData")
    public ArrayList<Movie> scanItem() throws IOException {         //HashMap<String,String>
        if(allMovies.isEmpty()){
            preProcess();
        }
        return allMovies;
    }

    @GetMapping("/movieData/{movieName}")
    public Movie scanSingleItem(@PathVariable("movieName") String Name) throws IOException {

        String targetURL = url + Name;
        Document document;

        try{
            document = Jsoup.connect(targetURL).ignoreContentType(true).get();
            //System.out.println(document);///
        }catch (IOException ignored){
            System.out.println("Could not scan document");
            return null;
        }


        Element table = document.select("table").first();
        Elements rows = table.select("tr");
        movie = new Movie();
        movie.setId(nextID.incrementAndGet());
        Integer flag = 1;
        for (Element row : rows){

            Elements data1 = row.select("th");
            Elements data2 = row.select("td");
            Elements li = data2.select("li");
            String column1 = data1.text();
            String column2 = "";
            if(li.isEmpty()){
                column2 = column2 + data2.text();
            }
            for(Element column : li){
                String liData = column.text();
                column2 = column2 + liData + ", ";
            }

            //System.out.println(column1 + "  :  " + column2);
            //Item.put(column1,column2);
            if(flag.equals(1)){
                movie.setMovieName(column1);
                flag = 0;
            }
            else if(column1.equals("Starring")){
                movie.setActorName(column2);
            }
            else if(column1.equals("Produced by")){
                movie.setProducer(column2);
            }
            else if(column1.equals("Release date")){

                int startPoint = 0;
                int endPoint = 0;
                for(int i=0; i<column2.length(); i++){
                    char var = column2.charAt(i);
                    if( var == '('){
                        startPoint = i;
                    }

                    if(var == ')'){
                        endPoint = i;
                        i = column2.length();
                    }

                }
                String date = column2.substring(startPoint+1,endPoint);

                LocalDate localDate = LocalDate.parse(date);
                //Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                //System.out.println("Date : " + localDate);
                //movie.setReleaseDate(column2.substring(startPoint+1,endPoint));
                movie.setReleaseDate(localDate);
            }
            else if(column1.equals("Directed by")){
                movie.setDirector(column2);
            }
            else if(column1.equals("Running time")){
                movie.setDuration(column2);
            }
            else if(column1.equals("Production companies") || column1.equals("Production company")){
                movie.setProductionHouse(column2);
            }
            else if(column1.equals("Music by")){
                movie.setMusicComposer(column2);
            }
            else if(column1.equals("Language")){
                movie.setLanguage(column2);
            }
            else if(column1.equals("Box office")){
                movie.setBoxOfficeCollection(column2);
            }
            else if(column1.equals("Budget")){
                movie.setBudget(column2);
            }
            else{

            }

            //Item.put("",Para);
        }
        Element paragraph = document.select("p").get(0);
        String Para = paragraph.text();
        Integer i = 1;
        while(Para.length() < 10){
            paragraph = document.select("p").get(i);
            Para = paragraph.text();
            i++;
        }
        movie.setParagraph(Para);
        return movie;
    }



}
