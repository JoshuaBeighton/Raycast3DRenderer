import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
public class GraphicsPanel extends JPanel {
    public Double[][] values;
    public GraphicsPanel(Double[][] values){
        super();
        this.values = values;
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0, 100, 100);
        for (int i = 0; i < values.length; i++){
            for (int j = 0; j < values[0].length; j++){
                try{
                    Double distance = values[i][j];
                    int brightness = (int)(distance * 255);
                    if (brightness > 255){
                        brightness = 255;
                    }
                    if (brightness !=0){
                        g.setColor(new Color(100 + (brightness/3),(brightness /3),brightness));
                    }
                    else{
                        g.setColor(Color.black);
                    }
                    
                    
                    g.fillRect(j, i, 1, 1);
                }
                catch (Exception e){

                }
            }
        }
    }
}
