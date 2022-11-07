// Done by Marc
import java.util.ArrayList;

public class Cast implements MoviePersonnelInterface{
    private String name;
    private ArrayList<Movie> movies = new ArrayList<>();

    public Cast(String n){
        // Default Constructor
        this.name = n;
    }

    public String getName() {
        // Implementing abstract method from PersonInterface
        return this.name;
    }

    public void setName(String name) {
        // Implementing abstract method from PersonInterface
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    public void addToMovies(Movie cMovie) {
        movies.add(cMovie);
    }

}
