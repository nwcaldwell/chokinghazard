package models;

import helpers.Json;
import helpers.JsonObject;

public class Developer implements Serializable<Developer>{
  
  private boolean isPlacedOnBoard;
  private Cell currentCell;
  private Player owner;
  
  public Developer(Player owner, Cell currentCell) {
	  this.owner = owner;
	  this.currentCell = currentCell;
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
    this.isPlacedOnBoard = true;
  }
  
  public String toString(){
	  return "DEVELOPER";
  }

  public String serialize() {
	  /*This creates a string that represents a Developer object for saving and loading
	   * 
	   * currentCell will be saved using it's (x,y) coordinatesm in currentCellX and currentCellY .
	   *  This will make the Cell unique
	   * 
	   */
	    return Json.jsonPair("Developer", Json.jsonObject(Json.jsonMembers(
	    		Json.jsonPair("isPlacedOnBoard", Json.jsonValue(isPlacedOnBoard + "")),
	    		Json.jsonPair("currentCellX", Json.jsonValue(currentCell.getX() + "")), 
	    		Json.jsonPair("currentCellY", Json.jsonValue(currentCell.getY() + ""))
	    		)));
  	}

	public Developer loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return this;
	}
}
