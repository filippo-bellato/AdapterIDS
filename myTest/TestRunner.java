package myAdapter;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args){
        Result test1 = JUnitCore.runClasses(ListAdapterTest.class);
        for(Failure failure : test1.getFailures()){
            System.out.println(failure.toString());
        }

        Result test2 = JUnitCore.runClasses(MapAdapterTest.class);
        for(Failure failure : test2.getFailures()){
            System.out.println(failure.toString());
        }
    }
}
