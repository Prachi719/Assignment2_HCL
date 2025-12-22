package Assignment2;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexMenu {
	private static final String MOBILE_REGEX = "^[6-9][0-9]{9}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9_]{5,15}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	int n;
    	try {
    		while(true) {
    			System.out.println(" ------------Validation Menu -----------");
    			System.out.println("Press 1  for validate mobile no. ");
    			System.out.println("Press 2  for validate Email Id");
    			System.out.println("Press 3  for validate Username");
    			System.out.println("Press 4  for validate Password");
    			System.out.println("Press 5  for Exit");
    			System.out.println("Enter your choice");
    			
    			n = sc.nextInt();
    			sc.nextLine();
    			switch(n) {
    			case 1: System.out.println("Enter Mobile no:");
    				String mob = sc.nextLine();
    				validateInput(mob, MOBILE_REGEX, "Mobile Number");
    				break;
    			case 2:
                    System.out.print("Enter email ID: ");
                    String em = sc.nextLine();
                    validateInput(em, EMAIL_REGEX, "Email ID");
                    break;

                case 3:
                    System.out.print("Enter username: ");
                    String name = sc.nextLine();
                    validateInput(name, USERNAME_REGEX, "username");
                    break;

                case 4:
                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();
                    validateInput(pass, PASSWORD_REGEX, "Password");
                    break;

                case 5:
                    System.out.println("You Exited from the program. Thank You!");
                    return;

                default:
                    System.out.println("Invalid choice! Please select from 1 to 5.");
            }
    			}
    		
    	} catch(Exception e) {
    		System.out.println("Exception : Invalid Input Given By User");
            
    	}
    }
    
    private static void validateInput(String input, String regex, String fieldName) {
        if (Pattern.matches(regex, input)) {
            System.out.println("Welcome! " + fieldName + " is valid.");
        } else {
            System.out.println("Invalid " + fieldName + "!");
        }
    }
}

