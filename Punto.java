/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1;

import java.io.*;
import java.util.ArrayList;
import org.w3c.dom.*;
/**
 *
 * @author Eloy
 */
public class Punto {
    
    double x,y;
    int distancia;
      
    Document doc;
    
    FileReader fr= null;
    BufferedReader in = null;
    FileWriter fw = null;
    PrintStream pw = null;
    
    public ArrayList<Punto> puntos = new ArrayList<>();
    protected ArrayList<Punto>puntoMenor = new ArrayList<>();
    
    public Punto(){}
    
    public Punto(double x, double y){
        this.x=x;
        this.y = y;
    }
     
    public double getX(){return x;}
    
    public double getY(){return y;}
    
    public void generarPuntosAleatorios(int numPuntos)
    {
        for(int i=0; i<numPuntos;i++)
        {
            double randomX = Math.floor(Math.random()*(1800-0+1)+0);
            double randomY = Math.floor(Math.random()*(800-0+1)+0);
            
            Punto p = new Punto(randomX,randomY);
            puntos.add(p);
        }
        
        quickSort(puntos, 0, puntos.size()-1);
    }
    
    public void abrirArchivo(File file) throws IOException{
        
        File f = file;
        try {
            fr = new FileReader(f);
            
            in = new BufferedReader(fr);
            
            String punto="";
            int contador=-1;
            for(int i=0;i<6;i++) 
            {
               in.readLine();
               contador++;
            }
            
            boolean finFichero = false;
            String linea []=null;
            punto=in.readLine();
            while(!finFichero)
            {
                if(contador==5)
                {                    
                      
                    linea=punto.split(" ");
                    
                    
                    if(linea.length==3)
                    {
                    Double varX = Double.parseDouble(linea[1]);
                    Double varY = Double.parseDouble(linea[2]);
                    
                    Punto p = new Punto(varX,varY);
                    
                    puntos.add(p);
                    }
                    punto = in.readLine();
                    if(punto.equals("EOF"))
                        finFichero=true;   
                }
            }
            
            if(punto==null)
            {
                in.close();
                fr.close();
            }
                
            quickSort(puntos, 0, puntos.size()-1);
            
        } catch (FileNotFoundException ex) {}
    }
    
    public void quickSort(ArrayList<Punto> P, int indice, int ultimo){
        if(indice<ultimo)
        {
            int intPartition=partition(P,indice,ultimo);
            quickSort(P, indice, intPartition);
            quickSort(P, intPartition+1, ultimo);
        }    
    }
    
    private int partition(ArrayList<Punto> P, int indice, int ultimo){
        double pivote = calcularTamanio(P.get(indice));
        int i=indice-1;
        int j=ultimo+1;
        
        do
        {
            do
            {
                j--;
            } while(calcularTamanio(P.get(j))>pivote);
            
            do
            {
                i++;
            }while(calcularTamanio(P.get(i))<pivote); 
        
            if(i<j)
            {
                Punto aux = puntos.get(j);
                puntos.set(j,puntos.get(i));
                puntos.set(i,aux);
            }
        }while(i<j);
        
        return j;
    }
    
    private double calcularTamanio(Punto p){
        
        return Math.sqrt(Math.pow(p.getX()+p.getY(),2));
    }
    
    protected double calcularDistancia(Punto p1, Punto p2){
        
        double varX = Math.pow(p1.getX()-p2.getX(), 2);
        double varY = Math.pow(p1.getY()-p2.getY(),2);
        
        return Math.sqrt(varX+varY);
    }

    @Override
    public String toString() {
        return "Puntos{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
