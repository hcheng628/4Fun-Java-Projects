package us.supercheng.akka.entity;

import akka.actor.UntypedActor;

import java.util.Vector;

/**
 * Created by cl799honchen on 7/15/2017.
 */
public class PrimeNumberAkka extends UntypedActor {
    private Vector<Integer> primeNumList;

    public PrimeNumberAkka(){
        this.primeNumList = new Vector<Integer>();
    }

    public Vector<Integer> getPrimeNumList() {
        return primeNumList;
    }

    public void setPrimeNumList(Vector<Integer> primeNumList) {
        this.primeNumList = primeNumList;
    }

    public void listPrimeNums(int[] inRange){
        int begin = inRange[0];
        int end = inRange[1];

        for(int i = begin; i <= end; i++){
            if(i==1){
                i++;
            }
            boolean primeFlag = true;
            for(int j=2;j<i && primeFlag; j++){
                int temp = i % j;
                if( temp == 0){
                    // System.out.println(i + "/" + j + "=" + temp);
                    primeFlag = false;
                    // System.out.println("Not Prime: " + primeFlag + " " + i);
                    break;
                }
            }
            if(primeFlag){
                //System.out.println("Prime Number: " + i);
                this.primeNumList.add(i);
            }
        }
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        int [] inputIntArr = (int []) message;
        this.listPrimeNums(inputIntArr);
    }
}