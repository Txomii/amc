package Practica1B;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eloy
 */
public class Dijkstra {
    
    Ciudad c1,c2, cAux;
    double distancia;
    
    double[] distance;
    public ArrayList<Ciudad>c;
    int numAristas;
    static int [][] aristas;
    boolean [] visitados;
    int n;
    double distanciaTotal;
    public String [] path;

    public Dijkstra(){}
    
    public Dijkstra(ArrayList<Ciudad> ciudades){
        n=ciudades.size();
        numAristas=0;
        distanciaTotal = 0;
        aristas= new int[n][n];
        c = new ArrayList<>(n);
        visitados=new boolean[n+1];
        distance = new double[n];
        c=ciudades;
        for(int i=0;i<n;i++)
            distance[i]=Double.POSITIVE_INFINITY;
        
        path=new String[n];
        for(int i=0;i<n;i++)
            path[i]="";
    }
    
    public Dijkstra(Ciudad c1, Ciudad c2)
    {
        this.c1=c1;
        this.c2=c2;
        
        cAux=new Ciudad();
        
        distancia=cAux.calcularDistancia(c1, c2);
    }
    
    
    public int getFirstCO(int index)
    {
        for(int i=0;i<c.size();i++)
            if(aristas[index][i]>0)return i;
        
        return n;
    }
    
    public int getNumAristas(){return numAristas;}
    
    public int getNextCO(int index,int firstCO){
        for(int i=firstCO+1; i<c.size();i++)
            if(aristas[index][i]>0)
                return i;
        
        return n;
    }
    
    public static void show()
    {
        for (int [] arista: aristas)
            System.out.println(Arrays.toString(arista));
    }
    
    public void caminoMinimo(int index)
    {
        int CO;
        int first = index;
        int cont=0;
        
        distance[index]=0;
        
        while(!visitados[first]){
            CO = getFirstCO(first);
            while(visitados[CO])
                CO=getNextCO(first, CO);
            
            if(CO==n)
                visitados[first]=true;
            
            else{
                while(!visitados[CO]&&CO<n){
                    
                    visitados[first]=true;
                    double distanciaAcutal = distance[first]+aristas[first][CO];
                    if(distanciaAcutal<distance[CO])
                    {
                        distance[CO]=distanciaAcutal;                        
                        path[CO]=path[first]+" "+c.get(first).getValor();   
                         
                    }                
                   
                    CO = getNextCO(first, CO);   
                }
            }
                  
            first=indexGet(distance, visitados);
        }
        
        for (int i = 0; i <n ; i++)
            path[i] = path[i]+" "+c.get(i).getValor();
        
        
        for(int i=0;i<distance.length;i++)
            if(distance[i]!=Double.POSITIVE_INFINITY)
                distanciaTotal+=distance[i];
            
        System.out.println("DISTANCIA TOTAL: "+ distanciaTotal);
        System.out.println("DIMENSION: " + c.size());
        System.out.println("Iniciar nodo:"+c.get(index).getValor());

        for (int i = 0; i <n ; i++) 
            if(distance[i]!=Double.POSITIVE_INFINITY && distance[i]!=0.0)
                System.out.println(distance[i]+" - "+path[i]);  

    }
    
    
    public int indexGet(double [] distance, boolean [] visited)
    {
        int j=0;
        
        double disMin = Double.POSITIVE_INFINITY;
        for(int i=0; i<distance.length;i++)
            if(!visited[i])
                if(distance[i]<disMin)
                {
                    disMin=distance[i];
                    j=i;
                }
        
        return j;
    }
    
    
    public void generaGrafo(ArrayList<Ciudad> c)//addArista
    {
        for(int i=0; i<c.size()-1;i++)
            for(int j=i+1;j<c.size()-1;j++)
            {
                Dijkstra g= new Dijkstra(c.get(i),c.get(j));
                aristas[i][j]=(int)g.distancia;  
                numAristas++;
            }
    }
    
    public void guardarArchivo(String ruta, String name,String ext) throws IOException
    {
        File file = new File(ruta+"."+ext);
        
        if(!file.exists())
            file.createNewFile();
        
        PrintWriter writer = new PrintWriter(file,"UTF-8");
        
        writer.println("NAME : "+name);
        writer.println("TYPE : TOUR");
        writer.println("DIMENSION : "+c.size());
        writer.println("SOLUTION : "+distanciaTotal);
        writer.println("TOUR_SECTION");
        for (int i = 0; i <n ; i++) 
            if(distance[i]!=Double.POSITIVE_INFINITY && distance[i]!=0.0)
                writer.println(distance[i]+" - "+path[i]);
        
        writer.println(-1);
        writer.print("EOF");
        writer.close();
    }
}
