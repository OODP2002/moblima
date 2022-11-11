import java.time.Duration;
import java.util.ArrayList;

public class Movie {
    private String movieName;
    private final String movieID;
    private Duration movieDuration;
    private Status showingStatus;
    private String synopsis;
    private View viewingMode;
    private Hype movieHype;
    private int movieSales;
    private OverallReviews  overallReviews;
    private ArrayList<MoviePersonnel> moviePersonnelList = new ArrayList<>();
    private AgeEnum ageRating;

    public Movie(String movieID){
        this.movieID = movieID;
        this.overallReviews = new OverallReviews();
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieID() {
        return movieID;
    }

    public Duration getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Duration movieDuration) {
        this.movieDuration = movieDuration;
    }

    public Status getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(Status showingStatus) {
        this.showingStatus = showingStatus;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public View getViewingMode() {
        return viewingMode;
    }

    public void setViewingMode(View viewingMode) {
        this.viewingMode = viewingMode;
    }

    public Hype getMovieHype() {
        return movieHype;
    }

    public void setMovieHype(Hype movieHype) {
        this.movieHype = movieHype;
    }

    public void setOverallReviews(OverallReviews overallReviews) {
        this.overallReviews = overallReviews;
    }

    public ArrayList<MoviePersonnel> getMoviePersonnelList() {
        return moviePersonnelList;
    }

    public void setMoviePersonnelList(ArrayList<MoviePersonnel> moviePersonnelList) {
        this.moviePersonnelList = moviePersonnelList;
    }

    public void setAgeRating(AgeEnum ageRating) {
        this.ageRating = ageRating;
    }

    public int getMovieSales(){
        return movieSales;
    }

    public void setMovieSales(int movieSales){
        this.movieSales = movieSales;
    }

    public OverallReviews getOverallReviews() {
        return this.overallReviews;
    }

    public double getAvgRating() {
        return getOverallReviews().getAvgRating();
    }

    public void printMoviePersonnel() {
        for (MoviePersonnel moviePersonnel : this.moviePersonnelList) {
            System.out.println("Name: " + moviePersonnel.getName() + "\t Role: " + moviePersonnel.getRole());
        }
    }

    public void addMoviePersonnel(String name, Role role) {
        moviePersonnelList.add(new MoviePersonnel(name, role));
    }

    public AgeEnum getAgeRating() {
        return this.ageRating;
    }

}