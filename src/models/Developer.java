package models;

import helpers.JsonObject;

public class Developer implements Serializable<Developer>{
  private boolean isPlacedOnBoard;
  private Cell currentCell;
  private Player owner;
  
  public Developer(Player owner) {
	  this.owner = owner;
	  this.isPlacedOnBoard = false;
  }
  
  public Player getOwner() {
	  return owner;
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

  public Developer loadObject(JsonObject json) {
    // TODO Auto-generated method stub
    return null;
  }
}

