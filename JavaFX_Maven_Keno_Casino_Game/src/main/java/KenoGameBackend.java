import java.util.Random;
import java.util.ArrayList;

public class KenoGameBackend{
    // Default draws are 20, Default bet amount is $1, Default won is $0
    protected static int totalDraws = 20, betAmount = 1, totalWinnings = 0, drawCount = 0;
    // Will store the 20 randomly drawn numbers
    protected static int[] drawResult = new int[20];
    // Will store the users selected spots
    protected static ArrayList<String> userInputs = new ArrayList<>();
    // Will Store the matches between user selection and draw results
    protected static ArrayList<String> matchedStore = new ArrayList<>();

    // Ensures # of user selected buttons = # of spots in game (1/4/8/10)
    static boolean verifySpotCount(String inputText) {
        int nNodes = userInputs.size();
        if ((inputText == "1" && nNodes == 1) || (inputText == "4" && nNodes == 4) || (inputText == "8" && nNodes == 8)
                || (inputText == "10" && nNodes == 10)) {
            return true;
        }
        return false;
    }

    // Stores user selections/random selections into userInputs
    static void storeSpots(String text, boolean add) {
        if (add) {
            userInputs.add(text);
        } else {
            userInputs.remove(text);
        }
    }



    // Setter for the # of draws
    static void setterNumDraws(int value) {
        totalDraws = value;
    }



    // Getter for the number of remaining draws
    static int getterNumDraws() {
        return totalDraws;
    }

    // Getter to return the current draw
    static int getterCurrentDraw() {
        return drawCount;
    }

    // Setter to increment draw count when a draw has completed
    static void incrementDraw() {
        drawCount++;
    }

    // Setter to decrement the # of draws
    static void decrementDraw() {
        totalDraws--;
    }

    // Setter value for the bet amount. FIXED AT $1, but expandable functionality (any $ amount)
    static void setUserBet(int value) {
        betAmount = value;
    }

    // Getter for the amount the user has bet, fixed at $1
    static String getUserBet() {
        return Integer.toString(betAmount);
    }

    // Execution of the 20, at-random draws
    static void executeRandomDrawings() {
        int[] playableNumbers = new int[80];  // Total drawable numbers
        for (int i = 0; i < 80; ++i) {  // Fill array with all possible Keno matchings/drawings
            playableNumbers[i] = i + 1;
        }
        Random rand = new Random();
        // Swap Array values at random
        for (int i = 0; i < playableNumbers.length; ++i) {
            int indexShuffle = rand.nextInt(playableNumbers.length);
            int tempShuffle = playableNumbers[indexShuffle];
            playableNumbers[indexShuffle] = playableNumbers[i];
            playableNumbers[i] = tempShuffle;
        }

        //  Add 20 randomly selected values into the drawResult
        for (int i = 0; i < 20; i++) {
            drawResult[i] = playableNumbers[i];
        }

    }


     // Returns a draw at the specified index
    static String getDrawResultAtIndex(int index) {
        return Integer.toString(drawResult[index]);
    }

    // Re-initializes the game, clearing out all old values
    static void reinitializeKenoGame() {
        totalDraws = 20;
        betAmount = 1;
        drawCount = 0;
        totalWinnings = 0;
        userInputs.clear();
        matchedStore.clear();
    }

    // Resets the matched store holding the matched values ONLY
    // Enables the matchedStore array to be used in future drawings
    static void clearMatchedStore() {
        matchedStore.clear();
    }

    // Returns amount won by the user in a single draw
    static String getterAmountWon() {
        String winnings;
        for (int i = 0; i < 20; ++i) {
            matchedStore.add(i, Integer.toString(drawResult[i]));
        }
        matchedStore.retainAll(userInputs); // Remove all non-matches
        // Tabulate winnings from the remaining matches in matchedStore, based on the number of matches and
        // number of spots played.
        winnings = KenoResultsTabulation.kenoWinningTabulation(userInputs.size(), matchedStore.size(), betAmount);
        totalWinnings += Integer.parseInt(winnings); // Increment the total winnings for the user
        return winnings;
    }

    // Option to select random input for the user
    static void provideRandomInput(String providedText) {
        userInputs.clear(); // No need with random inputs
        int[] randomNumbers = new int[80];

        for (int i = 0; i < 80; ++i) {
            randomNumbers[i] = i + 1;
        }
        Random rand = new Random();

        // Randomly swap array values
        for (int i = 0; i < randomNumbers.length; ++i) {
            int index = rand.nextInt(randomNumbers.length);
            int temp = randomNumbers[index];
            randomNumbers[index] = randomNumbers[i];
            randomNumbers[i] = temp;
        }
        for (int i = 0; i < Integer.parseInt(providedText); ++i) {
            userInputs.add(i, Integer.toString(randomNumbers[i]));
        }
    }

    // Matched items are returned in string format
    static String getMatches() {
        if (matchedStore.size() == 0) {
            return "N/A";
        }
        StringBuilder matchedString = new StringBuilder();
        for (String stringChar : matchedStore)
            matchedString.append(stringChar + ", ");

        return matchedString.toString();
    }

    // User selection/random selections returned in string format
    static String getSelections() {
        StringBuilder selectionsString = new StringBuilder();
        for (String stringChar : userInputs)
            selectionsString.append(stringChar + ", ");
        return selectionsString.toString();
    }

    // Getter to return the number of matches per draw
    static String getterNumberMatched() {
        return Integer.toString(matchedStore.size());
    }

    // Getter for the total winnings in a game of Keno
    static String getterTotalWinnings() {
        return Integer.toString(totalWinnings);
    }

}

