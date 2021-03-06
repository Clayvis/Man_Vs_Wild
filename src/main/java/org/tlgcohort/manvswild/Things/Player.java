package org.tlgcohort.manvswild.Things;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.tlgcohort.manvswild.GameLogic.Game.getWorldMap;


public class Player implements Serializable {

    private String name;
    private int health;
    private int attackPower;

    private List<Food> backpack = new ArrayList<>();
    private List<Item> toolBackpack = new ArrayList<>();
    private Location currLocation;
    private int progressionTracker = 0;
    private int eventCount = 0;
    private Boolean isDead = false;


    public Player(String name, int health, int attackPower, int progressionTracker,Location currLocation) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.progressionTracker = progressionTracker;
        this.currLocation = currLocation;
    }

    public void displayStatAndMsg(){
        System.out.println(displayPlayerStats());
        System.out.println(displayMsg());
    }

    private String displayPlayerStats(){
        String stats;
        stats = "\n||================================================================================================================================================||\n" +
                "\tPlayer: " + getName() + "\t\tHealth Lvl: " + getHealth() + "\t\tLocation: " + getCurrLocation().getName() + "\t\tBackpack: " + getBackpack() + getToolBackpack() +
                "\n||================================================================================================================================================||";
        return stats;
    }
    private String displayMsg(){
        String body;
        body =  "+--------------------------------------------------------------------------------------------------------------------------------------------------+\n" +
                "\t"+ getCurrLocation().getDesc() + "\n\n\t" + getCurrLocation().randomScript()
                + ". \n+--------------------------------------------------------------------------------------------------------------------------------------------------+";
        return body;
    }

    public void attack(){
        calcAttackPower();
        if (currLocation.presentNPC()){
            if (currLocation.getNpc().getHealth() > 0){
                int opponentDamage = currLocation.getNpc().getHealth() - attackPower;
                currLocation.getNpc().setHealth(opponentDamage);
                System.out.println("+------------------------------------------------------------------------------------+");
                System.out.println("\t" + name + " attacked the " + currLocation.getNpc().getName() + "!!\n\tYou inflicted " + attackPower + " damage!");
                System.out.println("+------------------------------------------------------------------------------------+");
                setEventCount(3);
            } else{
                System.out.println("+------------------------------------------------------------------------------------+");
                System.out.println(currLocation.getNpc().getName() + " is already dead. Calm down killer.");
                System.out.println("+------------------------------------------------------------------------------------+");
            }
        } else{
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.println("There is nothing to attack, goofy.");
            System.out.println("+------------------------------------------------------------------------------------+");
        }
    }

    public Boolean isDead(){
        if(getHealth() <= 0){
            isDead = true;
        }
        return isDead;
    }

    public void showOpponentStats(){
        getCurrLocation().getNpc().displayStats();
    }

    // takes all exits from current location and compares the user input to find a match, if so , then changes location to user input...
    public void move(String newLocation){
        int newLocationIndex = -1;
        if(currLocation.allExitsGenerator().contains(newLocation)){
            System.out.println("\n\tGoing to " + newLocation + ".....");
        for (int i = 0; i < getWorldMap().length; i++){
            if (getWorldMap()[i].getName().toLowerCase().equals(newLocation)){
                newLocationIndex = i;
                break;
            }
            }
        setCurrLocation(getWorldMap()[newLocationIndex]);
        setEventCount(3);
        }
        else{
            System.out.println("\n\tYou cannot go there.....\n");
        }
    }

    // allows player to select an item from their backpack to use and increase their health
    public void heal(){
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.println("\nFood Available : ");

        for(Food aItem : backpack){
            System.out.println((count+1)+ ") " + aItem);
            count++;
        }
        System.out.println("\nEat food? <enter a number?");
        int choice = scanner.nextInt();
        setHealth(health + backpack.get(choice-1).getHealthPoints());
        backpack.remove(choice - 1);
        setEventCount(2);
    }

    //grab an item from the currentLocation
    public void get(String item) {
        try {
            int newLocationIndex = -1;
            //checking if userInput is an item
            for (int i = 0; i < currLocation.getItems().size(); i++) {
                if (currLocation.getItems().get(i).getName().toLowerCase().equals(item)) {
                    newLocationIndex = i;
                    toolBackpack.add(currLocation.getItems().get(newLocationIndex));
                    getCurrLocation().getItems().remove(newLocationIndex);
                    setEventCount(1);
                    break;
                }
            }
            //checking if userInput is a food
            for (int i = 0; i < currLocation.getFoods().size(); i++) {
                if (currLocation.getFoods().get(i).getName().toLowerCase().equals(item)) {
                    newLocationIndex = i;
                    getBackpack().add(currLocation.getFoods().get(newLocationIndex));
                    getCurrLocation().getItems().remove(newLocationIndex);
                    setEventCount(1);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("\nYou cannot get that....\n");
        }
    }

    //display players current location on the map
    public String viewPortableMap(){
        return this.getCurrLocation().getMap();
    }

    //calculate and increase the attack power of the player based on the items they have
    private void calcAttackPower(){
        int power = 0;
        for(Item item : toolBackpack){
            power += item.getPowerLevel();
        }
        int attackDamage = (power + attackPower) / 2;
        setAttackPower(attackDamage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Food> getBackpack() {
        return backpack;
    }

    public void setBackpack(List<Food> backpack) {
        this.backpack = backpack;
    }

    public Location getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(Location currLocation) {
        this.currLocation = currLocation;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int setAttackPower(int attackPower){
        return this.attackPower = attackPower;
    }

    public int getProgressionTracker() {
        return progressionTracker;
    }

    public List<Item> getToolBackpack() {
        return toolBackpack;
    }

    public void setToolBackpack(List<Item> toolBackpack) {
        this.toolBackpack = toolBackpack;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", backpack=" + backpack +
                ", currLocation=" + currLocation +
                '}';
    }
}
