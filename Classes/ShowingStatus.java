public class ShowingStatus implements MovieInformation<Status>{

    private Status showingStatus;

    public ShowingStatus(){
        Status status = Status.COMINGSOON;
        this.showingStatus = status;
    }
    public ShowingStatus(Status showingStatus){
        this.showingStatus = showingStatus;
    }

    public String getDetailString(){
        String result = "";
        switch (this.showingStatus){
            case COMINGSOON:
                result = "Coming Soon";
                break;
            case PREVIEW:  
                result = "Preview";
                break;
            case NOWSHOWING:
                result = "Now Showing";
                break;
            case ENDOFSHOWING:
                result = "End Of Showing";
                break;
        }
        return result;
    }

    public Status getDetail(){
        return this.showingStatus;
    }

    public void setDetail(Status showingStatus){
        this.showingStatus = showingStatus;
    }
}
