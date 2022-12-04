package jmail.client.controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jmail.client.Main;
import jmail.lib.models.Email;
import jmail.lib.models.commands.CommandDeleteEmail;
import jmail.lib.models.commands.CommandDeleteEmail.CommandDeleteEmailParameter;
import jmail.lib.models.commands.CommandSendEmail;
import jmail.lib.models.commands.CommandSendEmail.CommandSendEmailParameter;

public class FXMLController implements Initializable {

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

  @FXML private Button NewMailButton;

  @FXML private Button SearchButton;

  @FXML private Button ReplyButton;

  @FXML private Button FwdButton;

  @FXML private Button FwdallButton;

  @FXML private Button TrashButton;

  @FXML
  public void buttonNewMail(javafx.event.ActionEvent e, Email email) {
    // TODO NewMailButton function
  }

  @FXML
  public void buttonSearch(javafx.event.ActionEvent e) {
    // TODO SearchButton function
  }

  @FXML
  public void buttonReply(javafx.event.ActionEvent e, Email email) {
    Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);

    var em =
        new Email(
            UUID.randomUUID().toString(),
            email.subject(),
            email.body(),
            "emmedeveloper@gmail.com", // FIXME: aggiungere uno User.getMail()
            List.of(email.sender()),
            today.getTime(),
            false);

    this.buttonNewMail(e, em); // FIXME: non so se worka?
  }

  @FXML // FIXME: da capire come passare un Email object
  public void buttonFwd(javafx.event.ActionEvent e, Email email, List<String> newRecipients) {
    Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);

    var em =
        new Email(
            UUID.randomUUID().toString(),
            email.subject(),
            email.body(),
            "emmedeveloper@gmail.com", // FIXME: aggiungere uno User.getMail()
            newRecipients,
            today.getTime(),
            false);

    var params = new CommandSendEmailParameter(em);
    Main.getClient().sendCommand(new CommandSendEmail(params));
  }

  @FXML
  public void buttonFwdall(javafx.event.ActionEvent e) {
    // TODO FwdallButton function
  }

  @FXML // FIXME: non so se si può passare un parametro alla funzione
  public void buttonTrash(javafx.event.ActionEvent e, String emailID) {
    var params = new CommandDeleteEmailParameter(emailID);
    Main.getClient().sendCommand(new CommandDeleteEmail(params));
  }
  /*public void setTopText(String text) {
    // set text from another class
    NewMailButton.setText("\n" + text);
  }*/
}
