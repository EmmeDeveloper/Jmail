package jmail.server;

import io.github.mimoguz.custom_window.DwmAttribute;
import io.github.mimoguz.custom_window.StageOps;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jmail.lib.logger.ObservableStreamAppender;
import jmail.server.controllers.FXMLMainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

  public static void main(String... args) throws IOException {

    Properties properties = new Properties();
    properties.load(
        Objects.requireNonNull(
            Main.class.getClassLoader().getResourceAsStream("server.properties")));

    Server server = new Server(Integer.parseInt(properties.getProperty("port")));
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("server.fxml"));
    Parent root = loader.load();

    FXMLMainController mainController = loader.getController();

    ObservableStreamAppender.getObservable()
        .addListener((observable, oldValue, newValue) -> mainController.setTopText(newValue));
    Scene newScene = new Scene(root);
    Stage newStage = new Stage();
    newStage.setScene(newScene);

    newStage.setTitle("SERVER");
    newStage.getIcons().add(new Image("logo.png"));
    Platform.runLater(
        () -> {
          final var handle = StageOps.findWindowHandle(newStage);
          // Optionally enable the dark mode:
          StageOps.dwmSetBooleanValue(handle, DwmAttribute.DWMWA_USE_IMMERSIVE_DARK_MODE, true);
          // Enable the mica material
          // DWMWA_SYSTEMBACKDROP_TYPE method is the newer way:
          if (!StageOps.dwmSetIntValue(
              handle,
              DwmAttribute.DWMWA_SYSTEMBACKDROP_TYPE,
              // There is also DWMSBT_TABBEDWINDOW option, which gives a more translucent look.
              DwmAttribute.DWMSBT_MAINWINDOW.value)) {
            // This is the "old" way:
            StageOps.dwmSetBooleanValue(handle, DwmAttribute.DWMWA_MICA_EFFECT, true);
          }
        });

    newStage.show();
    LOGGER.debug("oh oh errore di prova");
    LOGGER.info("informazione importantissima");
    LOGGER.warn("avvertimento! 123 \"ciao\"");
    LOGGER.error("errore! https://ilpartitocomunista.it/");
  }

  @Override
  public void stop() {}
}
