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

    public void setDetail(View view) {
        this.viewingMode = view;
    }
}
