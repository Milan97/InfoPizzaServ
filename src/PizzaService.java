import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Cedric on 30.12.2015.
 */
public class PizzaService extends Application {

    // -- Controls -- //
    // Compose Order
    Button addBtn = new Button("Hinzufügen");
    Button removeBtn = new Button("Entfernen");
    Button cancelBtn = new Button("Abbrechen");
    // Send Order
    Button sendOrdBtn = new Button("Bestellung Senden");
    // Store Order
    //TODO Figure out how Combo Boxes work
    //ComboBox<String> Pizzen = new ComboBox<>()

    // -- Data Stuff -- //
    DataGatherer data = new DataGatherer();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Fenster daher zaubern
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Pizza Service by Moritz, Milan, Cedric");

        setUpUI(root);

        primaryStage.show();
    }

    public static void main(String args[])  {
        PizzaService.launch(args);
    }

    public void setUpUI(StackPane root) {
        // Milan, hier kannst du dann die Ganzen Components aus Controls
        // in root zu ner schönen UI verarbeiten, müsste dann im Fenster
        // auftauchen ^^

        // Beispiel
        HBox box = new HBox();
        box.getChildren().addAll(addBtn, removeBtn, cancelBtn);
        root.getChildren().addAll(box);
    }
}
