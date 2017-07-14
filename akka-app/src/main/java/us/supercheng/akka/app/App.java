package us.supercheng.akka.app;

/**
 * Created by cl799honchen on 7/14/2017.
 */
public class App {


    public static void main(String[] arg){
        int begin = 1;
        int end = 10000;
        int[] range = new int[2];
        range[0] = begin;
        range[1] = end;

        App cal = new App();

        cal.listPrimeNums(range);

    }

    public void listPrimeNums(int[] inRange){
        int begin = inRange[0];
        int end = inRange[1];
        for(int i = begin; i <= end; i++){
            System.out.println(i);
        }

    }
}
