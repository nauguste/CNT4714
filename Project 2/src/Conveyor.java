/*   
     Name:  Naseem Auguste
     Course: CNT 4714 – Fall 2022 
     Assignment title: Project 2 – Multi-threaded Programming
     Date: Thursday October 6, 2022 
*/ 


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conveyor 
{

	private int conveyornum;
	private Lock lock;
	
	public Conveyor(int conveyornum) 	//constructor
	{
		this.conveyornum = conveyornum;	
		this.lock = new ReentrantLock();
	}
	
	public int getName() 	//constructor
	{
		return conveyornum;
	}
	
	public boolean getConveyor()
	{
		return lock.tryLock(); 	//returns true if request was granted, false otherwise
	}
	
	public void unlockConveyor()
	{
		lock.unlock();
	}
}
