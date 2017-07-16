package us.supercheng.akka.app.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.akka.app.AkkaApp;
import us.supercheng.akka.entity.PrimeNumber;
import us.supercheng.akka.entity.PrimeNumberAkka;

/**
 * Created by cl799honchen on 7/14/2017.
 */

public class App_Test {
    int begin = 1;
    int end = 500000;
    int[] range = new int[2];

    @Before
    public void warmUp(){
        range[0] = begin;
        range[1] = end;
    }

    @Test
    public void regular(){
        PrimeNumber cal = new PrimeNumber();
        long start_time = System.nanoTime();
        cal.listPrimeNums(range);
        long end_time = System.nanoTime();
        double duration_time = (end_time - start_time) / 1e6;
        System.out.println("Regular Approach: " + duration_time);
    }

    @Test
    public void akka(){
        AkkaApp app = new AkkaApp(100,range);
        long start_time = System.nanoTime();
        app.work();
        long end_time = System.nanoTime();
        double duration_time = (end_time - start_time) / 1e6;
        System.out.println("Akka Approach: " + duration_time);
    }
}