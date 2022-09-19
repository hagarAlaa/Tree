/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.Scanner;

/**
 *
 * @author DeLL
 */
public class Tree {

    public static class Node {

        Node left;
        Node right;
        int num;

        public Node(int num) {
            this.num = num;
            left = null;
            right = null;
        }

    }

    static void printLeaf(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            System.out.println(node.num);
        }
        if (node.left != null) {
            printLeaf(node.left);
        }
        if (node.right != null) {
            printLeaf(node.right);
        }
    }

    public static void main(String[] args) {

        GameTree t = new GameTree();
        while (true) {

            Scanner input = new Scanner(System.in);
            String command = input.next();
            if ("STARTUP".equalsIgnoreCase(command)) {
                int num = input.nextInt();
                for (int i = 0; i < num; i++) {
                    int x = input.nextInt();
                    String s = input.next();
                    t.add_player(x, s);
                }
            } else if ("DISPLAY_ALL_PLAYERS".equalsIgnoreCase(command)) {
                t.display_all_players_info(t.root);
            } else if ("NUM_OF_PLAYERS".equalsIgnoreCase(command)) {
                System.out.println("Number of players :"+t.NUM_OF_PLAYERS(t.root));
            } else if ("DISPLAY_PLAYER_INFO".equalsIgnoreCase(command)) {
                int key = input.nextInt();
                PlayerNode player = t.display_player_by_ID(t.root, key);
                if (player == null) {
                    System.out.println("Not found any player with ID number <" + key + ">");
                } else {
                    System.out.println( "Player with ID <"+player.getPlayerID()+
                            "> is <"+player.getPlayername()+"> and the stamina level is <"+player.getStamina()
                            +">");
                }
            } else if ("ADD_PLAYER".equalsIgnoreCase(command)) {
                int x = input.nextInt();
                String s = input.next();
                t.add_player(x, s);
            } else if ("UPDATE_PLAYER_INFO".equalsIgnoreCase(command)) {
                int key = input.nextInt();
                t.UPDATE_PLAYER_INFO(key);
            } else if ("DELETE_PLAYER".equalsIgnoreCase(command)) {
                int key = input.nextInt();
                PlayerNode p = t.display_player_by_ID(t.root, key);
                if (p != null) {
                    System.out.println("The player <" + p.getPlayername() + "> left the game");
                    t.DELETE_PLAYER(key, t.root);}
                else{
                    System.out.println("Not found any player with ID number <"+key+">");
                }
            } else if ("QUIT".equalsIgnoreCase(command)) {
                System.exit(0);

            }
        }
    }
}
