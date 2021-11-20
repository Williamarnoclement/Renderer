import javax.swing.JComponent;
import java.awt.*;

public class Draw extends JComponent {

  ThreeDPolygon poly;

  public Draw() {
    super();

    ThreeDPoint[] p = new ThreeDPoint[3];

    p[0] = new ThreeDPoint(0, 60, 0);
    p[1] = new ThreeDPoint(100, 55, 10);
    p[2] = new ThreeDPoint(30, 100, 60);
    this.poly = new ThreeDPolygon(p);


  }

  @Override
  protected void paintComponent(Graphics pinceau) {
    Graphics carrePinceau = pinceau.create();

    carrePinceau.setColor(Color.RED);

    this.poly.print2DPolygon();
    carrePinceau.fillPolygon(this.poly.get2DPolygon());

  }
}
