/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1;

import java.util.ArrayList;

/**
 *
 * @author Eloy
 */
public class Triangulo {

    Punto p1;
    Punto p2;
    Punto p3;
    public Punto pMenor;
    public double distancia;
    public Triangulo tMenor;

    public Triangulo(Punto p1, Punto p2, Punto p3){
       this.p1=p1;
       this.p2=p2;
       this.p3=p3;
       Punto aux = new Punto();
       double d1,d2,d3;
       
       distancia=0;
       
       d1=aux.calcularDistancia(p1, p2);
       d2=aux.calcularDistancia(p1, p3);
       d3=aux.calcularDistancia(p2, p3);
       
       if(d1>=d2 && d1>=d3)
       {
           distancia=d2+d3;
           
           if(d2>d3)
               pMenor=p3;
           else 
               pMenor=p1;
       }
    
       else if(d2>=d1 && d2>=d3)
       {   
           distancia=d1+d3;
           
           if(d1>d3)
               pMenor=p2;
           else 
               pMenor=p1;
       }

       else
       {
           distancia=d1+d2; 
           
           if(d1>d2)
               pMenor=p3;
           else
               pMenor=p2;
       }
    }

    public Triangulo() {}
    
    public double getDistancia(){return distancia;}
    
    public void setDistancia(double d){distancia=d;}
    
    public Triangulo busquedaExhaustiva(ArrayList<Punto> P){
        
        Triangulo minimo = null,aux;
        minimo = new Triangulo(P.get(0),P.get(1),P.get(2));
        
        for(int i=0;i<P.size();i++)
            for(int j=i+1;j<P.size();j++)
                for(int k=j+1;k<P.size();k++)
                {
                    aux = new Triangulo(P.get(i),P.get(j),P.get(k));
                    if(aux.getDistancia()<minimo.getDistancia())
                    {
                        minimo=aux;
                        minimo.setDistancia(aux.getDistancia());
                    }
                        
                }
        tMenor=minimo;
        return minimo;
    }
    
    public void DyV(ArrayList<Punto> P)
    {
        double dMin= DyV(P, 0, P.size()-1);    
    }
    
    private double DyV(ArrayList<Punto> P, int index, int end)
    {
        ArrayList<Punto> pu = new ArrayList();
        int mitad = (index+end)/2;
        int a1=0, a2=0, a3=0, a4=0;
        distancia=0;
        Punto punto = new Punto();
        
        if(end-index<=6)
        {
            pu.add(P.get(index));
            pu.add(P.get(index+1));
            pu.add(P.get(index+2));
            pu.add(P.get(index+3));
            pu.add(P.get(index+4));
            pu.add(P.get(end));
            tMenor =busquedaExhaustiva(pu);
            return tMenor.getDistancia();
        }
        
        else if(end-index<=5)
        {
            pu.add(P.get(index));
            pu.add(P.get(index+1));
            pu.add(P.get(index+2));
            pu.add(P.get(index+3));
            pu.add(P.get(end));
            tMenor =busquedaExhaustiva(pu);
            return tMenor.getDistancia();
        }
        
        else if(end-index<=4)
        {
            pu.add(P.get(index));
            pu.add(P.get(index+1));
            pu.add(P.get(index+2));
            pu.add(P.get(end));
            tMenor =busquedaExhaustiva(pu);
            return tMenor.getDistancia();
        }
        
        else if(end-index<=3)
        {
            tMenor =new Triangulo(P.get(index), P.get(index+1), P.get(end));
            return tMenor.getDistancia();
        }
        
        else
        {
            double izquierdo = DyV(P,index,mitad);
            double derecho = DyV(P,mitad+1,end);
            
            if(izquierdo<derecho)
                distancia=izquierdo;
            else 
                distancia=derecho;
        
        
            for(a1=mitad;a1>=index;a1--)
                if(P.get(mitad+1).getX()-P.get(a1).getX()>distancia)
                    break;
            for(a2=mitad+1;a2<=end;a2++)
                if(P.get(a2).getX()-P.get(mitad).getX()>distancia)
                    break;

            for(a3=a1+1;a3<=mitad;a3++)
                for(a4=mitad+1;a4<=a2-1;a4++)
                    if(punto.calcularDistancia(P.get(a3), P.get(a4))<distancia)
                        distancia=punto.calcularDistancia(P.get(a3), P.get(a4));

            return distancia;
        }
    }
    
    public void mostrar(Triangulo t)
    {
        System.out.println(t.p1.toString()+" "+t.p2.toString()+" "+t.p3.toString() );
    }
}
