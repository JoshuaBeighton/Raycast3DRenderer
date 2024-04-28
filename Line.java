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
        Vertex diff = Vertex.subtract(intercept, l.intercept);

        double lineWidth = .2;

        double v1xCoefficient = this.direction.x;
        double v1yCoefficient = this.direction.y;
        double v1zCoefficient = this.direction.z;


        double v2xCoefficient = l.direction.x;
        double v2yCoefficient = l.direction.y;
        double v2zCoefficient = l.direction.z;

        Vertex pointOfCollision;

        Matrix m1 = new Matrix(v1xCoefficient, v2xCoefficient, v1yCoefficient, v2yCoefficient);
        Matrix m2 = new Matrix(v1xCoefficient, v2xCoefficient, v1zCoefficient, v2zCoefficient);
        Matrix m3 = new Matrix(v1yCoefficient, v2yCoefficient, v1zCoefficient, v2zCoefficient);

        try {
            Matrix m1Inv = m1.getInverse();
            double v1theta = m1Inv.a * diff.x + m1Inv.b * diff.y;
            double v2theta = m1Inv.a * diff.x + m1Inv.b * diff.y;
            if (Math.abs((v1theta * v1zCoefficient) + (v2theta * v2zCoefficient) - diff.z) < lineWidth){
                if (v2theta <=0){
                    return v1theta;
                }
                
            }
            else{
                throw new NoIntersectionException();
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        try {
            Matrix m2Inv = m2.getInverse();
            double v1theta = m2Inv.a * diff.x + m2Inv.b * diff.z;
            double v2theta = m2Inv.a * diff.x + m2Inv.b * diff.z;
            if (Math.abs(v1theta * v1yCoefficient + v2theta * v2yCoefficient - diff.y) < lineWidth){
                if (v2theta <= 0){
                    return v1theta;
                }
            }
            else{
                throw new NoIntersectionException();
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        try {
            Matrix m3Inv = m3.getInverse();
            double v1theta = m3Inv.a * diff.y + m3Inv.b * diff.z;
            double v2theta = m3Inv.a * diff.y + m3Inv.b * diff.z;
            if (Math.abs(v1theta * v1xCoefficient + v2theta * v2xCoefficient - diff.x) < lineWidth){
                if (v2theta <= 0){
                    return v1theta;
                }
            }
            else{
                throw new NoIntersectionException();
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
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
