public class MovieDuration implements MovieInformation<Integer>{
    private Integer movieDuration;

    public MovieDuration(){
        this.movieDuration = 0;
    }

    public MovieDuration(int movieDuration){
        this.movieDuration = movieDuration;
    }

    public Integer getDetail(){
        return this.movieDuration;
    }

    public void setDetail(Integer movieDuration){
        this.movieDuration = movieDuration;
    }
}
