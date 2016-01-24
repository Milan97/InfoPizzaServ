import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    Button cancelBtn = new Button("Leeren");
    //Setup Pizza
    Button sizeBtn = new Button("Finish");
    ComboBox<String> pizzaSize = new ComboBox<String>();
    // Send Order
    Button sendOrdBtn = new Button("Bestellung Senden");
    // Store Order
    ComboBox<String> Pizzen = new ComboBox<String>();
    ListView<String> einkaufsWagView = new ListView<String>(einkaufsWag);
    //Label
    Label sizes = new Label("Wähle deine gewuenschte Pizza-Größe");  


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
        
        //Combo Box Size befuellen
        ObservableList<String> pizzaSizes = FXCollections.observableArrayList();
        pizzaSizes.addAll("Small", "Medium", "Family", "Party");
        pizzaSize.setItems(pizzaSizes);
        pizzaSize.setValue(pizzaSizes.get(0));
    
        // Eventmanagement einrichten
        setUpEvents();

        primaryStage.show();
    }

    private void setUpEvents() {
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
        public void handle(ActionEvent event) { 
        Stage pizzasetup = new Stage();
        pizzasetup.setTitle("Deine Pizza");
        pizzasetup.setWidth(250);
        pizzasetup.setHeight(400);
        pizzasetup.setResizable(false);
        pizzasetup.setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
            public void handle (WindowEvent event) {
            einkaufsWag.add(Pizzen.getValue());  
          }
          
          
          
          
        });
        
        VBox comboLabel = new VBox(20);
        comboLabel.getChildren().addAll(sizes, pizzaSize);
        
        VBox finishbtn = new VBox(20);
        finishbtn.setAlignment(Pos.BOTTOM_CENTER);
        finishbtn.getChildren().addAll(sizeBtn);
        
        VBox settings = new VBox(200);
        settings.setPadding(new Insets(10,10,10,10));
        settings.getChildren().addAll(comboLabel, finishbtn);
        
        pizzasetup.setScene(new Scene(settings));
        pizzasetup.show();
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

        sendOrdBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Quittungs Fenster erstellen
                Stage quittung = new Stage();
                quittung.setTitle("Quittung");
                quittung.setWidth(250);
                quittung.setHeight(700);
                quittung.setResizable(false);
                quittung.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        einkaufsWag.clear();
                    }
                });
                VBox mainBox = new VBox(); //TODO: Scrollbarkeit einführen

                // Schrift erstellen
                Text textComponent = new Text();
                textComponent.setFont(Font.font(java.awt.Font.MONOSPACED));
                textComponent.setWrappingWidth(240);

                // Überschrift schreiben
                String text = "";
                text += "\n" +
                        "================================\n" +
                        " Ihre Quittung:\n" +
                        "================================\n\n\n";

                // Pizzen schreiben
                for (String pizza : einkaufsWag) {
                    // Schreibe eine Reihe für jede Pizza

                    // Variablen initiern
                    String row = "";
                    String price = String.format("%.2f", data.getPrice(pizza));

                    // Reihe schreiben
                    row += " " + pizza;
                    for (int i = 0, n = 31-pizza.length()-price.length(); i < n; i++)
                        row += " ";
                    row += price;

                    text += row + "\n\n";
                }

                // Gesamtpreis berechnen
                double totalPrice = 0.0;
                for (String pizza : einkaufsWag) {
                    totalPrice += data.getPrice(pizza);
                }

                // Zusammenfassung schreiben
                String summary= "";
                String totalDiscript = "TOTAL:";
                String totalPriceStr = String.format("%.2f", totalPrice);

                summary += "\n--------------------------------\n\n";
                summary += " " + totalDiscript;
                for (int i = 0, n = 31-totalDiscript.length()-totalPriceStr.length(); i < n; i++)
                    summary += " ";
                summary += totalPriceStr;

                summary += "\n\n\n" +
                        " Milan, Moritz und Cedric danken\n" +
                        " ihnen vielmals fuer ihren Ein-\n" +
                        " kauf bei IngoPizza.\n\n" +
                        " Auf Wiedersehen! ^^";

                text += summary;


                // Componenten zu mainBox hinzufügen
                textComponent.setText(text);
                mainBox.getChildren().add(textComponent);

                // Fenster Zeigen
                quittung.setScene(new Scene(mainBox));
                quittung.show();
            }
        });
    }

    public static void main(String args[])  {
        PizzaService.launch(args);
    }

    public void setUpUI(StackPane root) {
        Label label = new Label("Hier findest du Unsere Pizzen");
        Label orderText = new Label ("Dein Einkaufswagen");

        VBox ourPizzas = new VBox(10);
        ourPizzas.getChildren().addAll (label, Pizzen);

        VBox yourOrder = new VBox(10);
        yourOrder.getChildren().addAll(orderText, einkaufsWagView);

        VBox order = new VBox(20);
        order.getChildren().addAll(ourPizzas, yourOrder);

        VBox buttons = new VBox(10);
        buttons.setPadding(new Insets (100, 0, 0, 0));
        buttons.getChildren().addAll(addBtn, removeBtn);

        HBox first = new HBox(10);
        first.getChildren().addAll(order, buttons);

        HBox second = new HBox(20);
        second.getChildren().addAll(sendOrdBtn, cancelBtn);

        VBox background = new VBox(10);
        background.getChildren().addAll(first, second);




        root.getChildren().addAll(background);
        root.setPadding(new Insets(10, 20, 20,20));
    }
}
