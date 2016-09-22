import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Review {
  private int id;
  private String paragraph;
  private String pictureUrl;
  private String rating;
  private int restaurantId;

  public Review(String paragraph, String pictureUrl, String rating, int restaurantId) {
    this.paragraph = paragraph;
    this.pictureUrl = pictureUrl;
    this.rating = rating;
    this.restaurantId = restaurantId;
  }

  public String getParagraph() {
    return paragraph;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public String getRating() {
    return rating;
  }

  public int getId() {
    return id;
  }

  public int getRestaurantId() {
    return restaurantId;
  }

  @Override
  public boolean equals(Object otherReview) {
    if (!(otherReview instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) otherReview;
      return this.getParagraph().equals(newReview.getParagraph()) &&
             this.getPictureUrl().equals(newReview.getPictureUrl()) &&
             this.getRating() == (newReview.getRating()) &&
             this.getRestaurantId() == (newReview.getRestaurantId()) &&
             this.getId() == (newReview.getId());

    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews (paragraph, pictureUrl, rating, restaurantId) VALUES (:paragraph, :pictureUrl, :rating, :restaurantId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("paragraph", this.paragraph)
        .addParameter("pictureUrl", this.pictureUrl)
        .addParameter("rating", this.rating)
        .addParameter("restaurantId", this.restaurantId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Review> all() {
    String sql = "SELECT * FROM reviews";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  public static Review find(int id) {
    String sql = "SELECT * FROM reviews where id=:id";
    try(Connection con = DB.sql2o.open()) {
      Review review = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
      return review;
    }
  }
    // public void delete() {
    //   try(Connection con = DB.sql2o.open()) {
    //     String sql = "DELETE FROM restaurants WHERE id = :id;";
    //     con.createQuery(sql)
    //     .addParameter("id", id)
    //     .executeAndUpdate();
    //   }
    // }
}
