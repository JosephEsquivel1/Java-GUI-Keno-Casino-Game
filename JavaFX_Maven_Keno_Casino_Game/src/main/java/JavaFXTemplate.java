// JavaFXTemplate
//
// Project 2 Part 2
// Keno JavaFX GUI
// NET ID: Jesqui20 Partner: Asif Rifat, details provided in collaboration doc
// Email: Jesqui20@uic.edu
// Joseph Esquivel, UIC, Spring 2022
//
// Description:
// Implementation of the Keno Casino game, allowing single-player functionality.
/*
    This Keno Casino Game supports a single player.

    The player begins by selecting the number of spots that they want to play when prompted at the start (either 1, 4, 8, or 10 spots).

    The player then fills out a single bet card with the number of spots they have chosen to play, or allows the game to randomly select numbers for them. A bet card is a grid of numbers, 1-80, that the player bets on for each drawing.

    The player then specifies the number of drawings they w9ould like to play their bet card for (1-4 drawings allowed per bet card)

    Drawings then begin, where 20 numbers are drawn at random, between 1 and 80 with no duplicates.

    The player wins by matching a set amount of their numbers from their bet card, to the numbers that are randomly drawn.

    After each drawing, the player will be informed of ho wmany numbers they matched, what those numbers were, and how much they have won on that drawing.

    The player will also be notified of the total they have won since they started the game.

    After the selected amount of drawings have completed, the player will be able to fill out a new bet card with its corresponding spots to play and drawings to play, or they may exit the game.

    Source: https://nclottery.com/KenoHow
*/

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class JavaFXTemplate extends Application {

    Stage start_window;
    Scene scene1, scene2, scene3, currentScene;

    // 1 draw event containing 20 sub-draws
    private int i = 0, j = 0, k = 0, m = 0;
    private final int TOTAL_DRAWINGS = 20;
    private static ArrayList<ToggleButton> tglist = new ArrayList<ToggleButton>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        start_window = primaryStage;

        // this is scene 1 (start scene)
        VBox start_layout = new VBox(50);
        scene1 = new Scene(start_layout, 800, 670);

        // this shows the odds or rules depending on user input
        Text rules_oddsText = new Text("Original Text");

        // this creates the menu and adds the menu items
        Menu kenoMenu = new Menu("Menu");
        MenuBar menuBar = new MenuBar();
        HBox menu = new HBox(10);
        menu.getChildren().add(menuBar);
        menu.setAlignment(Pos.TOP_RIGHT);
        menuBar.getMenus().addAll(kenoMenu);

        MenuItem rules = new MenuItem("Rules");
        MenuItem odds = new MenuItem("Odds Of Winning");
        MenuItem exit = new MenuItem("Exit");

        // rules action event
        rules.setOnAction((ActionEvent) -> {
            rules_oddsText.setText(
                    "Welcome to the Keno Casino Game!\n\n" +
                            "Here's How To Play:\n\n" +
                            "This Keno Casino Game supports a single player.\n\n" +
                            "The player begins by selecting the number of spots that " +
                            "they want to play when prompted at the start (either 1, 4, 8, or 10 spots).\n\n" +
                            "The player then fills out a single bet card with the number of spots they have chosen" +
                            " to play, or allows the game to randomly select numbers for them. A bet card is a grid " +
                            "of numbers, 1-80, that the player bets on for each drawing.\n\n" +
                            "The player then specifies the number of drawings they would like to play their bet card " +
                            "for (1-4 drawings allowed per bet card)\n\n" +
                            "Drawings then begin, where 20 numbers are drawn at random, between 1 and 80 with no " +
                            "duplicates.\n\n" +
                            "The player wins by matching a set amount of their numbers from their bet card, to the " +
                            "numbers that are randomly drawn.\n\n" +
                            "After each drawing, the player will be informed of how many numbers they matched, what " +
                            "those numbers were, and the amount won on that drawing.\n\n" +
                            "The player will also be notified of the total they have won since they started the game.\n\n" +
                            "After the selected amount of drawings have completed, the player will be able to fill out " +
                            "a new bet card with its corresponding spots to play and drawings to play, or they may exit " +
                            "the game.\n\n");
            currentScene = scene1;
            start_window.setScene(scene3);
        });

        // odds action event
        odds.setOnAction((ActionEvent) -> {
            rules_oddsText.setText(
                    "Here Are The Odds For A Game of Keno:\n\n" +
                            "1 Spot Game: 1 in 4.00 Odds\n\n" +
                            "4 Spot Game: 1 in 3.86 Odds\n\n" +
                            "8 Spot Game: 1 in 9.77 Odds\n\n" +
                            "10 Spot Game: 1 in 9.05 Odds\n\n");
            currentScene = scene1;
            start_window.setScene(scene3);
        });

        // exit action event
        exit.setOnAction(e -> start_window.close());

        // add sub menus Rules, Odds, Exit to keno Menu:
        kenoMenu.getItems().addAll(rules);
        kenoMenu.getItems().addAll(odds);
        kenoMenu.getItems().addAll(exit);


        // start button
        Button button1 = new Button("                            Start Game                            ");
        button1.setOnAction(e -> start_window.setScene(scene2));
        button1.setAlignment(Pos.BASELINE_CENTER);
        button1.setStyle("-fx-padding: 10;" + "-fx-background-color: LIGHTCYAN; -fx-text-fill: BLACK; " + "-fx-font: 42.1px \"Serif\";");

        // welcome scene
        Label welcomeText = new Label("Welcome to Keno");
        welcomeText.setStyle("-fx-padding: 40;" + "-fx-background-color: MIDNIGHTBLUE; -fx-text-fill: White; " + "-fx-font: 98.89px \"Serif\";");
        welcomeText.setAlignment(Pos.BASELINE_CENTER);

        start_layout.setStyle("-fx-padding: 200;");
        start_layout.setStyle("-fx-background-color: LIGHTSLATEGREY;" );
        start_layout.getChildren().addAll(menu, welcomeText, button1);


        // scene 2 ***************************************************************************************
        VBox layout2 = new VBox(10);
        scene2 = new Scene(layout2, 800, 670);

        Label amountText = new Label("                                  Bet Amount                                ");
        Label drawText = new Label  ("                                    Draws                                       ");
        Label spotText = new Label  ("                                    Spots                                        ");

        amountText.setStyle("-fx-padding: 5; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        drawText.setStyle("-fx-padding: 5; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        spotText.setStyle("-fx-padding: 5; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label randomInput_Text = new Label("Random Input");

        // back, clear, and draw button
        Button backButton = new Button("                                    Back                                      ");
        backButton.setStyle("-fx-padding: 5.5; -fx-font: 14 px;");

        backButton.setAlignment(Pos.BOTTOM_RIGHT);

        Button clearButton = new Button("                Clear                ");
        Button draw = new Button       ("                Draw                 ");
        draw.setDisable(true);

        // this allows for random input
        CheckBox randomInput_box = new CheckBox();
        randomInput_box.setDisable(true);
        HBox random_box = new HBox(10);
        random_box.getChildren().addAll(randomInput_Text, randomInput_box);

        // menu for scene 2
        MenuBar menuBar2 = new MenuBar();

        // exit
        Menu exit_menu2 = new Menu("Exit");
        MenuItem exitConfirm2 = new MenuItem("Confirm Exit");
        exitConfirm2.setOnAction(e -> start_window.close());
        exit_menu2.getItems().addAll(exitConfirm2);

        // new look
        Menu newLook2 = new Menu("New Look");
        MenuItem Light_Look = new MenuItem("Light");
        MenuItem Dark_Look = new MenuItem("Dark");
        newLook2.getItems().addAll(Light_Look, Dark_Look);

        // empty
        Menu emptyLook = new Menu("                                                                                      "
                + "                                                                                         ");

        menuBar2.getMenus().addAll( newLook2, exit_menu2, emptyLook);
        menuBar2.setStyle("-fx-background-color: LIGHTBLUE; -fx-font: 14px \"Serif\";");

        HBox menu2 = new HBox(10);
        menu2.getChildren().add(menuBar2);


        // Bet Amount Fixed to $1, STILL DISPLAYED
        ToggleGroup amountRadioGroup = new ToggleGroup();

        RadioButton amount1 = new RadioButton("$1 (Fixed Per Draw)");
        amount1.setToggleGroup(amountRadioGroup);
        amount1.setOnAction(e -> KenoGameBackend.setUserBet(1));


        // default
        amountRadioGroup.selectToggle(amount1);

        HBox amountBox = new HBox(20, amount1);


        // set/get draw amount(s)
        ToggleGroup numDrawRadioGroup = new ToggleGroup();

        RadioButton numDraw1 = new RadioButton("1");
        numDraw1.setToggleGroup(numDrawRadioGroup);
        numDraw1.setOnAction(e -> KenoGameBackend.setterNumDraws(20));

        RadioButton numDraw2 = new RadioButton("2");
        numDraw2.setToggleGroup(numDrawRadioGroup);
        numDraw2.setOnAction(e -> KenoGameBackend.setterNumDraws(40));

        RadioButton numDraw3 = new RadioButton("3");
        numDraw3.setToggleGroup(numDrawRadioGroup);
        numDraw3.setOnAction(e -> KenoGameBackend.setterNumDraws(60));

        RadioButton numDraw4 = new RadioButton("4");
        numDraw4.setToggleGroup(numDrawRadioGroup);
        numDraw4.setOnAction(e -> KenoGameBackend.setterNumDraws(80));

        // default
        numDrawRadioGroup.selectToggle(numDraw1);

        HBox numDrawBox = new HBox(20, numDraw1, numDraw2, numDraw3, numDraw4);

        ToggleGroup spotRadioGroup = new ToggleGroup();

        // create the grid (1-80) and disable select
        GridPane gridPane = new GridPane();
        gridPane.setDisable(true);


        RadioButton spot1 = new RadioButton("1");
        spot1.setToggleGroup(spotRadioGroup);
        spot1.setOnAction((ActionEvent) -> {

            gridPane.setDisable(false);
            randomInput_box.setDisable(false);
            draw.setDisable(false);
        });


        RadioButton spot4 = new RadioButton("4");
        spot4.setToggleGroup(spotRadioGroup);
        spot4.setOnAction((ActionEvent) -> {

            gridPane.setDisable(false);
            randomInput_box.setDisable(false);
            draw.setDisable(false);
        });

        RadioButton spot8 = new RadioButton("8");
        spot8.setToggleGroup(spotRadioGroup);
        spot8.setOnAction((ActionEvent) -> {

            gridPane.setDisable(false);
            randomInput_box.setDisable(false);
            draw.setDisable(false);
        });

        RadioButton spot10 = new RadioButton("10");
        spot10.setToggleGroup(spotRadioGroup);
        spot10.setOnAction((ActionEvent) -> {

            gridPane.setDisable(false);
            randomInput_box.setDisable(false);
            draw.setDisable(false);
        });


        HBox spotBox = new HBox(20, spot1, spot4, spot8, spot10);

        for (i = 1; i <= 80; i++) {
            ToggleButton btnNumber = new ToggleButton();
            btnNumber.setText(String.valueOf(i));
            btnNumber.setMinWidth(40);
            btnNumber.setOnAction((ActionEvent) -> {
                KenoGameBackend.storeSpots(btnNumber.getText(), btnNumber.isSelected());
            });

            tglist.add(btnNumber);
            gridPane.add(btnNumber, j, k, 1, 1);

            j++;
            if (i % 8 == 0) {
                k++;
                j = 0;
            }

        }

        // draw result
        Label draw_results = new Label("Draw Result:");
        draw_results.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label draw_output = new Label("N/A");
        draw_output.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label amount_won = new Label("Amount Won:");
        amount_won.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label amount_won_out = new Label("N/A");
        amount_won_out.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label choice_provided = new Label("Choices Provided:");
        choice_provided.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label selected_choice = new Label("N/A");
        selected_choice.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label selectedBetText = new Label("Bet");
        selectedBetText.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label selectedBet = new Label("N/A");
        selectedBet.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label totalBetText = new Label("Total Bet");
        totalBetText.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label totalBet = new Label("N/A");
        totalBet.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        Label itemsMatchedText = new Label("Matched Items");
        Label wonText = new Label("Won");
        Label drawNoText = new Label("Draw");
        Label draw1 = new Label("1");
        Label draw2 = new Label("2");
        Label draw3 = new Label("3");
        Label draw4 = new Label("4");

        Label oneItemsMatched = new Label("N/A");
        Label oneWon = new Label("N/A");

        Label twoItemsMatched = new Label("N/A");
        Label twoWon = new Label("N/A");

        Label threeItemsMatched = new Label("N/A");
        Label threeWon = new Label("N/A");

        Label fourItemsMatched = new Label("N/A");
        Label fourWon = new Label("N/A");

        Label hintUser = new Label("    Try Your Luck    ");
        hintUser.setStyle("-fx-padding: 45; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 34px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        HBox drawBox = new HBox(10);
        drawBox.getChildren().addAll(draw_results, draw_output, amount_won, amount_won_out);

        // the draw
        draw.setOnAction((ActionEvent) -> {

            RadioButton sRB = (RadioButton) spotRadioGroup.getSelectedToggle();
            String str = sRB.getText();

            // check if input is correct
            if (KenoGameBackend.verifySpotCount(str) == true && KenoGameBackend.getterNumDraws() > 0
                    || randomInput_box.isSelected() == true) {

                // executed draw
                KenoGameBackend.incrementDraw();

                RadioButton nRB = (RadioButton) numDrawRadioGroup.getSelectedToggle();
                String nstr = nRB.getText();

                // select
                selected_choice.setText(KenoGameBackend.getSelections());
                selectedBet.setText("$" + KenoGameBackend.getUserBet());
                totalBet.setText("$" + Integer.parseInt(KenoGameBackend.getUserBet()) * Integer.parseInt(nstr));
                hintUser.setText("      Drawing!       ");
                KenoGameBackend.executeRandomDrawings();

                // disable
                amountBox.setDisable(true);
                spotBox.setDisable(true);
                numDrawBox.setDisable(true);
                gridPane.setDisable(true);
                draw.setDisable(true);
                backButton.setDisable(true);
                clearButton.setDisable(true);
                randomInput_box.setDisable(true);

                // draw time
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        draw_output.setText(KenoGameBackend.getDrawResultAtIndex(m));

                        // loop until all draws completed
                        KenoGameBackend.decrementDraw();
                        m++;

                        if (m == 20) {
                            String tempAmount;

                            hintUser.setText("Continue to Next Draw");
                            if (Integer.parseInt(nstr) == KenoGameBackend.getterCurrentDraw()) {
                                hintUser.setText("      Game Over      ");
                                draw.setDisable(true);

                                // enable back and clear button
                                backButton.setDisable(false);
                                clearButton.setDisable(false);

                            } else {
                                draw.setDisable(false);
                            }

                            // Show results
                            tempAmount = KenoGameBackend.getterAmountWon();
                            amount_won_out.setText("$" + KenoGameBackend.getterTotalWinnings());

                            if (KenoGameBackend.getterCurrentDraw() == 1) {
                                oneItemsMatched.setText(KenoGameBackend.getMatches());
                                oneWon.setText("$" + tempAmount);

                            } else if (KenoGameBackend.getterCurrentDraw() == 2) {
                                twoItemsMatched.setText(KenoGameBackend.getMatches());
                                twoWon.setText("$" + tempAmount);

                            } else if (KenoGameBackend.getterCurrentDraw() == 3) {
                                threeItemsMatched.setText(KenoGameBackend.getMatches());
                                threeWon.setText("$" + tempAmount);

                            } else if (KenoGameBackend.getterCurrentDraw() == 4) {
                                fourItemsMatched.setText(KenoGameBackend.getMatches());
                                fourWon.setText("$" + tempAmount);

                            } else {
                                throw new RuntimeException("Illegal Draw");
                            }

                            // Reset
                            KenoGameBackend.clearMatchedStore();
                        }
                    }
                }));


                m = 0;
                timeline.setCycleCount(TOTAL_DRAWINGS - m);
                timeline.play();

            } else {
                // incorrect number of draws selected
                hintUser.setText("  Check Your Spots   ");
            }
        });

        // back button (reset game and go to main menu)
        backButton.setAlignment(Pos.BOTTOM_RIGHT);
        backButton.setOnAction((ActionEvent) -> {
            i = 0;
            j = 0;
            k = 0;

            spotBox.setDisable(false);
            amountBox.setDisable(false);
            numDrawBox.setDisable(false);
            amountRadioGroup.selectToggle(amount1);
            numDrawRadioGroup.selectToggle(numDraw1);
            spotRadioGroup.selectToggle(null);
            draw.setDisable(true);
            draw_output.setText("N/A");
            selected_choice.setText("N/A");
            amount_won_out.setText("N/A");

            oneItemsMatched.setText("N/A");
            twoItemsMatched.setText("N/A");
            threeItemsMatched.setText("N/A");
            fourItemsMatched.setText("N/A");

            totalBet.setText("N/A");
            selectedBet.setText("N/A");

            oneWon.setText("N/A");
            twoWon.setText("N/A");
            threeWon.setText("N/A");
            fourWon.setText("N/A");

            hintUser.setText("    Try Your Luck    ");

            gridPane.setDisable(false);

            for (i = 1; i <= 80; i++) {
                ToggleButton btn = new ToggleButton();
                btn = tglist.get(i - 1);
                if (btn.isSelected())
                    btn.fire();
            }
            gridPane.setDisable(true);

            randomInput_box.setDisable(false);
            if (randomInput_box.isSelected())
                randomInput_box.fire();
            randomInput_box.setDisable(true);

            KenoGameBackend.reinitializeKenoGame();

            // go back to main menu
            start_window.setScene(scene1);
        });

        clearButton.setOnAction((ActionEvent) -> {

            i = 0;
            j = 0;
            k = 0;
            spotBox.setDisable(false);
            amountBox.setDisable(false);
            numDrawBox.setDisable(false);
            amountRadioGroup.selectToggle(amount1);
            numDrawRadioGroup.selectToggle(numDraw1);
            spotRadioGroup.selectToggle(null);
            draw.setDisable(true);
            randomInput_box.setDisable(true);

            draw_output.setText("N/A");
            selected_choice.setText("N/A");
            amount_won_out.setText("N/A");


            oneItemsMatched.setText("N/A");
            twoItemsMatched.setText("N/A");
            threeItemsMatched.setText("N/A");
            fourItemsMatched.setText("N/A");

            totalBet.setText("N/A");
            selectedBet.setText("N/A");

            oneWon.setText("N/A");
            twoWon.setText("N/A");
            threeWon.setText("N/A");
            fourWon.setText("N/A");
            hintUser.setText("   Try Your Luck    ");


            gridPane.setDisable(false);
            // Reset all nodes
            for (i = 1; i <= 80; i++) {
                ToggleButton btn = new ToggleButton();
                btn = tglist.get(i - 1);
                if (btn.isSelected())
                    btn.fire();
            }
            gridPane.setDisable(true);

            randomInput_box.setDisable(false);
            if (randomInput_box.isSelected())
                randomInput_box.fire();
            randomInput_box.setDisable(true);

            KenoGameBackend.reinitializeKenoGame();
        });

        // randomInput_box
        randomInput_box.setOnAction((ActionEvent) -> {

            if (randomInput_box.isSelected() == true) {
                RadioButton sRB = (RadioButton) spotRadioGroup.getSelectedToggle();
                String str = sRB.getText();
                KenoGameBackend.provideRandomInput(str);

                gridPane.setDisable(false);

                for (i = 1; i <= 80; i++) {
                    ToggleButton btn = new ToggleButton();
                    btn = tglist.get(i - 1);
                    if (btn.isSelected())
                        btn.fire();
                }
                gridPane.setDisable(true);

                spotBox.setDisable(true);

            } else {
                gridPane.setDisable(false);
                spotBox.setDisable(false);
            }

        });


        // user interface
        VBox vbox1 = new VBox(10);
        VBox vbox21 = new VBox(10);
        VBox vbox2 = new VBox(10);
        HBox drawAndClear = new HBox(20);

        drawAndClear.getChildren().addAll(draw, clearButton);

        vbox1.getChildren().addAll(backButton, amountText, amountBox, drawText, numDrawBox, spotText, spotBox, gridPane,
                random_box, drawAndClear);

        GridPane tab = new GridPane();
        tab.setHgap(30);
        tab.setVgap(10);

        tab.add(drawNoText, 0, 0, 1, 1);
        tab.add(itemsMatchedText, 2, 0, 1, 1);
        tab.add(wonText, 3, 0, 1, 1);

        tab.add(draw1, 0, 1, 1, 1);
        tab.add(oneItemsMatched, 2, 1, 1, 1);
        tab.add(oneWon, 3, 1, 1, 1);

        tab.add(draw2, 0, 2, 1, 1);
        tab.add(twoItemsMatched, 2, 2, 1, 1);
        tab.add(twoWon, 3, 2, 1, 1);

        tab.add(draw3, 0, 3, 1, 1);
        tab.add(threeItemsMatched, 2, 3, 1, 1);
        tab.add(threeWon, 3, 3, 1, 1);

        tab.add(draw4, 0, 4, 1, 1);
        tab.add(fourItemsMatched, 2, 4, 1, 1);
        tab.add(fourWon, 3, 4, 1, 1);

        HBox betTab = new HBox(20);
        betTab.getChildren().addAll(selectedBetText, selectedBet, totalBetText, totalBet);
        tab.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        vbox21.getChildren().addAll(choice_provided, selected_choice, betTab, tab, hintUser);

        vbox2.getChildren().addAll(drawBox, vbox21);

        HBox hbox12 = new HBox(30);
        hbox12.getChildren().addAll(vbox1, vbox2);

        layout2.getChildren().addAll(menu2, hbox12);
        layout2.setStyle("-fx-padding: 10;");

        // modern look
        Dark_Look.setOnAction((ActionEvent) -> {

            layout2.setStyle("-fx-padding: 10; -fx-background-color: DIMGRAY;");

            menuBar2.setStyle("-fx-background-color: silver; -fx-font: 14px \"Serif\";");

            randomInput_Text.setStyle("-fx-font: 14px \"Serif\"; -fx-font-style: italic;");

            amountText.setStyle("-fx-padding: 5; -fx-background-color: MIDNIGHTBLUE; -fx-text-fill: White; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            drawText.setStyle("-fx-padding: 5; -fx-background-color: MIDNIGHTBLUE; -fx-text-fill: White; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            spotText.setStyle("-fx-padding: 5; -fx-background-color: MIDNIGHTBLUE; -fx-text-fill: White; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            vbox2.setStyle( "-fx-text-fill: Silver;" +"-fx-font: 14px \"Serif\"; -fx-font-style: italic;");

            tab.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            hintUser.setStyle("-fx-padding: 45; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: BLACK; -fx-font: 34px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            for (i = 1; i <= 80; i++) {
                ToggleButton btn = new ToggleButton();
                btn = tglist.get(i - 1);
                btn.setStyle("-fx-text-fill: NAVY; -fx-font: 14 \"Serif\"; -fx-border-color: NAVY;");
            }

            draw_results.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            draw_output.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            amount_won.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            amount_won_out.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            choice_provided.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            selected_choice.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            selectedBetText.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            selectedBet.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            totalBetText.setStyle("-fx-padding: 11; -fx-background-color: GAINESBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            totalBet.setStyle("-fx-padding: 11; -fx-background-color: MEDIUMPURPLE; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

        });

        // default look
        Light_Look.setOnAction((ActionEvent) -> {

            menuBar2.setStyle("-fx-background-color: LIGHTBLUE; -fx-font: 14px \"Serif\";");

            layout2.setStyle("-fx-padding: 10; -fx-background-color: WHITESMOKE;");

            backButton.setAlignment(Pos.BOTTOM_RIGHT);

            hintUser.setStyle("-fx-padding: 45; -fx-background-color: WHITESMOKE; -fx-text-fill: Black; -fx-font: 34px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            tab.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            randomInput_Text.setStyle(null);

            draw.setStyle(null);

            clearButton.setStyle(null);

            backButton.setStyle(null);

            amountText.setStyle("-fx-padding: 5; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            drawText.setStyle("-fx-padding: 5; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            spotText.setStyle("-fx-padding: 5; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            vbox2.setStyle(null);

            for (i = 1; i <= 80; i++) {
                ToggleButton btn = new ToggleButton();
                btn = tglist.get(i - 1);
                btn.setStyle(null);
            }

            draw_results.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            draw_output.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            amount_won.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            amount_won_out.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            choice_provided.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            selected_choice.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            selectedBetText.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            selectedBet.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            totalBetText.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");

            totalBet.setStyle("-fx-padding: 11; -fx-background-color: GAINSBORO; -fx-text-fill: Black; -fx-font: 14px \"Serif\";" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                    + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: Black;");


        });

        /* Scene 3 Starts */
        VBox layout3 = new VBox(10);
        scene3 = new Scene(layout3, 800, 670);

        // Back Button Scene 3
        Button rulesBackButton = new Button("Back");
        backButton.setAlignment(Pos.BOTTOM_RIGHT);
        rulesBackButton.setOnAction(e -> start_window.setScene(currentScene));

        ScrollPane scrollArea1 = new ScrollPane();

        rules_oddsText.wrappingWidthProperty().bind(scene3.widthProperty());
        scrollArea1.setFitToWidth(true);
        scrollArea1.setContent(rules_oddsText);

        layout3.getChildren().addAll(rulesBackButton, scrollArea1);
        layout3.setStyle("-fx-padding: 5;");

        start_window.setScene(scene1);
        start_window.setTitle("KENO");
        start_window.show();
    }
}