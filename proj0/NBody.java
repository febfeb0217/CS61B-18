public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] Galaxy = readPlanets(filename);
        StdDraw.setScale(-r, r);
        for(double iT = 0; iT<T;iT+=dt){
            double[] xForces = {Galaxy[0].calcNetForceExertedByX(Galaxy),Galaxy[1].calcNetForceExertedByX(Galaxy),Galaxy[2].calcNetForceExertedByX(Galaxy),Galaxy[3].calcNetForceExertedByX(Galaxy),Galaxy[4].calcNetForceExertedByX(Galaxy)};
            double[] yForces = {Galaxy[0].calcNetForceExertedByY(Galaxy),Galaxy[1].calcNetForceExertedByY(Galaxy),Galaxy[2].calcNetForceExertedByY(Galaxy),Galaxy[3].calcNetForceExertedByY(Galaxy),Galaxy[4].calcNetForceExertedByY(Galaxy)};
            for(int e = 0; e<5;e++){
                Galaxy[e].update(dt,xForces[e],yForces[e]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int j = 0;j<5;j++){
                Galaxy[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", Galaxy.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < Galaxy.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Galaxy[i].xxPos, Galaxy[i].yyPos, Galaxy[i].xxVel,
                    Galaxy[i].yyVel, Galaxy[i].mass, Galaxy[i].imgFileName);
        }
        StdDraw.enableDoubleBuffering();
    }
    public static double readRadius(String s){
        In in = new In(s);
        int FirstItem = in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int n = in.readInt();
        double radius = in.readDouble();
        Planet[] Galaxy = new Planet[n];
        for(int b = 0; b<n ; b++){
            Planet p = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
            Galaxy[b] = p;
        }
        return Galaxy;
    }
}
