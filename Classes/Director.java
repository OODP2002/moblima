// Done by Marc
import java.util.ArrayList;

public class Director implements MoviePersonnelInterface{
    private String name;
    private ArrayList<Movie> movies = new ArrayList<>();

    public Director(String n) {
        // Default Constructor
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    public void addToMovies(Movie cMovie) {
        movies.add(cMovie);
    }
}
