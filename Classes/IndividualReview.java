public class IndividualReview {
    private int reviewRating;
    private String reviewDescription;

    public IndividualReview(int reviewRating, String reviewDescription) {
        this.reviewRating = reviewRating;
        this.reviewDescription = reviewDescription;
    }

    public int getReviewRating() {
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
