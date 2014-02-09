package models;

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

  //Questioning if we should save ownerColor or not. When creating the Developer
  //objects, we would already be in the Player that owns the Developer...
  public String serialize() {
	  /*This creates a string that represents a Developer object for saving and loading
	   *
	   * The boolean isPlacedOnBoard is stored. toString() for a boolean either returns "True" or "False"
	   * so it needs to be convered to "true" and "false" so that loading is easier.
	   * 
	   * currentCell will be saved using it's (x,y) coordinatesm in currentCellX and currentCellY .
	   *  This will make the Cell unique
	   * 
	   * The owner should (I hope) be unique by the player's color, so that will be saved this way..
	   * We may not even need to save this, since the Developer should be made with the corresponding
	   * Player who owns it... anyways :P
	   */
	    return Json.jsonPair("Developer", Json.jsonObject(Json.jsonMembers(
	    		Json.jsonPair("isPlacedOnBoard", Json.jsonValue(isPlacedOnBoard.toString().toLower())),
	    		Json.jsonPair("currentCellX", Json.jsonValue(currentCell.getX() + "")), 
	    		Json.jsonPair("currentCellY", Json.jsonValue(currentCell.getY() + "")),
	    		Json.jsonPair("ownerColor", Json.jsonValue(owner.getColor().toString()))
	    		)));
  }

  public Developer loadObject(String serial) {
    // TODO Auto-generated method stub
    return null;
  }
}

