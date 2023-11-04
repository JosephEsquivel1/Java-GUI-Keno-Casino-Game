//
// Source: https://nclottery.com/KenoHow
// This class is responsible for tabulating the users earnings based on
// the Carolina Keno earnings listed on the original site.
//
public class KenoResultsTabulation {
    // This function computes the earnings based on the winnings from Carolina Keno
    // and the users selected number of spots, number of spot matches, and their $1 fixed bet.
    public static String kenoWinningTabulation(int numSpots, int numMatched, int amountBet) {
        int earningAmount = 0;
        switch (numSpots) {
            case 1:
                switch (numMatched) {
                    case 1:
                        earningAmount = 2;
                        break;
                    default:
                        earningAmount = 0;
                        break;
                }
                break;
            case 4:
                switch (numMatched) {
                    case 2:
                        earningAmount = 1;
                        break;
                    case 3:
                        earningAmount = 5;
                        break;
                    case 4:
                        earningAmount = 75;
                        break;
                    default:
                        earningAmount = 0;
                        break;
                }
                break;
            case 8:
                switch (numMatched) {
                    case 4:
                        earningAmount = 2;
                        break;
                    case 5:
                        earningAmount = 12;
                        break;
                    case 6:
                        earningAmount = 50;
                        break;
                    case 7:
                        earningAmount = 750;
                        break;
                    case 8:
                        earningAmount = 10000;
                        break;
                    default:
                        earningAmount = 0;
                        break;
                }
                break;
            case 10:
                switch (numMatched) {
                    case 0:
                        earningAmount = 5;
                        break;
                    case 5:
                        earningAmount = 2;
                        break;
                    case 6:
                        earningAmount = 15;
                        break;
                    case 7:
                        earningAmount = 40;
                        break;
                    case 8:
                        earningAmount = 450;
                        break;
                    case 9:
                        earningAmount = 4250;
                        break;
                    case 10:
                        earningAmount = 100000;
                        break;
                    default:
                        earningAmount = 0;
                        break;
                }
                break;
            default:
                earningAmount = 0;
                break;
        }
        return Integer.toString(earningAmount * amountBet);
    }
}
