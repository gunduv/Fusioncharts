package com.cg.th;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class FactorialCalculator implements Callable<Integer> 
{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 3907368960302003148L;
	private Integer number;
 
    public FactorialCalculator(Integer number) {
        this.number = number;
    }
 
/*	@Override
	public Integer getRawResult() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	protected void setRawResult(Integer value) {
		// TODO Auto-generated method stub
		number = value;
	}

*/	public Integer call() {
    	long start = new Date().getTime();
        int result = number;
        try {
        	number = new Random().nextInt(10);
            System.out.println("number - " + number);
        	TimeUnit.SECONDS.sleep(30);
        	if(number%9==0){
        		TimeUnit.SECONDS.sleep(120);	
        	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long end = new Date().getTime();
        System.out.println("Result for number - " + number + " -> " + result);
        System.out.println("Time taken to calculate: " + (end-start));
        return number;
	}

}
 