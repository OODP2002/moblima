import java.util.Scanner;

import javax.lang.model.element.ModuleElement.DirectiveKind;
public class MovieDemo{
    public static void main (String[] args){
        MovieDuration movieDuration = new MovieDuration(200);
        ShowingStatus showingStatus = new ShowingStatus(Status.COMINGSOON);
        Synopsis synopsis = new Synopsis("Black people fighting");
        ViewingMode viewingMode = new ViewingMode(View._2D);
        MovieHype movieHype = new MovieHype(Hype.BLOCKBUSTER);
        MovieSales movieSales = new MovieSales(0);

        Movie movie1 = new Movie("Black Panther", 6969, movieDuration, showingStatus, synopsis, viewingMode, movieHype, movieSales);

        System.out.println(movie1.getMovieName());
        System.out.println(movie1.getMovieID());
        System.out.println(movie1.getMovieDuration().getDetail());
        System.out.println(movie1.getShowingStatus().getDetail());
        System.out.println(movie1.getSynopsis().getDetail());
        System.out.println(movie1.getViewingMode().getDetail());
        System.out.println(movie1.getMovieHype().getDetail());
        System.out.println(movie1.getMovieSales().getDetail());
        
        movie1.getOverallReviews().addReview(4, "This movie sucks");
        movie1.getOverallReviews().addReview(5, "This movie is great");

        System.out.println(movie1.getOverallReviews().getAvgRating());

        movie1.addMoviePersonnel("Dick", Role.DIRECTOR);
        movie1.addMoviePersonnel("cock", Role.CAST);

        movie1.printMoviePersonnel();
    }
}