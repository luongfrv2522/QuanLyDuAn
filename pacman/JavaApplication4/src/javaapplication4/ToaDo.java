/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;
public class ToaDo {
    int x;
    int y;
    int z;
    public ToaDo(){
        this.x=0;
        this.y=0;
        this.z=0;
    }
    public void set(int a,int b)
    {
        this.x=a;
        this.y=b;
    }
    public void hienthi()
    {
        System.out.print("("+this.x+","+this.y+")"+"<-");
    }
}
