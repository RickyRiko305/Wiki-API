# Wiki-API

#### Functionalities covered :
-	Get Movies on ascending order of title. | localhost:8080/movies/name/ASCENDING_ORDER |
-	Get Movies on descending order of title. | localhost:8080/movies/name/DESCENDING_ORDER |
-	Get Movies on ascending order of release date. | localhost:8080/movies/release/ACENDING_ORDER |
-	Get Movies on descending order of release date. | localhost:8080/movies/release/DECENDING_ORDER |
-	Get a particular movie details (actor, director, producer, music composer, production house, release date, duration, language, budget, box office collection). | localhost:8080/movieData/{movieName} | (movieName should be as in the file Movie_List.txt)

Note :  Some methods takes time to give the result so wait for the execution of complete. It happened due to fetching of data for different movies.

Note: Since I have used Spring Boot framework its giving the json format data where the API is called for get request.
