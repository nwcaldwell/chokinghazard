package models;

import java.util.LinkedList;

class TwoSpaceTile extends Tile {
  
	public TwoSpaceTile(Space[][] spaces) {
		super(spaces);
		spaces[0][0] = new RiceSpace();
      spaces[0][1] = new VillageSpace();
	}
	 
   //rotates the piece.
	public void rotate() 
   {
		if(spaces[0][0] != null && spaces[0][1]!= null)
      {
         spaces[1][1] = spaces[0][1];
         spaces[0][1] = spaces[0][0];
         spaces[0][0] = null;
      }
      if(spaces[0][1] != null && spaces[1][1] != null)
      {
         spaces[1][1] = spaces[0][1];
         spaces[1][0] = spaces[1][1];
         spaces[0][1] = null;
      }
      if(spaces[1][1] != null && spaces[1][0] != null)
      {
         spaces[1][0] = spaces[1][1];
         spaces[0][0] = spaces[1][0];
         spaces[1][1] = null;
      }
      if(spaces[0][0] != null && spaces[1][0] != null)
      {
         spaces[0][1] = spaces[0][0];
         spaces[0][0] = spaces[1][0];
         spaces[1][0] = null;
      }
         
	}

	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	public TwoSpaceTile loadObject(String serial) {
		// TODO Auto-generated method stub
		return null;
	}
}

