import java.util.*;

public class RandomNumberGame{
	public static void main(String args[]) {
		Random randomNumber = new Random();
		int target = randomNumber.nextInt(100-0+1);
		Scanner scanner = new Scanner(System.in);
		
		boolean rightNumber = false;
		
		
		do {
			System.out.println("Enter a number between 0-100: ");
			String userInput = scanner.nextLine();
			int userNumber = Integer.parseInt(userInput);
			if(userNumber > target) System.out.println("Lower");
			if(userNumber < target) System.out.println("Higher");
			if(userNumber == target) {
				System.out.println("Congratulations " + userNumber + " is the correct number!!!");
				rightNumber = true;
			} 
		}
		while (!rightNumber);
		
		scanner.close();
	}
}