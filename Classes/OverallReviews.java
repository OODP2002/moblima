import java.util.ArrayList;

public class OverallReviews {
    private ArrayList<IndividualReview> reviews = new ArrayList<>();
    private int reviewCount=0;
    private double avgRating=0;

    public int getReviewCount() {
        return this.reviewCount;
    }

    public double getAvgRating() {
        return this.avgRating;
    }

    public IndividualReview getReview(int index){
        return this.reviews.get(index);
    }

    public void addReview(double reviewRating, String reviewDescription) {
        reviews.add(new IndividualReview(reviewRating, reviewDescription));
        this.avgRating = (this.avgRating * this.reviewCount + reviewRating)/(this.reviewCount+1);
        this.reviewCount++;
    }
}
