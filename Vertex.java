public class Vertex {
    public double x;
    public double y;
    public double z;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vertex subtract(Vertex v1, Vertex v2) {
        double x = v1.x - v2.x;
        double y = v1.y - v2.y;
        double z = v1.z - v2.z;
        return new Vertex(x,y,z);
    }

    public double getDistance(Vertex v) {
        double xDiff = x - v.x;
        double yDiff = y - v.y;
        double zDiff = z - v.z;
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2) + Math.pow(zDiff, 2));
    }


    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}
