package models;



public class Developer implements Serializable<Developer>{
    private boolean isPlacedOnBoard;
    private Cell currentCell;
    
    public Developer() {

    }

    public boolean isPlacedOnBoard() {
        return isPlacedOnBoard;
    }

    public void setPlacedOnBoard(boolean isPlacedOnBoard) {
        this.isPlacedOnBoard = isPlacedOnBoard;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public Developer loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
