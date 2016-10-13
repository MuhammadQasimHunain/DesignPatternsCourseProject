package chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main extends JFrame implements MouseListener {

    private static final long serialVersionUID = 1L;

    //Variable Declaration
    private static final int Height = 700;
    private static final int Width = 1110;
    private static Rook whiteRook01, whiteRook02, blackRook01, blackRook02;
    private static Knight whiteKnight01, whiteKnight02, blackKnight01, blackKnight02;
    private static Bishop whiteBishop01, whiteBishop02, blackBishop01, blackBishop02;
    private static Pawn whitePawn[], blackPawn[];
    private static Queen whiteQueen, blackQueen;
    private static King whiteKing, blackKing;
    private Cell cell, previous;
    private int chance = 0;
    private Cell boardState[][];
    private ArrayList<Cell> destinationList = new ArrayList<Cell>();
    private Player white = null, black = null;
    private JPanel board = new JPanel(new GridLayout(8, 8));
    private JPanel wDetails = new JPanel(new GridLayout(3, 3));
    private JPanel bDetails = new JPanel(new GridLayout(3, 3));
    private JPanel wComboPanel = new JPanel();
    private JPanel bComboPanel = new JPanel();
    private JPanel controlPanel, whitePlayer, blackPlayer, temp, displayTime, showPlayer, time;
    private JSplitPane split;
    private JLabel label, mov;
    private static JLabel CHNC;
    private Time timer;
    public static Main Mainboard;
    private boolean selected = false, end = false;
    private Container content;
    private ArrayList<Player> wPlayer, bPlayer;
    private ArrayList<String> wNames = new ArrayList<String>();
    private ArrayList<String> bNames = new ArrayList<String>();
    private JComboBox<String> wCombo, bCombo;
    private String wName = null, bName = null, winner = null;
    static String move;
    private Player tempPlayer;
    private JScrollPane wScroll, bScroll;
    private String[] WNames = {}, BNames = {};
    private JSlider timeSlider;
    private BufferedImage image;
    private Button start, wselect, bselect, WNewPlayer, BNewPlayer;
    public static int timeRemaining = 60;

    public static void main(String[] args) {

        variableInitialization();

        //Setting up the board
        Mainboard = new Main();
        Mainboard.setVisible(true);
        Mainboard.setResizable(false);
    }

    private static void variableInitialization() {
        //variable initialization
        whiteRook01 = new Rook("WR01", "White_Rook.png", Piece.WHITE_COLOR);
        whiteRook02 = new Rook("WR02", "White_Rook.png", Piece.WHITE_COLOR);
        blackRook01 = new Rook("BR01", "Black_Rook.png", Piece.BLACK_COLOR);
        blackRook02 = new Rook("BR02", "Black_Rook.png", Piece.BLACK_COLOR);
        whiteKnight01 = new Knight("WK01", "White_Knight.png", Piece.WHITE_COLOR);
        whiteKnight02 = new Knight("WK02", "White_Knight.png", Piece.WHITE_COLOR);
        blackKnight01 = new Knight("BK01", "Black_Knight.png", Piece.BLACK_COLOR);
        blackKnight02 = new Knight("BK02", "Black_Knight.png", Piece.BLACK_COLOR);
        whiteBishop01 = new Bishop("WB01", "White_Bishop.png", Piece.WHITE_COLOR);
        whiteBishop02 = new Bishop("WB02", "White_Bishop.png", Piece.WHITE_COLOR);
        blackBishop01 = new Bishop("BB01", "Black_Bishop.png", Piece.BLACK_COLOR);
        blackBishop02 = new Bishop("BB02", "Black_Bishop.png", Piece.BLACK_COLOR);
        whiteQueen = new Queen("WQ", "White_Queen.png", Piece.WHITE_COLOR);
        blackQueen = new Queen("BQ", "Black_Queen.png", Piece.BLACK_COLOR);
        whiteKing = new King("WK", "White_King.png", Piece.WHITE_COLOR, King.WHITE_KING_X_AXIS_POSITION, King.WHITE_KING_Y_AXIS_POSTION);
        blackKing = new King("BK", "Black_King.png", Piece.BLACK_COLOR, King.BLACK_KING_X_AXIS_POSITION, King.BLACK_KING_Y_AXIS_POSITION);
        whitePawn = new Pawn[8];
        blackPawn = new Pawn[8];
        for (int i = 0; i < 8; i++) {
            whitePawn[i] = new Pawn("WP0" + (i + 1), "White_Pawn.png", Piece.WHITE_COLOR);
            blackPawn[i] = new Pawn("BP0" + (i + 1), "Black_Pawn.png", Piece.BLACK_COLOR);
        }
    }

    //Constructor
    private Main() {
        
        initializeMainMethodVariable();

        setTimerSliderDetails(timeSlider);

        fetchingPlayersDetails();

        defineFrameLayout();

        definePalyerDialogBox();

        defineGameBoard();

        //Defining all the Cells
        defineAllCells();

        defineTimeVariables();

        resizingForInActiveGame();

        content.add(split);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void resizingForInActiveGame() {
        //The Left Layout When Game is inactive
        temp = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                try {
                    image = ImageIO.read(this.getClass().getResource("clash.jpg"));
                } catch (IOException ex) {
                    System.out.println("not found");
                }

                g.drawImage(image, 0, 0, null);
            }
        };

        temp.setMinimumSize(new Dimension(800, 700));
        controlPanel.setMinimumSize(new Dimension(285, 700));
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, temp, controlPanel);
    }

    private void defineGameBoard() throws HeadlessException {
        JPanel whitestats = new JPanel(new GridLayout(3, 3));
        JPanel blackstats = new JPanel(new GridLayout(3, 3));
        wCombo = new JComboBox<String>(WNames);
        bCombo = new JComboBox<String>(BNames);
        wScroll = new JScrollPane(wCombo);
        bScroll = new JScrollPane(bCombo);
        wComboPanel.setLayout(new FlowLayout());
        bComboPanel.setLayout(new FlowLayout());
        wselect = new Button("Select");
        bselect = new Button("Select");
        wselect.addActionListener(new SelectHandler(Piece.WHITE_COLOR));
        bselect.addActionListener(new SelectHandler(Piece.BLACK_COLOR));
        WNewPlayer = new Button("New Player");
        BNewPlayer = new Button("New Player");
        WNewPlayer.addActionListener(new Handler(Piece.WHITE_COLOR));
        BNewPlayer.addActionListener(new Handler(Piece.BLACK_COLOR));
        wComboPanel.add(wScroll);
        wComboPanel.add(wselect);
        wComboPanel.add(WNewPlayer);
        bComboPanel.add(bScroll);
        bComboPanel.add(bselect);
        bComboPanel.add(BNewPlayer);
        whitePlayer.add(wComboPanel, BorderLayout.NORTH);
        blackPlayer.add(bComboPanel, BorderLayout.NORTH);
        whitestats.add(new JLabel("Name   :"));
        whitestats.add(new JLabel("Played :"));
        whitestats.add(new JLabel("Won    :"));
        blackstats.add(new JLabel("Name   :"));
        blackstats.add(new JLabel("Played :"));
        blackstats.add(new JLabel("Won    :"));
        whitePlayer.add(whitestats, BorderLayout.WEST);
        blackPlayer.add(blackstats, BorderLayout.WEST);
        controlPanel.add(whitePlayer);
        controlPanel.add(blackPlayer);
    }

    private void defineTimeVariables() throws HeadlessException {
        showPlayer = new JPanel(new FlowLayout());
        showPlayer.add(timeSlider);
        JLabel setTime = new JLabel("Set Timer(in mins):");
        start = new Button("Start");
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        start.addActionListener(new START());
        start.setPreferredSize(new Dimension(120, 40));
        setTime.setFont(new Font("Arial", Font.BOLD, 16));
        label = new JLabel("Time Starts now", JLabel.CENTER);
        label.setFont(new Font("SERIF", Font.BOLD, 30));
        displayTime = new JPanel(new FlowLayout());
        time = new JPanel(new GridLayout(3, 3));
        time.add(setTime);
        time.add(showPlayer);
        displayTime.add(start);
        time.add(displayTime);
        controlPanel.add(time);
        board.setMinimumSize(new Dimension(800, 700));
    }

    private void defineFrameLayout() {
        //Cell cell;
        board.setBorder(BorderFactory.createLoweredBevelBorder());
        //pieces.Piece P;
        content = getContentPane();
        setSize(Width, Height);
        setTitle("Chess");
        content.setBackground(Color.black);
        controlPanel = new JPanel();
        content.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(3, 3));
        controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP, TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
    }

    private void definePalyerDialogBox() {
        //Defining the Player Box in Control Panel
        whitePlayer = new JPanel();
        whitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
        whitePlayer.setLayout(new BorderLayout());
        
        blackPlayer = new JPanel();
        blackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
        blackPlayer.setLayout(new BorderLayout());
    }

    private void fetchingPlayersDetails() {
        //Fetching Details of all Players
        wPlayer = Player.fetchPlayers();
        Iterator<Player> witr = wPlayer.iterator();
        while (witr.hasNext()) {
            wNames.add(witr.next().name());
        }
        
        bPlayer = Player.fetchPlayers();
        Iterator<Player> bitr = bPlayer.iterator();
        while (bitr.hasNext()) {
            bNames.add(bitr.next().name());
        }
        WNames = wNames.toArray(WNames);
        BNames = bNames.toArray(BNames);
    }

    private void initializeMainMethodVariable() {
        timeRemaining = 60;
        timeSlider = new JSlider();
        move = "White";
        wName = null;
        bName = null;
        winner = null;
        board = new JPanel(new GridLayout(8, 8));
        wDetails = new JPanel(new GridLayout(3, 3));
        bDetails = new JPanel(new GridLayout(3, 3));
        bComboPanel = new JPanel();
        wComboPanel = new JPanel();
        wNames = new ArrayList<String>();
        bNames = new ArrayList<String>();
        board.setMinimumSize(new Dimension(800, 700));
        ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
        this.setIconImage(img.getImage());
    }

    //A function used to define all cells
    public void defineAllCells() {
        pieces.Piece P;
        Cell cell;
        boardState = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                P = null;
                if (i == 0 && j == 0) {
                    P = blackRook01;
                } else if (i == 0 && j == 7) {
                    P = blackRook02;
                } else if (i == 7 && j == 0) {
                    P = whiteRook01;
                } else if (i == 7 && j == 7) {
                    P = whiteRook02;
                } else if (i == 0 && j == 1) {
                    P = blackKnight01;
                } else if (i == 0 && j == 6) {
                    P = blackKnight02;
                } else if (i == 7 && j == 1) {
                    P = whiteKnight01;
                } else if (i == 7 && j == 6) {
                    P = whiteKnight02;
                } else if (i == 0 && j == 2) {
                    P = blackBishop01;
                } else if (i == 0 && j == 5) {
                    P = blackBishop02;
                } else if (i == 7 && j == 2) {
                    P = whiteBishop01;
                } else if (i == 7 && j == 5) {
                    P = whiteBishop02;
                } else if (i == 0 && j == 3) {
                    P = blackKing;
                } else if (i == 0 && j == 4) {
                    P = blackQueen;
                } else if (i == 7 && j == 3) {
                    P = whiteKing;
                } else if (i == 7 && j == 4) {
                    P = whiteQueen;
                } else if (i == 1) {
                    P = blackPawn[j];
                } else if (i == 6) {
                    P = whitePawn[j];
                }
                cell = new Cell(i, j, P);
                cell.addMouseListener(this);
                board.add(cell);
                boardState[i][j] = cell;
            }
        }
    }

    //A function to set time slider details
    public void setTimerSliderDetails(JSlider timeSlider) {
        timeSlider.setMinimum(1);
        timeSlider.setMaximum(15);
        timeSlider.setValue(1);
        timeSlider.setMajorTickSpacing(2);
        timeSlider.setPaintLabels(true);
        timeSlider.setPaintTicks(true);
        timeSlider.addChangeListener(new TimeChange());
    }

    // A function to change the chance from white Player to black Player or vice verse
    // It is made public because it is to be accessed in the Time Class
    public void changeChance() {
        if (boardState[getKing(chance).getXAxisPosition()][getKing(chance).getYAxisPosition()].isCheck()) {
            chance ^= 1;
            gameEnd();
        }
        if (destinationList.isEmpty() == false) {
            cleanDestinations(destinationList);
        }
        if (previous != null) {
            previous.deSelect();
        }
        previous = null;
        chance ^= 1;
        if (!end && timer != null) {
            timer.resetTimer();
            timer.startTimer();
            showPlayer.remove(CHNC);
            if (Main.move == "White") {
                Main.move = "Black";
            } else {
                Main.move = "White";
            }
            CHNC.setText(Main.move);
            showPlayer.add(CHNC);
        }
    }

    //A function to retrieve the black King or white King
    private King getKing(int color) {
        if (color == Piece.WHITE_COLOR) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }

    //A function to clean the highlights of possible destination cells
    private void cleanDestinations(ArrayList<Cell> destlist) //Function to clear the last move's destinations
    {
        ListIterator<Cell> it = destlist.listIterator();
        while (it.hasNext()) {
            it.next().removePossibleDestination();
        }
    }

    //A function that indicates the possible moves by highlighting the Cells
    private void highlightDestinations(ArrayList<Cell> destlist) {
        ListIterator<Cell> it = destlist.listIterator();
        while (it.hasNext()) {
            it.next().setPossibleDestination();
        }
    }

    //Function to check if the king will be in danger if the given move is made
    private boolean willKingBeInDanger(Cell fromcell, Cell tocell) {
        Cell newboardstate[][] = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    newboardstate[i][j] = new Cell(boardState[i][j]);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    System.out.println("There is a problem with cloning !!");
                }
            }
        }

        if (newboardstate[tocell.xAxisPosition][tocell.yAxisPosition].getPiece() != null) {
            newboardstate[tocell.xAxisPosition][tocell.yAxisPosition].removePiece();
        }

        newboardstate[tocell.xAxisPosition][tocell.yAxisPosition].setPiece(newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].getPiece());
        if (newboardstate[tocell.xAxisPosition][tocell.yAxisPosition].getPiece() instanceof King) {
            ((King) (newboardstate[tocell.xAxisPosition][tocell.yAxisPosition].getPiece())).setXAxisPosition(tocell.xAxisPosition);
            ((King) (newboardstate[tocell.xAxisPosition][tocell.yAxisPosition].getPiece())).setYAxisPosition(tocell.yAxisPosition);
        }
        newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].removePiece();
        if (((King) (newboardstate[getKing(chance).getXAxisPosition()][getKing(chance).getYAxisPosition()].getPiece())).isInDanger(newboardstate) == true) {
            return true;
        } else {
            return false;
        }
    }

    //A function to eliminate the possible moves that will put the King in danger
    private ArrayList<Cell> filterDestination(ArrayList<Cell> destlist, Cell fromcell) {
        ArrayList<Cell> newlist = new ArrayList<Cell>();
        Cell newboardstate[][] = new Cell[8][8];
        ListIterator<Cell> it = destlist.listIterator();
        int x, y;
        while (it.hasNext()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    try {
                        newboardstate[i][j] = new Cell(boardState[i][j]);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }

            Cell tempc = it.next();
            if (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece() != null) {
                newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].removePiece();
            }
            newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].setPiece(newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].getPiece());
            x = getKing(chance).getXAxisPosition();
            y = getKing(chance).getYAxisPosition();
            if (newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].getPiece() instanceof King) {
                ((King) (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece())).setXAxisPosition(tempc.xAxisPosition);
                ((King) (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece())).setYAxisPosition(tempc.yAxisPosition);
                x = tempc.xAxisPosition;
                y = tempc.yAxisPosition;
            }
            newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].removePiece();
            if ((((King) (newboardstate[x][y].getPiece())).isInDanger(newboardstate) == false)) {
                newlist.add(tempc);
            }
        }
        return newlist;
    }

    //A Function to filter the possible moves when the king of the current player is under Check 
    private ArrayList<Cell> inCheckFilter(ArrayList<Cell> destlist, Cell fromcell, int color) {
        ArrayList<Cell> newlist = new ArrayList<Cell>();
        Cell newboardstate[][] = new Cell[8][8];
        ListIterator<Cell> it = destlist.listIterator();
        int x, y;
        while (it.hasNext()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    try {
                        newboardstate[i][j] = new Cell(boardState[i][j]);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Cell tempc = it.next();
            if (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece() != null) {
                newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].removePiece();
            }
            newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].setPiece(newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].getPiece());
            x = getKing(color).getXAxisPosition();
            y = getKing(color).getYAxisPosition();
            if (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece() instanceof King) {
                ((King) (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece())).setXAxisPosition(tempc.xAxisPosition);
                ((King) (newboardstate[tempc.xAxisPosition][tempc.yAxisPosition].getPiece())).setYAxisPosition(tempc.yAxisPosition);
                x = tempc.xAxisPosition;
                y = tempc.yAxisPosition;
            }
            newboardstate[fromcell.xAxisPosition][fromcell.yAxisPosition].removePiece();
            if ((((King) (newboardstate[x][y].getPiece())).isInDanger(newboardstate) == false)) {
                newlist.add(tempc);
            }
        }
        return newlist;
    }

    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkMate(int color) {
        ArrayList<Cell> dlist = new ArrayList<Cell>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardState[i][j].getPiece() != null && boardState[i][j].getPieceColor() == color) {
                    dlist.clear();
                    dlist = boardState[i][j].movePiece(boardState, i, j);
                    dlist = inCheckFilter(dlist, boardState[i][j], color);
                    if (dlist.size() != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void gameEnd() {
        cleanDestinations(destinationList);
        displayTime.disable();
        timer.countdownTimer.stop();
        if (previous != null) {
            previous.removePiece();
        }
        if (chance == 0) {
            white.updateGamesWon();
            white.updatePlayer();
            winner = white.name();
        } else {
            black.updateGamesWon();
            black.updatePlayer();
            winner = black.name();
        }
        JOptionPane.showMessageDialog(board, "Checkmate!!!\n" + winner + " wins");
        whitePlayer.remove(wDetails);
        blackPlayer.remove(bDetails);
        displayTime.remove(label);

        displayTime.add(start);
        showPlayer.remove(mov);
        showPlayer.remove(CHNC);
        showPlayer.revalidate();
        showPlayer.add(timeSlider);

        split.remove(board);
        split.add(temp);
        WNewPlayer.enable();
        BNewPlayer.enable();
        wselect.enable();
        bselect.enable();
        end = true;
        Mainboard.disable();
        Mainboard.dispose();
        Mainboard = new Main();
        Mainboard.setVisible(true);
        Mainboard.setResizable(false);
    }

    //These are the abstract function of the parent class. Only relevant method here is the On-Click Fuction
    //which is called when the user clicks on a particular cell
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        cell = (Cell) arg0.getSource();
        if (previous == null) {
            if (cell.getPiece() != null) {
                if (cell.getPieceColor() != chance) {
                    return;
                }
                cell.select();
                previous = cell;
                destinationList.clear();
                destinationList = cell.movePiece(boardState, cell.xAxisPosition, cell.yAxisPosition);
                if (cell.getPiece() instanceof King) {
                    destinationList = filterDestination(destinationList, cell);
                } else if (boardState[getKing(chance).getXAxisPosition()][getKing(chance).getYAxisPosition()].isCheck()) {
                    destinationList = new ArrayList<Cell>(filterDestination(destinationList, cell));
                } else if (destinationList.isEmpty() == false && willKingBeInDanger(cell, destinationList.get(0))) {
                    destinationList.clear();
                }
                highlightDestinations(destinationList);
            }
        } else if (cell.xAxisPosition == previous.xAxisPosition && cell.yAxisPosition == previous.yAxisPosition) {
            cell.deSelect();
            cleanDestinations(destinationList);
            destinationList.clear();
            previous = null;
        } else if (cell.getPiece() == null || previous.getPieceColor() != cell.getPieceColor()) {
            if (cell.isPossibleDestination()) {
                if (cell.getPiece() != null) {
                    cell.removePiece();
                }
                cell.setPiece(previous.getPiece());
                if (previous.isCheck()) {
                    previous.removeCheck();
                }
                previous.removePiece();
                if (getKing(chance ^ 1).isInDanger(boardState)) {
                    boardState[getKing(chance ^ 1).getXAxisPosition()][getKing(chance ^ 1).getYAxisPosition()].setCheck();
                    if (checkMate(getKing(chance ^ 1).getcolor())) {
                        previous.deSelect();
                        if (previous.getPiece() != null) {
                            previous.removePiece();
                        }
                        gameEnd();
                    }
                }
                if (getKing(chance).isInDanger(boardState) == false) {
                    boardState[getKing(chance).getXAxisPosition()][getKing(chance).getYAxisPosition()].removeCheck();
                }
                if (cell.getPiece() instanceof King) {
                    ((King) cell.getPiece()).setXAxisPosition(cell.xAxisPosition);
                    ((King) cell.getPiece()).setYAxisPosition(cell.yAxisPosition);
                }
                changeChance();
                if (!end) {
                    timer.resetTimer();
                    timer.startTimer();
                }
            }
            if (previous != null) {
                previous.deSelect();
                previous = null;
            }
            cleanDestinations(destinationList);
            destinationList.clear();
        } else if (previous.getPieceColor() == cell.getPieceColor()) {
            previous.deSelect();
            cleanDestinations(destinationList);
            destinationList.clear();
            cell.select();
            previous = cell;
            destinationList = cell.movePiece(boardState, cell.xAxisPosition, cell.yAxisPosition);
            if (cell.getPiece() instanceof King) {
                destinationList = filterDestination(destinationList, cell);
            } else if (boardState[getKing(chance).getXAxisPosition()][getKing(chance).getYAxisPosition()].isCheck()) {
                destinationList = new ArrayList<Cell>(filterDestination(destinationList, cell));
            } else if (destinationList.isEmpty() == false && willKingBeInDanger(cell, destinationList.get(0))) {
                destinationList.clear();
            }
            highlightDestinations(destinationList);
        }
        if (cell.getPiece() != null && cell.getPiece() instanceof King) {
            ((King) cell.getPiece()).setXAxisPosition(cell.xAxisPosition);
            ((King) cell.getPiece()).setYAxisPosition(cell.yAxisPosition);
        }
    }

    //Other Irrelevant abstract function. Only the Click Event is captured.
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub		
    }

    class START implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

            if (white == null || black == null) {
                JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
                return;
            }
            white.updateGamesPlayed();
            white.updatePlayer();
            black.updateGamesPlayed();
            black.updatePlayer();
            WNewPlayer.disable();
            BNewPlayer.disable();
            wselect.disable();
            bselect.disable();
            split.remove(temp);
            split.add(board);
            showPlayer.remove(timeSlider);
            mov = new JLabel("Move:");
            mov.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            mov.setForeground(Color.red);
            showPlayer.add(mov);
            CHNC = new JLabel(move);
            CHNC.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            CHNC.setForeground(Color.blue);
            showPlayer.add(CHNC);
            displayTime.remove(start);
            displayTime.add(label);
            timer = new Time(label);
            timer.startTimer();
        }
    }

    class TimeChange implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            timeRemaining = timeSlider.getValue() * 60;
        }
    }

    class SelectHandler implements ActionListener {

        private int color;

        SelectHandler(int i) {
            color = i;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            tempPlayer = null;
            String n = (color == Piece.WHITE_COLOR) ? wName : bName;
            JComboBox<String> jc = (color == Piece.WHITE_COLOR) ? wCombo : bCombo;
            JComboBox<String> ojc = (color == Piece.WHITE_COLOR) ? bCombo : wCombo;
            ArrayList<Player> pl = (color == Piece.WHITE_COLOR) ? wPlayer : bPlayer;
            //ArrayList<Player> otherPlayer=(color==0)?bPlayer:wPlayer;
            ArrayList<Player> opl = Player.fetchPlayers();
            if (opl.isEmpty()) {
                return;
            }
            JPanel det = (color == Piece.WHITE_COLOR) ? wDetails : bDetails;
            JPanel PL = (color == Piece.WHITE_COLOR) ? whitePlayer : blackPlayer;
            if (selected == true) {
                det.removeAll();
            }
            n = (String) jc.getSelectedItem();
            Iterator<Player> it = pl.iterator();
            Iterator<Player> oit = opl.iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if (p.name().equals(n)) {
                    tempPlayer = p;
                    break;
                }
            }
            while (oit.hasNext()) {
                Player p = oit.next();
                if (p.name().equals(n)) {
                    opl.remove(p);
                    break;
                }
            }

            if (tempPlayer == null) {
                return;
            }
            if (color == Piece.WHITE_COLOR) {
                white = tempPlayer;
            } else {
                black = tempPlayer;
            }
            bPlayer = opl;
            ojc.removeAllItems();
            for (Player s : opl) {
                ojc.addItem(s.name());
            }
            det.add(new JLabel(" " + tempPlayer.name()));
            det.add(new JLabel(" " + tempPlayer.getGamesPlayed()));
            det.add(new JLabel(" " + tempPlayer.getGamesWon()));

            PL.revalidate();
            PL.repaint();
            PL.add(det);
            selected = true;
        }

    }

    class Handler implements ActionListener {

        private int color;

        Handler(int i) {
            color = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String name;
            name = (color == Piece.WHITE_COLOR) ? wName : bName;

            JPanel j;
            j = (color == Piece.WHITE_COLOR) ? whitePlayer : blackPlayer;

            ArrayList<Player> N = Player.fetchPlayers();
            Iterator<Player> it = N.iterator();
            JPanel detail = (color == Piece.WHITE_COLOR) ? wDetails : bDetails;
            name = JOptionPane.showInputDialog(j, "Enter your name");

            if (name != null) {

                while (it.hasNext()) {
                    if (it.next().name().equals(name)) {
                        JOptionPane.showMessageDialog(j, "Player exists");
                        return;
                    }
                }

                if (name.length() != 0) {
                    Player tem = new Player(name);
                    tem.updatePlayer();
                    if (color == Piece.WHITE_COLOR) {
                        white = tem;
                    } else {
                        black = tem;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
            detail.removeAll();
            detail.add(new JLabel(" " + name));
            detail.add(new JLabel(" 0"));
            detail.add(new JLabel(" 0"));
            j.revalidate();
            j.repaint();
            j.add(detail);
            selected = true;
        }
    }
}
