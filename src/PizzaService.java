import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Cedric on 30.12.2015.
 */
public class PizzaService extends Application {

    // -- Data Stuff -- //
    DataGatherer data = new DataGatherer();
    ObservableList<String> einkaufsWag = FXCollections.observableArrayList();

    // -- Controls -- //
    // Compose Order
    Button addBtn = new Button("Hinzufuegen");
    Button removeBtn = new Button("Entfernen");
    Button cancelBtn = new Button("Abbrechen");
    // Send Order
    Button sendOrdBtn = new Button("Bestellung Senden");
    // Store Order
    ComboBox<String> Pizzen = new ComboBox<String>();
    ListView<String> einkaufsWagView = new ListView<String>(einkaufsWag);



    @Override
    public void start(Stage primaryStage) throws Exception {
        // Fenster daher zaubern
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Pizza Service by Moritz, Milan, Cedric");

        // Elemente ordnen
        setUpUI(root);

        // Combo Box befüllen
        ObservableList<String> pizzaVals = FXCollections.observableArrayList();
        pizzaVals.addAll(data.getPizzas());
        Pizzen.setItems(pizzaVals);
        Pizzen.setValue(pizzaVals.get(0));

        // Eventmanagement einrichten
        setUpEvents();

        primaryStage.show();
    }

    private void setUpEvents() {
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                einkaufsWag.add(Pizzen.getValue());
            }
        });

        removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                einkaufsWag.remove(einkaufsWag.size() - 1);
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                einkaufsWag.clear();
            }
        });
    }

    public static void main(String args[])  {
        PizzaService.launch(args);
    }

    public void setUpUI(StackPane root) {
        // Milan, hier kannst du dann die Ganzen Components aus Controls
        // in root zu ner schönen UI anordnen, müsste dann im Fenster
        // auftauchen ^^
        // Um den ganzen Back End Code kann ich mich kümmern, sorg du
        // dann einfach dafür das des ganze Zeug mit Layouts und so
        // gescheid aussieht.

        // Beispiel
        HBox box = new HBox();
        box.getChildren().addAll(addBtn, removeBtn, cancelBtn, Pizzen, einkaufsWagView);
        root.getChildren().addAll(box);
    }
}
