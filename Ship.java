import java.util.List;

public class Ship {
    private String name;
    private List<Position> cells;

    public Ship() { }   
    public Ship(List<Position> cells) {
        this.cells = cells;
    }

    public List<Position> getCells() {
        return cells;
    }

    public void setCells(List<Position> cells) {
        this.cells = cells;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Position p : cells) {
            builder.append(p).append(" ");
        }
        return builder.toString();
    }
}
