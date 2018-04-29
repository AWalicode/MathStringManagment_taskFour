package com.wostal.taskfour;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Runs threads which produces and calculates math strings by queue 
 * @author Aleksander Wostal
 *
 */
public class App 
{
	public static final int MAX_SIZE = 100;
	
    public static void main( String[] args )
    {
		Queue<String> tasks = new LinkedList<String>();
		int size = MAX_SIZE;
		if(args.length > 0) {
			try {
				size = Integer.parseInt(args[0]);
			}catch(Exception e) {
				size = MAX_SIZE;
			}
		}
        
		TaskProducer producer1 = new TaskProducer(tasks, size);
		TaskProducer producer2 = new TaskProducer(tasks, size);

		
		producer1.start();
		producer2.start();

		
		TaskConsumer consumer1 = new TaskConsumer(tasks);
		TaskConsumer consumer2 = new TaskConsumer(tasks);
		TaskConsumer consumer3 = new TaskConsumer(tasks);
		TaskConsumer consumer4 = new TaskConsumer(tasks);
		
		consumer1.start();
		consumer2.start();
		consumer3.start();
		consumer4.start();
    }
}
