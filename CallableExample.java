package com.cg.th;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
 
public class CallableExample 
{
      public static void main(String[] args) 
      {
//          ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1000);
//          ExecutorService executor = Executors.newWorkStealingPool(2000);
    	  ForkJoinPool pool = new ForkJoinPool(2000);
          List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();
          	  CompletionService<Integer> taskCompletionService =
                  new ExecutorCompletionService<Integer>(pool);        		  
          Random random = new Random();
          
      	long start = new Date().getTime();
        
          for (int i=0; i<4000; i++)
          {
              Integer number = i;
              FactorialCalculator calculator  = new FactorialCalculator(number);
              Future<Integer> result = 
            		  taskCompletionService.submit(calculator);
              resultList.add(result);
              
          }
          int total = 0;
           
/*          for(Future<Integer> future : resultList)
          {
            
*/         for(int j=0;j<resultList.size();j++){
            try
                {
                	Future<Integer> future = taskCompletionService.take();
                	total =future.get(); 
                    System.out.println("Future result is - " + " - " + total + "; And Task done is " + future.isDone());
                    System.out.println("Complted jobs:" + j);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
//            }
}
            //shut down the executor service now
            pool.shutdown();
            
            long end = new Date().getTime();
            System.out.println("Time taken to end program: " + (end-start));
            System.out.println("Time wait total : " + (total));
      }
}
