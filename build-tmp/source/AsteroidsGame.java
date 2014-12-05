import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip bob = new SpaceShip();
ArrayList <Asteroids> joe = new ArrayList <Asteroids>();
ArrayList <Bullet> joey = new ArrayList <Bullet>();
Stars bg = new Stars();
boolean aIsPressed = false;
boolean dIsPressed = false;
boolean fourIsPressed = false;
boolean sixIsPressed = false;
int numAsteroids = 20;
boolean victory = false;
int endX;
public void setup() 
{
  size(600,600);
  rectMode(CENTER);
  for(int i=0;i<numAsteroids;i++)
  {
    joe.add(new Asteroids(i));
  }
}
public void draw() 
{
  if(victory==false)
  {
  if(joe.size()==0)
  {
    victory=true;
  }
  bg.drawStars();
  bob.show();
  bob.move();
  for(int i=0;i<joe.size();i++)
  {
    joe.get(i).show();
    joe.get(i).move();
    joe.get(i).destroy();
  }
  for(int i=0;i<joey.size();i++)
  {
    boolean deleting = false;
    joey.get(i).show();
    joey.get(i).move();
    if(joey.get(i).getX() > 600)
    {
      deleting = true;
    }
    if(joey.get(i).getY() > 600)
    {
      deleting = true;
    }
    if(joey.get(i).getX() < 0)
    {
      deleting = true;
    }
    if(joey.get(i).getY() < 0)
    {
      deleting = true;
    }
    if(deleting == true)
    {
      joey.remove(i);
      i--;
    }
  }
  if(aIsPressed) {bob.accelerate(0.2f);}
  if(dIsPressed) {bob.accelerate(-0.2f);}
  if(fourIsPressed) {bob.rotate(-5);}
  if(sixIsPressed) {bob.rotate(5);}
}
else 
{
  background(0);
  textAlign(CENTER);
  bg.drawStars();
  text("you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck you suck",300,800-endX,75,200);
  endX++;
}
}
public void keyReleased()
{
  if(key=='a')
  {
    aIsPressed = false;
  }
  if(key==CODED)
  {
  if(keyCode == LEFT)
  {
    fourIsPressed = false;
  }
  if(keyCode == RIGHT)
  {
    sixIsPressed = false;
  }
  }
}
public void keyPressed()
{
  if(key==' ')
  {
    joey.add(new Bullet(bob));
  }
  if(key=='a')
  {
    aIsPressed = true;
  }
  if(key==CODED)
  {
  if(keyCode == LEFT)
  {
    fourIsPressed = true;
  }
  if(keyCode == RIGHT)
  {
    sixIsPressed = true;
  }
  }
  if(key=='0')
  {
    bob.setX((int)(Math.random()*600));
    bob.setY((int)(Math.random()*600));
    bob.setDirectionX(0);
    bob.setDirectionY(0);
    bob.setPointDirection((int)(Math.random()*360));
  }
}
class Bullet extends Floater
{
  double dRadians;
  Bullet(SpaceShip theShip)
  {     
    myCenterX = theShip.myCenterX;
    myCenterY = theShip.myCenterY;
    myPointDirection = theShip.myPointDirection;
    dRadians =myPointDirection*(Math.PI/180);
    myDirectionX = (5 * Math.cos(dRadians) + theShip.myDirectionX);
    myDirectionY = (5 * Math.sin(dRadians) + theShip.myDirectionY);
  }
  public void move()
  {
    myCenterX = myCenterX + myDirectionX;
    myCenterY = myCenterY + myDirectionY;
  }
  public void show()
  {
    fill(255,0,0);
    ellipse((float)myCenterX, (float)myCenterY, 4, 4);
  }
  public void setX(int x){myCenterX=x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY=y;}   
  public int getY(){return (int)myCenterY;}    
  public void setDirectionX(double x){myDirectionX=x;}   
  public double getDirectionX(){return myDirectionX;}   
  public void setDirectionY(double y){myDirectionY=y;}  
  public double getDirectionY(){return myDirectionY;}   
  public void setPointDirection(int degrees){myPointDirection=degrees;}   
  public double getPointDirection(){return myPointDirection;}
}
class SpaceShip extends Floater  
{   
    SpaceShip()
    {
      corners = 8;
      xCorners = new int[corners];
      yCorners = new int[corners];
      xCorners[0] = -8;
      yCorners[0] = 0;
      xCorners[1] = -12;
      yCorners[1] = 16;
      xCorners[2] = -4;
      yCorners[2] = 12;
      xCorners[3] = 12;
      yCorners[3] = 8;
      xCorners[4] = 20;
      yCorners[4] = 0;
      xCorners[5] = 12;
      yCorners[5] = -8;
      xCorners[6] = -4;
      yCorners[6] = -12;
      xCorners[7] = -12;
      yCorners[7] = -16;                        
      r = 255;
      g = 255;
      b = 255;
      myCenterX = 300;
      myCenterY = 300;
      myDirectionX = 0;
      myDirectionY = 0;
      myPointDirection = 0;
    }
  public void setX(int x){myCenterX=x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY=y;}   
  public int getY(){return (int)myCenterY;}    
  public void setDirectionX(double x){myDirectionX=x;}   
  public double getDirectionX(){return myDirectionX;}   
  public void setDirectionY(double y){myDirectionY=y;}  
  public double getDirectionY(){return myDirectionY;}   
  public void setPointDirection(int degrees){myPointDirection=degrees;}   
  public double getPointDirection(){return myPointDirection;}
}

class Asteroids extends Floater
{
  int rotSpeed,myNum;
  public Asteroids(int myTemp)
  {
    rotSpeed = (int)((Math.random()*20)-10);
    corners = 5;
    xCorners = new int[corners];
    yCorners = new int[corners];
    int[] yS = {-6,-3,9,6,-6};
    int[] xS = {6,12,6,-3,-3};
    xCorners = xS;
    yCorners = yS;
    r = 95;
    g = 82;
    b = 82;
    myCenterX = (int)(Math.random()*600);
    myCenterY = (int)(Math.random()*600);
    myDirectionX = ((Math.random()*4)-2);
    myDirectionY = ((Math.random()*4)-2);
    myNum = myTemp;
  }
  public void destroy()
  {
    for(int f=0;f<joey.size();f++)
    {
      if(dist((int)myCenterX,(int)myCenterY,joey.get(f).getX(),joey.get(f).getY())<10)
      {
        joe.remove(myNum);
        for(int i=0;i<joe.size();i++)
        {
          joe.get(i).minusOne(myNum);
        }
      }
    }
  if(dist((int)myCenterX,(int)myCenterY,bob.getX(),bob.getY())<30)
    {
      joe.remove(myNum);
      for(int i=0;i<joe.size();i++)
      {
        joe.get(i).minusOne(myNum);
      }
    }
  }
  public void minusOne(int destroyNum)
  {
    if(myNum>destroyNum)
    {
      myNum = myNum - 1;
    }
  }
  public void move()
  {
    super.move();
    rotate(rotSpeed);
  }
  public void setX(int x){myCenterX=x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY=y;}   
  public int getY(){return (int)myCenterY;}    
  public void setDirectionX(double x){myDirectionX=x;}   
  public double getDirectionX(){return myDirectionX;}   
  public void setDirectionY(double y){myDirectionY=y;}  
  public double getDirectionY(){return myDirectionY;}   
  public void setPointDirection(int degrees){myPointDirection=degrees;}   
  public double getPointDirection(){return myPointDirection;}

}

abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int r,g,b;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);
  abstract public int getX();
  abstract public void setY(int y);
  abstract public int getY();    
  abstract public void setDirectionX(double x); 
  abstract public double getDirectionX();  
  abstract public void setDirectionY(double y);  
  abstract public double getDirectionY();  
  abstract public void setPointDirection(int degrees);
  abstract public double getPointDirection();

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(r,g,b);   
    //stroke(r,g,b);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

class Stars
{
  int numStars;
  int[] x;
  int[] y;
  Stars()
  {
    numStars = (int)((Math.random()*200)+100);
    x = new int[numStars];
    y = new int[numStars];
    for(int i=0;i<numStars;i++)
    {
      x[i] = (int)(Math.random()*600);
      y[i] = (int)(Math.random()*600);
    }
  }
  public void drawStars()
  {
    fill(0);
    rect(300,300,600,600);
    fill(255);
    for(int i=0;i<numStars;i++)
    {
      rect(x[i],y[i],2,2);
    }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
