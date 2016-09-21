import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Restaurant {
  private int id;
  private String name;
  private String type;
  private boolean delivery;
  private String priceRange;
  private List<Review> reviews;

  public Restaurant(String name, String type, boolean delivery, String priceRange) {
    this.name = name; 
    this.type = type;
    this.delivery = delivery;
    this.priceRange = priceRange;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public boolean getDelivery() {
    return delivery;
  }

  public String getPriceRange() {
    return priceRange;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getType().equals(newRestaurant.getType()) &&
             this.getDelivery() == (newRestaurant.getDelivery()) &&
             this.getPriceRange().equals(newRestaurant.getPriceRange()) &&
             this.getId() == newRestaurant.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants (name, type, delivery, priceRange) VALUES (:name, :type, :delivery, :priceRange)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("type", this.type)
        .addParameter("delivery", this.delivery)
        .addParameter("priceRange", this.priceRange)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants where id=:id";
      Restaurant restaurant = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }
}
