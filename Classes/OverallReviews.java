import java.util.ArrayList;

public class OverallReviews {
    private ArrayList<IndividualReview> reviews = new ArrayList<>();
    private int reviewCount=0;
    private int avgRating=0;

    public int getReviewCount() {
        return this.reviewCount;
    }

    public int getAvgRating() {
        return this.avgRating;
    }

    public void addReview(int reviewRating, String reviewDescription) {
        reviews.add(new IndividualReview(reviewRating, reviewDescription));
        this.avgRating = (this.avgRating * this.reviewCount + reviewRating)/(this.reviewCount+1);
        this.reviewCount++;
    }
}
