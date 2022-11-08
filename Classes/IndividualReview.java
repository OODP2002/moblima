public class IndividualReview {
    private double reviewRating;
    private String reviewDescription;
    public IndividualReview(double reviewRating, String reviewDescription) {
        this.reviewRating = reviewRating;
        this.reviewDescription = reviewDescription;
    }
    public double getReviewRating() {
        return this.reviewRating;
    }
    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }
    public String getReviewDescription() {
        return this.reviewDescription;
    }
    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
