import javax.swing.JComponent;
import java.awt.*;
import java.lang.Math;

public class Draw extends JComponent {

  ThreeDPolygon[] square = new ThreeDPolygon[6];

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

    this.square[0] = new ThreeDPolygon(_top);
    this.square[1]  = new ThreeDPolygon(_left);
    this.square[2]  = new ThreeDPolygon(_right);
    this.square[3]  = new ThreeDPolygon(_bottom);
    this.square[4]  = new ThreeDPolygon(_back);
    this.square[5]  = new ThreeDPolygon(_front);
  }

  public ThreeDPolygon Rotate_polygon(ThreeDPolygon poly, double timeline){
    PointConverter converter = new PointConverter();
    ThreeDPoint[] new_points = new ThreeDPoint[poly.get3Dpoints().length];
    for (int i = 0; i < poly.get3Dpoints().length ; i++) {
        poly.print2DPolygon();
        new_points[i] = converter.rotateAxisX(poly.get3Dpoints()[i], true, (double) timeline);
        poly.set3DPolygon(new_points);
        new_points[i] = converter.rotateAxisY(poly.get3Dpoints()[i], true, (double) timeline);
        poly.set3DPolygon(new_points);
    }
    return poly;
  }

  public ThreeDPolygon GetClosest_polygon(ThreeDPolygon un, ThreeDPolygon deux){
    double somme_x = 0;
    for (int i = 0; i < un.get3Dpoints().length ; i++) {
      somme_x = somme_x + un.get3Dpoints()[i].x;
    }
    double moyenne_x_un = somme_x / un.get3Dpoints().length;

    somme_x = 0;
    for (int i = 0; i < deux.get3Dpoints().length ; i++) {
      somme_x = somme_x + deux.get3Dpoints()[i].x;
    }
    double moyenne_x_deux = somme_x / deux.get3Dpoints().length;

    if (moyenne_x_un > moyenne_x_deux) {
      return deux;
    }
    return un;
  }

  public ThreeDPolygon[] permutate_in_array(ThreeDPolygon[] l, int a, int b){
    ThreeDPolygon tmp = l[a];
    l[a] = l[b];
    l[b] = tmp;
    return l;
  }

  public ThreeDPolygon[] Get3DPolygonLayers(ThreeDPolygon[] layer_list){
    for (int i = 0; i < layer_list.length; i++) {
        for (int j = i+1; j < layer_list.length; j++) {
          ThreeDPolygon current;
          current = GetClosest_polygon(layer_list[i], layer_list[j]);
          if (current == layer_list[i]) {
            layer_list = permutate_in_array(layer_list, i,j);
          }
        }
    }
    return layer_list;
  }

  @Override
  protected void paintComponent(Graphics pinceau) {
    Graphics carrePinceau = pinceau.create();

    /*rotation 3D points*/
    for (int i = 0; i < square.length; i++) {
      this.Rotate_polygon(square[i], t*0.0001);
    }


    /*Affichage*/
    /*Reset display*/
    carrePinceau.setColor(Color.WHITE);
    carrePinceau.fillRect(0,0,Display.Width, Display.Height);

    square = Get3DPolygonLayers(square);

    carrePinceau.setColor(Color.RED);

    for (int i = 0; i < square.length; i++) {
      if (i == 0) carrePinceau.setColor(Color.RED);
      if (i == 1) carrePinceau.setColor(Color.BLUE);
      if (i == 2) carrePinceau.setColor(Color.PINK);
      if (i == 3) carrePinceau.setColor(Color.YELLOW);
      if (i == 4) carrePinceau.setColor(Color.CYAN);
      if (i == 5) carrePinceau.setColor(Color.GREEN);
       carrePinceau.fillPolygon(this.square[i].get2DPolygon());
    }

    this.t++;
  }
}
