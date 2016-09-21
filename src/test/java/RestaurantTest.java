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
}
