public class Planet {

  /** instance variable for the plabet class */
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  /** constructor that can initialized an instance of the planet class */
  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
}

  /** second constructor that takes in a planet object and make an identical Planet instance */
  public Planet(Planet p) {
  this.xxPos = p.xxPos;
  this.yyPos = p.yyPos;
  this.xxVel = p.xxVel;
  this.yyVel = p.yyVel;
  this.mass = p.mass;
  this.imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double dX;
    double dY;
    double distance;

    dX = p.xxPos - this.xxPos;
    dY = p.yyPos - this.yyPos;
    distance = ((dX *= dX) + (dY *= dY));
    return Math.sqrt(distance);
  }
    public double calcForceExertedBy(Planet p) {
      double G = 6.67 * Math.pow(10, -11);
      double m1 = this.mass;
      double m2 = p.mass;
      double distance;

      distance = calcDistance(p);
      return G * m1 * m2 / (distance *= distance);
    }


    public double calcForceExertedByX(Planet p) {
      double totalForce = calcForceExertedBy(p);
      double dX;
      double r = calcDistance(p);

      dX = p.xxPos - this.xxPos;
      return totalForce * dX / r;
    }

    public double calcForceExertedByY(Planet p) {
      double totalForce = calcForceExertedBy(p);
      double dY;
      double r = calcDistance(p);

      dY = p.yyPos - this.yyPos;
      return totalForce * dY / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
      double k = allPlanets.length;
      double netForceX = 0;

      for (int i = 0; i < k; i++) {
        if (!allPlanets[i].equals(this)) {
          netForceX += calcNetForceExertedByX(allPlanets[i]);
        }
      }
      return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
      double k = allPlanets.length;
      double netForceY = 0;

      for (int i = 0; i < k; i++) {
        if (!allPlanets[i].equals(this)) {
          netForceY += calcNetForceExertedByY(allPlanets[i]);
        }
      }
      return netForceY;
    }

    public void update(double dt, double fX, double fY) {
      double netXAccerleration = fX / this.mass;
      double netYAccerleration = fY / this.mass;

      this.xxVel += dt * netXAccerleration;
      this.yyVel += dt * netYAccerleration;
      this.xxPos += dt * this.xxVel;
      this.yyPos += dt * this.yyVel;
    }

    public void draw() {
      StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
