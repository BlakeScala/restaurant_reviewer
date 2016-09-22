import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.io.Console;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    Console console = System.console();

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("restaurants", Restaurant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/add-restaurant", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name= request.queryParams("name");
      String type=request.queryParams("type");
      String priceRange=request.queryParams("priceRange");
      System.out.println(priceRange);
      String deliveryString=request.queryParams("delivery");
      Boolean delivery;
      if (deliveryString.equals("yes")) {
        delivery = true;
      } else {
        delivery = false;
      }
      Restaurant restaurant = new Restaurant(name, type, delivery, priceRange);
      restaurant.save();
      model.put("template", "templates/index.vtl");
      model.put("restaurants", Restaurant.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurant/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      model.put("restaurant", restaurant);
      model.put("reviews", restaurant.getReviews());
      model.put("template", "templates/restaurant-reviews.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurant/:id/add-review", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      model.put("restaurant", restaurant);
      model.put("template", "templates/add-review.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("restaurant/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      restaurant.delete();
      model.put("template", "templates/index.vtl");
      model.put("restaurants", Restaurant.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("restaurant/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      model.put("restaurant", restaurant);
      String paragraph = request.queryParams("paragraph");
      String pictureUrl = request.queryParams("pictureUrl");
      String rating = request.queryParams("rating");
      int restaurantId =  restaurant.getId();
      Review review = new Review(paragraph, pictureUrl, rating, restaurantId);
      review.save();
      model.put("template", "templates/restaurant-reviews.vtl");
      model.put("reviews", restaurant.getReviews());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
