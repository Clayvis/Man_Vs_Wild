package org.tlgcohort.manvswild.client;

import org.tlgcohort.manvswild.commands.CommandEngine;
import org.tlgcohort.manvswild.commands.Commands;
import org.tlgcohort.manvswild.*;
import org.tlgcohort.manvswild.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverOne {
    //menu options
    private static final String START_GAME = "Start Game";
    private static final String QUIT = "Quit";



    //class var, so I can call it anywhere in my class
    private static Scanner scanner;


    public static void main(String[] args) {

       // Testing command engine
//        String[] dummyInput = {"Go", "campsite"};
//        CommandEngine commandOne = new CommandEngine();
//        commandOne.displayCommands();
//        commandOne.commandProcessor(dummyInput);

        GameLogic gl = new GameLogic();

        //add options to main menu
        List<String> mainMenu = new ArrayList<>();
        mainMenu.add(START_GAME);
        mainMenu.add(QUIT);

        //track users choice be declaring an empty string
        String choice = "";

        //while user doesn't select quit then they can keep replaying the game
        while(!choice.equalsIgnoreCase(QUIT)){
            System.out.println("\nWelcome Survivor!!!");
            System.out.println("\n***********");

            //print out our main menu options & increment the index of each choice
            int choiceIndex = 1;
            for(String opt : mainMenu){
                System.out.println(choiceIndex + ") " + opt);
                choiceIndex++;
            }

            //prompt for user to select from the main menu
            System.out.println("**********");
            System.out.println("\nSelect an option>> ");

            //capture user's choice as an integer
            scanner = new Scanner(System.in);
            int selectedOpt = scanner.nextInt();

            //capture choice by getting the value at this index
            choice = mainMenu.get(selectedOpt - 1);

            //choice cases
            if(choice.equalsIgnoreCase(START_GAME)){

                //game logic here
                // startGame();
                System.out.println("\nThe game is now starting...............\n");
                System.out.println(".88b  d88.  .d8b.  d8b   db     db    db .d8888.     db   d8b   db d888888b db      d8888b. \n" +
                        "88'YbdP`88 d8' `8b 888o  88     88    88 88'  YP     88   I8I   88   `88'   88      88  `8D \n" +
                        "88  88  88 88ooo88 88V8o 88     Y8    8P `8bo.       88   I8I   88    88    88      88   88 \n" +
                        "88  88  88 88~~~88 88 V8o88     `8b  d8'   `Y8b.     Y8   I8I   88    88    88      88   88 \n" +
                        "88  88  88 88   88 88  V888      `8bd8'  db   8D     `8b d8'8b d8'   .88.   88booo. 88  .8D \n" +
                        "YP  YP  YP YP   YP VP   V8P        YP    `8888Y'      `8b8' `8d8'  Y888888P Y88888P Y8888D' \n" +
                        "                                                                                            ");
                System.out.println("Enter your name.....");
                scanner = new Scanner(System.in);
                String playerName = scanner.nextLine();
                System.out.println("How much health would you want to start with ? <Enter a number>");
                int playerHealth = scanner.nextInt();
                Player player = new Player(playerName,playerHealth);

                try {
                    gl.startGame(player);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }



    }
}