import javax.swing.JOptionPane;
class MineModel {

  MineViewer viewer;
  public static final int CUSTOM = 0;
  private int customXGrid = 0; //may need later
  private int customYGrid = 0; //may need later
  private int customMineCount = 0;

  public static final int EASY = 1;
  private final int easyGridSize = 8;
  private final int easyMineCount = 10;

  public static final int MEDIUM = 2;
  private final int medGridSize = 16;
  private final int medMineCount = 40;

  public static final int HARD = 3;
  private final int hardGridSize = 24;
  private final int hardMineCount = 99;

  private int gameMode = 0;
  private int mineCount = 0;
  private char[][] mineField;
  private char[][] playField;

  public MineModel() {
  }
  public MineModel(int[] customSettings) { //for custom game selection
    customMineCount = customSettings[2];
    mineField = new char[customSettings[0]][customSettings[1]];
    playField = new char[customSettings[0]][customSettings[1]];
    setMine(customMineCount, getColumns(), getRows());
  }
  public MineModel(int gameMode) { //preset mode selection
    this.gameMode = gameMode;
    switch (gameMode) {
      case EASY:
        mineField = new char[easyGridSize][easyGridSize];
        playField = new char[easyGridSize][easyGridSize];
        mineCount = easyMineCount;
        setMine(mineCount, getColumns(), getRows());
        break;
      case MEDIUM:
        mineField = new char[medGridSize][medGridSize];
        playField = new char[medGridSize][medGridSize];
        mineCount = medMineCount;
        setMine(mineCount, getColumns(), getRows());
        break;
      case HARD:
        mineField = new char[hardGridSize][hardGridSize];
        playField = new char[hardGridSize][hardGridSize];
        mineCount = hardMineCount;
        setMine(mineCount, getColumns(), getRows());
        break;
      case CUSTOM:
        System.out.println("ERROR");
        break;
    }
  }

  //getter methods
  public int getRows() {
    return playField.length;
  }
  public int getColumns() {
    return playField[0].length;
  }
  public void setMine(int mineCount, int height, int width){
      int x;
      int y;
      int count = 0;

      while(count < mineCount){
         x = (int)(Math.random()*width);
         y = (int)(Math.random()*height);

         if(mineField[x][y] != 'M'){
           mineField[x][y] = 'M';
           count++;
         }
         else{
           continue;
         }
     }
  }

  public void setFlag(int x, int y) {
<<<<<<< HEAD
    if(playField[x][y] !='F'){
        playField[x][y] = 'F';
    }else{playField[x][y] = 'U';}
=======
    playField[x][y] = 'F';
>>>>>>> e44ef543d106d52cd90c2576f5a8892310e8b948
  }
  public void mineCheck(int x, int y) {
    if (mineField[x][y]=='M') {
      playField = mineField;
    }
  }
  public char[][] getMineField(){
    return mineField;
  }
  public char[][] getPlayField(){
    return playField;
  }

}
