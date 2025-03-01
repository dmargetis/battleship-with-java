
public class Position {
    private final String row;
    private final String column;

    public Position(String row, String column) {
        this.row = row;
        this.column = column;
    }

    public Position(int intRow, int intColumn) {
        this.row = String.valueOf((char) ('A' + intRow - 1));
        this.column = String.valueOf(1 + intColumn - 1);
    }

    public Position(String coordinates) {
        this.row = coordinates.substring(0,1);
        if(coordinates.length() == 2) {
            this.column = coordinates.substring(1, 2);
        }
        else
            this.column = coordinates.substring(1, 3);
    }

    public boolean equals(Object aPosition) {
        Position other = (Position) aPosition;
        return this.row.equals(other.row) && this.column.equals(other.column);
    }

    public int hashCode() {
        return row.hashCode() + column.hashCode();
    }


    public int getIntRow() {
        return row.charAt(0) - 64;
    }

    public int getIntColumn() {
        return Integer.parseInt(column);
    }

    public String toString() {
        return this.row + this.column;
    }

    public static int getLength(Position one, Position two) {
        if(one.row.equals(two.row)) {
            return 1 + Math.abs(one.getIntColumn() - two.getIntColumn());
        }
        else {
            return 1 + Math.abs(one.getIntRow() - two.getIntRow());
        }
    }


    public static String getParts(Position one, Position two) {
        if(one.row.equals(two.row)) {
            return one.getHorizontalParts(two);
        } else
            return one.getVerticalParts(two);
    }

    public String getHorizontalParts(Position other) {

        int start = Integer.parseInt(this.column);
        int end = Integer.parseInt(other.column);
        if(start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        String r = this.row;
        StringBuilder sb = new StringBuilder();
        for(int i = start; i <= end; i++) {
            sb.append(r).append(i).append(" ");
        }
        return sb.toString();
    }

    public String getVerticalParts(Position other) {
        char start = this.row.charAt(0);
        char end = other.row.charAt(0);
        if(start > end) {
            char temp = start;
            start = end;
            end = temp;
        }
        int col = Integer.parseInt(this.column);
        StringBuilder sb = new StringBuilder();
        for(char c = start; c <= end; c++) {
            sb.append(String.format("%c%d ", c, col));
        }
        return sb.toString();
    }

    public static boolean validatePosition(Position position) {
        boolean valid = true;
        int column = position.getIntColumn();
        char row = position.row.charAt(0);

        if(column < 1 || column > 10) {
            valid = false;
        }
        else if(row < 'A' || row > 'J') {
            valid = false;
        }
        return valid;

    }

    public static boolean validatePositions(Position one, Position two) {
        boolean valid = true;
        int oneColumn = one.getIntColumn();
        int twoColumn = two.getIntColumn();

        char oneRow = one.row.charAt(0);
        char twoRow = two.row.charAt(0);
        if(oneColumn < 1 || oneColumn > 10 || twoColumn < 1 || twoColumn > 10) {
            valid = false;
        }
        else if(oneRow < 'A' || oneRow > 'J' || twoRow < 'A' || twoRow > 'J') {
            valid = false;
        }
        else if(!one.row.equals(two.row) && !one.column.equals(two.column))
            valid = false;
        return valid;
    }

    public static void main(String[] args) {
        Position position = new Position(3,4);
        System.out.println(position);
    }
}

