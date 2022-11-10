import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class MovieStore {
    
    // Attributes

    private String path = ("Classes/src/movies.txt");
    private static MovieStore instance = new MovieStore();
    private HashMap<Integer, Movie> movieHashMap = new HashMap<>();     // key=MOVIE_ID
    private TxtReaderWriter movieReaderWriter = new TxtReaderWriter(path);


    // Get Movie object given movie_ID
    public Movie getMovie(int movieID) {
        return movieHashMap.get(movieID);
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
            Movie movie = new Movie(Integer.parseInt(line[1]));
            movie.setMovieName(line[0]);
            movie.setMovieDuration(new MovieDuration(Integer.parseInt(line[2])));
            movie.setShowingStatus(Status.valueOf(line[3]));
            movie.setSynopsis(new Synopsis(line[4]));
            movie.setViewingMode(new ViewingMode(View.valueOf(line[5])));
            movie.setMovieHype(new MovieHype(Hype.valueOf(line[6])));
            movie.setMovieSales(new MovieSales(Integer.parseInt(line[7])));
            movie.setAgeRating(new AgeRating(AgeEnum.valueOf(line[8])));

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


    public void addMovie(Movie movie) {
        movieHashMap.put(movie.getMovieID(), movie);
    }

    // parse HashMap to ArrayList<String[]>\
    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        Set<Integer> keys = movieHashMap.keySet();

        // Iterate over each Movie object
        for (Integer key: keys) {
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
            if (movie.getShowingStatus() == Status.ENDOFSHOWING && toggle == 1 || movie.getShowingStatus() != Status.ENDOFSHOWING)
                System.out.println("MovieID " + movie.getMovieID() + ": " + movie.getMovieName());
        }
    }

    public Movie searchMovie(int id){
        int movieID;
        for (int i=0; i<this.movies.size(); i++) {
            movieID = this.movies.get(i).getMovieID();
            if (movieID == id && this.movies.get(i).getShowingStatus().getDetail() !=Status.ENDOFSHOWING){
                return this.movies.get(i);
            }
        }
        return null;
    }

    public void ListTop5(int toggle) {
        int n = this.movies.size();
        Movie[] movieArr = new Movie[n];
        for (int i=0; i<n; i++) {
            movieArr[i] = this.movies.get(i);
        }
        // Sort by movie sales
        if (toggle == 0) { 
            for (int i=0; i<n-1; i++) {
                for (int j=0; j<n-i-1; j++) {
                    if (movieArr[j].getMovieSales().getDetail() < movieArr[j+1].getMovieSales().getDetail()) {
                        Movie temp = movieArr[j];
                        movieArr[j] = movieArr[j+1];
                        movieArr[j+1] = temp;
                    }
                }
            }
            int num = (n < 5) ? n : 5;           
            System.out.println("Top " + num + " movies by Movie Sales:");
            for (int i=0; i<num; i++) {
                System.out.println((i+1) + ": " + movieArr[i].getMovieName() + "\nMovie Sales: " + movieArr[i].getMovieSales().getDetail() + "\n");
            }
        }
        // Sort by average rating
        else { 
            for (int i=0; i<n-1; i++) {
                for (int j=0; j<n-i-1; j++) {
                    if (movieArr[j].getOverallReviews().getAvgRating() < movieArr[j+1].getOverallReviews().getAvgRating()) {
                        Movie temp = movieArr[j];
                        movieArr[j] = movieArr[j+1];
                        movieArr[j+1] = temp;
                    }
                }
            }
            int num = (n < 5) ? n : 5;           
            System.out.println("Top " + num + " movies by rating:");
            for (int i=0; i<num; i++) {
                System.out.println((i+1) + ": " + movieArr[i].getMovieName() + "\nMovie Rating: " + movieArr[i].getOverallReviews().getAvgRating() + "\n");
            }
        }

    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void writeToMoviesFile() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine(); //Header row
            
            FileWriter writer = new FileWriter(path);
            writer.write(header);
            for (int i = 0; i < this.movies.size(); i++){
                writer.write("\n" + this.movies.get(i).toString());
            }
            writer.close();
            reader.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

}
