package models;

import helpers.Json;
import helpers.JsonObject;

public class Developer implements Serializable<Developer>{
  
  private boolean isPlacedOnBoard;
  private Cell currentCell;
  private Player owner;
  //these methods only used to make the loadObject method bearable. Was created after the implementation of the logic in the game so yeah....
  //these dont get updated...
  private int currentCellX;
  private int currentCellY;
  
  public Developer(Player owner, Cell currentCell) {
	  this.owner = owner;
	  this.currentCell = currentCell;
  }
  
//these methods only used to make the loadObject method bearable. Was created after the implementation of the logic in the game so yeah....
  public int getCurrentCellX(){
	  return currentCellX;
  }
  
//these methods only used to make the loadObject method bearable. Was created after the implementation of the logic in the game so yeah....
  public int getCurrentCellY(){
	  return currentCellY;
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
	  //return "DEVELOPER";
	  return isPlacedOnBoard + " " + currentCell.getClass() + " " + owner.getPlayerColor();
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


@Override
public Developer loadObject(JsonObject json) {
	isPlacedOnBoard = Boolean.parseBoolean(json.getString("isPlacedOnBoard"));
	currentCellX = Integer.parseInt(json.getString("currentCellX"));
	currentCellY = Integer.parseInt(json.getString("currentCelly"));
	return this;
}

}
