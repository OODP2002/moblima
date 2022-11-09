import java.util.ArrayList;

public class Movie{
    private String movieName;
    private int movieID;
    private MovieDuration movieDuration;
    private ShowingStatus showingStatus;
    private Synopsis synopsis;
    private ViewingMode viewingMode;
    private MovieHype movieHype;
    private MovieSales movieSales;
    private OverallReviews  overallReviews;
    private ArrayList<MoviePersonnel> moviePersonnelList = new ArrayList<>();

    public Movie(){
        this.movieName = "Rush Hour";
        this.movieID = -1;
        this.movieDuration = new MovieDuration();
        this.showingStatus = new ShowingStatus();
        this.synopsis = new Synopsis();
        this.viewingMode = new ViewingMode();
        this.movieHype = new MovieHype();
        this.movieSales = new MovieSales();
        this.overallReviews = new OverallReviews();
        this.moviePersonnelList = new ArrayList<>();
    }

    public Movie(String movieName, int movieID, MovieDuration movieDuration, ShowingStatus showingStatus, Synopsis synopsis, ViewingMode viewingMode, MovieHype movieHype, MovieSales movieSales){
        this.movieName = movieName;
        this.movieID = movieID;
        this.movieDuration = movieDuration;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.viewingMode = viewingMode;
        this.movieHype = movieHype;
        this.movieSales = movieSales;
        this.overallReviews = new OverallReviews(); // Can be changed to be instantiated with the parameters
        this.moviePersonnelList = new ArrayList<>();
    }

    
    public String getMovieName(){
        return this.movieName;
    }

    public void setMovieName(String movieName ){
        this.movieName = movieName;
    }

    public int getMovieID(){
        return this.movieID;
    }

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    public MovieDuration getMovieDuration(){
        return this.movieDuration;
    }

    public void setMovieDuration(MovieDuration movieDuration){
        this.movieDuration = movieDuration;
    }

    public ShowingStatus getShowingStatus(){
        return this.showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus){
        this.showingStatus = showingStatus;
    }

    public Synopsis getSynopsis(){
        return this.synopsis;
    }

    public void setSynopsis(Synopsis synopsis){
        this.synopsis = synopsis;
    }

    public ViewingMode getViewingMode(){
        return this.viewingMode;
    }

    public void setViewingMode(ViewingMode viewingMode){
        this.viewingMode = viewingMode;
    }

    public MovieHype getMovieHype(){
        return this.movieHype;
    }

    public void setMovieHype(MovieHype movieHype){
        this.movieHype = movieHype;
    }

    public MovieSales getMovieSales(){
        return this.movieSales;
    }

    public void setMovieSales(MovieSales movieSales){
        this.movieSales = movieSales;
    }

    public OverallReviews getOverallReviews() {
        return this.overallReviews;
    }

    public void setOverallReviews(OverallReviews overallReviews) {
        this.overallReviews = overallReviews;
    }

    public void printMoviePersonnel() {
        for (int i=0; i<this.moviePersonnelList.size(); i++) {
            System.out.println("Name: "+ moviePersonnelList.get(i).getName() + "\t Role: " + moviePersonnelList.get(i).getRole());
        }
    }

    public void addMoviePersonnel(String name, Role role) {
        moviePersonnelList.add(new MoviePersonnel(name, role));
    }

    public void printInfo() {
        System.out.println("Movie Name: " + this.getMovieName());
        System.out.println("Movie ID: " + this.getMovieID());
        System.out.println("Movie Duration: " + this.getMovieDuration().getDetail());
        System.out.println("Showing Status: " + this.getShowingStatus().getDetailString());
        System.out.println("Synopsis: " + this.getSynopsis().getDetail());
        System.out.println("Viewing Mode: " + this.getViewingMode().getDetailString());
        System.out.println("Movie Hype: " + this.getMovieHype().getDetailString());
        System.out.println("Movie Sales: " + this.getMovieSales().getDetail());
        System.out.println("Average rating: " + this.getOverallReviews().getAvgRating());
        this.printMoviePersonnel();
    }

    public String toString(){
        String movieDurationString = String.valueOf(this.movieDuration.getDetail());

        String overallReviewsString = "";
        overallReviewsString = String.valueOf(this.overallReviews.getReview(0).getReviewRating()) + this.overallReviews.getReview(0).getReviewDescription();
        for(int i = 1; i<this.overallReviews.getReviewCount(); i++){
            overallReviewsString = overallReviewsString + "~" + String.valueOf(this.overallReviews.getReview(i).getReviewRating()) + this.overallReviews.getReview(i).getReviewDescription();
        }

        String moviePersonString = "";
        moviePersonString = this.moviePersonnelList.get(0).getName();
        for (int i = 1; i<moviePersonnelList.size(); i++){
            moviePersonString = moviePersonString + "~" + this.moviePersonnelList.get(i).getName();
        }

        return this.movieName + "|" + String.valueOf(this.movieID) + "|" + movieDurationString + "|" + this.showingStatus.getDetailString() + "|" + this.synopsis.getDetail() + "|" + this.viewingMode.getDetailString() + "|" + this.movieHype.getDetailString() + "|" + String.valueOf(this.movieSales.getDetail()) + "|" + overallReviewsString + "|" + moviePersonString;
    }
}