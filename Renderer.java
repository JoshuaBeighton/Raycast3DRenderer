import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Renderer extends JFrame {
    private Vertex cameraTerminator;
    private Vertex[][] cameraScreen;
    private final int width = 500;
    private final int height = 500;
    private Cube c;
    private GraphicsPanel graphicsPanel;
    public Renderer(Cube c) {
        super();
        setSize(width, height);
        setVisible(true);
        getContentPane().setBackground(Color.BLACK);
        initialiseCamera();
        this.c = c;
        graphicsPanel = new GraphicsPanel(getDistances());
        graphicsPanel.setBounds(0,0,width, height);
        add(graphicsPanel);
        repaint();
    }

    private void initialiseCamera() {
        cameraTerminator = new Vertex(0, 0, 5);
        double z = 3;

        double minX = -3;
        double maxX = minX * -1;
        double minY = -3;
        double maxY = minY * -1;

        double xDivisor = width / (maxX - minX);
        double yDivisor = height / (maxY - minY);
        cameraScreen = new Vertex[height][width];
        for (int i = 0; i < height; i++) {
            double y = i / yDivisor + minY;
            for (int j = 0; j < width; j++) {
                double x = j / xDivisor + minX;
                cameraScreen[i][j] = new Vertex(x, y, z);
            }
        }
    }

    @Override
    public void paint(Graphics g){
        if (graphicsPanel != null)
        graphicsPanel.paintComponent(g);
    }

    private Double[][] getDistances(){
        Double[][] distances = new Double[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                try {
                    double distance = pixelOnCube(cameraScreen[i][j], c);
                    distances[i][j] = 1 / distance;
                } catch (Exception e) {
                    distances[i][j] = (double)0;
                }
            }
        }
        return distances;
    }

    private double pixelOnCube(Vertex pixel, Cube c) throws NoIntersectionException {
        Line lineInsideCamera = new Line(cameraTerminator, pixel);
        for (Line l : c.getLines()) {
            try {
                double theta = lineInsideCamera.getTheta(l);
                return theta;
            } catch (NoIntersectionException e) {
            }
        }
        throw new NoIntersectionException();
    }
}
