package BurritoKing;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BurritoKing {
	
	static Scanner input;
	public static void main(String[] args) {
		System.out.println("<---Welcome to Burrito King!--->");
		
		ItemDetails liveStatusBurrito = new ItemDetails("Burrito", Manager.priceOfBurrito, Manager.preparationTimeOfBurritoBatch, Manager.batchSizeOfBurrito, 0);
		ItemDetails liveStatusFries = new ItemDetails("Fries", Manager.batchSizeOfFries, Manager.preparationTimeOfFriesBatch, Manager.batchSizeOfFries, 0);
		ItemDetails liveStatussoda = new ItemDetails("Burrito", Manager.priceOfSoda, Manager.preparationTimeOfSodaBatch, Manager.batchSizeOfSoda, 0);

		
		HashMap<String, ItemDetails> liveStatusRestaurant = new HashMap<>();
		liveStatusRestaurant.put("burrito", liveStatusBurrito);
		liveStatusRestaurant.put("fries", liveStatusFries);
		liveStatusRestaurant.put("soda", liveStatussoda);
				
		boolean programRunning = true;
		do {
			System.out.println("\n========================================================\n");
			System.out.println("->How can we help you?");
			System.out.println("1. Order Items");
			System.out.println("2. Show Sales Report");
			System.out.println("3. Update Prices");
			System.out.println("4. Exit");
			System.out.print("Enter a number from the available options: ");
			input = new Scanner(System.in);
			String userInput = input.next();
			int userSelection;
			try {
				userSelection = Integer.parseInt(userInput);
			}
			catch (Exception e){
				userSelection = 0;
			}
			
			switch (userSelection) {
				case 1:
					Customer.orderMenu(liveStatusRestaurant);
					break;
				case 2:
					Manager.generateReport(liveStatusRestaurant);
					break;
				case 3:
					Manager.updatePrice(liveStatusRestaurant);
					break;
				case 4:
					System.out.println("\n<---Thank You for chosing us! Visit Again--->");
					programRunning = false;
					break;
				default:
					System.out.println("\nIncorrect Input! Make sure to enter a number from available options\n");
			}
		}
		while(programRunning);
	}
}


class ItemDetails{
	String itemName;
	float itemPrice;
	float itemBatchPreparationTime;
	float itemBatchSize;
	float itemAvailableInInventory;
	int itemSold = 0;
	float netSales = 0;
	
	ItemDetails(String itemName, float itemPrice, float itemBatchPreparationTime, float itemBatchSize, float itemAvailableInInventory) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBatchPreparationTime = itemBatchPreparationTime;
		this.itemBatchSize = itemBatchSize;
		this.itemAvailableInInventory = itemAvailableInInventory;
	}
}

// This class contains the logic to execute tasks by manager - Get Report and Change Price 
class Manager{
	
	static Scanner input;
//	This is the initial status of the store
//	Price
	static float priceOfBurrito = 7;
	static float priceOfFries = 4;
	static float priceOfSoda = 2.50f;
// Preparation Time	
	static float preparationTimeOfBurritoBatch = 9;
	static float preparationTimeOfFriesBatch = 8;
	static float preparationTimeOfSodaBatch = 0;
// Batch Size	
	static float batchSizeOfBurrito = 2;
	static float batchSizeOfFries = 5;
	static float batchSizeOfSoda = 0;
	
//	Generate Report
	static void generateReport(HashMap<String, ItemDetails> liveStatusRestaurant) {
		System.out.println("\n========================================================\n");
		System.out.println("Total Sales:");
//		Calculating Sales of Each Item
		
		System.out.printf("Burritos- Quantity: %d Amount: %.2f\n", liveStatusRestaurant.get("burrito").itemSold, liveStatusRestaurant.get("burrito").netSales);
		System.out.printf("Fries- Quantity: %d Amount: %.2f\n", liveStatusRestaurant.get("fries").itemSold, liveStatusRestaurant.get("fries").netSales);
		System.out.printf("Soda- Quantity: %d Amount: %.2f\n", liveStatusRestaurant.get("soda").itemSold, liveStatusRestaurant.get("soda").netSales);
		System.out.printf("Total Sales %.2f", liveStatusRestaurant.get("burrito").netSales + liveStatusRestaurant.get("fries").netSales + liveStatusRestaurant.get("soda").netSales);
	}
		
	
//	Change Price of a specific item
	static void updatePrice(HashMap<String, ItemDetails> liveStatusRestaurant) {
		
//		Print Statements for Update Menu Price
		System.out.println("\n========================================================\n");
		System.out.println("->Which item's price do you want to update?");
		System.out.println("1.Burrito");
		System.out.println("2.Fries");
		System.out.println("3.Soda");
		System.out.println("4.Back to Main Menu");
		System.out.print("Enter a number from the available options: ");
		
//		Select Items
		input = new Scanner(System.in);
		String userInput = input.next();
		int userSelection;
		try {
			userSelection = Integer.parseInt(userInput);
		}
		catch (Exception e){
			System.out.println("Invalid Number. Please enter an available number!\n");
			updatePrice(liveStatusRestaurant);
			return;
		}
		
		if(userSelection == 4) return;//Return to main menu
		
//		Select New Price
		System.out.print("Enter New Price: ");
		String newPrice = input.next();
		float newPriceFloat;
		try {
			newPriceFloat = Float.parseFloat(newPrice);
		}
		catch (Exception e){
			System.out.print("Invalid Number. Please enter correct number!\n");
			updatePrice(liveStatusRestaurant);
			return;
		}
		
//		Price Change Logic
		switch (userSelection) {
		case 1:
			ItemDetails priceUpdateBurrito = liveStatusRestaurant.get("burrito");
			priceUpdateBurrito.itemPrice = newPriceFloat;
			updatePrice(liveStatusRestaurant);
			break;
		case 2:
			ItemDetails priceUpdateFries = liveStatusRestaurant.get("fries");
			priceUpdateFries.itemPrice = newPriceFloat;
			updatePrice(liveStatusRestaurant);
			break;
		case 3:
			ItemDetails priceUpdateSoda = liveStatusRestaurant.get("soda");
			priceUpdateSoda.itemPrice = newPriceFloat;
			updatePrice(liveStatusRestaurant);
			break;
		default:
			System.out.println("\nIncorrect Input! Make sure to enter a number from available options\n");
			updatePrice(liveStatusRestaurant);
		}
	}
}

class OrderDetails{
	String itemName;
	float waitTime;
	float orderPrice;
	float amountGiven;
}

// This class contains functionality provided to the customer
class Customer{
	static Scanner input;
	static void orderMenu(HashMap<String, ItemDetails> liveStatusRestaurant) {
		System.out.println("\n========================================================\n");
		System.out.println("->What do you want to order?");
		System.out.println("1.Burrito");
		System.out.println("2.Fries");
		System.out.println("3.Soda");
		System.out.println("4.Back to Main Menu");
		System.out.print("Enter a number from the available options: ");
		
		input = new Scanner(System.in);
		String userInput = input.next();
		int userSelection;
		try {
			userSelection = Integer.parseInt(userInput);
		}
		catch (Exception e){
			userSelection = 0;
		}
		
		if(userSelection == 4)return;
		
		System.out.print("Enter Quantity: ");
		String newPrice = input.next();
		float quantity;
		try {
			quantity = Float.parseFloat(newPrice);
		}
		catch (Exception e){
			System.out.print("Invalid Number. Please enter correct number!\n");
			orderMenu(liveStatusRestaurant);
			return;
		}
		String userSelectionItem = "";
		switch ((int)userSelection) {
		case 1:
			userSelectionItem = "burrito";
			break;
		case 2:
			userSelectionItem = "fries";
			break;
		case 3:
			userSelectionItem = "soda";
			break;
		default:
			System.out.println("\nIncorrect Input! Make sure to enter a number from available options\n");
			orderMenu(liveStatusRestaurant);
			return;
		}
		
		placeOrder(liveStatusRestaurant, userSelectionItem, quantity);
		
		
		
	}
	
	private static void placeOrder(HashMap<String, ItemDetails> liveStatusRestaurant, String userSelection, float quantity) {
		ItemDetails updateItemDetails = liveStatusRestaurant.get(userSelection);
		updateItemDetails.itemSold += quantity;
		updateItemDetails.netSales += (updateItemDetails.itemPrice * quantity);
		OrderDetails orderDetails = new OrderDetails();
		if(updateItemDetails.itemAvailableInInventory < quantity && updateItemDetails.itemBatchPreparationTime != 0) {
			quantity -= updateItemDetails.itemAvailableInInventory;
			float preparationTime = 0;
			float itemProduced = 0;
			while(itemProduced < quantity) {
				preparationTime += updateItemDetails.itemBatchPreparationTime;
				itemProduced += updateItemDetails.itemBatchSize;				
			}
			updateItemDetails.itemAvailableInInventory = itemProduced - quantity;
			orderDetails.waitTime = preparationTime;
		}
		else if(updateItemDetails.itemAvailableInInventory >= quantity) {
			updateItemDetails.itemAvailableInInventory -= quantity;
			orderDetails.waitTime = 0;
		}
		else {			
			orderDetails.waitTime = 0;
		}
		orderDetails.itemName = updateItemDetails.itemName;
		orderDetails.orderPrice = updateItemDetails.itemPrice*quantity;
		updateItemDetails.netSales += orderDetails.orderPrice;
		float amount = 0;
		
//		Get Amount
		do {			
			System.out.printf("Total Amount for %.0f %s is %.2f\n", quantity, updateItemDetails.itemName, orderDetails.orderPrice);
			System.out.print("Enter Amount Paid:");
			String amountInput = input.next();
			try {
				amount = Float.parseFloat(amountInput);
			}
			catch (Exception e){
				System.out.print("Invalid Number. Please enter correct number!\n");
			}
			if(amount >= orderDetails.orderPrice) {
				System.out.print("--->Order Placed!\n");
				break;
			}
			else {
				System.out.print("Insufficient Amount\n");
			}
			
		} while (true);
		
		System.out.printf("You will recieve $%.2f\n", amount - orderDetails.orderPrice);
		if(orderDetails.waitTime>0) {
			System.out.printf("Please wait for %.0f minutes\n", orderDetails.waitTime);
		}
		else {
			System.out.printf("Your order is ready!\n");
		}
		orderMenu(liveStatusRestaurant);	
		return;
	}
}