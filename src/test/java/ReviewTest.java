import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ReviewTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void Review_instantiatesCorrectly_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    assertEquals(true, myReview instanceof Review);
  }

  @Test
  public void getParagraph_returnsParagraphCorrectly_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    assertEquals("great place", myReview.getParagraph());
  }

  @Test
  public void getPictureUrl_returnsPictureUrlCorrectly_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    assertEquals("www.google.com/1223433", myReview.getPictureUrl());
  }

  @Test
  public void getRating_returnsRatingCorrectly_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    assertEquals(5, myReview.getRating());
  }

  @Test
  public void getRestaurantId_returnsRestaurantIdCorrectly_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    assertEquals(1, myReview.getRestaurantId());
  }

  @Test
  public void equals_returnsTrueIfAttributesAreTheSame_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    Review myReviewTwo = new Review("great place", "www.google.com/1223433", 5, 1);
    assertTrue(myReview.equals(myReviewTwo));
  }

  @Test
  public void all_returnsAllInstancesOfReview_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    Review myReviewTwo = new Review("great place", "www.google.com/1223433", 5, 1);
    myReview.save();
    myReviewTwo.save();
    assertTrue(Review.all().get(0).equals(myReview));
    assertTrue(Review.all().get(1).equals(myReviewTwo));
  }

  @Test
  public void save_savesReviewToRestaurant_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    myReview.save();
    assertTrue(Review.all().get(0).equals(myReview));
  }

  @Test
  public void save_assignsIdToReview_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    myReview.save();
    assertTrue(myReview.getId() > 0);
  }

  @Test
  public void find_returnsReviewWithSameId_true() {
    Review myReview = new Review("great place", "www.google.com/1223433", 5, 1);
    myReview.save();
    Review myReviewTwo = new Review("sucked badly", "www.google.com/392829", 3, 1);
    myReviewTwo.save();
    assertEquals(Review.find(myReviewTwo.getId()), myReviewTwo);
  }
}
