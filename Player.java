public class Player {
    private String name;
    private BattleshipGame game;

    public Player(String name, BattleshipGame game) {
        this.game = game;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public BattleshipGame getGame() {
        return game;
    }

}
