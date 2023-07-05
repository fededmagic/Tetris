import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JGraph extends JPanel {

    private BufferedImage image = null; //buffer relay
    private Graphics2D graph = null;
    private Color background = null;
    private Color pencil = null;
    private int width = 0;
    private int height = 0;

    public void setBackground(Color pencil) {
        this.background = background;
    }

    public void setPencil(Color pencil) {
        this.pencil = pencil;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBackground() {
        return background;
    }

    public Color getPencil() {
        return pencil;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public JGraph(int width, int height) {

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.background = Color.LIGHT_GRAY;
        this.pencil = Color.BLACK;
        this.width = width;
        this.height = height;
        clean();
    }

    public void clean() {

        Graphics2D graph = this.image.createGraphics();     //crea un nuovo oggetto di tipo grafico
        graph.setColor(this.pencil);
        graph.setBackground(this.background);
        graph.clearRect(0,0, this.width, this.height);    //crea un'immagine in memoria
        repaint();      //fa il refresh a video
    }

    public void paint(Graphics g) {     //verrà chiamato da swing quando deve fare il refresh

        super.paint(g);
        g.drawImage(this.image, 0,0, this.width, this.height, null);    //prende l'immagine di relay e la spalma sul panel
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.drawLine(x1, y1, x2, y2);
        repaint();
    }

    public void drawPoint(int x, int y, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.drawLine(x, y, x, y);
        repaint();
    }

    public void drawPoint2(int x, int y, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.fillOval(x, y, 2, 2);
        repaint();
    }

    public void drawOval(int x, int y, int width, int height, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.drawOval(x, y, width, height);
        repaint();
    }

    public void fillOval(int x, int y, int width, int height, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.fillOval(x, y, width, height);
        repaint();
    }

    public void drawRect(int x, int y, int width, int height, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.drawRect(x, y, width, height);
        repaint();
    }
    public void fillRect(int x, int y, int width, int height, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.fillRect(x, y, width, height);
        repaint();
    }

    public void drawString(int x, int y, String text, Color color) {

        Graphics2D graph = this.image.createGraphics();
        graph.setColor(color);
        graph.drawString(text, x, y);
        repaint();
    }
}