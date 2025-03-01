import java.util.ArrayList;
import java.util.List;

public class GameGrid {
    private final String[][] frame = new String[11][11];

    public GameGrid() {
        initializeFrame();
    }

    private void initializeFrame() {
        frame[0][0] = " ";
        for(int i = 1; i < 11; i++) {
            frame[0][i] = String.valueOf(i);
        }
        for(int j = 1; j < 11; j++) {
            frame[j][0] = String.valueOf((char) ('A' + j - 1));
        }

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                frame[i][j] = "~";
            }
        }
    }

    public void copyPositionValueTo(GameGrid copyGrid, Position p) {
        int row = p.getIntRow();
        int column = p.getIntColumn();
        copyGrid.frame[row][column] = this.frame[row][column];
    }

    public boolean setShotPosition(Position position) {
        int row = position.getIntRow();
        int column = position.getIntColumn();
        if(frame[row][column].equals("O") || frame[row][column].equals("X") ) {
            frame[row][column] = "X";
            return true;
        }
        else {
            frame[row][column] = "M";
            return false;
        }
    }

    public void setPositionOnFrame(String symbol, Position position) {
        int row = position.getIntRow();
        int column = position.getIntColumn();
        frame[row][column] = symbol;
    }

    public void printFrame() {
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                System.out.print(frame[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public List<String> setNoZone(Position p) {
        List<Position> noZonePositionList = new ArrayList<>();
        List<String> noZoneStringList = new ArrayList<>();
        for(int i = -1; i <= 1; i ++) {
            for(int j = -1; j <= 1; j++) {
                noZonePositionList.add(new Position(p.getIntRow() + i, p.getIntColumn() + j));
            }
        }
        for(Position position : noZonePositionList) {
            //System.out.print(position.toString() + " ");
            noZoneStringList.add(position.toString());
        }
        return noZoneStringList;
    }

    public static void main(String[] args) {
        GameGrid grid = new GameGrid();
        grid.printFrame();
        Position p = new Position("C2");
        grid.setNoZone(p);
        grid.setPositionOnFrame("O", p);
        grid.setShotPosition(p);
        grid.printFrame();
    }
}
