import java.util.ArrayList;

public class Movie {
    private String movieName;
    private int movieID;
    private MovieDuration movieDuration;
    private Status showingStatus;
    private Synopsis synopsis;
    private ViewingMode viewingMode;
    private MovieHype movieHype;
    private int movieSales;
    private OverallReviews  overallReviews;
    private ArrayList<MoviePersonnel> moviePersonnelList = new ArrayList<>();
    private AgeRating ageRating;

    public Movie(int movieID){
        this.movieName = "Rush Hour";
        this.movieID = movieID;
        this.movieDuration = new MovieDuration();
        this.synopsis = new Synopsis();
        this.viewingMode = new ViewingMode();
        this.movieHype = new MovieHype();
        this.overallReviews = new OverallReviews();
        this.moviePersonnelList = new ArrayList<>();
        this.ageRating = new AgeRating();
    }
    
    public String getMovieName(){
        return this.movieName;
    }

    public ArrayList<MoviePersonnel> getMoviePersonnelList() {
        return moviePersonnelList;
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

    public Status getShowingStatus(){
        return this.showingStatus;
    }

    public void setShowingStatus(Status showingStatus){
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
        for (int i=0; i<this.moviePersonnelList.size(); i++) {
            System.out.println("Name: "+ moviePersonnelList.get(i).getName() + "\t Role: " + moviePersonnelList.get(i).getRole());
        }
    }

    public void addMoviePersonnel(String name, Role role) {
        moviePersonnelList.add(new MoviePersonnel(name, role));
    }

    public AgeRating getAgeRating() {
        return this.ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    //-------------------------------------------

    // to remove
    public void printInfo(int toggle) {

        System.out.println("Movie Name: " + this.getMovieName());
        System.out.println("Movie ID: " + this.getMovieID());
        System.out.println("Movie Duration: " + this.getMovieDuration().getDetail());
        System.out.println("Showing Status: " + this.getShowingStatus());
        System.out.println("Age Rating: " + this.getAgeRating().getDetailString());
        System.out.println("Synopsis: " + this.getSynopsis().getDetail());
        System.out.println("Viewing Mode: " + this.getViewingMode().getDetailString());
        System.out.println("Movie Hype: " + this.getMovieHype().getDetailString());
        if (toggle==0) System.out.println("Movie Sales: " + this.getMovieSales());
        System.out.println("Average rating: " + this.getOverallReviews().getAvgRating());
        this.printMoviePersonnel();
    }

    public String toString(){
        String movieDurationString = String.valueOf(this.movieDuration.getDetail());

        String overallReviewsString = "";
        overallReviewsString = this.overallReviews.getReview(0).getReviewRating() + "" + this.overallReviews.getReview(0).getReviewDescription();
        for(int i = 1; i<this.overallReviews.getReviewCount(); i++){
            overallReviewsString = overallReviewsString + "~" + this.overallReviews.getReview(i).getReviewRating() + "" + this.overallReviews.getReview(i).getReviewDescription();
        }

        String moviePersonString = "";
        moviePersonString = this.moviePersonnelList.get(0).getName();
        for (int i = 1; i<moviePersonnelList.size(); i++){
            moviePersonString = moviePersonString + "~" + this.moviePersonnelList.get(i).getName();
        }

        return this.movieName + "|" + String.valueOf(this.movieID) + "|" + movieDurationString + "|" + this.showingStatus + "|" + this.synopsis.getDetail() + "|" + this.viewingMode.getDetailString() + "|" + this.movieHype.getDetailString() + "|" + String.valueOf(this.movieSales) + "|" + this.getAgeRating().getDetailString() + "|" + overallReviewsString + "|" + moviePersonString;
    }
}