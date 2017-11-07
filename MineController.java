import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MineController extends MouseAdapter {
  MineModel model;
  MineViewer viewer;
  MineController(){}//empty constructor
  MineController(MineModel model, MineViewer viewer){
    this.model = model;
    this.viewer = viewer;
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
