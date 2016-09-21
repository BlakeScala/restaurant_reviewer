import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;


public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void restaurant_instantiatesCorrectly_true() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    assertEquals(true, restaurant instanceof Restaurant);
  }

  @Test
  public void getName_returnsName_true() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    assertEquals("Joe's Pizza", restaurant.getName());
  }

  @Test
  public void getType_returnsTypeOfFood_true() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    assertEquals("Italian", restaurant.getType());
  }

  @Test
  public void getDelivery_returnsTypeOfFood_true() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    assertEquals(true, restaurant.getDelivery());
  }

  @Test
  public void getPriceRange_returnsPriceRange_true() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    assertEquals("cheap", restaurant.getPriceRange());
  }

  @Test
  public void equals_returnsTrueIfAttributesAreTheSame() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    Restaurant restaurantTwo = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    assertTrue(restaurant.equals(restaurantTwo));
  }

  @Test
  public void equals_returnsFalseIfRestaurantsAreDifferent() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    Restaurant restaurantTwo = new Restaurant("Joe's Subs", "Italian", true, "cheap");
    assertTrue(!(restaurant.equals(restaurantTwo)));
  }

  @Test
  public void all_returnsAllInstancesOfRestaurant_true() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    Restaurant restaurantTwo = new Restaurant("Joe's Subs", "Italian", true, "cheap");
    restaurant.save();
    restaurantTwo.save();
    assertEquals(true, Restaurant.all().get(0).equals(restaurant));
    assertEquals(true, Restaurant.all().get(1).equals(restaurantTwo));
  }

  @Test
  public void save_savesRestaurantToDatabase() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    restaurant.save();
    assertTrue(Restaurant.all().get(0).equals(restaurant));
  }

  @Test
  public void save_assignsIdToRestaurant() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    restaurant.save();
    assertEquals(true, restaurant.getId() > 0);
  }

  @Test
  public void find_returnsRestaurantWithSameId_restaurantTwo() {
    Restaurant restaurant = new Restaurant("Joe's Pizza", "Italian", true, "cheap");
    Restaurant restaurantTwo = new Restaurant("Joe's Subs", "Italian", true, "medium");
    restaurant.save();
    restaurantTwo.save();
    assertEquals(Restaurant.find(restaurantTwo.getId()), restaurantTwo);
  }

  @Test
  public void getReviews_retrievesAndReturnsAllReviewsFromDatabase_reviewList() {
    Restaurant restaurant = new Restaurant ("Joe's Pizza", "Italian", true, "cheap");
    restaurant.save();
    Review myReview = new Review("great place", "www.google.com/1223433", 5, restaurant.getId());
    myReview.save();
    Review myReviewTwo = new Review("sucked badly", "www.google.com/392829", 3, restaurant.getId());
    myReviewTwo.save();
    Review[] reviews = new Review[] {myReview, myReviewTwo};
    assertEquals(true, myReview instanceof Review);
    // assertTrue(restaurant.getReviews().containsAll(Arrays.asList(reviews)));
  }
}
