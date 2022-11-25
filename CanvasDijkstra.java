package Practica1B;


import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eloy
 */
public class CanvasDijkstra extends Canvas{
    
    Dijkstra dis;
    String[] arraySplited;

    public CanvasDijkstra() {}
    
    public CanvasDijkstra(Dijkstra d) {
        setSize(5000,1000);
        setBackground(Color.WHITE);
        dis = d;
        arraySplited=null;
        repaint();
    }
    
    @Override
    public void paint(Graphics g){//Split de path[] y tomar posición del array
        Image ima = createImage(this.getWidth(),this.getHeight());
        Graphics og = ima.getGraphics();
        
        Graphics2D grosor = (Graphics2D)og;
        grosor.setStroke(new BasicStroke(2));
                
        for(int i=0;i<dis.n;i++)
        {
            og.setColor(Color.red);
            if(dis.distance[i]!=Double.POSITIVE_INFINITY)
            {
                og.drawOval((int)dis.c.get(i).getX(), (int)dis.c.get(i).getY(), 30,30);
            
                String nodo = String.valueOf(dis.c.get(i).getValor());
               
                og.drawString(nodo, (int)dis.c.get(i).getX()+15, (int)dis.c.get(i).getY()+15);

                og.setColor(Color.BLACK);  
                
                arraySplited=dis.path[i].split(" ");
                
                for(int j=0;j<arraySplited.length;j++)
                {
                    if(!arraySplited[j].equals(""))
                    {
                        int valor = Integer.parseInt(arraySplited[j]);
                        int pos = posCiudad(valor);

                        if(pos!=-1)
                        {
                            og.drawLine((int)dis.c.get(i).getX(), (int)dis.c.get(i).getY(), (int)dis.c.get(pos).getX(), (int)dis.c.get(pos).getY());
                            og.drawString(String.valueOf(dis.distance[i]), (int)dis.c.get(i).getX(), (int)dis.c.get(i).getY());
                        }          
                    }
                }      
            }  
        }  
        
        og.drawImage(ima, 0, 0, null);
        g.drawImage(ima, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public int posCiudad(int valor){
        int pos =-1;
        boolean encontrado = false;
        int i=0;
        while(i<dis.n && !encontrado)
        {
            if(dis.c.get(i).getValor()==valor)
                encontrado=true;
            else i++;
        }
        if(encontrado)
           pos=i;
        
        return pos;
    }
    
}
