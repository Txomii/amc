package Practica1B;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.w3c.dom.Document;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eloy
 */
public class Ciudad {
    
    Document doc;
    
    FileReader fr= null;
    BufferedReader in = null;
    FileWriter fw = null;
    PrintStream pw = null;
    
    double x,y;
    int valor;
    
    public ArrayList<Ciudad> ciudades = new ArrayList();
    
    
    public Ciudad(){}
    
    public Ciudad(double x, double y,int valor)
    {
        this.x=x;
        this.y=y;
        this.valor=valor;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public void abrirArchivo(File file) throws IOException{
        
        File f = file;
        try {
            fr = new FileReader(f);
            
            in = new BufferedReader(fr);
            
            String coordenada="";
            int contador=-1;
            for(int i=0;i<6;i++) 
            {
               in.readLine();
               contador++;
            }
            
            boolean finFichero = false;
            String linea []=null;
            coordenada=in.readLine();
            while(!finFichero)
            {
                if(contador==5)
                {                    
                      
                    linea=coordenada.split(" ");
                    
                    
                    if(linea.length==3)
                    {
                        int value = Integer.parseInt(linea[0]);
                        Double varX = Double.parseDouble(linea[1]);
                        Double varY = Double.parseDouble(linea[2]);
                        
                         Ciudad c = new Ciudad(varX,varY,value);

                        ciudades.add(c);
                    }
                    coordenada = in.readLine();
                    if(coordenada.equals("EOF"))
                        finFichero=true;   
                }
            }
            
            if(coordenada==null)
            {
                in.close();
                fr.close();
            }
            
            quickSort(ciudades, 0, ciudades.size()-1);
        } catch (FileNotFoundException ex) {}
    }
    
    public void generarCiudadesAleatorias(int numCiudades)
    {
        for(int i=1; i<=numCiudades;i++)
        {
            double randomX = Math.floor(Math.random()*(1800-0+1)+0);
            double randomY = Math.floor(Math.random()*(800-0+1)+0);
            int Valoraleatorio =(int) Math.floor(Math.random()*(numCiudades-0+1)+0);
            //int vvalor = 10;
            Ciudad co = new Ciudad(randomX,randomY, Valoraleatorio);
            ciudades.add(co);
        }
        
        quickSort(ciudades, 0, ciudades.size()-1);
    }
   
    public void quickSort(ArrayList<Ciudad> co, int indice, int ultimo){
        if(indice<ultimo)
        {
            int intPartition=partition(co,indice,ultimo);
            quickSort(co, indice, intPartition);
            quickSort(co, intPartition+1, ultimo);
        }    
    }
    
    private int partition(ArrayList<Ciudad> co, int indice, int ultimo){
        double pivote = calcularTamanio(co.get(indice));
        int i=indice-1;
        int j=ultimo+1;
        
        do
        {
            do
            {
                j--;
            } while(calcularTamanio(co.get(j))>pivote);
            
            do
            {
                i++;
            }while(calcularTamanio(co.get(i))<pivote); 
        
            if(i<j)
            {
                Ciudad aux = ciudades.get(j);
                ciudades.set(j,ciudades.get(i));
                ciudades.set(i,aux);
            }
        }while(i<j);
        
        return j;
    }
    
    private double calcularTamanio(Ciudad c){
        
        return Math.sqrt(Math.pow(c.getX()+c.getY(),2));
    }
    
    public Double calcularDistancia(Ciudad c1, Ciudad c2)
    {
        double varX = Math.pow(c1.getX()-c2.getX(), 2);
        double varY = Math.pow(c1.getY()-c2.getY(),2);
        
        return ((Math.sqrt(varX+varY)*100)%100)+1;
    }
}
