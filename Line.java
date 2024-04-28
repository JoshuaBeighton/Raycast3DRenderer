public class Line {
    public Vertex v1;
    public Vertex v2;
    public Vertex intercept;
    public Vertex direction;

    public Line(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.intercept = v1;
        this.direction = Vertex.subtract(v2, v1);
    }

    public double getTheta(Vertex v) throws NoIntersectionException {
        Vertex thetaDirection = Vertex.subtract(direction, v);
        double x = thetaDirection.x / direction.x;
        double y = thetaDirection.y / direction.y;
        double z = thetaDirection.z / direction.z;
        if (x == y && y == z) {
            return x;
        }
        throw new NoIntersectionException();
    }

    public double getTheta(Line l) throws NoIntersectionException {
        Vertex diff = Vertex.subtract(l.intercept, intercept);

        double lineWidth = .1;

        double v1xDirection = this.direction.x;
        double v1yDirection = this.direction.y;
        double v1zDirection = this.direction.z;


        double v2xDirection = l.direction.x;
        double v2yDirection = l.direction.y;
        double v2zDirection = l.direction.z;

        

        Matrix m1 = new Matrix(v1xDirection, -1 * v2xDirection, v1yDirection, -1 * v2yDirection);
        Matrix m2 = new Matrix(v1xDirection, -1 * v2xDirection, v1zDirection, -1 * v2zDirection);
        Matrix m3 = new Matrix(v1yDirection, -1 * v2yDirection, v1zDirection, -1 * v2zDirection);

        try {
            Matrix m1Inv = m1.getInverse();
            double v1theta = m1Inv.a * diff.x + m1Inv.b * diff.y;
            double v2theta = m1Inv.c * diff.x + m1Inv.d * diff.y;
            if (Math.abs((v1theta * v1zDirection) - (v2theta * v2zDirection) - diff.z) < lineWidth){
                if (Math.abs(v2theta) <= 1){
                    return v1theta;
                }
                
            }
            
            throw new NoIntersectionException();
            
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        //try {
        //    Matrix m2Inv = m2.getInverse();
        //    double v1theta = m2Inv.a * diff.x + m2Inv.b * diff.z;
        //    double v2theta = m2Inv.c * diff.x + m2Inv.d * diff.z;
        //    if (Math.abs(v1theta * v1yDirection + v2theta * v2yDirection - diff.y) < lineWidth){
        //        if (Math.abs(v2theta) <= 1){
        //            return v1theta;
        //        }
        //    }
        //    else{
        //        throw new NoIntersectionException();
        //    }
        //} catch (ArithmeticException e) {
        //    e.printStackTrace();
        //}
        //try {
        //    Matrix m3Inv = m3.getInverse();
        //    double v1theta = m3Inv.a * diff.y + m3Inv.b * diff.z;
        //    double v2theta = m3Inv.c * diff.y + m3Inv.d * diff.z;
        //    if (Math.abs(v1theta * v1xDirection + v2theta * v2xDirection - diff.x) < lineWidth){
        //        if (Math.abs(v2theta) <= 1){
        //            return v1theta;
        //        }
        //    }
        //    else{
        //        throw new NoIntersectionException();
        //    }
        //} catch (ArithmeticException e) {
        //    e.printStackTrace();
        //}
        return 0;
    }

    @Override
    public String toString() {
        String s = "x1: " + v1.x + ", y1: " + v1.y + ", z1 " + v1.z + ",     x2: " + direction.x + ", y2: "
                + direction.y + ", z2 "
                + direction.z;
        return s;
    }
}
