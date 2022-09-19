package tree;

import java.util.LinkedList;
import java.util.Queue;

public class GameTree {

    public PlayerNode root;

    public GameTree() {
        this.root = null;
    }

    public void add_player(int id, String name) {
        PlayerNode p = new PlayerNode();
        if (root == null) {
            root = p;
            root.setPlayerID(id);
            root.setPlayername(name);

        } else {
            Queue<PlayerNode> list = new LinkedList();
            list.add(root);
            while (true) {
                PlayerNode node = list.remove();
                if (node.getLeft() != null && node.getRight() != null) {
                    if (id < node.getPlayerID()) {
                        list.add(node.getLeft());
                    } else {
                        list.add(node.getRight());
                    }
                } else {
                    if (node.getLeft() == null && node.getRight() == null) {
                        if (id > node.getPlayerID()) {
                            node.setRight(p);
                            node.getRight().setPlayerID(id);
                            node.getRight().setPlayername(name);
                            break;

                        } else {
                            node.setLeft(p);
                            node.getLeft().setPlayerID(id);
                            node.getLeft().setPlayername(name);
                            break;

                        }
                    } else if (node.getRight() == null && id > node.getPlayerID()) {
                        node.setRight(p);
                        node.getRight().setPlayerID(id);
                        node.getRight().setPlayername(name);
                        break;

                    } else if (node.getLeft() == null && id < node.getPlayerID()) {
                        node.setLeft(p);
                        node.getLeft().setPlayerID(id);
                        node.getLeft().setPlayername(name);
                        break;
                    } else {
                        if (node.getLeft() != null && id < node.getPlayerID()) {
                            list.add(node.getLeft());
                        } else if (node.getRight() != null) {
                            list.add(node.getRight());
                        }
                    }
                }
            }

        }
    }

    public void display_all_players_info(PlayerNode p) {
        if (p == null) {
            return;
        } else {
            System.out.println(p.toString());
            if (p.getLeft() != null) {
                display_all_players_info(p.getLeft());
            }
            if (p.getRight() != null) {
                display_all_players_info(p.getRight());
            }

        }

    }

    public PlayerNode display_player_by_ID(PlayerNode r, int key) {
        if (r == null) {
            return null;
        }
        if (r.getPlayerID() == key) {
            return r;
        }
        PlayerNode left = display_player_by_ID(r.getLeft(), key);
        if (left != null) {
            return left;
        }
        PlayerNode right = display_player_by_ID(r.getRight(), key);
        return right;
    }

    public void UPDATE_PLAYER_INFO(int key) {
        PlayerNode player = display_player_by_ID(root, key);
        if (player == null) {
            System.out.println("Not found any player with ID number <" + key + ">");
            return;
        }
        if (player.getStamina() > 5) {
            player.setStamina(player.getStamina() - 5);
            System.out.println(" “the player <" + player.getPlayername()
                    + "> received a hit and the stamina level reduced by 5");
        } else {
            DELETE_PLAYER(key, root);
            System.out.println("The stamina level for the player <" + player.getPlayername() + ""
                    + "> reaches zero and <" + player.getPlayername() + "> left the game!” ");

        }

    }

    public int NUM_OF_PLAYERS(PlayerNode r) {
        if (r == null) {
            return 0;
        }
        return 1 + (NUM_OF_PLAYERS(r.getLeft()) + NUM_OF_PLAYERS(r.getRight()));
    }

    public void DELETE_PLAYER(int key, PlayerNode node) {

        PlayerNode parent = null;

        PlayerNode curr = node;
        while (curr != null && curr.getPlayerID() != key) {
            parent = curr;

            if (key < curr.getPlayerID()) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }

        if (curr == null) {
            return;
        }

        // Case 1: node to be deleted has no children, i.e., it is a leaf node
        if (curr.getLeft() == null && curr.getRight() == null) {
            
            if (curr != root) {
                if (parent.getLeft() == curr) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            else {
                root = null;
            }
        } // Case 2: node to be deleted has two children
        else if (curr.getLeft() != null && curr.getRight() != null) {
            // find its inorder successor node
            PlayerNode s;
            if(parent.getLeft()==curr)
            s = getMax(curr.getLeft());
            else {s=getMin(curr.getRight());}
            int val = s.getPlayerID();

            DELETE_PLAYER(s.getPlayerID(), root);

            curr.setPlayerID(val);
        } // Case 3: node to be deleted has only one child
        else {
            PlayerNode child = (curr.getLeft() != null) ? curr.getLeft() : curr.getRight();

            
            if (curr != root) {
                if (curr == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }
            else {
                root = child;
            }
        }

    }

    public PlayerNode getMax(PlayerNode curr) {
        while (curr.getRight() != null) {
            curr = curr.getRight();
        }
        return curr;
    }

    public PlayerNode getMin(PlayerNode curr) {
          while (curr.getLeft()!= null) {
            curr = curr.getLeft();
        }
        return curr;
    }
}
