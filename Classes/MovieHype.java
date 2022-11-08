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

    public String getDetailString(){
        String result = "";
        switch (this.movieHype){
            case REGULAR:
                result = "Regular";
                break;
            case BLOCKBUSTER:  
                result = "Blockbuster";
                break;
        }
        return result;
    }
    public void setDetail(Hype hype) {
        this.movieHype = hype;
    }
}
    
