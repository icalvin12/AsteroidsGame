SpaceShip bob = new SpaceShip();
ArrayList <Asteroids> joe = new ArrayList <Asteroids>();
Stars bg = new Stars();
boolean aIsPressed = false;
boolean dIsPressed = false;
boolean fourIsPressed = false;
boolean sixIsPressed = false;
int numAsteroids = 10;
public void setup() 
{
  size(600,600);
  rectMode(CENTER);
  for(int i=0;i<numAsteroids;i++)
  {
    joe.add(new Asteroids(i));
  }
  System.out.println(joe);
}
public void draw() 
{
  bg.drawStars();
  bob.show();
  bob.move();
  for(int i=0;i<joe.size();i++)
  {
    joe.get(i).show();
    joe.get(i).move();
    joe.get(i).destroy();
  }
  if(aIsPressed) {bob.accelerate(0.2);}
  if(dIsPressed) {bob.accelerate(-0.2);}
  if(fourIsPressed) {bob.rotate(-5);}
  if(sixIsPressed) {bob.rotate(5);}
}
public void keyReleased()
{
  if(key=='a')
  {
    aIsPressed = false;
  }
  if(key=='d')
  {
    dIsPressed = false;
  }
  if(key=='4')
  {
    fourIsPressed = false;
  }
  if(key=='6')
  {
    sixIsPressed = false;
  }
}
public void keyPressed()
{
  if(key=='a')
  {
    aIsPressed = true;
  }
  if(key=='d')
  {
    dIsPressed = true;
  }
  if(key=='4')
  {
    fourIsPressed = true;
  }
  if(key=='6')
  {
    sixIsPressed = true;
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
class SpaceShip extends Floater  
{   
    SpaceShip()
    {
      corners = 4;
      xCorners = new int[corners];
      yCorners = new int[corners];
      xCorners[0] = -8;
      yCorners[0] = -8;
      xCorners[1] = 16;
      yCorners[1] = 0;
      xCorners[2] = -8;
      yCorners[2] = 8;
      xCorners[3] = -2;
      yCorners[3] = 0 ;
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
    println(myNum);
  }
  public void destroy()
  {
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
    stroke(r,g,b);    
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
  void drawStars()
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
