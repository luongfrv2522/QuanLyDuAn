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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
public class Board extends JPanel implements ActionListener {
    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);
    private int b=0,h=0;
    private Image ii;
    private final Color dotColor = new Color(192, 192, 0);
    private final Color dotColor1 = new Color(255, 0, 0);
    private Color mazeColor;
    private boolean inGame = false;
    TimDuong t=new TimDuong();
    private final int BLOCK_SIZE = 24;
    private int N_BLOCKS=t.n;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int PAC_ANIM_DELAY = 2;
    private final int PACMAN_ANIM_COUNT = 4;
    private final int PACMAN_SPEED = 6;
    private int pacAnimCount = PAC_ANIM_DELAY;
    private int pacAnimDir = 1;
    private int pacmanAnimPos = 0;
    private int k=30;
    private int pacsLeft, score;
    public int[] dx, dy;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    public TimDuong a1=new TimDuong();
    public int b1[][]= new int[100][100];
    public int pacman_x, pacman_y, pacmand_x, pacmand_y;
    public int req_dx, req_dy, view_dx, view_dy;
    public int levelData[] =new int[300];
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    private int currentSpeed = 3;
    private int[] screenData;
    private Timer timer;
    public Board() {
        nhap1();
        loadImages();
        initVariables();
        initBoard();
    }
    public void check(int i,int j)
    {
        if(i-1<0)
            this.b1[i][j]+=2;
        if(j-1<0)
            this.b1[i][j]+=1;
        if(i+1>=this.N_BLOCKS)
            this.b1[i][j]+=8;
        if(j+1>=this.N_BLOCKS)
            this.b1[i][j]+=4;
    }
    public void Docfile()
    {
            try {
            LinkedList<Character> l=new LinkedList();
            LinkedList<ToaDo> tt=new LinkedList();
            File f = new File("E:/pi.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line=br.readLine();
            this.N_BLOCKS=Integer.parseInt(line);
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
            for(int i=0;i<this.N_BLOCKS;i++)
            {
                for(int j=0;j<this.N_BLOCKS;j++)
                   switch(l.peek()){
                       case 'o':
                           this.b1[i][j]=16;
                           check(i,j);
                           l.pop();
                           break;
                       case 'B':
                           this.b1[i][j]=32;
                           check(i,j);
                           l.pop();
                           break;
                        case 'P':
                           this.b1[i][j]=64;
                           pacman_x=j*BLOCK_SIZE;
                           pacman_y=i*BLOCK_SIZE;
                           check(i,j);
                           l.pop();
                           break;
                        case '|':
                           this.b1[i][j]=0;
                           ToaDo t12=new ToaDo();
                           t12.set(i, j);
                           t12.z=-1;
                           tt.add(t12);
                           //check(i,j);
                           l.pop();
                           break;
                        default:
                            this.b1[i][j]=0;
                            ToaDo t11=new ToaDo();
                            t11.set(i,j);
                            t11.z=-2;
                            tt.add(t11);
                            //check(i,j);
                            l.pop();
                            break;
                   }
            }
            while(!tt.isEmpty())
            {
                ToaDo a12=new ToaDo();
                a12=tt.pop();
                if(a12.z==-1)
                {
                   if(a12.y-1>=0)
                   {
                        this.b1[a12.x][a12.y-1]+=4;
                        this.b1[a12.x][a12.y]+=1;
                    }
                    if(a12.y+1<this.N_BLOCKS)
                    {
                        this.b1[a12.x][a12.y+1]+=1;
                        this.b1[a12.x][a12.y]+=4;
                    }
                    if(a12.x-1<0)
                        this.b1[a12.x][a12.y]+=2;
                    if(a12.x+1>=this.N_BLOCKS)
                        this.b1[a12.x][a12.x]+=8;
                }
                if(a12.z==-2)
                {
                    if(a12.x-1>=0)
                    {
                        this.b1[a12.x-1][a12.y]+=8;
                        this.b1[a12.x][a12.y]+=2;
                    }
                    if(a12.x+1<this.N_BLOCKS)
                    {
                        this.b1[a12.x+1][a12.y]+=2;
                        this.b1[a12.x][a12.y]+=8;
                    }
                   if(a12.y-1<0)
                        this.b1[a12.x][a12.y]+=1;
                    if(a12.y+1>=this.N_BLOCKS)
                        this.b1[a12.x][a12.y]+=4;
                }
            }
            fr.close();
            br.close();
            } catch (Exception ex) {
                 System.out.println("Loi doc file: "+ex);
         }
   }
    public void nhap1()
    {
        Docfile();
        k=0;
        for(int i=0;i<this.N_BLOCKS;i++)
            for(int j=0;j<this.N_BLOCKS;j++)
            {
                levelData[k]=b1[i][j];
                k++;
            }
    } 
    private void initBoard() {
        
        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);        
    }

    private void initVariables() {
        screenData = new int[N_BLOCKS * N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(400, 400);
        dx = new int[4];
        dy = new int[4];
        
        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        initGame();
    }

    private void doAnim() {

        pacAnimCount--;
        if (pacAnimCount <= 0) {
            pacAnimCount = PAC_ANIM_DELAY;
            pacmanAnimPos = pacmanAnimPos + pacAnimDir;

            if (pacmanAnimPos == (PACMAN_ANIM_COUNT - 1) || pacmanAnimPos == 0) {
                pacAnimDir = -pacAnimDir;
            }
        }
    }

    private void playGame(Graphics2D g2d) {
        //timer.setInitialDelay(100);
        //timer.start();
        movePacman();
        drawPacman(g2d);
        checkMaze();
        h++;
    }

    private void showIntroScreen(Graphics2D g2d) {
        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }

    private void drawScore(Graphics2D g) {
        String s,b1,h1;
        g.setFont(smallFont);
        g.setColor(new Color(96, 128, 255));
        b1 = "Số Bước : " + b;
        g.drawString(b1, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);
        s = "Điểm : " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 36);
    }

    private void checkMaze() {
        short i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i] & 48) != 0) {
                finished = false;
            }
            i++;
        }

        if (finished) {
            inGame=false;
        }
    }
    private void movePacman() {
        int pos;
        int ch;
        ToaDo t1=new ToaDo();
        t1=t.l2.get(h);
        if (req_dx == -pacmand_x && req_dy == -pacmand_y) {
            pacmand_x = req_dx;
            pacmand_y = req_dy;
            view_dx = pacmand_x;
            view_dy = pacmand_y;
        }
        if(pacman_x==t1.y*BLOCK_SIZE)
        {
            req_dx=0;
            if(pacman_y-BLOCK_SIZE==t1.x*BLOCK_SIZE)
                req_dy=-1;
            else
                req_dy=1;
        }
        if(pacman_y==t1.x*BLOCK_SIZE)
        {
            req_dy=0;
            if(pacman_x-BLOCK_SIZE==t1.y*BLOCK_SIZE)
                req_dx=-1;
            else
                req_dx=1;
        }
        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];
            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    if(k>0)
                        k--;
                    b++;
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                    view_dx = pacmand_x;
                    view_dy = pacmand_y;
                    pacman_x+=pacmand_x*BLOCK_SIZE;
                    pacman_y+=pacmand_y*BLOCK_SIZE;
                    pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
                    ch = screenData[pos];
                    if ((ch & 16) != 0) {
                        screenData[pos] = (short) (ch & 15);
                        score+=4;
                    }
                    if((ch&32)!=0)
                    {
                        screenData[pos] = (short) (ch & 15);
                        score+=k;
                    }
                }
            }
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
    }
    private void drawPacman(Graphics2D g2d) {
        if (view_dx == -1) {
            drawPacnanLeft(g2d);
        } else if (view_dx == 1) {
            drawPacmanRight(g2d);
        } else if (view_dy == -1) {
            drawPacmanUp(g2d);
        } else {
            drawPacmanDown(g2d);
        }
    }
    private void drawPacmanUp(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2up, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3up, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4up, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2down, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3down, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4down, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1,pacman_x+1, pacman_y+1,this);
                break;
        }
    }

    private void drawPacnanLeft(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2left, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3left, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4left, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2right, pacman_x + 1, pacman_y+ 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3right, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4right, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }
                if ((screenData[i] & 32) != 0) { 
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 5, 5);
                }
                i++;
            }
        }
    }

    private void initGame() {
        score = 0;
        initLevel();
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
      }
    private void loadImages() {
        pacman1 = new ImageIcon("E:/images/pacman.gif").getImage();
        pacman2up = new ImageIcon("E:/images/up1.gif").getImage();
        pacman3up = new ImageIcon("E:/images/up2.gif").getImage();
        pacman4up = new ImageIcon("E:/images/up3.gif").getImage();
        pacman2down = new ImageIcon("E:/images/down1.gif").getImage();
        pacman3down = new ImageIcon("E:/images/down2.gif").getImage();
        pacman4down = new ImageIcon("E:/images/down3.gif").getImage();
        pacman2left = new ImageIcon("E:/images/left1.gif").getImage();
        pacman3left = new ImageIcon("E:/images/left2.gif").getImage();
        pacman4left = new ImageIcon("E:/images/left3.gif").getImage();
        pacman2right = new ImageIcon("E:/images/right1.gif").getImage();
        pacman3right = new ImageIcon("E:/images/right2.gif").getImage();
        pacman4right = new ImageIcon("E:/images/right3.gif").getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height); 
        drawMaze(g2d); 
        drawPacman(g2d);
        drawScore(g2d);
        doAnim();
        if (inGame) {
            playGame(g2d);
        } else {
            timer.stop();
            showIntroScreen(g2d);
        }
        
        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                    movePacman();
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                    movePacman();
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                    movePacman();
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                    movePacman();
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                    pacman_x=0;
                    pacman_y=0;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    timer.start();
                    inGame = true;
                    b=0;
                    k=30;
                    h=0;
                    nhap1();
                    initGame();
                }
                if(key=='n'||key=='N')
                    timer.start();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                req_dx = 0;
                req_dy = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}
