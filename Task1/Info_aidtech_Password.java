import java.util.Random;
import java.util.Scanner;

public class Info_aidtech_Password {
    public String generate(int length, String result) {
        Random rand = new Random();
        String ans = "";
		String arr[]=result.split(" ");
		for(String st : arr){
			ans += st.charAt(rand.nextInt(st.length()));
		}
        for (int i = 4; i < length; i++) {
            ans += result.charAt(rand.nextInt(result.length()));
        }
        return ans;
    }

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) 
        {
            Info_aidtech_Password obj = new Info_aidtech_Password();
            System.out.println("Enter the length of the password:");
            int length;
            try 
            {
                length = sc.nextInt();
                if (length <= 0) 
                {
                    System.out.println("Invalid input, please provide a valid length for the password.");
                    return;
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid input, please provide a valid integer for the password length.");
                return;
            }

            String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String small = "abcdefghijklmnopqrstuvwxyz";
            String num = "0123456789";
            String special = "!@#$%^&*_?-/";

            System.out.println("Choose character sets from: 1>uppercase, 2>lowercase, 3>number, 4>special character.");
            String result = "";
            System.out.println("Your choices:");

            for (int i = 0; i < 4; i++) 
            {
                int n;
                try 
                {
                    n = sc.nextInt();
                } 
                catch (Exception e) 
                {
                    System.out.println("Invalid input, please provide a valid integer choice.");
                    return;
                }

                if (n == 1) 
                {
                    System.out.println("Your choice is an uppercase character set.");
                    result += caps+" ";
                } 
                else if (n == 2)
                {
                    System.out.println("Your choice is a lowercase character set.");
                    result += small+" " ;
                } 
                else if (n == 3) 
                {
                    System.out.println("Your choice is a number character set.");
                    result += num+" ";
                } 
                else if (n == 4) 
                {
                    System.out.println("Your choice is a special character set.");
                    result += special+" ";
                } 
                else 
                {
                    System.out.println("Your choice is complete.");
                    break;
                }
            }
			System.out.println(result);
            if (result.equals("")) 
            {
                System.out.println("Invalid input, please choose between 1 and 4.");
            } 
            else 
            {
                System.out.println("Your password is : " + obj.generate(length, result));
            }
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}