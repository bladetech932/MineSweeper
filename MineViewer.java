import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MineViewer extends JFrame {
  private MineModel model;
  private ImageIcon imageMine = new ImageIcon("mine.png");
	private ImageIcon imageFlag = new ImageIcon("flag.png");
  private ImageIcon imageOne = new ImageIcon("1.png");
  private ImageIcon imageTwo = new ImageIcon("2.png");
  private ImageIcon imageThree = new ImageIcon("3.png");
  private ImageIcon imageFour = new ImageIcon("4.png");
  private ImageIcon imageFive = new ImageIcon("5.png");
  private ImageIcon imageSix = new ImageIcon("6.png");
  private ImageIcon imageSeven = new ImageIcon("7.png");
  private ImageIcon imageEight = new ImageIcon("8.png");
  private Object[] gameModes = {"Custom","Easy","Medium","Hard"};
  private Color superGrey = new Color(116,116,116);
  private JPanel panel;
  private Container pane;
  JButton[][] btn;

  public MineViewer() { //Init Call
    super("Insert Title Here"); // super must be first in the constructor

    int selected = JOptionPane.showOptionDialog(null,"Select Difficulty below", //showInputDialog
    "Difficulty Settings",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null ,gameModes, gameModes[1]);

    if (selected==1) { model = new MineModel(MineModel.EASY); }
    if (selected==2) { model = new MineModel(MineModel.MEDIUM); }
    if (selected==3) { model = new MineModel(MineModel.HARD); }
    if (selected==0) { model = new MineModel(initCustomGame()); }

    //Frame Init
    setSize(1000,1000);
		setResizable(false);//set to false on deployment
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // menubar init
    Container contentpane = getContentPane();
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(getFileMenu(this));
    menuBar.add(getNewGameMenu(this));
    contentpane.add(menuBar, BorderLayout.NORTH);

    panel = new JPanel();
    panel.setLayout(new GridLayout(model.getRows(),model.getColumns()));

    buildButtonArray(model.getRows(), model.getColumns());  //init grid buttons

    //pane = this.getContentPane(); pane. if contentpane is needed
    add(panel);
  }

  private void buildButtonArray(int rows, int columns){
    btn = new JButton[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0;j < columns; j++) {
				btn[i][j] = new JButton();
				btn[i][j].setBackground(Color.WHITE);
        btn[i][j].setName(i+","+j);
				btn[i][j].addMouseListener(new MineController(model, this));
        panel.add(btn[i][j]);
			}
		}
  }

  private int[] initCustomGame(){
    JPanel customPanel = new JPanel();
    int[] customSettings = {10,10,10};
    JTextField rowNum = new JTextField(5);
    rowNum.setText(Integer.toString(customSettings[0]));
		JTextField columnNum = new JTextField(5);
    columnNum.setText(Integer.toString(customSettings[1]));
		JTextField mineNum = new JTextField(5);
    mineNum.setText(Integer.toString(customSettings[2]));

		customPanel.setLayout(new GridLayout(5, 2));
		customPanel.add(new JLabel("Rows: "));
		customPanel.add(rowNum);
		customPanel.add(new JLabel("Columns: "));
		customPanel.add(columnNum);
		customPanel.add(new JLabel("Mines: "));
		customPanel.add(mineNum);

    JOptionPane.showConfirmDialog(this, customPanel, "Game Selection",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null);
    try {
      customSettings[0] = Integer.parseInt(rowNum.getText());
      customSettings[1] = Integer.parseInt(columnNum.getText());
      customSettings[2] = Integer.parseInt(mineNum.getText());
    }
    catch(NumberFormatException ex) {}
    return customSettings;
  }

  public JMenu getFileMenu(MineViewer viewer) {
	  JMenu file = new JMenu("File");
	  JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem quit = new JMenuItem("Quit");

		//load.addActionListener(new MenuListener(MineModel.LOAD, viewer));
		//save.addActionListener(new MenuListener(MineModel.SAVE, viewer));
		//quit.addActionListener(new MenuListener(MineModel.QUIT));

		file.add(load);
		file.add(save);
		file.add(quit);
		return file;
  }

  public JMenu getNewGameMenu(MineViewer view) {
    // The JMenuBar that will Control the Elements

		// The NewGame Menu elements
		JMenu newGame = new JMenu("New Game");
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");
		JMenuItem custom = new JMenuItem("Custom");

		// Adding Action Listener to the elements
		//easy.addActionListener(new MenuListener(GameLogic.NEW_GAME,GameLogic.EASY, frame));
		//medium.addActionListener(new MenuListener(GameLogic.NEW_GAME,GameLogic.MEDIUM, frame));
		//hard.addActionListener(new MenuListener(GameLogic.NEW_GAME,GameLogic.HARD, frame));
		//custom.addActionListener(new MenuListener(GameLogic.NEW_GAME,GameLogic.CUSTOM, frame));

		// Adding them to the newGame Menu
		newGame.add(easy);
		newGame.add(medium);
		newGame.add(hard);
		newGame.add(custom);
		return newGame;
	}

  public void updateField(char[][] mineField, char[][] playField){
    for (int i = 0;i<mineField.length;i++) {
      for (int j = 0;j<mineField[0].length;j++) {
        if (mineField[i][j]=='M') {
          btn[i][j].setIcon(imageMine);
        }
        else if (playField[i][j]=='F') {
          btn[i][j].setIcon(imageFlag);
        }
        else if (mineField[i][j]=='0') {
          btn[i][j].setBackground(superGrey);
        }
        else if (mineField[i][j]=='1') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageOne);
        }
        else if (mineField[i][j]=='2') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageTwo);
        }
        else if (mineField[i][j]=='3') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageThree);
        }
        else if (mineField[i][j]=='4') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageFour);
        }
        else if (mineField[i][j]=='5') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageFive);
        }
        else if (mineField[i][j]=='6') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageSix);
        }
        else if (mineField[i][j]=='7') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageSeven);
        }
        else if (mineField[i][j]=='8') {
          btn[i][j].setBackground(superGrey);
          btn[i][j].setIcon(imageEight);
        }
        else{
          btn[i][j].setIcon(null);
        }
      }
    }
  }

}
