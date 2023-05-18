package main;
import java.util.ArrayList;

/**
 * This is the Market class. This class will control the logic
 * for buying and selling all purchasable items such as Athletes
 * and Items.
 * 
 * @author Kush Desai
 * @author Yunu Cho
 * 
 */
public class Market {
	private ArrayList<Athlete> purchasableAthletes;
	private ArrayList<Item> purchasableItems;
	private ArrayList<Athlete> starterAthletes = new ArrayList<Athlete>();
	private ArrayList<Athlete> weeklyPurchasedAthletes = new ArrayList<Athlete>();
	private ArrayList<Item> weeklyPurchasedItems = new ArrayList<Item>();
	
	public Market() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean buy() {
		return false;
	}
	public void sell() {
		
	}
	public ArrayList<Athlete> getPurchasableAthletes() {
		return purchasableAthletes;
	}
	public void setPurchasableAthletes() {
		GenerateRandom randomAthlete = new GenerateRandom();
		purchasableAthletes = randomAthlete.generateWeeklyAthletes();
		System.out.println(purchasableAthletes);
	}
	
	public ArrayList<Item> getPurchasableItems() {
		return purchasableItems;
	}
	public void setPurchasableItems() {
		GenerateRandom randomItem = new GenerateRandom();
		purchasableItems = randomItem.generateWeeklyItems();
	}
	public ArrayList<Athlete> getStarterAthletes() {
		return starterAthletes;
	}
	
	public ArrayList<Athlete> getWeeklyPurchasedAthletes() {
		return weeklyPurchasedAthletes;
	}
	
	public ArrayList<Item> getWeeklyPurchasedItems() {
		return weeklyPurchasedItems;
	}
	
	public void addWeeklyPurchasedAthletes(Athlete athlete) {
		weeklyPurchasedAthletes.add(athlete);
	}
	
	public void addWeeklyPurchasedItems(Item item) {
		weeklyPurchasedItems.add(item);
	}
	
	public void clearWeeklyPurchases() {
		weeklyPurchasedAthletes.clear();
		weeklyPurchasedItems.clear();
	}
}
