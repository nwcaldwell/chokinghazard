package models;

import helpers.Json;
import helpers.JsonObject;

class TwoSpaceTile extends Tile {
	private String imageSource = "src/resources/twoTile.png";
  
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
	
	public String getImageSource(){
		return this.imageSource;
	}
	
	public String toString(){
    	return "TWO SPACE TILE";
    }

	public String serialize() {
        return Json.jsonObject(Json.jsonMembers(
            Json.jsonPair("spaces", Json.serializeArray(spaces))
        ));
	}

	public TwoSpaceTile loadObject(JsonObject json) {
    	Space[][] spaces = new Space[2][2];
    	JsonObject[][] objects = (JsonObject[][]) (Object) json.getJsonObject("spaces");
    	for(int x = 0; x < 4; ++x)
    		spaces[x/2][x%2] = (new Space()).loadObject(objects[x/2][x%2]);
    	this.spaces = spaces;
    	return this;
	}
}

