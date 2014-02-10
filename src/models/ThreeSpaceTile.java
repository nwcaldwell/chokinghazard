

package models;

import helpers.JsonObject;

import java.util.LinkedList;

class ThreeSpaceTile extends Tile {
	private String imageSource = "src/resources/threeTile.png";
	
	public ThreeSpaceTile(Space[][] spaces) {
	    super(spaces);
	    spaces[0][0] = new RiceSpace();
	    spaces[0][1] = new VillageSpace();
	    spaces[1][1] = new RiceSpace();
	}
    
    //rotates the piece.
	public void rotate() 
   {
		if(spaces[0][0] != null && spaces[0][1]!= null && spaces[1][1] != null)
      {
         spaces[1][0] = spaces[1][1];
         spaces[1][1] = spaces[0][1];
         spaces[0][1] = spaces[0][0]; 
         spaces[0][0] = null;
      }
      if(spaces[0][1] != null && spaces[1][1] != null && spaces[1][0] != null)
      {
         spaces[0][0] = spaces[1][0];
         spaces[1][1] = spaces[0][1];
         spaces[1][0] = spaces[1][1];
         spaces[0][1] = null;
      }
      if(spaces[1][1] != null && spaces[1][0] != null && spaces[0][0] != null)
      {
         spaces[0][1] = spaces[0][0];
         spaces[1][0] = spaces[1][1];
         spaces[0][0] = spaces[1][0];
         spaces[1][1] = null;
      }
      if(spaces[0][0] != null && spaces[1][0] != null && spaces[0][1] != null)
      {
         spaces[1][1] = spaces[0][1];
         spaces[0][1] = spaces[0][0];
         spaces[0][0] = spaces[1][0];
         spaces[1][0] = null;
      }
         
	}

	public String getImageSource(){
		return this.imageSource;
	}
	
	public String toString(){
    	return "THREE SPACE TILE";
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public ThreeSpaceTile loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}

