import java.awt.Point;

public class PointConverter{
  public static Point convertPoint(ThreeDPoint _point){
    int x2d = (int) (Display.Width / 2 +_point.y);
    int y2d = (int) (Display.Height / 2 +_point.z);

    return (new Point(x2d, y2d));
  }
}
