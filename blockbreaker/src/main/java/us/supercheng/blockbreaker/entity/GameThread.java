package us.supercheng.blockbreaker.entity;

/**
 * Created by hongyu on 6/24/17.
 */
public class GameThread implements Runnable {
    private BlockPanel blockPanel;

    public GameThread(BlockPanel inBlockPanel){
        this.blockPanel = inBlockPanel;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(3);
                this.blockPanel.update();
                // System.out.println("Run Run Run");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
