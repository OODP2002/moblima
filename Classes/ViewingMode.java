public class ViewingMode implements MovieInformation<View> {
    private View viewingMode;

    public ViewingMode() {
        this.viewingMode = View._2D;
    }

    public ViewingMode(View view) {
        this.viewingMode = view;
    }

    public View getDetail() {
        return this.viewingMode;
    }

    public String getDetailString(){
        String result = "";
        switch (this.viewingMode){
            case _2D:
                result = "2D";
                break;
            case _3D:  
                result = "3D";
                break;
        }
        return result;
    }

    public void setDetail(View view) {
        this.viewingMode = view;
    }
}
