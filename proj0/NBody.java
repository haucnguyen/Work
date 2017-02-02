public class NBody {

  public static double readRadius(String file) {
    In in = new In(file);
    int number_planets = in.readInt()
    double radius = in.readDouble();

    return radius;
  }

  public static Planet[] readPlanets(String file) {
    In in = new In(file);
    int number_planets = in.readInt();
    double radius = in.readDouble();
    Planet[] planets = new Planet[number_planets];
    int 
  }
}
