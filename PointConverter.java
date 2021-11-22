import java.awt.Point;

public class PointConverter{
  public static Point convertPoint(ThreeDPoint _point){
    int x2d = (int) (Display.Width / 2 +_point.y);
    int y2d = (int) (Display.Height / 2 +_point.z);

    return (new Point(x2d, y2d));
  }

  public static ThreeDPoint rotateAxisX(ThreeDPoint _point, boolean CW, double degres ){
    double radius = Math.sqrt(_point.y*_point.y + _point.z*_point.z );
    double theta = Math.atan2(_point.y, _point.z);
    theta += 2*Math.PI/360*degres*(CW?1:-1);
    ThreeDPoint new_point = new ThreeDPoint(_point.x,radius * Math.sin(theta),radius * Math.cos(theta));
    return new_point;
  }

  public static ThreeDPoint rotateAxisY(ThreeDPoint _point, boolean CW, double degres ){
    double radius = Math.sqrt(_point.x*_point.x + _point.z*_point.z );
    double theta = Math.atan2(_point.x, _point.z);
    theta += 2*Math.PI/360*degres*(CW?1:-1);
    ThreeDPoint new_point = new ThreeDPoint(radius * Math.sin(theta),_point.y  ,radius * Math.cos(theta));
    return new_point;
  }
}
