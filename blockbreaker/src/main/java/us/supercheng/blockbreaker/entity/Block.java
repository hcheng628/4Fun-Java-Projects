package us.supercheng.blockbreaker.entity;

import java.awt.*;

/**
 * Created by hongyu on 6/24/17.
 */
public class Block extends Rectangle{
    private Image image;
    private int speedX = 1;
    private int speedY = -1;
    private boolean alive;
    private boolean hasCoolStuff;

    public Block(int inHeight, int inWidth, int inX, int inY, Image inImage){
        this.height = inHeight;
        this.width = inWidth;
        this.x = inX;
        this.y = inY;
        this.image = inImage;
        this.alive = true;
        this.hasCoolStuff =  1 + (int)(Math.random() * 5) == 3 ? true : false ;
        //this.hasCoolStuff = true;
        //System.out.println(this.hasCoolStuff);
    }

    public void drawBlock(Graphics graphics, Component component){
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, component);
    }

    public void changeXDirection(){
        this.speedX *= -1;
    }

    public void changeYDirection(){
        this.speedY *= -1;
    }

    public int getSpeedX(){
        return this.speedX;
    }

    public int getSpeedY(){
        return this.speedY;
    }

    public boolean getIsAlive(){
        return this.alive;
    }

    public void destoryBlock(){
        this.alive = false;
    }

    public boolean isHasCoolStuff(){
        return this.hasCoolStuff;
    }

    public void setCoolStuffSpeed(){
        this.speedY = 1;
        this.speedX = 0;
    }
}