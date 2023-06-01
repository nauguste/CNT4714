/*   
     Name:  Naseem Auguste
     Course: CNT 4714 – Fall 2022 
     Assignment title: Project 2 – Multi-threaded Programming
     Date: Thursday October 6, 2022 
*/ 

import java.util.Random;

 

public class RoutingStation implements Runnable 
{
	private int threadnum;
	private int workload;
	private Conveyor inputConveyor;
	private Conveyor outputConveyor;
	//private int totalstations;
	
	private static Random rand = new Random();
	
	public RoutingStation(int threadnum, int workload, Conveyor inputConveyor, Conveyor outputConveyor) 	//constructor
	{
		this.threadnum = threadnum;
		this.workload = workload;
		this.inputConveyor = inputConveyor;
		this.outputConveyor = outputConveyor;
	}

	public int getworkload() 
	{
		return workload;
	}
	
	
	public Conveyor getinputConveyor() 
	{
		return inputConveyor;
	}
	
	public Conveyor getoutputConveyor() 
	{
		return outputConveyor;
	}
	
	public void Sleep()
	{
		try 
		{
			Thread.sleep(rand.nextInt(750));
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void doWork()
	{
		this.workload--;
		
		if(workload > 0)
		{
			System.out.println("ROUTING STATION " + this.threadnum + " HAS BEGUN DELIVERING PACKAGES WITH CONVEYORS.");
			for(int i =0; i < 500000; i++); //dummy loop
			System.out.println("ROUTING STATION " + this.threadnum + " HAS SUCCESSFULLY DELIVERED PACKAGE WITH CONVEYORS. " + this.workload + " PACKAGES REMAINING\n");
		}
		if(this.workload == 0)
		{
			System.out.println("ROUTING STATION " + this.threadnum + " HAS SUCCESSFULLY DELIVERED ALL PACKAGES. RELEASING LOCKS TO C" + this.inputConveyor.getName() + 
							   " AND C" + this.outputConveyor.getName() + ". BEGINNING SHUTDOWN PROCEDURE");
		}
	}
	

	@Override
	public void run() 
	{
		
		System.out.println("\n========================= ROUTING STATION " + this.threadnum + " COMING ONLINE =========================\n");
	
		System.out.println("ROUTING STATION " + this.threadnum + " IS ASSIGNED INPUT CONVEYOR FROM C" + this.inputConveyor.getName());
		System.out.println("ROUTING STATION " + this.threadnum + " IS ASSIGNED OUTPUT CONVEYOR FROM C" + this.outputConveyor.getName());
		System.out.println("ROUTING STATION " + this.threadnum + " HAS A WORK ORDER OF " + this.workload + " PACKAGES");
		
		while(this.workload > 0)
		{
			System.out.println("ROUTING STATION " + this.threadnum + " IS ENTERING THE LOCK ACQUISITION PHASE ");
		
			if(this.inputConveyor.getConveyor())
			{
				System.out.println("ROUTING STATION " + this.threadnum + " HAS ACQUIRED THE INPUT LOCK TO C" + this.inputConveyor.getName() + " AND IS HOLDING IT");
				
				if(this.outputConveyor.getConveyor())
				{
					System.out.println("ROUTING STATION " + this.threadnum + " HAS ACQUIRED THE OUTPUT LOCK TO C" + this.outputConveyor.getName() + " AND IS HOLDING IT");
					
					doWork();
					
					if(this.workload > 0)
					{
					this.inputConveyor.unlockConveyor();
					System.out.println("ROUTING STATION " + this.threadnum + " HAS RELEASED THE INPUT LOCK TO C" + this.inputConveyor.getName());
					
					this.outputConveyor.unlockConveyor();
					System.out.println("ROUTING STATION " + this.threadnum + " HAS RELEASED THE OUTPUT LOCK TO C" + this.outputConveyor.getName());
					
					Sleep();
					}
					else 
					{
						this.inputConveyor.unlockConveyor();
						this.outputConveyor.unlockConveyor();
						System.out.println("\n========================= ROUTING STATION " + this.threadnum + " GOING OFFLINE =========================\n");
					}
				}
				else
				{
					System.out.println("\n+++++++++++++++++++++++++++ SYNCHRONIZATION ISSUE ++++++++++++++++++++++++++\n\nROUTING STATION " + this.threadnum + " COULD NOT ACQUIRE OUTPUT LOCK TO C" + this.outputConveyor.getName() +
									   ". ROUTING STATION " + ((threadnum + Main.x -1) % Main.x) + " CURRENTLY HOLDS THE LOCK. RELEASING THE INPUT LOCK TO C" + this.inputConveyor.getName());
					
					this.inputConveyor.unlockConveyor();
					Sleep();
				}
			}
			else 
			{
				System.out.println("ROUTING STATION " + this.threadnum + " COULD NOT ACQUIRE INPUT LOCK TO C" + this.inputConveyor.getName());
				Sleep();
			}
		}	
	}
}
