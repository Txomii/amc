/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Eloy
 */
public class CanvasTriangulo extends Canvas{

    ArrayList<Punto> pu = new ArrayList();
    Triangulo tMenor = null;

    public CanvasTriangulo() {}
    
    public CanvasTriangulo(ArrayList<Punto> p,Triangulo t) {
        setSize(5000,1000);
        setBackground(Color.WHITE);
        pu=p;
        tMenor=t;
        repaint();
    }
    
    
    @Override
    public void paint(Graphics g){
        Image ima = createImage(this.getWidth(),this.getHeight());
        Graphics og = ima.getGraphics();
        
        og.setColor(Color.black);
        for(int i=0;i<pu.size();i++)
            og.drawOval((int)pu.get(i).getX(), (int)pu.get(i).getY(), 10,10);
        
        og.setColor(Color.red);
        Graphics2D grosor = (Graphics2D)og;
        grosor.setStroke(new BasicStroke(2));
        if(tMenor.pMenor==tMenor.p1)
        {
            grosor.drawLine((int)tMenor.pMenor.getX(), (int)tMenor.pMenor.getY(), (int)tMenor.p2.getX(), (int)tMenor.p2.getY());
            grosor.drawLine((int)tMenor.pMenor.getX(), (int)tMenor.pMenor.getY(), (int)tMenor.p3.getX(), (int)tMenor.p3.getY());
        }
        
        else if(tMenor.pMenor==tMenor.p2)
        {
            grosor.drawLine((int)tMenor.pMenor.getX(), (int)tMenor.pMenor.getY(), (int)tMenor.p1.getX(), (int)tMenor.p1.getY());
            grosor.drawLine((int)tMenor.pMenor.getX(), (int)tMenor.pMenor.getY(), (int)tMenor.p3.getX(), (int)tMenor.p3.getY());
        }
        
        else
        {
            grosor.drawLine((int)tMenor.pMenor.getX(), (int)tMenor.pMenor.getY(), (int)tMenor.p2.getX(), (int)tMenor.p2.getY());
            grosor.drawLine((int)tMenor.pMenor.getX(), (int)tMenor.pMenor.getY(), (int)tMenor.p1.getX(), (int)tMenor.p1.getY());
        }
       
        og.drawImage(ima, 0, 0, null);
        g.drawImage(ima, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void representa(ArrayList<Punto>p){
        pu=p;
        repaint();
    }
}
