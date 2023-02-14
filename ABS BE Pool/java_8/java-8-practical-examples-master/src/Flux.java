import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;


 class test1 extends Thread{

     @Override
     public void run() {
         System.out.println("in run method  running.........");
     }
 }

class test2 extends test1{

}

public class Flux {

    public static void main(String[] args) {

        String str1="Scaler";
        System.out.println(str1.hashCode());
         str1 = str1.concat("avc");
        System.out.println(str1.hashCode());
        String str2="Scaler";
        System.out.println(str2.hashCode());
        String str3=new String("Scaler");
        System.out.println(str1==str2);
        //true because both points to same memory allocation

        System.out.println(str1==str3);
        //false because str3 refers to instance created in heap

        System.out.println(str1.equals(str3));

        System.out.println(str1.substring(4));



    }
}