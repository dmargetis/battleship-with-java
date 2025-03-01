
import java.util.Scanner;

public class MultiplayerGame {
    Player player1;
    Player player2;
    Scanner scanner = new Scanner(System.in);

    public void run() {
        player1 = new Player("Player 1", new BattleshipGame());
        player2 = new Player("Player 2", new BattleshipGame());

        setGame(player1, player2);
        setGame(player2, player1);

        while(!player1.getGame().areAllShipsSunk() && !player2.getGame().areAllShipsSunk()) {
            turn(player1, player2);
            turn(player2, player1);
        }
    }

    public void setGame(Player player, Player opponent) {
        System.out.printf("%s, place your ships on the game field%n", player.getName());
        opponent.getGame().init();
        System.out.println("Press Enter and pass the move to another player");
        String input = scanner.nextLine();
        System.out.println("...");

    }

    public void turn(Player player, Player opponent) {
        player.getGame().fogOfWarGrid.printFrame();
        System.out.println("------------------------");
        opponent.getGame().gameGrid.printFrame();
        System.out.printf("%s, it's your turn:%n", player.getName());
        player.getGame().play();
        System.out.println("Press Enter and pass the move to another player");
        String input = scanner.nextLine();
        for(int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
