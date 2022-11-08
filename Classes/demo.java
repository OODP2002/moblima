import java.util.ArrayList;

public class demo {
    public static void main(String[] args) {
        MovieStore movieStore = MovieStore.getInstance();
        ArrayList<Movie> movies = movieStore.getMovies();
        for (int i=0; i<movies.size(); i++) {
            System.out.println("----------------------");
            System.out.println("Movie " + (i+1));
            movies.get(i).printInfo();
        }
    }
}
