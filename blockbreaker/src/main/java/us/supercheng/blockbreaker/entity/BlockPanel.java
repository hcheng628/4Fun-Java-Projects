package us.supercheng.blockbreaker.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * Created by hongyu on 6/24/17.
 */
public class BlockPanel extends JPanel implements KeyListener {
    private final int rowHeight = 25;
    private final int rowSpace = 5;

    private final int PanelTopMost = 0;
    private final int PanelBottomMost = 800;
    private final int PanelLeftMost = 0;
    private final int PanelRightMost = 600;

    private final int Paddle_Moving_Distance = 15;


    private GameThread gameThread;
    private Vector<Block> blocks;
    private Block paddle;
    private Vector<Block> balls;
    private Vector<Block> coolStuffs;


    public BlockPanel(){
        this.gameThread = new GameThread(this);
        this.blocks = new Vector<Block>();
        this.balls = new Vector<Block>();
        this.coolStuffs = new Vector<Block>();

        this.paddle = new Block(rowHeight,rowHeight*6, 265,760, new ImageIcon(this.getClass().getResource("/imgs/paddle.png")).getImage());
        this.balls.add(new Block(15,15,this.paddle.x + 75,738, new ImageIcon(this.getClass().getResource("/imgs/ball.png")).getImage()));

        for(int i=0; i<12; i++){
            this.blocks.add(new Block(rowHeight,rowHeight*2,i*rowHeight*2, rowHeight*0+rowSpace, new ImageIcon(this.getClass().getResource("/imgs/blue.png")).getImage()));
            this.blocks.add(new Block(rowHeight,rowHeight*2,i*rowHeight*2, rowHeight*1+rowSpace*2, new ImageIcon(this.getClass().getResource("/imgs/green.png")).getImage()));
            this.blocks.add(new Block(rowHeight,rowHeight*2,i*rowHeight*2, rowHeight*2+rowSpace*3, new ImageIcon(this.getClass().getResource("/imgs/red.png")).getImage()));
            this.blocks.add(new Block(rowHeight,rowHeight*2,i*rowHeight*2, rowHeight*3+rowSpace*4, new ImageIcon(this.getClass().getResource("/imgs/yellow.png")).getImage()));
        }

    }

    public void update(){
        for(Block eachBall : this.balls){
            if(eachBall.getIsAlive()){
                if((PanelRightMost - eachBall.x ) < 7 || (eachBall.x - PanelLeftMost) < 7){
                    eachBall.changeXDirection();
                }

                if((eachBall.y - PanelTopMost) < 7){
                    eachBall.changeYDirection();
                }
                if((PanelBottomMost - eachBall.y ) < 7){
                    eachBall.destoryBlock();
                }
                for(Block eachBlock : this.blocks){
                    if(eachBlock.intersects(eachBall) && eachBlock.getIsAlive()){
                        eachBlock.destoryBlock();
                        eachBall.changeYDirection();
                        if(eachBlock.isHasCoolStuff()){
                            Block tempExtraBlock = new Block(15,15, eachBlock.x,eachBlock.y, new ImageIcon(this.getClass().getResource("/imgs/extra.png")).getImage());
                            tempExtraBlock.setCoolStuffSpeed();
                            this.coolStuffs.add(tempExtraBlock);
                        }
                    }
                }
                if(this.paddle.intersects(eachBall)){
                    eachBall.changeYDirection();
                }
                eachBall.x += eachBall.getSpeedX();
                eachBall.y += eachBall.getSpeedY();
                // System.out.println("X: " + eachBall.x + " Y: " + eachBall.y);
            }
        }
        for(Block eachExtra : this.coolStuffs){
            if((PanelBottomMost - eachExtra.y) < 7) {
                eachExtra.destoryBlock();
            }
            if(eachExtra.getIsAlive()) {
                if(eachExtra.intersects(this.paddle) && eachExtra.getIsAlive()) {
                    eachExtra.destoryBlock();
                    this.balls.add(new Block(15,15,this.paddle.x + 75,738, new ImageIcon(this.getClass().getResource("/imgs/ball.png")).getImage()));
                }
                eachExtra.y += eachExtra.getSpeedY();
            }
        }
        this.repaint();
    }

    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        for(Block eachBlock : blocks){
            if(eachBlock.getIsAlive()){
                eachBlock.drawBlock(graphics, this);
            }
        }
        for(Block eachBlock : balls){
            if(eachBlock.getIsAlive()) {
                eachBlock.drawBlock(graphics,this);
            }
        }
        for(Block eachExtra : this.coolStuffs){
            if(eachExtra.getIsAlive()){
                eachExtra.drawBlock(graphics, this);
            }
        }
        this.paddle.drawBlock(graphics, this);
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed:" + e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            this.paddle.x -= Paddle_Moving_Distance;
            if(this.paddle.x <= 0){
                this.paddle.x = 0;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.paddle.x += Paddle_Moving_Distance;
            if(this.paddle.x >= 525){
                this.paddle.x = 525;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            Thread newThread = new Thread(this.gameThread);
            newThread.start();
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            this.balls.add(new Block(15,15,this.paddle.x + 50,738, new ImageIcon(this.getClass().getResource("/imgs/ball.png")).getImage()));
        }

        // System.out.println("X: " + this.paddle.x);
        // this.repaint();
    }

    public void keyTyped(KeyEvent e) {
        // Nothing
        // System.out.println("keyTyped:" + e.getKeyChar());
    }


    public void keyReleased(KeyEvent e) {
        // Nothing
    }
}