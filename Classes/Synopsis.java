public class Synopsis extends MovieInformation<String>{
    private String synopsis;

    public Synopsis(){
        this.synopsis = "This movie's synopsis is not available."
    }

    public Synopsis(String synopsis){
        this.synopsis = synopsis;
    }

    public String getDetail(){
        return this.synopsis;
    }

    public void setDetail(String synopsis){
        this.synopsis = synopsis;
    }
}
