/*   
     Name:  Naseem Auguste
     Course: CNT 4714 – Fall 2022 
     Assignment title: Project 2 – Multi-threaded Programming
     Date: Thursday October 6, 2022 
*/ 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.PrintStream;

public class Main 
{
	
	static int MAX = 10; //maximum thread pool
	static int x;
	
	public static void main(String[] args) 
	{
		try 
		{
			ArrayList<Integer> config = new ArrayList<Integer>();
			
			File myObj = new File("config1.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextInt()) 
		    {
		    	config.add(myReader.nextInt()); 	//Scan in integer values from config.txt and insert them into an array. 			
		    }
		    myReader.close();
		    //System.out.println(config);
		    
			
		    File file = new File("output2.txt");
		    //Instantiating the PrintStream class
		    PrintStream stream = new PrintStream(file);
		    System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
		    System.setOut(stream);
		    
		    System.out.println("\n=========================  PACKAGE SIMULATION BEGINNING =========================\n");
		      
		    ExecutorService threads = Executors.newFixedThreadPool(MAX); 	//Creates the threads of MAX capacity
		    
		    x = config.get(0);
		    //System.out.println("X = " + x);
			int stations[] = new int[config.get(0)]; 	//array for stations
			//System.out.println("\nThere are " + stations.length + " stations in this simulation.");
			
			for(int i = 1; i <= stations.length; i++) 	//assigns workload to each station
			{
				stations[i-1] = config.get(i);
				//System.out.println("\nStation #" + (i-1) + " has " + stations[i-1] + " tasks to complete.");
			}
			//System.out.println("\n");
			
			
			Conveyor[] convey; 	//Declares array of Conveyor
			convey =  new Conveyor[x]; //Allocates spaces for x amount of conveyors
			for(int i = 0; i < x; i++)
			{
				convey[i] = new Conveyor(i);	//Initializes conveyor i with number i
			}
			
			System.out.println("STATIONS ARE BEING INITIALIZED ----- WORKLOAD ASSIGNMENT BEGINS\n");
			for(int i = 1; i <= stations.length; i++) 	//assigns workload to each station
				System.out.println("ROUTING STATION #" + (i-1) + " HAS " + stations[i-1] + " PACKAGES TO DELIVER");
			
			RoutingStation[] station =  new RoutingStation[x]; 	//Creates x amount of station objects 
			for(int i = 0; i < x; i++)
			{
				
				if(i == 0)
				{
					station[i] =  new RoutingStation(i, config.get(i+1), convey[i], convey[x-1]); 	//Connect the first station to the first and last conveyor
					threads.execute(station[i]);
				}
				else if(i == x-1)
				{
					station[i] =  new RoutingStation(i, config.get(i+1), convey[i], convey[i-1]); 	//Connect the last station to the last and 2nd to last conveyor
					threads.execute(station[i]);
				}
				else
				{
					station[i] =  new RoutingStation(i, config.get(i+1), convey[i], convey[i-1]); 	//Connect the first station to the current and next conveyor
					threads.execute(station[i]);
				}
			}		
			threads.shutdown();
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("File not Found.");
		}
	}
}


