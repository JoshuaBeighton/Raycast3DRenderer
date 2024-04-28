import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        Cube cube = getCube();
        Renderer renderer = new Renderer(cube);
    }

    private static Cube getCube(){
        List<Vertex> vertices = new ArrayList<Vertex>();
        vertices.add(new Vertex(1, 1,1));
        vertices.add(new Vertex(1, 1,-1));
        vertices.add(new Vertex(1, -1,1));
        vertices.add(new Vertex(1, -1,-1));
        vertices.add(new Vertex(-1, 1,1));
        vertices.add(new Vertex(-1, 1,-1));
        vertices.add(new Vertex(-1, -1,1));
        vertices.add(new Vertex(-1, -1,-1));
        return new Cube(vertices);
    }
}
