package jmail.client.models.model;

import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jmail.lib.models.Email;
import jmail.lib.models.User;

public class DataModel {

  private static final DataModel instance = new DataModel();
  private final ObjectProperty<User> currentUser;
  private final SimpleStringProperty currentFolder; // Enum: inbox, sent, trash
  private final ObservableList<Email> inbox;
  private final ObservableList<Email> sent;
  private final ObservableList<Email> trash;
  private final ObjectProperty<Email> currentEmail;

  // TODO: Preferiti, bozze, etichette
  private final SimpleBooleanProperty serverStatusConnected;

  private DataModel() {
    currentUser = new SimpleObjectProperty<>();
    currentFolder = new SimpleStringProperty();

    inbox = FXCollections.emptyObservableList();
    sent = FXCollections.emptyObservableList();
    trash = FXCollections.emptyObservableList();

    currentEmail = new SimpleObjectProperty<>();
    serverStatusConnected = new SimpleBooleanProperty();
  }

  // get instance
  public static DataModel getInstance() {
    return instance;
  }

  // get current user observable
  public ObservableObjectValue<User> currentUser() {
    return currentUser;
  }

  // set current user
  public void setCurrentUser(User user) {
    currentUser.set(user);
  }

  // get current folder observable string property
  public ObservableStringValue currentFolder() {
    return currentFolder;
  }

  // set current folder
  public void setCurrentFolder(String folder) {
    currentFolder.set(folder);
  }

  // get inbox observable list
  public ObservableList<Email> getInbox() {
    return inbox;
  }

  // set inbox
  public void setInbox(List<Email> emails) {
    inbox.setAll(emails);
  }

  // get sent observable list
  public ObservableList<Email> getSent() {
    return sent;
  }

  // set sent
  public void setSent(List<Email> emails) {
    sent.setAll(emails);
  }

  // get trash observable list
  public ObservableList<Email> getTrash() {
    return trash;
  }

  // set trash
  public void setTrash(List<Email> emails) {
    trash.setAll(emails);
  }

  // get current email observable
  public ObservableObjectValue<Email> currentEmail() {
    return currentEmail;
  }

  // set current email
  public void setCurrentEmail(Email email) {
    currentEmail.set(email);
  }

  // get server status connected observable
  public Observable serverStatusConnected() {
    return serverStatusConnected;
  }

  // set server status connected
  public void setServerStatusConnected(boolean connected) {
    serverStatusConnected.set(connected);
  }
}
