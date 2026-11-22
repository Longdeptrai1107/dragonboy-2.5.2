package nro;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import main.GameCanvas;
import main.GameMidlet;

final class TextBoxCommandListener implements CommandListener {
   private TField textField;
   private final TextBox textBox;

   TextBoxCommandListener(TField textFieldValue, TextBox textBoxValue) {
      this.textField = textFieldValue;
      this.textBox = textBoxValue;
   }

   public final void commandAction(Command command, Displayable displayable) {
      if (command.getLabel().equals(mResources.OK)) {
         this.textField.setText(this.textBox.getString());
      }

      MotherCanvas.showGameCanvas(GameMidlet.instance);
      GameCanvas.instance.setFullScreenMode(true);
      this.textField.justReturnFromTextBox = true;
   }
}
