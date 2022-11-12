/**
 * The individual review class stores a review done by a movie goer.
 * It includes a string and a rating from (0-5).
 * 
 * @author Zhe Kai
 * @version 1.0
 */
public class IndividualReview {
    private int reviewRating;
    private String reviewDescription;

    /**
     * Constructor for the IndividualReview object
     * @param reviewRating Movie rating from 0-5.
     * @param reviewDescription Movie review.
     */
    public IndividualReview(int reviewRating, String reviewDescription) {
        this.reviewRating = reviewRating;
        this.reviewDescription = reviewDescription;
    }

    /**
     * 
     * @return int Rating of the review.
     */
    public int getReviewRating() {
        return this.reviewRating;
    }

    /**
     * 
     * @return String Description of the review.
     */
    public String getReviewDescription() {
        return this.reviewDescription;
    }

    /**
     * Method sets the review rating of the review.
     * @param reviewRating Rating of the review.
     */
    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    /**
     * Method sets the description of the review.
     * @param reviewDescription Description of the review.
     */
    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
