package assignment2;
import java.util.Scanner;
public class Q4 {
	static int[] deck = new int[52];
	static String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
								"10", "Jack", "Queen", "King"};
	static int[] dealer = new int[12];
	static int[] player = new int[12];
	static int playercount = 0;
	static int dealercount = 0;
	static int drawcount = -1; 
	static int count = 0;
	static int bank = 100;
	static int stake = 5;
	  public static void main(String[] args) {  
	    Scanner s = new Scanner(System.in);
		  // Initialize cards
	    for (int i = 0; i < deck.length; i++)
	      deck[i] = i;	
	    System.out.println("Enter s stake you would like to play : ");
	    stake = s.nextInt();
	    setupNewGame();
	    System.out.println(caluclateScore(dealer));
	    System.out.println(caluclateScore(player));
	    char decision = ' ';
	    for (int i = 0; i < player.length; i++) {
	    	 System.out.println("Do you want a new card: Y/N");
	    	 decision = s.next().charAt(0);
	 	    if(decision == 'y' || decision =='Y')
	 	    	hit();
	 	    else{
	 	    	count++;
	 	    	finishDealersPlay();
	 	    	if(caluclateWinnings() == 0){
	 	    		System.out.println("its a DRAW");
	 	    		System .exit(1);
				}
	 	    	else if(caluclateWinnings() == 1){
	 	    		System.out.println("player WON");
	 	    		bank = bank + stake;
	 	    		System.out.println(bank);
	 	    		System .exit(1);
	 	    	}
	 	    	else if(caluclateWinnings() == -1){
	 	    		System.out.println("dealer WON");
	 	    		bank = bank - stake;
	 	    		System.out.println(bank);
	 	    		System .exit(1);
	 	    	}
	 	    }
	 	    		
		}
	   
	  }
	  
	  public static int drawCard(){
		  drawcount ++;
		  return deck[drawcount];	  
	  }
	  public static void shuffle(){
		  for (int i = 0; i < deck.length; i++) {
		      // Generate an index randomly
		      int index = (int)(Math.random() * 52);
		      int temp = deck[i];
		      deck[i] = deck[index]; 
		      deck[index] = temp;
		    }
	  }
	  public static void setupNewGame(){
		   // Shuffles the cards
		  shuffle();
		  int count = 0;
		  System.out.println("The cards of dealer are : ");
		  while(count < 2){
			  int i = drawCard();
			  int rank = (i % 13) + 1;
			  if(rank >=10)
				  rank = 10;
			  if(rank == 1)
				  rank = 11;
			  dealer[dealercount++] = rank; 
			  printHand(dealer);
			  if(caluclateWinnings() == 0){
				  System.out.println("its a DRAW");
				  System.out.println(bank);
				  System .exit(1);
			  }
			  else if(caluclateWinnings() == 1){
				  System.out.println("player WON");
				  bank = bank + stake;
				  System.out.println(bank);
				  System .exit(1);
			  }
			  else if(caluclateWinnings() == -1){
				  System.out.println("dealer WON");
				  bank = bank - stake;
				  System.out.println(bank);
				  System .exit(1);
			  }
			  count ++;
		  }
		  System.out.println("The cards of the player are : ");
		  hit();
		  hit();
		   
	  }
	  public static void hit(){
		  int i = drawCard();
		  int rank = (i % 13) + 1;
		  if(rank >10)
			  rank = 10;
		  if(caluclateScore(player) <= 10){
			  if(rank == 1)
				  rank = 11;
		  }
		 player[playercount++] = rank;
		 printHand(player);	
		 if(caluclateWinnings() == 0){
			 System.out.println("its a DRAW");
			 System.out.println(bank);
			 System .exit(1);
		 }
		 else if(caluclateWinnings() == 1){
			 System.out.println("player WON");
			 bank = bank + stake;
			 System.out.println(bank);
			 System .exit(1);
		 }
		 else if(caluclateWinnings() == -1){
			 System.out.println("dealer WON");
			 bank = bank - stake;
			 System.out.println(bank);
			 System .exit(1);
		 }
	  }
	  public static void printHand(int[] a){
		  String suit = suits[deck[drawcount] / 13];
		  String rank = ranks[deck[drawcount] % 13];
		  System.out.println(" " + rank + " of " + suit);
		  
	  }
	  public static int caluclateScore(int[] hand){
		  int sum = 0;
		  if(hand == dealer)
			  for (int i = 0; i < dealercount; i++) {
				sum = sum + hand[i];
			}
		  else
			  for (int i = 0; i < playercount; i++) {
					sum = sum + hand[i];
				}

		  return sum;	  
	  }
	  public static void finishDealersPlay(){
		 while(caluclateScore(dealer) < 17){
			 int i = drawCard();
			  int rank = (i % 13) + 1;
			  if(rank >10)
				  rank = 10;
			  if(rank == 1)
				  rank = 11;
			  dealer[dealercount++] = rank;  
			  printHand(dealer);
			  if(caluclateWinnings() == 0){
				  System.out.println("its a DRAW");
				  System.out.println(bank);
				  System .exit(1);
			  }
			  else if(caluclateWinnings() == 1){
				  System.out.println("player WON");
				  bank = bank + stake;
				  System.out.println(bank);
				  System .exit(1);
			  }
			  else if(caluclateWinnings() == -1){
				  System.out.println("dealer WON");
				  bank = bank - stake;
				  System.out.println(bank);
				  System .exit(1);
			  }

		 }
			 
	  }
	  public static int caluclateWinnings(){
		  if(caluclateScore(dealer) > 21){
			  return 1;
		  }
		  if(caluclateScore(dealer) == 21){
			  return -1;
		  }
		  if(caluclateScore(player) > 21){
			  return -1;
		  }
		  if(caluclateScore(player) == 21){
			  return 1;
		  }
		  if(count == 1){
			  if(caluclateScore(dealer) > caluclateScore(player))
				  return -1;
			  else if(caluclateScore(dealer) < caluclateScore(player))
				  return 1;
			  else
				  return 0;
		  }
		  else
			  return 2;
			  
}
	 
 }
