package com.wostal.taskfour;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Creates tasks
 * @author Aleksander Wostal
 */
public class TaskProducer extends Thread {
	
	/**
	 * Queue of tasks
	 */
	private Queue<String> tasks;
	
	/**
	 * Size limit of queue
	 */
	private int MAX_SIZE;
	
	/**
	 * Producer's state, can create tasks or wait
	 */
	private static boolean isWaiting;
	
	
	/**
	 * Constructor
	 */
	public TaskProducer(Queue<String> tasks, int MAX_SIZE) {
		this.tasks = tasks;
		this.MAX_SIZE = MAX_SIZE;
	}
	
	/**
	 * Generates random math string to solve
	 * @return string
	 */
	public String generateTask(){
		String str = "";
		if(ThreadLocalRandom.current().nextInt(0, 2) == 0) {
		    str+="-";
		}
		str+=ThreadLocalRandom.current().nextInt(0, 59999);
		for(int i = 0; i<ThreadLocalRandom.current().nextInt(1, 99);i++) {
			int c = ThreadLocalRandom.current().nextInt(0, 4);
			switch(c) {
				case 1: 
					str+="-";
					break;
				case 2:
					str+="*";
					break;
				case 3:
					str+="/";
					break;
				default: 
					str+="+";
			}
			str+=ThreadLocalRandom.current().nextInt(0, 59999);
		}
		return str;
	}
	
	/**
	 * Adds task/string to queue
	 */
	public void addTask() {
		synchronized(tasks) {
			if(tasks.size() >= MAX_SIZE) {
				isWaiting = true;
			}
			
			if(isWaiting) {
				if(tasks.size() <= MAX_SIZE/2) {
					isWaiting = false;
					String task = generateTask();
					tasks.add(task);
					System.out.println("#"+this.getName()+" created task[" + tasks.size() + "]: " + task); 
				}	
			}else {
				String task = generateTask();
				tasks.add(task);
				System.out.println("#"+this.getName()+" created task[" + tasks.size() + "]: " + task);
			}
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Runs thread
	 */
	public void run() {
		while(true) {
			this.addTask();
		}
	}
		
	
}
