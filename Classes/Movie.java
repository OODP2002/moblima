import java.util.ArrayList;

public class Movie{
    private String movieName;
    private int movieID;
    private MovieDuration movieDuration;
    private ShowingStatus showingStatus;
    private Synopsis synopsis;
    private ViewingMode viewingMode;
    private MoviePopularity moviePopularity;
    private MovieSales movieSales;

    public Movie(){
        this.movieName = 'Rush Hour';
        this.movieID = -1;
        this.movieDuration = new MovieDuration();
        this.showingStatus = new ShowingStatus();
        this.synopsis = new Synopsis();
        this.viewingMode = new ViewingMode();
        this.moviePopularity = new MoviePopularity();
        this.movieSales = new MovieSales();
    }

    public Movie(String movieName, int movieID, MovieDuration movieDuration, ShowingStatus showingStatus, Synopsis synopsis, ViewingMode viewingMode, MoviePopularity moviePopularity, MovieSales movieSales){
        this.movieName = movieName;
        this.movieID = movieID;
        this.movieDuration = movieDuration;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.viewingMode = viewingMode;
        this.moviePopularity = moviePopularity;
        this.movieSales = movieSales;
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

    public MoviePopularity getMoviePopularity(){
        return this.moviePopularity;
    }

    public void setMoviePopularity(MoviePopularity moviePopularity){
        this.moviePopularity = moviePopularity;
    }

    public MovieSales getMovieSales(){
        return this.movieSales;
    }

    public void setMovieSales(MovieSales movieSales){
        this.movieSales = movieSales;
    }
}