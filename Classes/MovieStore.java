import java.util.ArrayList;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieStore {
    
    // Attributes
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private String path = System.getProperty("user.dir") + ("/Classes/src/movies.txt");
    private static MovieStore instance = new MovieStore();


    // Constructor
    private MovieStore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                this.movies.add(createMovieObj(line));
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

        // Showing status
        switch(infoArr[3]){
            case "COMINGSOON":
                showingStatus = new ShowingStatus(Status.COMINGSOON);
                break;
            case "PREVIEW":
                showingStatus = new ShowingStatus(Status.PREVIEW);
                break;
            case "NOWSHOWING":
                showingStatus = new ShowingStatus(Status.NOWSHOWING);
                break;
            case "ENDOFSHOWING":
                showingStatus = new ShowingStatus(Status.ENDOFSHOWING);
                break;
            default:
                showingStatus = new ShowingStatus();
        }
        
        // Viewing mode
        switch(infoArr[5]){
            case "_2D":
                viewingMode = new ViewingMode(View._2D);
                break;
            case "_3D":
                viewingMode = new ViewingMode(View._3D);
                break;
            default:
                viewingMode = new ViewingMode();
        }

        // Movie hype
        switch(infoArr[6]) {
            case "REGULAR":
                movieHype = new MovieHype(Hype.REGULAR);
                break;
            case "BLOCKBUSTER":
                movieHype = new MovieHype(Hype.BLOCKBUSTER);
                break;
            default:
                movieHype = new MovieHype();
        }

        Movie movie = new Movie(movieName, movieID, movieDuration, showingStatus, synopsis, viewingMode, movieHype, movieSales);

        // Overall reviews
        char tempChar;
        int tempInt;
        String tempString;
        String[] reviewArr = infoArr[8].split("@");
        for (int i=0; i<reviewArr.length; i++) {
            tempChar = reviewArr[i].charAt(0);
            tempInt = tempChar - '0';
            tempString = reviewArr[i].substring(1);
            movie.getOverallReviews().addReview(tempInt, tempString);
        }

        // Movie Personnel List
        String[] personnelArr = infoArr[9].split("@");
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

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    // public void writeToMoviesFile() {
    //     try {
    //         FileWriter writer = new FileWriter(path);
    //         writer.write("movieName | movieID | movieDuration | showingStatus | synopsis | viewingMode | movieHype | movieSales | overallReviews | moviePersonnelList");
    //         for (int i = 0; i < this.movies.size(); i++) {

    //         }
    //     } catch (IOException err) {
    //         err.printStackTrace();
    //     }
    // }
}