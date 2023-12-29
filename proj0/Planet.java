import java.lang.reflect.Array;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double r = Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double F;
        double G = 6.67e-11;
        F = G*mass*p.mass/(calcDistance(p)*calcDistance(p));
        return F;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double fx = 0;
        int aL =  allPlanets.length;

        for(int i = 0; i < aL; i++){
            boolean equal = allPlanets[i].equals(this);
            if (!equal) {
                double F = this.calcForceExertedBy(allPlanets[i]);
                double r =this.calcDistance(allPlanets[i]);
                fx += F*(allPlanets[i].xxPos-xxPos)/r;
            }
        }
        return fx;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double fy = 0;
        int aL =  allPlanets.length;

        for(int i = 0; i < aL; i++){
            boolean equal = allPlanets[i].equals(this);
            if (!equal) {
                double F = this.calcForceExertedBy(allPlanets[i]);
                double r =this.calcDistance(allPlanets[i]);
                fy += F*(allPlanets[i].yyPos-yyPos)/r;
            }
        }
        return fy;
    }

    public void update(double dt, double fX, double fY){
        xxPos = xxPos+(xxVel+(fX/mass)*dt)*dt;
        yyPos = yyPos+(yyVel+(fY/mass)*dt)*dt;
        xxVel = xxVel+(fX/mass)*dt;
        yyVel = yyVel+(fY/mass)*dt;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
