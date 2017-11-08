import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MineController extends MouseAdapter implements ActionListener {
  MineModel model;
  MineViewer viewer;
  int menuItem;

  MineController(){}//empty constructor

  MineController(MineModel model, MineViewer viewer) {
    this.model = model;
    this.viewer = viewer;
  }
  MineController(int menuItem, MineViewer viewer) {
    this.menuItem = menuItem;
    this.viewer = viewer;
  }
  MineController(int menuItem){
    this.menuItem = menuItem;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if(menuItem == MineModel.SAVE) {
      model.loadGame();
    }
    else if(menuItem == MineModel.LOAD) {
      model.saveGame();
    }
    else if(menuItem == MineModel.QUIT) {
      System.exit(0);
    }
    else if(menuItem == MineModel.CUSTOM) {
				 int[] gameSettings = viewer.initCustomGame();
				 MineModel model = new MineModel(gameSettings);
         viewer.newGame(model);
		}
    else {
				MineModel model = new MineModel(menuItem);
        viewer.newGame(model);
		}
  }
  public void mouseReleased(MouseEvent e) {

    Object source = e.getSource();
    JButton tempBtn = (JButton) e.getComponent();
    String str = tempBtn.getName();
    int index = str.indexOf(',');
    int x = Integer.parseInt(str.substring(0,index));
    int y = Integer.parseInt(str.substring(index+1,str.length()));

    if (SwingUtilities.isLeftMouseButton(e) == true) {
      System.out.println("Left ");
      System.out.println(tempBtn.getName());
      model.mineCheck(x,y);
    }
    if (SwingUtilities.isRightMouseButton(e) == true) {
      System.out.println(tempBtn.getName());
      model.setFlag(x,y);
    }
    viewer.updateField(model.getMineField(),model.getPlayField());
  }
}
