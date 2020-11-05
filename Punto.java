package Imagen_Eventos;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Punto{

    private int x, y;
    private int diametro = 30;

    /**
    Generar un punto en las coordenadas (0,0)
    */
    public Punto() {
        this.x = 0;
        this.y = 0;
    }
/**
 * Generar un punto en las coordenadas (x, y)
 * @param x Coordenada en x del punto
 * @param y Coordenada en y del punto
 */
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }   
    public void dibujarC(Graphics g){
        g.fillOval(GetX(), GetY(), diametro, diametro);
    }    
    public void dibujarR(Graphics g){
        g.fillRect(GetX(), GetY(), diametro, diametro*2);
    }
    public void dibujarLinea(Graphics g){
        g.drawLine(0, 0, GetX(), GetY());
    }
    /**
     * Asignar valor de la Coordenada x
     * @param x Coordenada x
     */
    public void SetX(int x){
        this.x = x;
    }
    /**
     * Obtener el valor de la Coordenada en x
     * @return Coordenada en x del punto
     */
    public int GetX(){
        return this.x;
    }
    /**
     * Asignar valor de la Coordenada y
     * @param x Coordenada x
     */
    public void SetY(int y){
        this.y = y;
    }
    /**
     * Obtener el valor de la Coordenada en y
     * @return Coordenada en y del punto
     */
    public int GetY(){
        return this.y;
    }               
    
}
