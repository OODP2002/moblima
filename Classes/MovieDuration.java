public class MovieDuration implements MovieInformation<int>{
    private int movieDuration;

    public MovieDuration(){
        this.movieDuration = 0;
    }

    public MovieDuration(int movieDuration){
        this.movieDuration = movieDuration;
    }

    public int getDetail(){
        return this.movieDuration;
    }

    public void setDetail(int movieDuration){
        this.movieDuration = movieDuration;
    }
}
