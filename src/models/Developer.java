package models;

class Developer implements Serializable<Developer>{
  private boolean isPlacedOnBoard;
  private Cell currentCell;
  private Player owner;
  
  public Developer(Player owner) {
	  this.owner = owner;
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
	    return Json.jsonPair("Developer", Json.jsonObject(Json.jsonMembers(
	    		Json.jsonPair("isPlacedOnBoard", Json.jsonValue(isPlacedOnBoard.toString().toLower())),
	    		currentCell.serialize(), 
	    		owner.serialize()
	    		)));
  }

  public Developer loadObject(String serial) {
    // TODO Auto-generated method stub
    return null;
  }
}

