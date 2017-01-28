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
    this.xxPos = xxPos;
    this.yyPos = yyPos;
    this.xxVel = xxVel;
    this.yyVel = yyVel;
    this.mass = mass;
    this.imgFileName = imgFileName;
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

  public double calcDX(Planet p) {
    return this.xxPos - p.xxPos;
  }

  public double calcDY(Planet p) {
    return this.yyPos - p.yyPos;
  }

  public double calcDistance(Planet p) {
    return Math.sqrt((this.calcDX(p) * this.calcDX(p)) + (this.calcDY(p) * this.calcDY(p)));
  }
  public double calcForceExertedBy(Planet p) {
    double r = this.calcDistance(p);
    if (r == 0.0) {
      return 0.0;
    }
    return G * this.mass * p.mass / (r * r);
  }

  public double calcForceExertedBy(Planet p) {
    if (this.calcDistance(p) == 0.0) {
      return 0.0;
    }
    return this.calcForceExertedBy(p) * p.calcDX(this) / this.calcDistance(p);
  }

  public double calcNetForceExertedByX(Planet[] allPlanets) {
    int i = 0;
    double netForceX = 0.0;
    while (i < allPlanets.length) {
        netForceX = netForceX + this.calcNetForceExertedByX(allPlanets[i]);
        i = i + 1;
    }
    return netForceX;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets) {
    int i = 0;
    double netForceX = 0.0;
    while (i < allPlanets.length) {
      netForceY = netForceY + this.calcNetForceExertedByY(allPlanets[i]);
      i = i + 1;
    }
    return netForceY;
  }
}
