package finaceTrackerApp;
import java.util.Scanner;
public class HomePage extends Functions{


    public void homePage(String userName) {
        LoginPage lp = new LoginPage();
        Scanner ss = new Scanner(System.in);

        System.out.println("1. New Category");
        System.out.println("2. List Categories");
        System.out.println("3. Add Transaction");
        System.out.println("4. View Transactions");
        System.out.println("5. Log Out");
        System.out.println("Choose your Option");
        int ch = ss.nextInt();

        switch (ch) {
            case 1: {
                newCategory(userName);
                homePage(userName);
                break;
            }
            case 2: {
                viewList(userName);
                homePage(userName);
                break;
            }
            case 3: {
                addTransaction(userName);
                homePage(userName);
                break;
            }
            case 4: {
                viewTransaction(userName);
                homePage(userName);
                break;
            }
            case 5: {
                lp.registerOrLoginPage();
                break;
            }
            default:{
                System.out.println("Choose right option!!!");
                homePage(userName);
                break;
            }
        }
    }
}
