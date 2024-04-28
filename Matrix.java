public class Matrix {
    public double a;
    public double b;
    public double c;
    public double d;
    public Matrix(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public double getDeterminant(){
        return a * d - b * c;
    }

    public Matrix getInverse(){
        double det = getDeterminant();
        double a = this.d / det;
        double b = -1 * this.b /det;
        double c = -1 * this.c /det;
        double d = this.a /det;

        return new Matrix(a, b, c, d);
    }
}
