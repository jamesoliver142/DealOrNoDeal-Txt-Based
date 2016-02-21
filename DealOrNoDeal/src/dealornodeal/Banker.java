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
public class Banker {

    private double total = 0;
    private int a = 0;
    private double offer =0;
    double Average = 0;
    String offerString;

    //Calculate the offer
    public void setOffer(int turn, Case[] cases, double myAmount) {

        for (int i = 0; i < cases.length; i++) {
            if (cases[i] == null) {
            } else {
                total = total + cases[i].getAmount();
                a++;
            }
        }
        //algorithm for the deal or no deal calculation
        Average = myAmount+total / a;
        offer = Average*turn/ 10;
         offerString = Integer.toString((int) (Math.round(offer)));

    }
    
    public double getOffer(int turn, Case[] cases, double myAmount) {
        setOffer(turn, cases, myAmount);
        System.out.println("The Bankers Offer is: $" +offerString );
        return offer;
    }
}

