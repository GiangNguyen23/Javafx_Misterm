package com.example.projectcomic;

import com.example.projectcomic.DBConnect.ConnectDatabase;
import com.example.projectcomic.model.Comic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private static final String EMPTY = "";
    @Override
    public void start(Stage stage) throws IOException {

        GridPane root = new GridPane();
        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Comic> bookList = connect.getComic();
        root.setPadding(new Insets(30));
        root.setHgap(20);
        root.setVgap(20);

        //Add
        root.add(new Label("Name Book:"), 0, 0);
        var tfNameC = new TextField();
        root.add(tfNameC, 0, 1);

        root.add(new Label("Author:"), 1, 0);
        var tfAuthor = new TextField();
        root.add(tfAuthor, 1, 1);

        root.add(new Label("Quantity:"),2,  0);
        var tfQuantity = new TextField();
        root.add(tfQuantity, 2, 1);

        root.add(new Label("Price:"), 3, 0);
        var tfPrice = new TextField();
        root.add(tfPrice, 3, 1);

        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String nameC = tfNameC.getText();
            String author = tfAuthor.getText();
            Integer quantity = Integer.valueOf(tfQuantity.getText());
            Double price = Double.valueOf(tfPrice.getText());
            if (!nameC.equals(EMPTY) && !author.equals(EMPTY) && !quantity.equals(EMPTY) && !price.equals(EMPTY)) {
                connect.insertBook(new Comic(nameC, author, quantity, price));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        root.add(btnAdd, 4, 1);

        //Display
        for(int i = 0; i < bookList.size(); i++){

            root.add(new Label (bookList.get(i).getNameC()), 0, i+2);
            root.add(new Label (bookList.get(i).getAuthor()), 1, i+2);
            root.add(new Label (" "+ bookList.get(i).getQuantity()), 2, i+2);
            root.add(new Label (" "+ bookList.get(i).getPrice()), 3, i+2);

            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), Insets.EMPTY)));
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int idNew = Integer.parseInt(btnUpdate.getId());

                TextField tfName = (TextField) root.getChildren().get(1);
                tfName.setText("" + bookList.get(idNew).getNameC());

                TextField tfAuthorC = (TextField) root.getChildren().get(3);
                tfAuthorC.setText("" + bookList.get(idNew).getAuthor());

                TextField tfQuantityC = (TextField) root.getChildren().get(5);
                tfQuantityC.setText("" + bookList.get(idNew).getQuantity());

                TextField tfPriceC = (TextField) root.getChildren().get(7);
                tfPriceC.setText("" + bookList.get(idNew).getPrice());

                var btnAddNew = new Button("Update");
                btnAddNew.setPadding(new Insets(5, 15, 5, 15));

                btnAddNew.setOnAction(actionEvent -> {
                    Integer id_New = bookList.get(idNew).id;
                    System.out.println(id_New);
                    String nameCNew = tfName.getText();
                    System.out.println(nameCNew);
                    String authorNew = tfAuthorC.getText();
                    Integer quantityNew = Integer.valueOf(tfQuantityC.getText());
                    Double priceNew = Double.valueOf(tfPriceC.getText());
                    if (!id_New.equals(EMPTY) && !nameCNew.equals(EMPTY) && !quantityNew.equals(EMPTY) && !priceNew.equals(EMPTY)) {
                        connect.updateBook(new Comic(id_New, nameCNew, authorNew, quantityNew, priceNew));
                        try {
                            start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                root.add(btnAddNew, 4, 1);
            });
            root.add(btnUpdate, 4, i+2);

             //Delete
            var btnDelete = new Button("Delete");
            btnDelete.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), Insets.EMPTY)));
            btnDelete.setId(String.valueOf(bookList.get(i).id));
            btnDelete.setOnAction(e -> {
                int idDel = Integer.parseInt(btnDelete.getId());
                connect.deleteBook(idDel);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Do you want to delete it?");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
           root.add(btnDelete, 5, i+2);
        }

        Scene scene = new Scene(root);
        stage.setTitle("Manage comic book");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}