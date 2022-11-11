import java.time.Duration;
import java.util.*;

public class MovieStore {
    
    // Attributes
    private final String path = ("Classes/src/movies.txt");
    private static MovieStore instance = new MovieStore();
    private HashMap<String, Movie> movieHashMap = new HashMap<>();     // key=MOVIE_ID
    private TxtReaderWriter movieReaderWriter = new TxtReaderWriter(path);


    // Get Movie object given movie_ID, returns null if movie does not exist
    public Movie searchMovie(String movieID) {
        return movieHashMap.get(movieID);
    }

    public Movie removeMovie(String movieID) {
        return movieHashMap.remove(movieID);
    }


    // Constructor
    private MovieStore() {
        loadMovieHashMap(movieReaderWriter.getRawStringFromFile());
    }

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
            for (String review: reviewArr) {
                int rating = Integer.parseInt(review.substring(0,1));
                movie.getOverallReviews().addReview(rating, review.substring(1));
            }

            // Get movie ppl
            String[] personnelArr = line[10].split("~");
            for (int i = 0; i < personnelArr.length; i++) {
                movie.addMoviePersonnel(personnelArr[i], (i == 1) ? Role.DIRECTOR : Role.CAST);
            }
            movieHashMap.put(movie.getMovieID(), movie);
        }
    }

    // Return instance of store
    public static MovieStore getInstance() {
        return instance;
    }


    // Add movie to HashMap given Movie object
    public void addMovie(Movie movie) {
        movieHashMap.put(movie.getMovieID(), movie);
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
            line.add(String.valueOf(movie.getMovieDuration()));
            line.add(String.valueOf(movie.getShowingStatus()));
            line.add(String.valueOf(movie.getSynopsis()));
            line.add(String.valueOf(movie.getShowingStatus()));
            line.add(String.valueOf(movie.getViewingMode()));
            line.add(String.valueOf(movie.getMovieHype()));
            line.add(String.valueOf(movie.getMovieSales()));
            line.add(String.valueOf(movie.getAgeRating()));

            // Add overall reviews
            OverallReviews overallReviews = movie.getOverallReviews();
            ArrayList<String> overallReviewsArr = new ArrayList<>();
            for (int i = 0; i < overallReviews.getReviewCount(); i++) {
                IndividualReview individualReview = overallReviews.getReview(i);
                // Join rating with review
                String rating = String.valueOf(individualReview.getReviewRating());
                String temp = rating.concat(individualReview.getReviewDescription());
                overallReviewsArr.add(temp);
            }
            line.add(String.join("~", overallReviewsArr));

            // Add movie personnel
            ArrayList<MoviePersonnel> moviePersonnelList = movie.getMoviePersonnelList();
            ArrayList<String> out = new ArrayList<>();
            for (MoviePersonnel moviePersonnel : moviePersonnelList) {
                if (moviePersonnel.getRole() == Role.DIRECTOR)
                    out.add(0, moviePersonnel.getName());
                else
                    out.add(moviePersonnel.getName());
            }
            line.add(String.join("~", out));

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

    public void ListTop5(int toggle) {
        List<Movie> movies = (List<Movie>) movieHashMap.values();

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
}
