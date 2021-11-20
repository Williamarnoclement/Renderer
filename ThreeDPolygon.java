import java.awt.Point;
import java.awt.Polygon;

public class ThreeDPolygon{
  private ThreeDPoint[] points;

  public ThreeDPolygon(ThreeDPoint... _points){
    this.points = new ThreeDPoint[_points.length];
    for (int i = 0;i < _points.length ; i++) {
      ThreeDPoint p = _points[i];
      this.points[i] = new ThreeDPoint(p.x, p.y, p.z);
    }
  }


  public Polygon get2DPolygon(){
    Polygon poly = new Polygon();
    for (int i = 0;i < this.points.length ; i++) {
      Point p = new PointConverter().convertPoint(this.points[i]);
      poly.addPoint(p.x, p.y);
    }
    return poly;
  }

  public void print2DPolygon(){
    Polygon poly = new Polygon();
    //System.out.println("-----");
    for (int i = 0;i < this.points.length ; i++) {
      Point p = new PointConverter().convertPoint(this.points[i]);
      poly.addPoint(p.x, p.y);
      //System.out.println("{ "+p.x+" , "+p.y+" }");
    }
  }
}
