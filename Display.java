import java.awt.*;
import javax.swing.*;

public class Display extends Canvas implements Runnable {

  private static final long SerialVersionUID = 1L;

  public Thread thread;
  public JFrame frame;

  public static final String title = "3D renderer";

  public static final int Width = 800;
  public static final int Height = 400;

  public static Boolean isRunning = false;

  public Draw draw;

  public Display(){
    this.frame = new JFrame();

    Dimension size = new Dimension(Width, Height);

    this.frame.setPreferredSize(size);
    this.draw = new Draw();
    this.frame.setContentPane(draw);
  }

  public static void main(String[] args) {

     Display display = new Display();
     display.frame.setTitle(title);
     display.frame.add(display);
     display.frame.pack();
     display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     display.frame.setResizable(false);
     display.frame.setVisible(true);

     display.start();
  }

  public synchronized void start(){
    this.isRunning = true;
    this.thread = new Thread(this, "Display");
    this.thread.start();
  }

  public synchronized void stop(){
    isRunning = false;
    try{
      this.thread.join();
    } catch(InterruptedException e){
      System.out.println("InterruptedException");
    }
  }

  @Override
  public void run(){

    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    int fps = 60;
    final double ns = 1000000000.0/fps;
    double delta =  0;
    int frames =  0;


    while(isRunning){
      long now = System.currentTimeMillis();
      delta += (now - lastTime)/ns;
      lastTime = now;
      while(delta >= 1){
        update();
        delta--;
      }
      render();
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        //this.frame.setTitle(this.title + " ¡ " + frames + " fps");
        frames = 0;
      }
    }

    stop();
  }


  private void render(){
    this.draw.repaint();
  }

  private void update(){

  }
}
