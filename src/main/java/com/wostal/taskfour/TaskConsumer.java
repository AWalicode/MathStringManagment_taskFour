package com.wostal.taskfour;

import java.util.Queue;

/**
 * Solves tasks
 * @author Aleksander Wostal
 */
public class TaskConsumer extends Thread {
	
	/**
	 * Queue of tasks
	 */
	private Queue<String> tasks;
	
	/**
	 * Constructor
	 */
	public TaskConsumer(Queue<String> tasks) {
		this.tasks = tasks;
	}
	
	
	/**
	 * Solves math string
	 * @return double
	 */
	public double solveTask(String str){
		if(!str.matches("^-?\\d+([-+/*]\\d+)*")) {
			try {
				throw new Exception("Cant solve this string: " + str);
			} catch (Exception e) {
				e.printStackTrace();
				return -1.0;
			}
		}
		Double result = 0.0;
		String[] parts = str.split("(?=[-+/*])|(?<=[+/*])");
		for(int index = 0; index<parts.length; index++) {
			double r = Double.parseDouble(parts[index]);
			while(index+2 <= parts.length) {
				if(parts[index+1].equals("+")) {
					index++;
					break;
				}
				if(parts[index+1].matches("-\\d+")) break;
				
				if(parts[index+1].equals("*")) {
					r=r*Double.parseDouble(parts[index+2]);
				}else if(parts[index+1].equals("/")) {
					r=r/Double.parseDouble(parts[index+2]);
				}	
				index+=2;
			}
			result+=r;
		}
		return result;
	}
	
	/**
	 * Gets task/string from queue
	 */
	public void getTask() {
		String task = "";
		synchronized(tasks) {
			if(tasks!=null) {
				if(tasks.size() > 0) {
					task = tasks.remove();
				}
				if(task!="") {
					System.out.println("#"+this.getName()+" solved task [" + tasks.size() + "]: " + task + " = " + this.solveTask(task));
				}
			}
		}
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Runs thread
	 */
	public void run() {
		while(true) {
			this.getTask();
		}
	}
}
