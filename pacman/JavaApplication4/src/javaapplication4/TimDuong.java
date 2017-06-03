/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

/**
 *
 * @author small
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class TimDuong extends ToaDo {
    int n;
    public int[][] a=new int[100][100];
    ToaDo[][] truoc=new ToaDo[100][100];
    ToaDo P=new ToaDo();
    LinkedList<ToaDo> l2=new LinkedList();
    LinkedList<ToaDo> l1=new LinkedList();
    int[][] xet=new int[100][100];
    int[][] fn=new int[100][100];
    int[][] gn=new int[100][100];
    int[][] hn=new int[100][100];
    public void Docfile1()
    {
            try {
            LinkedList<Character> l=new LinkedList();
            File f = new File("E:/pi.txt");
            FileReader fr = new FileReader(f);
            String line;
            BufferedReader br = new BufferedReader(fr);
            line=br.readLine();
            this.n=Integer.parseInt(line);
            String c="";
            while((line=br.readLine())!=null)
            {
                c+=line;
                c+=" ";
            }
            c=c.trim();
            for(int i=0;i<c.length();i++)
            {
                if(c.charAt(i)!=' ')
                    l.add(c.charAt(i));
            }
            for(int i=0;i<this.n;i++)
                for(int j=0;j<this.n;j++)
                   switch(l.peek()){
                       case 'o':
                           this.a[i][j]=1;
                           l.pop();
                           break;
                       case 'B':
                           ToaDo a=new ToaDo();
                           a.set(i, j);
                           l1.add(a);
                           this.a[i][j]=2;                           
                           l.pop();
                           break;
                        case 'P':
                           this.a[i][j]=3;
                           P.set(i, j);
                           l.pop();
                           break;
                        case '|':
                           this.a[i][j]=-1;
                           l.pop();
                           break;
                        default:
                            this.a[i][j]=-2;
                            l.pop();
                            break;
                   }     
            fr.close();
            br.close();
            } catch (Exception ex) {
                 System.out.println("Loi doc file: "+ex);
         }
   }
    public void khoitao()
    {
        for(int i=0;i<this.n;i++)
            for(int j=0;j<this.n;j++)
            {
                this.truoc[i][j]=new ToaDo();
                this.truoc[i][j].set(-1,-1);
                this.xet[i][j]=0;
                this.fn[i][j]=0;
                this.gn[i][j]=0;
                this.hn[i][j]=0;
            }
    }
    public TimDuong()
    {
        nhap();
    }
    public void nhap()
    {
        Docfile1();
        while(!l1.isEmpty())
        {
            A(this.P.x,this.P.y);
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(a[i][j]==1)
                {
                    A1(this.P.x,this.P.y,i,j);
                }
            }
        }
    }
    public void A(int u,int v)
    {
        khoitao();
        LinkedList<ToaDo> l=new LinkedList();
        ToaDo d=new ToaDo();
        ToaDo s1=new ToaDo();
        s1.set(u, v);
        ToaDo t=new ToaDo();
        d.set(u,v);
        l.add(d);
        while(!l.isEmpty())
        {
            d=l.pop();
            this.xet[d.x][d.y]=1;
            if(this.a[d.x][d.y]==2)
            {
                int vt=0;
                LinkedList<ToaDo> l4=new LinkedList();
                this.a[P.x][P.y]=0;
                this.P.x=d.x;
                this.P.y=d.y;   
                this.a[P.x][P.y]=3;
                for(int i=0;i<l1.size();i++)
                {
                    if(d.x==l1.get(i).x&&d.y==l1.get(i).y)
                    {
                        vt=i;
                    }
                }
                ToaDo to=new ToaDo();
                to.set(l1.get(0).x,l1.get(0).y);
                l1.set(0, l1.get(vt));
                l1.set(vt,to);
                l1.pop();
                ToaDo j=new ToaDo();
                j.set(d.x, d.y);
                l4.push(j);
                j=this.truoc[d.x][d.y];
                l4.push(j);
                while(j.x!=u||j.y!=v)
                { 
                    j=this.truoc[j.x][j.y];
                    l4.push(j);
                }
                l4.pop();
                while(!l4.isEmpty())
                {
                    if(a[l4.peek().x][l4.peek().y]==1)
                    {
                        a[l4.peek().x][l4.peek().y]=0;
                    }
                    l2.add(l4.pop());
                }
                break;
            }
            else
            {
                if(this.a[d.x][d.y]==-1)
                {
                    if(d.x-1>=0)
                    {
                        if(a[d.x-1][d.y]!=-2&&this.xet[d.x-1][d.y]==0)
                        {
                            this.truoc[d.x-1][d.y]=d;
                            ToaDo d1=new ToaDo();
                           // this.hn[d.x-1][d.y]=Math.abs(s-((d.x)-1))+Math.abs(g-d.y);
                            this.hn[d.x-1][d.y]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x-1][d.y]>Math.abs(l1.get(i).x-(d.x-1))+Math.abs(l1.get(i).y-(d.y)))
                                {
                                    this.hn[d.x-1][d.y]=Math.abs(l1.get(i).x-(d.x-1))+Math.abs(l1.get(i).y-(d.y));
                                }
                            }
                            d1.set(d.x-1,d.y);
                            l.add(d1);
                            this.gn[d.x-1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x-1][d.y]=this.hn[d.x-1][d.y]+this.gn[d.x-1][d.y];
                        }
                    }
                    if(d.x+1<this.n)
                    {
                        if(a[d.x+1][d.y]!=-2&&this.xet[d.x+1][d.y]==0)
                        {
                            this.truoc[d.x+1][d.y]=d;
                            ToaDo d1=new ToaDo();
                           // this.hn[d.x+1][d.y]=Math.abs(s-(d.x+1))+Math.abs(g-d.y);
                            this.hn[d.x+1][d.y]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x+1][d.y]>Math.abs(l1.get(i).x-(d.x+1))+Math.abs(l1.get(i).y-(d.y-1)))
                                {
                                    this.hn[d.x+1][d.y]=Math.abs(l1.get(i).x-(d.x+1))+Math.abs(l1.get(i).y-(d.y-1));
                                }
                            }
                            d1.set(d.x+1,d.y);
                            l.add(d1);
                            this.gn[d.x+1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x+1][d.y]=this.hn[d.x+1][d.y]+this.gn[d.x+1][d.y];
                        }
                    }
                    min1(l,fn);
                }
                else if(this.a[d.x][d.y]==-2)
                {
                    if(d.y-1>=0)
                    {
                        if(a[d.x][d.y-1]!=-1&&this.xet[d.x][d.y-1]==0)
                        {
                            this.truoc[d.x][d.y-1]=d;
                            ToaDo d1=new ToaDo();                   
                            this.hn[d.x][d.y-1]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x][d.y-1]>Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y-1)))
                                {
                                    this.hn[d.x][d.y-1]=Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y-1));
                                }
                            }
                            d1.set(d.x,d.y-1);
                            l.add(d1);
                            this.gn[d.x][d.y-1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y-1]=this.hn[d.x][d.y-1]+this.gn[d.x][d.y-1];
                        }
                    }
                    if(d.y+1<this.n)
                    {
                        if(a[d.x][d.y+1]!=-1&&this.xet[d.x][d.y+1]==0)
                        {
                            this.truoc[d.x][d.y+1]=d;
                            ToaDo d1=new ToaDo();
                           // this.hn[d.x][d.y+1]=Math.abs(s-d.x)+Math.abs(g-(d.y+1));
                            this.hn[d.x][d.y+1]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x][d.y+1]>Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y+1)))
                                {
                                    this.hn[d.x][d.y+1]=Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y+1));
                                }
                            }
                            d1.set(d.x,d.y+1);
                            l.add(d1);
                            this.gn[d.x][d.y+1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y+1]=this.hn[d.x][d.y+1]+this.gn[d.x][d.y+1];
                        }
                    }
                    min1(l,fn);
                }
                else
                {
                    if(d.y-1>=0)
                    {
                        if(a[d.x][d.y-1]!=-1&&this.xet[d.x][d.y-1]==0)
                        {
                            this.truoc[d.x][d.y-1]=d;
                            ToaDo d1=new ToaDo();                   
                            this.hn[d.x][d.y-1]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x][d.y-1]>Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y-1)))
                                {
                                    this.hn[d.x][d.y-1]=Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y-1));
                                }
                            }
                            d1.set(d.x,d.y-1);
                            l.add(d1);
                            this.gn[d.x][d.y-1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y-1]=this.hn[d.x][d.y-1]+this.gn[d.x][d.y-1];
                        }
                    }
                    if(d.y+1<this.n)
                    {
                        if(a[d.x][d.y+1]!=-1&&this.xet[d.x][d.y+1]==0)
                        {
                            this.truoc[d.x][d.y+1]=d;
                            ToaDo d1=new ToaDo();
                           // this.hn[d.x][d.y+1]=Math.abs(s-d.x)+Math.abs(g-(d.y+1));
                            this.hn[d.x][d.y+1]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x][d.y+1]>Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y+1)))
                                {
                                    this.hn[d.x][d.y+1]=Math.abs(l1.get(i).x-d.x)+Math.abs(l1.get(i).y-(d.y+1));
                                }
                            }
                            d1.set(d.x,d.y+1);
                            l.add(d1);
                            this.gn[d.x][d.y+1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y+1]=this.hn[d.x][d.y+1]+this.gn[d.x][d.y+1];
                        }
                    }
                    if(d.x-1>=0)
                    {
                        if(a[d.x-1][d.y]!=-2&&this.xet[d.x-1][d.y]==0)
                        {
                            this.truoc[d.x-1][d.y]=d;
                            ToaDo d1=new ToaDo();
                           // this.hn[d.x-1][d.y]=Math.abs(s-((d.x)-1))+Math.abs(g-d.y);
                            this.hn[d.x-1][d.y]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x-1][d.y]>Math.abs(l1.get(i).x-(d.x-1))+Math.abs(l1.get(i).y-(d.y)))
                                {
                                    this.hn[d.x-1][d.y]=Math.abs(l1.get(i).x-(d.x-1))+Math.abs(l1.get(i).y-(d.y));
                                }
                            }
                            d1.set(d.x-1,d.y);
                            l.add(d1);
                            this.gn[d.x-1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x-1][d.y]=this.hn[d.x-1][d.y]+this.gn[d.x-1][d.y];
                        }
                    }
                    if(d.x+1<this.n)
                    {
                        if(a[d.x+1][d.y]!=-2&&this.xet[d.x+1][d.y]==0)
                        {
                            this.truoc[d.x+1][d.y]=d;
                            ToaDo d1=new ToaDo();
                           // this.hn[d.x+1][d.y]=Math.abs(s-(d.x+1))+Math.abs(g-d.y);
                            this.hn[d.x+1][d.y]=100;
                            for(int i=0;i<l1.size();i++)
                            {
                                if(this.hn[d.x+1][d.y]>Math.abs(l1.get(i).x-(d.x+1))+Math.abs(l1.get(i).y-(d.y-1)))
                                {
                                    this.hn[d.x+1][d.y]=Math.abs(l1.get(i).x-(d.x+1))+Math.abs(l1.get(i).y-(d.y-1));
                                }
                            }
                            d1.set(d.x+1,d.y);
                            l.add(d1);
                            this.gn[d.x+1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x+1][d.y]=this.hn[d.x+1][d.y]+this.gn[d.x+1][d.y];
                        }
                    }
                    min1(l,fn);
                }
            }
         }
    }
    public void A1(int u,int v,int s,int g)
    {
        khoitao();
        LinkedList<ToaDo> l=new LinkedList();
        ToaDo d=new ToaDo();
        ToaDo s1=new ToaDo();
        s1.set(u, v);
        ToaDo t=new ToaDo();
        d.set(u,v);
        l.add(d);
        while(!l.isEmpty())
        {
            d=l.pop();
            this.xet[d.x][d.y]=1;
            if(truoc[s][g].x!=-1)
            {
                    int vt=0;
                    LinkedList<ToaDo> l4=new LinkedList();
                    this.a[P.x][P.y]=0;
                    this.P.x=s;
                    this.P.y=g;
                    this.a[P.x][P.y]=3;
                    ToaDo j=new ToaDo();
                    j.set(d.x, d.y);
                    l4.push(j);
                    j=this.truoc[d.x][d.y];
                    l4.push(j);
                    while(j.x!=u||j.y!=v)
                    { 
                        j=this.truoc[j.x][j.y];
                        l4.push(j);
                    }
                    l4.pop();
                    while(!l4.isEmpty())
                    {
                        if(a[l4.peek().x][l4.peek().y]==1)
                        {
                            a[l4.peek().x][l4.peek().y]=0;
                        }
                        l2.add(l4.pop());
                    }
                    break;
            }
            else
            {
                if(this.a[d.x][d.y]==-1)
                {
                    if(d.x-1>=0)
                    {
                        if(a[d.x-1][d.y]!=-2&&this.xet[d.x-1][d.y]==0)
                        {
                            this.truoc[d.x-1][d.y]=d;
                            ToaDo d1=new ToaDo();
                            this.hn[d.x-1][d.y]=Math.abs(s-((d.x)-1))+Math.abs(g-d.y);
                            d1.set(d.x-1,d.y);
                            l.add(d1);
                            this.gn[d.x-1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x-1][d.y]=this.hn[d.x-1][d.y]+this.gn[d.x-1][d.y];
                        }
                    }
                    if(d.x+1<this.n)
                    {
                        if(a[d.x+1][d.y]!=-2&&this.xet[d.x+1][d.y]==0)
                        {
                            this.truoc[d.x+1][d.y]=d;
                            ToaDo d1=new ToaDo();
                            this.hn[d.x+1][d.y]=Math.abs(s-(d.x+1))+Math.abs(g-d.y);
                            d1.set(d.x+1,d.y);
                            l.add(d1);
                            this.gn[d.x+1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x+1][d.y]=this.hn[d.x+1][d.y]+this.gn[d.x+1][d.y];
                        }
                    }
                    min1(l,fn);
                }
                else if(this.a[d.x][d.y]==-2)
                {
                    if(d.y-1>=0)
                    {
                        if(a[d.x][d.y-1]!=-1&&this.xet[d.x][d.y-1]==0)
                        {
                            this.truoc[d.x][d.y-1]=d;
                            ToaDo d1=new ToaDo();                   
                            this.hn[d.x][d.y-1]=Math.abs(s-d.x)+Math.abs(g-(d.y-1));
                            d1.set(d.x,d.y-1);
                            l.add(d1);
                            this.gn[d.x][d.y-1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y-1]=this.hn[d.x][d.y-1]+this.gn[d.x][d.y-1];
                        }
                    }
                    if(d.y+1<this.n)
                    {
                        if(a[d.x][d.y+1]!=-1&&this.xet[d.x][d.y+1]==0)
                        {
                            this.truoc[d.x][d.y+1]=d;
                            ToaDo d1=new ToaDo();
                            this.hn[d.x][d.y+1]=Math.abs(s-d.x)+Math.abs(g-(d.y+1));
                            d1.set(d.x,d.y+1);
                            l.add(d1);
                            this.gn[d.x][d.y+1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y+1]=this.hn[d.x][d.y+1]+this.gn[d.x][d.y+1];
                        }
                    }
                    min1(l,fn);
                }
                else
                {
                    if(d.y-1>=0)
                    {
                        if(a[d.x][d.y-1]!=-1&&this.xet[d.x][d.y-1]==0)
                        {
                            this.truoc[d.x][d.y-1]=d;
                            ToaDo d1=new ToaDo();                   
                            this.hn[d.x][d.y-1]=Math.abs(s-d.x)+Math.abs(g-(d.y-1));
                            d1.set(d.x,d.y-1);
                            l.add(d1);
                            this.gn[d.x][d.y-1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y-1]=this.hn[d.x][d.y-1]+this.gn[d.x][d.y-1];
                        }
                    }
                    if(d.y+1<this.n)
                    {
                        if(a[d.x][d.y+1]!=-1&&this.xet[d.x][d.y+1]==0)
                        {
                            this.truoc[d.x][d.y+1]=d;
                            ToaDo d1=new ToaDo();
                            this.hn[d.x][d.y+1]=Math.abs(s-d.x)+Math.abs(g-(d.y+1));
                            d1.set(d.x,d.y+1);
                            l.add(d1);
                            this.gn[d.x][d.y+1]=this.gn[d.x][d.y]+1;
                            this.fn[d.x][d.y+1]=this.hn[d.x][d.y+1]+this.gn[d.x][d.y+1];
                        }
                    }
                    if(d.x-1>=0)
                    {
                        if(a[d.x-1][d.y]!=-2&&this.xet[d.x-1][d.y]==0)
                        {
                            this.truoc[d.x-1][d.y]=d;
                            ToaDo d1=new ToaDo();
                            this.hn[d.x-1][d.y]=Math.abs(s-((d.x)-1))+Math.abs(g-d.y);
                            d1.set(d.x-1,d.y);
                            l.add(d1);
                            this.gn[d.x-1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x-1][d.y]=this.hn[d.x-1][d.y]+this.gn[d.x-1][d.y];
                        }
                    }
                    if(d.x+1<this.n)
                    {
                        if(a[d.x+1][d.y]!=-2&&this.xet[d.x+1][d.y]==0)
                        {
                            this.truoc[d.x+1][d.y]=d;
                            ToaDo d1=new ToaDo();
                            this.hn[d.x+1][d.y]=Math.abs(s-(d.x+1))+Math.abs(g-d.y);
                            d1.set(d.x+1,d.y);
                            l.add(d1);
                            this.gn[d.x+1][d.y]=this.gn[d.x][d.y]+1;
                            this.fn[d.x+1][d.y]=this.hn[d.x+1][d.y]+this.gn[d.x+1][d.y];
                        }
                    }
                    min1(l,fn);
                }
            }
         }
    }
    public void min1(LinkedList<ToaDo> l,int[][] fn)
    {
        int min1=100;
        int t=0;
        for(int i=0;i<l.size();i++)
        {
            if(fn[l.get(i).x][l.get(i).y]<=min1)
            {
                min1=fn[l.get(i).x][l.get(i).y];
                t=i;
            }
        }
        ToaDo d1=new ToaDo();
        d1.set(l.get(0).x,l.get(0).y);
        l.set(0, l.get(t));
        l.set(t,d1);
    }
}

