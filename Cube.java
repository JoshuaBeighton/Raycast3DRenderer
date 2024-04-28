import java.util.ArrayList;
import java.util.List;

public class Cube {
    private List<Vertex> vertices;
    private List<Line> lines;

    public List<Line> getLines() {
        return lines;
    }

    public Cube(List<Vertex> vertices) {
        this.vertices = vertices;
        this.lines = generateLines();
    }

    private List<Line> generateLines() {
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                //if (vertices.get(i).getDistance(vertices.get(j)) == 2){
                //    Line l = new Line(vertices.get(i), vertices.get(j));
                //    lines.add(l);
                //}
                Line l = new Line(vertices.get(i), vertices.get(j));
                lines.add(l);
            }
        }
        return lines;
    }

    //private Vertex getCenter() {
    //    double xTotal = 0;
    //    double yTotal = 0;
    //    double zTotal = 0;
    //    for (Vertex v : vertices) {
    //        xTotal += v.x;
    //        yTotal += v.y;
    //        zTotal += v.z;
    //    }
    //    return new Vertex(xTotal / vertices.size(), yTotal / vertices.size(), zTotal / vertices.size());
    //}
}
