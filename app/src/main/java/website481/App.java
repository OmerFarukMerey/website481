/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package website481;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;


public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static boolean searchForMeanOfThreeInt(ArrayList<Double> array, Double num1, Double num2, Double num3) {
        System.out.println("inside search");
        if (array == null || num1 == null || num2 == null || num3 == null) return false;
        
        double mean = (num1 + num2 + num3) / 3;
        for (double elt : array) {
          if (elt == mean) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(App.class);
        logger.error("An Error has Occured!");

        int port = Integer.parseInt(System.getenv("PORT"));
        port(port);
        logger.error("Current port number:" + port);

        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, Welcome to My Website\n Compute page of this website consists of two inputs where in the left side-box you can enter as many as numbers as you want. (it can be a floating point number)\n" +
          "On the right hand side you should add three numbers to compute it's mean and check whether it is contained on the list of numbers you wrote on the left side-input.\n" +
          "Please be aware the only the first three numbers will be considered on the right hand size inputs.");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Double> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            double value = Double.parseDouble(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          sc1.close();
          System.out.println(inputList);


          String input2 = req.queryParams("input2");
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          sc2.useDelimiter("[;\r\n]+");
          Double firstInput2Double = Double.parseDouble(sc2.next().replaceAll("\\s",""));
          Double secondInput2Double = Double.parseDouble(sc2.next().replaceAll("\\s",""));
          Double thirdInput2Double = Double.parseDouble(sc2.next().replaceAll("\\s",""));
          sc2.close();
          //int input2AsInt = Integer.parseInt(input2);

          boolean result = App.searchForMeanOfThreeInt(inputList, firstInput2Double, secondInput2Double, thirdInput2Double);

          Map<String, Boolean> map = new HashMap<String, Boolean>();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, 
        
        new MustacheTemplateEngine());

        get("/compute",
            (rq, rs) -> {
              Map<String, String> map = new HashMap<String, String>();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}