
package tree;

public class PlayerNode {

    private int playerID;
    private String Playername;
    private int stamina;
    private PlayerNode right;
    private PlayerNode left;
    
    public PlayerNode() {
       stamina=15;
    }
      
    public PlayerNode(int playerID, String Playername) {
        this.playerID = playerID;
        this.Playername = Playername;
        this.stamina =15;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPlayername(String Playername) {
        this.Playername = Playername;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setRight(PlayerNode right) {
        this.right = right;
    }

    public void setLeft(PlayerNode left) {
        this.left = left;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayername() {
        return Playername;
    }

    public int getStamina() {
        return stamina;
    }

    public PlayerNode getRight() {
        return right;
    }

    public PlayerNode getLeft() {
        return left;
    }

    @Override
    public String toString() {
        return  playerID + "   " + Playername + "   " + stamina ;
    }

   
    
    
}
