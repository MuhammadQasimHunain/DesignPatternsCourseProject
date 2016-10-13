package chess;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * This is the Player Class It provides the functionality of keeping track of
 * all the users Objects of this class is updated and written in the Game's Data
 * Files after every Game
 *
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Integer gamesPlayed;
    private Integer gamesWon;

    //Constructor
    public Player(String name) {
        this.name = name.trim();
        //this.lname = lname.trim();
        gamesPlayed = new Integer(0);
        gamesWon = new Integer(0);
    }

    //Name Getter
    public String name() {
        return name;
    }

    //Returns the number of games played
    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    //Returns the number of games won
    public Integer getGamesWon() {
        return gamesWon;
    }

    //Calculates the win percentage of the player
    public Integer getWinPercent() {
        return new Integer((gamesWon * 100) / gamesPlayed);
    }

    //Increments the number of games played
    public void updateGamesPlayed() {
    	if(gamesPlayed == null)
            gamesPlayed = new Integer(0);
    	
    	gamesPlayed++;	
    }

    //Increments the number of games won
    public void updateGamesWon() {
    	if(gamesWon == null)
            gamesWon = new Integer(0);

    	gamesWon++;
    }

    public static ArrayList<Player> fetchPlayers() //Function to fetch the list of the players
    {
        Player tempplayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            File infile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            input = new ObjectInputStream(new FileInputStream(infile));
            try {
                while (true) {
                    tempplayer = (Player) input.readObject();
                    players.add(tempplayer);
                }
            } catch (EOFException e) {
                input.close();
            }
        } catch (FileNotFoundException e) {
            players.clear();
            return players;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                input.close();
            } catch (IOException e1) {
            }
            JOptionPane.showMessageDialog(null, "Unable to read the required Game files !!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return players;
    }

    public void updatePlayer() //Function to update the statistics of a player
    {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Player tempPlayer;
        File inputFile = null;
        File outputFile = null;
        boolean playerDoNotExist;
        try {
            inputFile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            outputFile = new File(System.getProperty("user.dir") + File.separator + "tempfile.dat");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
            System.exit(0);
        }
        try {
            if (outputFile.exists() == false) {
                outputFile.createNewFile();
            }
            if (inputFile.exists() == false) {
                output = new ObjectOutputStream(new java.io.FileOutputStream(outputFile, true));
                output.writeObject(this);
            } else {
                input = new ObjectInputStream(new FileInputStream(inputFile));
                output = new ObjectOutputStream(new FileOutputStream(outputFile));
                playerDoNotExist = true;
                try {
                    while (true) {
                        tempPlayer = (Player) input.readObject();
                        if (tempPlayer.name().equals(name())) {
                            output.writeObject(this);
                            playerDoNotExist = false;
                        } else {
                            output.writeObject(tempPlayer);
                        }
                    }
                } catch (EOFException e) {
                    input.close();
                }
                if (playerDoNotExist) {
                    output.writeObject(this);
                }
            }
            inputFile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            if (outputFile.renameTo(newf) == false) {
                System.out.println("File Renameing Unsuccessful");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception e) {

        }
    }
}
