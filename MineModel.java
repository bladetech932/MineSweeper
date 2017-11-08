class MineModel implements Serializable {

  private static final long serialVersionUID = 42l;

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

  public static final int SAVE = 11;
  public static final int LOAD = 12;
  public static final int QUIT = 13;


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
    setMineField();
    setMines(customMineCount);
  }
  public MineModel(int gameMode) { //preset mode selection
    this.gameMode = gameMode;
    switch (gameMode) {
      case EASY:
        mineField = new char[easyGridSize][easyGridSize];
        playField = new char[easyGridSize][easyGridSize];
        mineCount = easyMineCount;
        break;
      case MEDIUM:
        mineField = new char[medGridSize][medGridSize];
        playField = new char[medGridSize][medGridSize];
        mineCount = medMineCount;
        break;
      case HARD:
        mineField = new char[hardGridSize][hardGridSize];
        playField = new char[hardGridSize][hardGridSize];
        mineCount = hardMineCount;
        break;
      case CUSTOM:
        System.out.println("ERROR");
        break;
    }
    setPlayField();
    setMineField();
    setMines(mineCount);
  }

  //getter methods
  public int getRows() {
    return playField.length;
  }
  public int getColumns() {
    return playField[0].length;
  }
  public char[][] getMineField() {
    return mineField;
  }
  public char[][] getPlayField(){
    return playField;
  }

  public void setFlag(int x, int y) {

    if(playField[x][y] !='F') {
      playField[x][y] = 'F';
    }
    else if(playField[x][y] == 'F') {
      playField[x][y] = 'U';
    }

  }
  public void setPlayField(){
    for (int i=0;i<playField.length; i++) {
      for (int j=0;j<playField[i].length; j++){
        playField[i][j] = 'U';
      }
    }
  }
  public void setMineField(){
    for (int i=0;i<mineField.length; i++) {
      for (int j=0;j<mineField[i].length; j++){
        mineField[i][j] = '0';
      }
    }
  }
  public void setMines(int mineCount){
      int x;
      int y;
      int count = 0;

      while(count < mineCount){
         x = (int)(Math.random()*getColumns());
         y = (int)(Math.random()*getRows());

         if(mineField[y][x] != 'M'){
           mineField[y][x] = 'M';
           mineCounter(x,y);
           count++;
         }
         else{
           continue;
         }
     }
  }

  public void mineCheck(int x, int y) {
    if(mineField[x][y]=='M') {
      mineField[x][y] = 'X';
      playField = mineField;
    }
    else if(mineField[x][y] == '0') {
      playField[x][y] = mineField[x][y];
      showZeros(x,y);
    }
    else {
      playField[x][y] = mineField[x][y];
    }
  }
  public void mineCounter(int x, int y){

        try {if(mineField[y-1][x] != 'M')   {mineField[y-1][x]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y][x+1] != 'M')   {mineField[y][x+1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y+1][x] != 'M')   {mineField[y+1][x]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y][x-1] != 'M')   {mineField[y][x-1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y-1][x-1] != 'M') {mineField[y-1][x-1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y-1][x+1] != 'M') {mineField[y-1][x+1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y+1][x-1] != 'M') {mineField[y+1][x-1]++;}}
        catch(IndexOutOfBoundsException e){}
        try {if(mineField[y+1][x+1] != 'M') {mineField[y+1][x+1]++;}}
        catch(IndexOutOfBoundsException e){}
  }

  public void showZeros(int x, int y){

      for (int i = x-1;i<=x+1 ;i++ ) {
        for (int j= y-1;j<=y+1 ;j++ ) {
          try{
            if(mineField[i][j] == '0'){
              if(playField[i][j] != '0'){
                playField[i][j] = mineField[i][j];
                System.out.println(i);
                System.out.println(j);
                showZeros(i,j);
              }
             }
             else{playField[i][j] = mineField[i][j];}
          }
          catch(Exception ex){
          }
        }
      }

  }

  public void saveGame(){}
  public void loadGame(){}
}
