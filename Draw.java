import javax.swing.JComponent;
import java.awt.*;
import java.lang.Math;

public class Draw extends JComponent {

  ThreeDPolygon right;
  ThreeDPolygon left;
  ThreeDPolygon top;
  ThreeDPolygon bottom;
  ThreeDPolygon front;
  ThreeDPolygon back;

  /*Creation du polygone*/
  ThreeDPoint[] _left = new ThreeDPoint[4];
  ThreeDPoint[] _right = new ThreeDPoint[4];
  ThreeDPoint[] _top = new ThreeDPoint[4];
  ThreeDPoint[] _bottom = new ThreeDPoint[4];
  ThreeDPoint[] _front = new ThreeDPoint[4];
  ThreeDPoint[] _back = new ThreeDPoint[4];

  int t;

  public Draw() {
    super();
    t = 0;



    _left[0] = new ThreeDPoint(0, 0, 0);
    _left[1] = new ThreeDPoint(100, 0, 0);
    _left[2] = new ThreeDPoint(100, 100, 0);
    _left[3] = new ThreeDPoint(0, 100, 0);

    _right[0] = new ThreeDPoint(0, 0, 100);
    _right[1] = new ThreeDPoint(100, 0, 100);
    _right[2] = new ThreeDPoint(100, 100, 100);
    _right[3] = new ThreeDPoint(0, 100, 100);

    _top[0] = new ThreeDPoint(100,  0, 0);
    _top[1] = new ThreeDPoint(100, 100, 0);
    _top[2] = new ThreeDPoint(100, 100, 100);
    _top[3] = new ThreeDPoint(100, 0, 100);

    _bottom[0] = new ThreeDPoint(0, 0, 0);
    _bottom[1] = new ThreeDPoint(0, 100, 0);
    _bottom[2] = new ThreeDPoint(0, 100, 100);
    _bottom[3] = new ThreeDPoint(0, 0, 100);

    _front[0] = new ThreeDPoint(0, 100, 100);
    _front[1] = new ThreeDPoint(100, 100, 100);
    _front[2] = new ThreeDPoint(100, 100, 0);
    _front[3] = new ThreeDPoint(0, 100, 0);

    _back[0] = new ThreeDPoint(0, 100, 100);
    _back[1] = new ThreeDPoint(100, 100, 100);
    _back[2] = new ThreeDPoint(100, 100, 0);
    _back[3] = new ThreeDPoint(0, 100, 0);

    this.top = new ThreeDPolygon(_top);
    this.left = new ThreeDPolygon(_left);
    this.right = new ThreeDPolygon(_right);
    this.bottom = new ThreeDPolygon(_bottom);
    this.back = new ThreeDPolygon(_back);
    this.front = new ThreeDPolygon(_front);
  }

  public ThreeDPolygon Rotate_polygon(ThreeDPolygon poly, double timeline){
    PointConverter converter = new PointConverter();
    ThreeDPoint[] new_points = new ThreeDPoint[poly.get3Dpoints().length];
    for (int i = 0; i < poly.get3Dpoints().length ; i++) {
        //new_points[i] = converter.rotateAxisX(poly.get3Dpoints()[i], true, (double) timeline);
        //poly.set3DPolygon(new_points);
        poly.print2DPolygon();
        new_points[i] = converter.rotateAxisX(poly.get3Dpoints()[i], true, (double) timeline);
        poly.set3DPolygon(new_points);
        new_points[i] = converter.rotateAxisY(poly.get3Dpoints()[i], true, (double) timeline);
        poly.set3DPolygon(new_points);
    }
    return poly;
  }

  @Override
  protected void paintComponent(Graphics pinceau) {
    Graphics carrePinceau = pinceau.create();

    /*rotation 3D points*/
    this.Rotate_polygon(top, t*0.0001);
    this.Rotate_polygon(back, t*0.0001);
    this.Rotate_polygon(bottom, t*0.0001);
    this.Rotate_polygon(front, t*0.0001);
    this.Rotate_polygon(right, t*0.0001);
    this.Rotate_polygon(left, t*0.0001);


    /*Affichage*/
    carrePinceau.setColor(Color.WHITE);
    carrePinceau.fillRect(0,0,Display.Width, Display.Height);

    carrePinceau.setColor(Color.RED);
    carrePinceau.fillPolygon(this.top.get2DPolygon());

    carrePinceau.setColor(Color.BLUE);
    carrePinceau.fillPolygon(this.right.get2DPolygon());

    carrePinceau.setColor(Color.PINK);
    carrePinceau.fillPolygon(this.bottom.get2DPolygon());

    carrePinceau.setColor(Color.YELLOW);
    carrePinceau.fillPolygon(this.left.get2DPolygon());

    carrePinceau.setColor(Color.CYAN);
    carrePinceau.fillPolygon(this.front.get2DPolygon());

    carrePinceau.setColor(Color.GREEN);
    carrePinceau.fillPolygon(this.back.get2DPolygon());

    this.t++;
  }
}
