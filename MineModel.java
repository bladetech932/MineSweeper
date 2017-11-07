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
    setPlayField();
    setMine(customMineCount, getColumns(), getRows());
  }
  public MineModel(int gameMode) { //preset mode selection
    this.gameMode = gameMode;
    switch (gameMode) {
      case EASY:
        mineField = new char[easyGridSize][easyGridSize];
        playField = new char[easyGridSize][easyGridSize];
        mineCount = easyMineCount;
        setPlayField();
        setMine(mineCount, getColumns(), getRows());
        break;
      case MEDIUM:
        mineField = new char[medGridSize][medGridSize];
        playField = new char[medGridSize][medGridSize];
        mineCount = medMineCount;
        setPlayField();
        setMine(mineCount, getColumns(), getRows());
        mineCounter(getRows(), getColumns());
        break;
      case HARD:
        mineField = new char[hardGridSize][hardGridSize];
        playField = new char[hardGridSize][hardGridSize];
        mineCount = hardMineCount;
        setPlayField();
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

    if(playField[x][y] !='F') {
      playField[x][y] = 'F';
    }
    else if(playField[x][y] == 'F') {
      playField[x][y] = 'U';
    }

  }
  public void mineCheck(int x, int y) {
    if (mineField[x][y]=='M') {
      playField = mineField;
    }
  }
  public char[][] getMineField() {
    return mineField;
  }
  public char[][] getPlayField(){
    return playField;
  }

  public void setPlayField(){
    for (int i=0;i<playField.length; i++) {
      for (int j=0;j<playField[i].length; j++){
        playField[i][j] = '0';
      }
    }
  }

  public void mineCounter(int x, int y){
    for (int i=0; i<y ; i++ ) {
      for (int j=0; j<x ; j++ ) {

        try {if(mineField[i-1][j] != 'M')   {playField[i-1][j]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i][j+1] != 'M')   {playField[i][j+1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i+1][j] != 'M')   {playField[i+1][j]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i][j-1] != 'M')   {playField[i][j-1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i-1][j-1] != 'M') {playField[i-1][j-1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i-1][j+1] != 'M') {playField[i-1][j+1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i+1][j-1] != 'M') {playField[i+1][j-1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[i+1][j+1] != 'M') {playField[i+1][j+1]++;}}
        catch(IndexOutOfBoundsException e){}
      }
    }
  }

}
