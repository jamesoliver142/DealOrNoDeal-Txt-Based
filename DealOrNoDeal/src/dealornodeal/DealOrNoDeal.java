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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DealOrNoDeal {

    Player player = new Player();
    Banker banker = new Banker();
    private int casesToRemove = 6;
    private double myAmount = 0;
    private double offer = 0;
    private int turn = 1;
    private int cases = 26;
    boolean clickable = true;
    String offerString;
    String myWinnings;
    private double amounts[] = {0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 2000, 5000,
        10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000
    };

    Case briefcase[] = new Case[27];
    //jumble method
    public void jumble()
    {
        Random rgen = new Random();
        for (int i = 0; i < amounts.length - 1; i++) {
            int Position = rgen.nextInt(amounts.length);
            double temp = amounts[i];
            amounts[i] = amounts[Position];
            amounts[Position] = temp;
        }

    }
    //add values to cases
    public void casesSetup() {
        jumble();
        for (int i = 0; i < briefcase.length; i++) {
            briefcase[i] = new Case();
            double value = amounts[i];
            briefcase[i].setAmount(value);
            briefcase[i].setFace(i);
        }
    }
    //show the text based cases
    public void showCases() {
        for (int i = 0; i < briefcase.length; i++) {
            if (i == 0) {
            } else if (briefcase[i] == null) {
                System.out.print("\t[X]");
            } else {
                System.out.print("\t[" + briefcase[i].getFace() + "]");
            }
            if (i % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
    //ask whether or not you are a new user or returning user
    public void enterName() {

        System.out.println("---- WELCOME TO DEAL OR NO DEAL ----");
        System.out.println();
        System.out.println("> Press 'R' to register a new account or 'L' to login");
        Scanner userInput = new Scanner(System.in);
        String input = userInput.next();
        while (!input.equals("L") && !input.equals("R")
                && !input.equals("l") && !input.equals("R")) {
            System.out.println("Error - Failed to recognise command");
            input = userInput.nextLine();
        }
        if (input.equals("R") || input.equals("r")) {
            createNewAccount();
        } else {
            accountLogIn();
        }
    }
    //Log in to an existing account
    public void accountLogIn() {
        boolean success = false;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Account Name: ");
        String userAcc = userInput.nextLine();
        System.out.print("Account Password: ");
        String userPass = userInput.nextLine();
        try {
            Scanner input = new Scanner(new File(userAcc + ".txt"));
            if (!userPass.equals(input.nextLine())) {
                System.out.println("Account or Password is incorrect. Please Try again..");
                accountLogIn();
            } else {
                success = true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Account or Password is incorrect. Please Try again..");
            accountLogIn();
        }
        //Account login successful
        if (success) {
            System.out.println("Account Login Successful!");
            System.out.println();
        }
    }
    //Create a new user
    public void createNewAccount() {
        System.out.println("---- Please enter your new user name and password ---- ");
        System.out.print("Account Name: ");
        Scanner userInput = new Scanner(System.in);
        String userAcc = userInput.nextLine();
        while (userAcc.length() > 8) {
            System.out.println("Please enter a maximum of 12 characters");
            System.out.print("Account Name: ");
            userAcc = userInput.nextLine();
        }
        System.out.print("Account Password: ");
        String userPass = userInput.nextLine();
        while (userPass.length() > 12) {
            System.out.println("Your password must be a maximum of 12 characters long");
            System.out.print("Account Password: ");
            userPass = userInput.nextLine();
        }
        // Account doesn't already exist
        try {
            Scanner input = new Scanner(new File(userAcc + ".txt"));
            System.out.println("There is already an account with that name, Try again.");
            createNewAccount();
        } catch (FileNotFoundException e) {
        }
        // Create player name
        System.out.println("Great! Your Username is " + userAcc + " and your Password is " + userPass);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(userAcc + ".txt"));
            out.write(userPass + "\n");
            out.close();
        } catch (IOException e) {
        }
        startGame();
    }
    //How to play the game
    public static void instructions() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("******** INSTRUCTIONS ********");
        System.out.println("Press enter to get the next instruction.");
        userInput.nextLine();
        System.out.println("There are 26 cases");
        userInput.nextLine();
        System.out.println("Please choose a case you think has the $200,000 in it");
        userInput.nextLine();
        System.out.println("Next you will have to select 6 cases to elimnate");
        userInput.nextLine();
        System.out.println("The banker will then offer you a deal and you can either choose to take it or continue playing to win more.");
        userInput.nextLine();
        System.out.println("The process is completed until you only have two cases left including yours");
        userInput.nextLine();
        System.out.println("You will then open your case and it will reveal how much you have won");
        userInput.nextLine();
        System.out.println("Pick wisely and know when to take the DEAL!");
        userInput.nextLine();
        System.out.println("Lets Play DEAL OR NO DEAL!!");

    }
    //welcome message
    public void welcome() {

        System.out.println("------------------------------------------------");
        System.out.println("---------Welcome to Deal or No Deal!!-----------");
        System.out.println("---------- Hosted by James Oliver --------------");
        System.out.println("---- Please Select from the Following Cases!----");
        System.out.println("------------------------------------------------");
    }
    //set up and start the game
    public void startGame() {

        boolean gamestatus = true;
        enterName();
        casesSetup();
        welcome();
        instructions();
        showCases();
        
        //get users choice for their case
        int choice = player.user();
        myAmount = briefcase[choice].getAmount();
        briefcase[choice] = null;
        cases--;
        
        //check how many cases remain and how many user needs to remove
        while (gamestatus == true) {
            showCases();
            if (cases == 25 || cases == 19 || cases == 14 || cases == 10
                    || cases == 7) {
                for (int i = casesToRemove; i > 0; i--) {
                    int remove = player.removeCase(i, briefcase);
                    briefcase[remove] = null;
                    cases--;
                }
                casesToRemove--;
                turn++;
                banker.setOffer(turn, briefcase, myAmount);
                offer = banker.getOffer(turn, briefcase, myAmount);
                gamestatus = player.gameStatus();
            } else if (cases == 1) {
                int remove = player.removeCase(1, briefcase);
                briefcase[remove] = null;
                gamestatus = false;
            } else {
                int remove = player.removeCase(1, briefcase);
                briefcase[remove] = null;
                cases--;
                banker.setOffer(turn, briefcase, myAmount);
                offer = banker.getOffer(turn, briefcase, myAmount);
                gamestatus = player.gameStatus();
            }
        }
        finishgame();
    }
    //display how much the user won
    public void finishgame() {
        
        //round to a whole number
        offerString = Integer.toString((int) (Math.round(offer)));
        myWinnings = Integer.toString((int) (Math.round(myAmount)));
        if (cases == 1) {
            System.out.println("You Rejected the Offer of Banker");
            System.out.println("Congratulations you won: $" + myWinnings);
            System.out.println("Congratulations you won: $" + myWinnings);

        } else {
            System.out.println("You Accepted the offer of Banker");
            System.out.println("Congratulations you won: $" + myWinnings);
            System.out.println("\"Congratulations you won: $" + offerString);
        }
    }

    public static void main(String[] args) {
        DealOrNoDeal dnd = new DealOrNoDeal();
        dnd.startGame();

    }

}
