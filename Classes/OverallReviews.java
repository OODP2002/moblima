import java.util.ArrayList;

/**
 * The OverallReviews class stores reviews and keeps track of the
 * number of reviews, as well as the average rating as reviews are added.
 * 
 * @author Zhe Kai
 * @version 1.0
 */

public class OverallReviews {
    private ArrayList<IndividualReview> reviews = new ArrayList<>();
    private int reviewCount=0;
    private double avgRating=0;

    /**
     * 
     * @return int This returns the number of reviews in OverallReviews object.
     */
    public int getReviewCount() {
        return this.reviewCount;
    }

    /**
     * 
     * @return double This returns the average rating of the reviews in the OverallReviews object.
     */
    public double getAvgRating() {
        return this.avgRating;
    }

    /**
     * 
     * @param index This is the index of the individual review in the list.
     * @return IndividualReview This returns the individual review at the specified index.
     */
    public IndividualReview getReview(int index){
        return this.reviews.get(index);
    }

    /**
     * This method creates an IndividualReview object and adds it to the OverallReviews object.
     * The average rating and review count are updated within the method.
     * 
     * @param reviewRating The rating of the movie (1-5).
     * @param reviewDescription The description of the review.
     */
    public void addReview(int reviewRating, String reviewDescription) {
        reviews.add(new IndividualReview(reviewRating, reviewDescription));
        this.avgRating = (this.avgRating * this.reviewCount + reviewRating)/(this.reviewCount+1);
        this.reviewCount++;
    }
}
