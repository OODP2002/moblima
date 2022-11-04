public class ShowingStatus extends MovieInformation<Status>{

    private Status showingStatus;

    public ShowingStatus(){
        Status status = Status.COMINGSOON;
        this.showingStatus = status;
    }
    public ShowingStatus(Status showingStatus){
        this.showingStatus = showingStatus;
    }

    public Status getDetail(){
        return this.showingStatus;
    }

    public void setDetail(Status showingStatus){
        this.showingStatus = showingStatus;
    }
}
