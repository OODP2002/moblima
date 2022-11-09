import java.util.Scanner;

import javax.lang.model.element.ModuleElement.DirectiveKind;
public class MovieDemo{
    public static void main (String[] args){
        MovieStore movieStore = MovieStore.getInstance();

        //System.out.println(movieStore.getMovie(2).getOverallReviews().getAvgRating());

        MovieDuration movieDuration = new MovieDuration(420);
        ShowingStatus showingStatus = new ShowingStatus(Status.PREVIEW);
        Synopsis synopsis = new Synopsis("this show is astronomically bad");
        ViewingMode viewingMode = new ViewingMode(View._3D);
        MovieHype movieHype = new MovieHype(Hype.BLOCKBUSTER);
        MovieSales movieSales = new MovieSales(9999);


        Movie movie3 = new Movie("Free Willy", 360, movieDuration, showingStatus, synopsis, viewingMode, movieHype, movieSales);
        movie3.getOverallReviews().addReview(2, "bad movie");
        movie3.getOverallReviews().addReview(5, "good movie");

        movie3.addMoviePersonnel("jackson", Role.DIRECTOR);
        movie3.addMoviePersonnel("Wang", Role.CAST);
        
        movieStore.addMovie(movie3);

        movieStore.writeToMoviesFile();
    }
}