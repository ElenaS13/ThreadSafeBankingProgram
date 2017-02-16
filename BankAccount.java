package BankingAppThreads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	
	private double balance;
	private String accountNumber;
	
	private Lock lock;
	
	public BankAccount(double initialBalance, String accountName) {
		super();
		this.balance = initialBalance;
		this.accountNumber = accountNumber;
		this.lock = new ReentrantLock();
	}
	
//	public void deposit(double amount){
//		balance +=amount;
//		
//	}
//	
//	public void withdraw(double amount){
//		balance -=amount;
//	}
	
//	public void deposit(double amount){
//		synchronized(this) {
//		balance +=amount;
//		}
//	}
//	
//	public void withdraw(double amount){
//		synchronized(this){
//		balance -=amount;
//	}
//	}
	
//	public void withdrawn(double amount){
//		lock.lock();
//		try{
//			balance-= amount;
//		}finally {
//			lock.unlock();
//		}
//	}
//	
//	public void deposit(double amount){
//		lock.lock();
//		try{
//			balance+=amount;
//		}finally{
//			lock.unlock();
//		}
//	}
	
	//using tryLock()
	
	public void deposit(double amount){
		boolean status = false;
		try{
			if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
				try{
					balance+=amount;
					status= true;
				}finally {
					lock.unlock();
				}
			}else {
				System.out.println("Could not get the lock");
			}
		} catch(InterruptedException e){
			//do something here
		}
		System.out.println("Transaction status = "+ status);
	}
	
	public void withdraw(double amount){
		boolean status = false;
		try{
			if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
				try{
					balance-=amount;
					status =true;
				}finally {
					lock.unlock();
				}
			}else {
				System.out.println("Could not get the lock");
			}
		} catch(InterruptedException e){
			//do something here
		}
		System.out.println("Transaction status = "+status);
	}
	
	
	
	//method that gets the bank account number
	public String getAccountNumber(){
		return accountNumber;
	}
	
	//method that prints the bank account number
	
	public void printAccountNumber() {
		System.out.println("Account number = "+ accountNumber);
	}
}
