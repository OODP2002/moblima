import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieStore {
    
    // Attributes
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private String path = ("Classes/src/movies.txt");
    private static MovieStore instance = new MovieStore();


    // Constructor
    private MovieStore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                movies.add(createMovieObj(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException err) {
            System.out.println("Error: Movie List not found.");
        }
    }

    // Return instance of store
    public static MovieStore getInstance() {
        return instance;
    }

    // Creates a new movie object based on a string
    private Movie createMovieObj(String info) {
        String[] infoArr = info.split("\\|");
        
        String movieName = infoArr[0];
        int movieID = Integer.parseInt(infoArr[1]);
        MovieDuration movieDuration = new MovieDuration(Integer.parseInt(infoArr[2]));
        // Need to add
        ShowingStatus showingStatus;
        Synopsis synopsis = new Synopsis(infoArr[4]);
        // Need to add
        ViewingMode viewingMode;
        // Need to add
        MovieHype movieHype;
        MovieSales movieSales = new MovieSales(Integer.parseInt(infoArr[7]));
        AgeRating ageRating;

        // Showing status
        switch(infoArr[3]){
            case "Coming Soon":
                showingStatus = new ShowingStatus(Status.COMINGSOON);
                break;
            case "Preview":
                showingStatus = new ShowingStatus(Status.PREVIEW);
                break;
            case "Now Showing":
                showingStatus = new ShowingStatus(Status.NOWSHOWING);
                break;
            case "End Of Showing":
                showingStatus = new ShowingStatus(Status.ENDOFSHOWING);
                break;
            default:
                showingStatus = new ShowingStatus();
        }
        
        // Viewing mode
        switch(infoArr[5]){
            case "2D":
                viewingMode = new ViewingMode(View._2D);
                break;
            case "3D":
                viewingMode = new ViewingMode(View._3D);
                break;
            default:
                viewingMode = new ViewingMode();
        }

        // Movie hype
        switch(infoArr[6]) {
            case "Regular":
                movieHype = new MovieHype(Hype.REGULAR);
                break;
            case "Blockbuster":
                movieHype = new MovieHype(Hype.BLOCKBUSTER);
                break;
            default:
                movieHype = new MovieHype();
        }

        // Age Rating
        switch(infoArr[8]) {
            case "G":
                ageRating = new AgeRating(AgeEnum.G);
                break;
            case "PG":
                ageRating = new AgeRating(AgeEnum.PG);
                break;
            case "PG13":
                ageRating = new AgeRating(AgeEnum.PG13);
                break;
            case "NC16":
                ageRating = new AgeRating(AgeEnum.NC16);
                break;
            case "M18":
                ageRating = new AgeRating(AgeEnum.M18);
                break;
            case "R21":
                ageRating = new AgeRating(AgeEnum.R21);
                break;
            default:
                ageRating = new AgeRating(AgeEnum.G);
        }

        Movie movie = new Movie(movieName, movieID, movieDuration, showingStatus, synopsis, viewingMode, movieHype, movieSales, ageRating);

        // Overall reviews
        char tempChar;
        int tempDouble;
        String tempString;
        String[] reviewArr = infoArr[9].split("~");
        for (int i=0; i<reviewArr.length; i++) {
            tempChar = reviewArr[i].charAt(0);
            tempDouble = tempChar - '0';
            tempString = reviewArr[i].substring(1);
            movie.getOverallReviews().addReview(tempDouble, tempString);
        }

        // Movie Personnel List
        String[] personnelArr = infoArr[10].split("~");
        for (int i=0; i<reviewArr.length; i++) {
            if (i==0) {
                movie.addMoviePersonnel(personnelArr[i], Role.DIRECTOR);
            }
            else {
                movie.addMoviePersonnel(personnelArr[i], Role.CAST);
            }
        }

        return movie;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
        return;
    }

    public Movie getMovie(int index){
        return this.movies.get(index);
    }

    public void printAllMovies(int toggle) {
        if (toggle==0) { // ADMIN
            for (int i=0; i<this.movies.size(); i++) {
                System.out.println("MovieID " + this.movies.get(i).getMovieID() + ": " + this.movies.get(i).getMovieName());
            }
        }
        else {
            for (int i=0; i<this.movies.size(); i++) {
                if (this.movies.get(i).getShowingStatus().getDetail() != Status.ENDOFSHOWING) {
                    System.out.println("MovieID " + this.movies.get(i).getMovieID() + ": " + this.movies.get(i).getMovieName());
                }
            }
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
