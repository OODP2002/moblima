import java.util.ArrayList;

public class demo {
    public static void main(String[] args) {
        MovieStore movieStore = MovieStore.getInstance();
        System.out.println(movieStore.getMovie(0).getAgeRating().getDetailString());
        Movie temp = new Movie("Avengers", 3, new MovieDuration(123), new ShowingStatus(Status.COMINGSOON), new Synopsis("Temporary synopsis"), new ViewingMode(View._3D), new MovieHype(Hype.BLOCKBUSTER), new MovieSales(33), new AgeRating(AgeEnum.NC16));
        temp.addMoviePersonnel("James Bond", Role.DIRECTOR);
        temp.addMoviePersonnel("Marc", Role.CAST);
        temp.getOverallReviews().addReview(4, "Pretty decent movie");
        movieStore.addMovie(temp);
        movieStore.writeToMoviesFile();
    }
}
