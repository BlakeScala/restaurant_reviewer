import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Restaurant {
  private int id;
  private String name;
  private String type;
  private boolean delivery;
  private String priceRange;
  private List<String> reviews;

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
}
