public class MovieHype implements MovieInformation<Hype>{
    private Hype movieHype;

    public MovieHype() {
        this.movieHype = Hype.REGULAR;
    }

    public MovieHype(Hype hype) {
        this.movieHype = hype;
    }

    public Hype getDetail() {
        return this.movieHype;
    }

    public void setDetail(Hype hype) {
        this.movieHype = hype;
    }
}
