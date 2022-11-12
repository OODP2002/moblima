import java.time.Duration;
import java.util.*;

/**
 * The MovieStore class stores all the information about the movies with a HashMap data structure.
 * It also interacts with the movie.txt file to read and write the data.
 * @author Zhe Kai
 * @version 1.0
 */
public class MovieStore {
    
    // Attributes
    private final String path = ("Classes/src/movies.txt");
    private static MovieStore instance = new MovieStore();
    private HashMap<String, Movie> movieHashMap = new HashMap<>();     // key=MOVIE_ID
    private TxtReaderWriter movieReaderWriter = new TxtReaderWriter(path);


    /**
     * This method searches for a Movie with the Movie ID and returns null if it does not exist.
     * @param movieID This is the unique Movie ID.
     * @return Movie This returns the Movie object with the inputted Movie ID.
     */
    // Get Movie object given movie_ID, returns null if movie does not exist
    public Movie searchMovie(String movieID) {
        return movieHashMap.get(movieID);
    }

    /**
     * This method returns the hashmap of the Movie Store
     * The key-value pair is the Movie ID and the Movie object respectively.
     * @return HashMap<String, Movie>
     */
    public HashMap<String, Movie> getMovieHashMap() {
        return movieHashMap;
    }

    /**
     * The ListTop5 method compares the movies in the MovieStore and lists the top 5 movies by movie sales or rating.
     * The listing format (sales or rating) can be controlled by the toggle parameter.
     * @param toggle This switches the listing method. When toggle is 0, the method lists the top 5 by movie sales. Else, it lists the top 5 by rating.
     */
    public void ListTop5(int toggle) {
        List<Movie> movies = new ArrayList<>(movieHashMap.values());

        // Sort by movie sales
        if (toggle == 0) {
            movies.sort(Comparator.comparing(Movie::getMovieSales).reversed());
        } else {
            movies.sort(Comparator.comparing(Movie::getAvgRating).reversed());
        }

        // List the movies
        int num = Math.min(movies.size(), 5);
        System.out.println("Top " + num + " movies by Movie Sales:");
        for (int i = 1; i <= num; i++) {
            System.out.printf("%d: %s\n", i, movies.get(i).getMovieName());
            System.out.println("Sales: " + movies.get(i).getMovieSales());
            System.out.println("Average rating: " + movies.get(i).getAvgRating());
        }
    }

    /**
     * This method adds a movie to the MovieSotre.
     * @param movie This is the Movie object to be added.
     */
    // Add movie to HashMap given Movie object
    public void addMovie(Movie movie) {
        movieHashMap.put(movie.getMovieID(), movie);
    }

    /**
     * Constructor for the movie store.
     */
    private MovieStore() {
        loadMovieHashMap(movieReaderWriter.getRawStringFromFile());
    }

    /**
     * This method is ran when the application is closed.
     * It writes all the data in the movie store into the movie.txt file
     */
    // Destructor
    public void closeMovieStore() {
        movieReaderWriter.setRawStringFromFile(parseHashMap());
    }

    private void loadMovieHashMap(ArrayList<String[]> movieRawStore) {
        for (String[] line: movieRawStore) {
            Movie movie = new Movie(line[1]);
            movie.setMovieName(line[0]);
            movie.setMovieDuration(Duration.ofMinutes(Long.parseLong(line[2])));
            movie.setShowingStatus(Status.valueOf(line[3]));
            movie.setSynopsis(line[4]);
            movie.setViewingMode(View.valueOf(line[5]));
            movie.setMovieHype(Hype.valueOf(line[6]));
            movie.setMovieSales(Integer.parseInt(line[7]));
            movie.setAgeRating(AgeEnum.valueOf(line[8]));

            // Get overall reviews
            String[] reviewArr = line[9].split("~");
            if (!reviewArr[0].isBlank()) {
                for (String review: reviewArr) {
                    int rating = Integer.parseInt(review.substring(0,1));
                    movie.getOverallReviews().addReview(rating, review.substring(1));
                }
            }

            // Get movie ppl
            String[] personnelArr = line[10].split("~");
            for (int i = 0; i < personnelArr.length; i++) {
                movie.addMoviePersonnel(personnelArr[i], (i == 0) ? Role.DIRECTOR : Role.CAST);
            }
            movieHashMap.put(movie.getMovieID(), movie);
        }
    }

    /**
     * This method returns the MovieStore object and utilises the 
     * @return This method returns the MovieStore object. 
     */
    // Return instance of store
    public static MovieStore getInstance() {
        return instance;
    }




    // parse HashMap to ArrayList<String[]>
    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        Set<String> keys = movieHashMap.keySet();

        // Iterate over each Movie object
        for (String key: keys) {
            Movie movie = movieHashMap.get(key);
            ArrayList<String> line = new ArrayList<>();

            line.add(movie.getMovieName());
            line.add(String.valueOf(key));
            line.add(String.valueOf(movie.getMovieDuration().toMinutes()));
            line.add(String.valueOf(movie.getShowingStatus()));
            line.add(String.valueOf(movie.getSynopsis()));
            line.add(String.valueOf(movie.getViewingMode()));
            line.add(String.valueOf(movie.getMovieHype()));
            line.add(String.valueOf(movie.getMovieSales()));
            line.add(String.valueOf(movie.getAgeRating()));

            // Add overall reviews
            OverallReviews overallReviews = movie.getOverallReviews();
            // Account for no reviews
            if (overallReviews.getReviewCount() == 0) {
                line.add("");
            } else {
                ArrayList<String> overallReviewsArr = new ArrayList<>();
                for (int i = 0; i < overallReviews.getReviewCount(); i++) {
                    IndividualReview individualReview = overallReviews.getReview(i);
                    // Join rating with review
                    String rating = String.valueOf(individualReview.getReviewRating());
                    String temp = rating.concat(individualReview.getReviewDescription());
                    overallReviewsArr.add(temp);
                }
                line.add(String.join("~", overallReviewsArr));
            }

            // Add movie personnel
            ArrayList<MoviePersonnel> moviePersonnelList = movie.getMoviePersonnelList();
            String director = null;
            ArrayList<String> out = new ArrayList<>();
            for (MoviePersonnel moviePersonnel : moviePersonnelList) {
                if (moviePersonnel.getRole() == Role.DIRECTOR)
                    director = moviePersonnel.getName();
                else
                    out.add(moviePersonnel.getName());
            }
            line.add(director.concat("~".concat(String.join("~", out))));

            String[] strArr = new String[line.size()];
            arrayListOut.add(line.toArray(strArr));
        }
        return arrayListOut;
    }
    public void printAllMovies(int toggle) {
        for (Movie movie: movieHashMap.values()) {
            if (movie.getShowingStatus() != Status.ENDOFSHOWING || toggle == 1)
                System.out.println("MovieID " + movie.getMovieID() + ": " + movie.getMovieName());
        }
    }


}
