import java.util.*;

public class BattleshipGame {
    GameGrid gameGrid;
    GameGrid fogOfWarGrid;
    Scanner scanner = new Scanner(System.in);
    String currentCoordinates;
    Position currentShot;
    Set<String> noZoneSet = new HashSet<>();
    List<Ship> ships = new ArrayList<>();
    int sunkShipsCounter;

    public void init() {
        gameGrid = new GameGrid();
        fogOfWarGrid = new GameGrid();
        gameGrid.printFrame();

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        setShipOnGrid("Aircraft Carrier",5);

        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        setShipOnGrid("Battleship", 4);

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        setShipOnGrid("Submarine", 3);

        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        setShipOnGrid("Cruiser", 3);

        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        setShipOnGrid("Destroyer", 2);

        //System.out.println("The game starts!");
        //fogOfWarGrid.printFrame();

    }

    public void play() {
        takeAShot();
        //fogOfWarGrid.printFrame();
        if(areAllShipsSunk()) {
            System.out.println("You sank the last ship. You won. Congratulations!");
        }
    }

    public void printShips() {
        for(Ship ship : ships) {
            System.out.println(ship);
        }
    }

    public void updateHitOnShips(Position hitPosition) {
        List<Position> positionsToBeRemovedList = new ArrayList<>();
        for(Ship ship : ships) {
            for(Position position : ship.getCells()) {
                if(position.equals(hitPosition)) {
                    positionsToBeRemovedList.add(position);
                }
            }
        }
        for(Ship ship : ships) {
            for (Position p : positionsToBeRemovedList) {
                ship.getCells().remove(p);
            }
        }
    }

    public void takeAShot() {
        System.out.println("Take a shot!");
        do {
            currentShot = new Position(scanner.nextLine());
            if(!Position.validatePosition(currentShot)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        } while(!Position.validatePosition(currentShot));
        boolean hit = gameGrid.setShotPosition(currentShot);
        gameGrid.copyPositionValueTo(fogOfWarGrid, currentShot);
        //fogOfWarGrid.printFrame();

        if(hit) {
            updateHitOnShips(currentShot);
            //printShips();
            if (isAShipSunk() && !areAllShipsSunk()) {
                System.out.println("You sank a ship!");
            }
            else if(!areAllShipsSunk()) {
                System.out.println("You hit a ship!\n");
            }
        }
        else {
            System.out.println("You missed!\n");
        }
    }

    public boolean areAllShipsSunk() {
        return sunkShipsCounter == 5;
    }

    public boolean isAShipSunk() {
        Ship shipToRemove = new Ship();
        boolean sunk = false;
        for(Ship ship : ships) {
            if (ship.getCells().isEmpty()) {
                sunk = true;
                sunkShipsCounter++;
                shipToRemove = ship;
            }
        }
        ships.remove(shipToRemove);
        return sunk;
    }

    public void setShipOnGrid(String shipName, int shipLength) {
        do {
            currentCoordinates = scanner.nextLine();
        } while(!validateShipCoordinates(currentCoordinates,shipName, shipLength));
        placeShip(currentCoordinates);
        gameGrid.printFrame();
    }

    public boolean validateShipCoordinates(String coordinates, String shipName, int length) {
        String[] startEndPositions = coordinates.split("\\s");
        Position startPosition = new Position(startEndPositions[0]);
        Position endPosition = new Position(startEndPositions[1]);
        int currentLength = Position.getLength(startPosition, endPosition);
        String[] parts = Position.getParts(startPosition, endPosition).split("\\s");
        if(!Position.validatePositions(startPosition,endPosition)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        else if(currentLength != length) {
            System.out.printf("Error! Wrong length of the %s! Try again:%n", shipName);
            return false;
        }
        for(String part : parts) {
            if(noZoneSet.contains(part)){
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }
        return true;
    }

    public void placeShip(String coordinates) {
        String[] startEndPositions = coordinates.split("\\s");
        Position startPosition = new Position(startEndPositions[0]);
        Position endPosition = new Position(startEndPositions[1]);
        String[] parts = Position.getParts(startPosition, endPosition).split("\\s");
        List<Position> cells = new ArrayList<>();
        for(String part : parts) {
            cells.add(new Position(part));
        }
        ships.add(new Ship(cells));
        for(String part : parts) {
            Position currentPosition = new Position(part);
            gameGrid.setPositionOnFrame("O", currentPosition);
            noZoneSet.addAll(gameGrid.setNoZone(currentPosition));
        }
    }
}
