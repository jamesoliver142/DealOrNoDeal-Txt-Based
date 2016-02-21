/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealornodeal;
/**
 *
 * @author James Oliver 1279196
 */
import java.util.Scanner;

public class Player {

    Scanner input = new Scanner(System.in);
    private Banker banker = new Banker();

    //check user correctly inputs the right value and see if they accept or reject the offer
    public boolean gameStatus() {
        System.out.print("Accept or Reject! [1]Accept [2]reject: ");
        int temp = input.nextInt();
        System.out.println();

        if (temp == 1) {
            return false;
        } if (temp == 2) {
            return true;
        } else {
            System.out.println("Invalid input. Try again");
            return gameStatus();
        }
    }
    //User inputs their select case and checks if the case is in bounds
    public int user() {

        boolean isOkay = false;
        int userInput = 0;
        while (isOkay == false) {
            System.out.print("\nPlease Select Your Case!: ");
            userInput = input.nextInt();
            if (userInput <= 0 || userInput >= 27) {
                System.out.println("Invalid input Try again");
            } else {
                isOkay = true;
            }
        }
        return userInput;
    }
    
   //user goes to remove a case checks that it is avalible and in bounds
    public int removeCase(int i, Case cases[]) {

        int choice = 0;
        boolean correctInput = false;

        while (correctInput == false) {
            System.out.print("Please remove " + i + " case/s: "); 
            choice = input.nextInt();
            if (choice <= 0 || choice >= cases.length || cases[choice] == null) {
                System.out.println();
                System.out.println("Invalid Input please Try again\n");
            } else {
                System.out.println( " You just removed case # " + choice);
                System.out.println("|" + choice + "| contains $"
                        + cases[choice].getAmount() + "\n");
                correctInput = true;
            }

        }
        return choice;
    }
}