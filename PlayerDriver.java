import java.util.Scanner;
public class PlayerDriver {

    static Scanner scan = new Scanner(System.in); //scanner object
    static MyArrayList<Player> playerList = new MyArrayList<Player>(Player[].class); //our list of player objects
    static String[] places = { //places text list for easy reading
        "first", 
        "second", 
        "third",
        "fourth", 
        "fith", 
        "sixth", 
        "seventh", 
        "eighth", 
        "ninth", 
        "tenth" };
    
    public static void main (String [] args){

        int players = 0; //the number of players the user will update
    
        System.out.print("Hello! How many players would you like to enter today? ");
        //call our input function which checks for ints
        players = input();

        System.out.println("\nExcellent. Let’s get started!\n");

        //call a function that loops through the desired player count
        looper(players);

        //call a function that recaps the provided player objects
        recap(players); 

        System.out.println("\nThank you for using our services. Good Bye! ");

    }
    
    //Prompt and check for valid input range of 1 - 10
    public static int input() {
        //base case variable to break out of the outer while loop
        int x = -1;

        //outer while loop to force the user into a valid input range
        while(x < 0 || x >10){
            
            //inner loop to check for valid input type
            while (!scan.hasNextInt()) {
                scan.nextLine(); // Flush the buffer
                System.out.print("Sorry, please enter an integer between 0 and 10: ");
            }
            
            x = scan.nextInt();
    
            //if the input is bad then let the user know
            if( x < 0 || x > 10){
                System.out.print("Please only enter a number between 0 and 10!  ");
            }
            if( x > 9000){
                System.out.print("IT'S OVER 9000!!!!!!!!");
            }
            scan.nextLine(); // Flush the buffer

    }
        //return some good input
        return x;
    }

    //take a crayon input that a normal human would input and translate into the enum index
    public static int translateCrayon(String crayon){
        //change the input to uppercase
        crayon = crayon.toUpperCase(); 

        //loop through the enum thing to match the color
        for (Player.Color colors : Player.Color.values()) {
            
            if(colors.toString().equals(crayon)){
                //return the enum place
                return colors.ordinal();
            }
        }

        //If this code executes then there was no color match
        int test = -1;
        //return -1 so we can prompt the user again
        return test;
    }

    //ensures a valid crayon color is iprovided
    public static void getGoodCrayon(int i){
        //color info
        System.out.print("What is the " + places[i] + " player’s favorite color? ");

        //set our base case to start the while loop
        int colorIndex = -1;

        //this will ask for input and call translateCrayon, the loop will continue if we get bad input
        while(colorIndex == -1){
            colorIndex = translateCrayon(scan.nextLine());
            //let the user know there was bad input
            if(colorIndex == -1){
                System.out.print("You've entered a color unknown to this universe, please try again!! ");
            }
        }
        //set the color to it's index
        playerList.get(i).setColor(colorIndex);
        System.out.println("\nThank you!");

    }

    //ensures hand input is valid
    public static void getGoodHand(int i){
        //base case variable for while loop
        boolean checker = false;

        //Ask for input and only continue if the result is valid
        while(checker == false){
            System.out.print("Is the " + places[i] + " player left- or right-handed? Please enter l or left for left-handed and r or right for right-handed: ");

            checker = playerList.get(i).setHanded(scan.nextLine());
            
            //let the user know there is bad input
            if(checker == false){
                System.out.print("You've entered a hand preferencce unknown to this universe, please try again!!\n");
            }
        }
    }

    //ensures level input type and range is valid
    public static void getGoodLevel(int i){
        //level info
        System.out.print("What level is the " + places[i] + " player? ");
        
        //call the input function to validate the input and set the player level
        playerList.get(i).setLevel(input());

        }

    //this function loops through the player count to create a payer object inside the PlayerList 
    public static void looper(int count){

        if(count == 0){
            System.out.println("You've opted for zero players. Congratulations, you played yourself.\n");
            //end the function and continue with program
            return;
        }

        //loop through the list
        for(int i = 0; i < count; i++){
            //what's the name, no need to validate since we don't discriminate
            System.out.print("What is the " + places[i] + " player's name? ");
            playerList.add(new Player(scan.nextLine()));

            //validate and set hand preference
            getGoodHand(i);
            //validate and set level
            getGoodLevel(i);
            //validate and set color
            getGoodCrayon(i);
        }
    }

    //loops through all of the provided objects and recaps stats
    public static void recap(int count){
        //used for friendly text
        String[] playerCounts = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten"
        };

        if(count == 0){
            System.out.println("According to my records, we have ZERO players in our records.");
            //end the function and continue with program
            return;
        }

        //match our string text to the playerCounts as an index, subtract one since we start at 0
        String countText = playerCounts[count -1];

        System.out.println("According to my records, we have " + countText + " players in our records.");
        for(int i = 0; i < count; i++){
            System.out.println(playerList.get(i).toString());
        }
    }

}
