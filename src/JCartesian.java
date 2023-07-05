import java.awt.*;

public class JCartesian extends JGraph {

    private double mx = 0;
    private double qx = 0;
    private double my = 0;
    private double qy = 0;

    private double xMin = 0;
    private double yMin = 0;
    private double xMax = 0;
    private double yMax = 0;

    public JCartesian(int width, int height, double xMin, double yMin, double xMax, double yMax) {
        super(width, height);

        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;

        this.mx = width / (xMax - xMin);
        this.qx = -mx * xMin;
        this.my = height / (yMin - yMax);
        this.qy = -my * yMax;
    }

    private int xToPanel(double x) {

        double d = (x * this.mx) + this.qx;
        //System.out.println("f(x = " + x + ") -> " + (int) Math.floor(d));
        return (int) Math.floor(d);
    }
    private int yToPanel(double y) {

        double d = (y * this.my) + this.qy;
        //System.out.println("f(y = " + y + ") -> " + (int) Math.floor(d));
        return (int) Math.floor(d);
    }

    public void drawLine(double x1, double y1, double x2, double y2, Color color) {

        super.drawLine(this.xToPanel(x1),
                this.yToPanel(y1),
                this.xToPanel(x2),
                this.yToPanel(y2),
                color);
    }

    public void drawPoint(double x, double y, Color color) {

        super.drawPoint(this.xToPanel(x),
                this.yToPanel(y),
                color);
    }

    public void drawPoint2(double x, double y, Color color) {

        super.drawPoint2(this.xToPanel(x),
                this.yToPanel(y),
                color);
    }

    public void drawOval(double x1, double y1, double x2, double y2, Color color) {

        int x1p = this.xToPanel(x1);
        int y1p = this.yToPanel(y1);
        int x2p = this.xToPanel(x2);
        int y2p = this.yToPanel(y2);
        int width = x2p - x1p;
        int height = -(y2p - y1p);
        super.drawOval(x1p, y2p, width, height, color);
    }

    public void fillOval(double x1, double y1, double x2, double y2, Color color) {

        int x1p = this.xToPanel(x1);
        int y1p = this.yToPanel(y1);
        int x2p = this.xToPanel(x2);
        int y2p = this.yToPanel(y2);
        int width = x2p - x1p;
        int height = -(y2p - y1p);
        super.fillOval(x1p, y2p, width, height, color);
    }

    public void drawRect(double x1, double y1, double x2, double y2, Color color) {

        int x1p = this.xToPanel(x1);
        int y1p = this.yToPanel(y1);
        int x2p = this.xToPanel(x2);
        int y2p = this.yToPanel(y2);
        int width = x2p - x1p;
        int height = -(y2p - y1p);
        super.drawRect(x1p, y2p, width, height, color);
    }

    public void fillRect(double x1, double y1, double x2, double y2, Color color) {

        int x1p = this.xToPanel(x1);
        int y1p = this.yToPanel(y1);
        int x2p = this.xToPanel(x2);
        int y2p = this.yToPanel(y2);
        int width = x2p - x1p;
        int height = -(y2p - y1p);
        super.fillRect(x1p, y2p, width, height, color);
    }

    public void drawString(double x, double y, String text, Color color) {

        int xp = this.xToPanel(x);
        int yp = this.yToPanel(y);
        super.drawString(xp, yp, text, color);
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }
}
