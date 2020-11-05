package Imagen_Eventos;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends JPanel {

    private ImageIcon im;
    private Point pi = new Point(0, 0);
    private ArrayList<Punto> circulo = null;
    private ArrayList<Punto> rectangulos = null;
    private ArrayList<Punto> lineas = null;
    private JLabel lbl;
    
    //Constructor inicializador
    public Window() {

        setCirculo(new ArrayList<>());
        setRectangulos(new ArrayList<>());
        setLineas(new ArrayList<>());
        
        lbl = new JLabel("-----------", SwingConstants.CENTER);

        im = new ImageIcon(getClass().getResource("patricio.png"));

        //Eventos anonimos        
        MouseAdapter ma = new MouseAdapter() {
            Graphics g = getGraphics();
            int disX, disY;
            final int constante = 30;
            boolean mov = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    getCirculo().add(new Punto(e.getX(), e.getY()));
                    repaint();
                }
                if (e.getButton() == 3) {
                    getRectangulos().add(new Punto(e.getX(), e.getY()));
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {                
                System.out.println("Mouse dentro, puedes dibujar o mover la imagen");
                e.getXOnScreen();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                lbl.setText("Mouse fuera");
                System.out.println("Mouse fuera, no puedes dibujar");
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (mov) {
                    pi.x = e.getX() - disX;
                    pi.y = e.getY() - disY;
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Rectangle rect = new Rectangle(pi.x, pi.y, 100, 100);
                if (rect.contains(e.getX(), e.getY())) {
                    disX = e.getX() - pi.x;
                    disY = e.getY() - pi.y;
                    mov = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                disX = 0;
                disY = 0;
                mov = false;
            }

            @Override
            public void mouseMoved(MouseEvent e) {    
                getLineas().add(new Punto(e.getX(), e.getY()));
                repaint();
                System.out.println("Mouse en movimiendo");                
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent mwe) {
                System.out.println("Scroll en movimiento");
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
    }
    //Metodo para dibujar
    public void paint(Graphics g) {
        g.drawImage(im.getImage(), pi.x, pi.y, this);

        for (Punto circulo : getCirculo()) {
            circulo.dibujarC(g);
        }
        for (Punto rectangulo : getRectangulos()) {
            rectangulo.dibujarR(g);
        }
        for (Punto linea : getLineas()) {
            linea.dibujarLinea(g);
        }
    }
    //Setter del circulo
    public void setCirculo(ArrayList<Punto> circulo) {
        this.circulo = circulo;
    }
    //Setter de rectangulo
    public void setRectangulos(ArrayList<Punto> rectangulos) {
        this.rectangulos = rectangulos;
    }
    //Getter rectangulos
    public ArrayList<Punto> getCirculo() {
        return circulo;
    }
    //Getter rectangulos
    public ArrayList<Punto> getRectangulos() {
        return rectangulos;
    }
    //Setter Lineas
    public void setLineas(ArrayList<Punto> lineas) {
        this.lineas = lineas;
    }
    //Getter Lineas
    public ArrayList<Punto> getLineas() {
        return lineas;
    }
   
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Eventos de Mouse");
        Window w = new Window();
        f.getContentPane().add(new Window());
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 800);
        f.setVisible(true);
        f.add(w.lbl, BorderLayout.SOUTH);
    }
}
