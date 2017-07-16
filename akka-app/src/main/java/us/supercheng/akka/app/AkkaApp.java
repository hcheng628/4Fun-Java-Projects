package us.supercheng.akka.app;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import us.supercheng.akka.entity.PrimeNumberAkka;

public class AkkaApp {
    private int numOfProcessors;
    private int threshold;
    private int [] allWork;

    public AkkaApp(int inNumOfProcessors, int [] inAllWork){
        this.numOfProcessors = inNumOfProcessors;
        this.allWork = inAllWork;
        this.threshold = this.allWork[1] / this.numOfProcessors;
    }

    public void work(){
        ActorSystem akkaSystem = ActorSystem.create("PrimeNumGenerator");
        ActorRef consumer = akkaSystem.actorOf(Props.create(PrimeNumberAkka.class));
        for(int i=1; i<= this.numOfProcessors;i++){
            System.out.println(">>> Dispatching Jobs " + i);
            int[] tempIntArr = new int [2];

            if(i==1){
                tempIntArr[0] = this.allWork[0];
            }else {
                tempIntArr[0] = this.allWork[1] / this.numOfProcessors * (i-1) - 1;
            }
            tempIntArr[1] = this.allWork[1] / this.numOfProcessors * i;

            consumer.tell(tempIntArr,ActorRef.noSender());
        }
    }
}