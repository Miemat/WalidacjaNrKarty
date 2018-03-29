package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Controller {

    @FXML
    Button button;
    @FXML
    Label label;
    @FXML
    TextField textField;
    @FXML
    FileChooser fileChooser;
    @FXML
    ListView<String> listView;

    public Controller() {

        textField = new TextField();
        label = new Label();
        button = new Button();
        listView = new ListView<>();


    }


    public void checkCreditNumber(javafx.event.ActionEvent actionEvent) {
        System.out.println("checking Credit Number");

        if (textField.getText().matches("^[0-9]+$")) {

            char[] numberCard = textField.getText().toCharArray();
            ArrayList<Integer> numbers;
            numbers = charToArray(numberCard);
            ArrayList<Integer> numbersAfterMulti = new ArrayList<>();
            Integer sum = multiToWeight(numbers);

            if (sum % 10 == 0) {
                label.setText("Poprawny numer karty kredytowej");
                label.setStyle("-fx-background-color: green");
                listView.getItems().add(textField.getText());
            } else {
                label.setText("Bledny numer karty kredytowej");
                label.setStyle("-fx-background-color: red");
            }

        } else {
            System.out.println("blad walidacji//Bledny numer");
            label.setText("Bledny numer karty kredytowej");
            label.setStyle("-fx-background-color: red");
        }
    }

    private Integer multiToWeight(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersAfterMulti = new ArrayList<>();
        boolean weight = true;
        for (Integer i : numbers) {
            if (weight) {
                Integer result = i * 2;
                if (result > 9) {
                    char[] a = result.toString().toCharArray();
                    numbersAfterMulti.addAll(charToArray(a));
                } else {
                    numbersAfterMulti.add(result);
                }
                weight = false;
            } else {
                numbersAfterMulti.add(i);
                weight = true;
            }
        }
        Integer sum = 0;
        for (Integer r : numbersAfterMulti) {
            sum += r;
        }

        return sum;
    }

    private ArrayList<Integer> charToArray(char[] numberCard) {
        ArrayList<Integer> listNumbers = new ArrayList<>();
        for (char i : numberCard) {
            listNumbers.add(Integer.valueOf(String.valueOf(i)));
        }

        return listNumbers;
    }


    public void validate(KeyEvent event) {
        String content = event.getCharacter();

        System.out.println("validation");
        if (!textField.getText().matches("^[0-9]+$")) {
            //when it not matches the pattern (1.0 - 6.0)
            //set the textField empty
            System.out.println("bledny numer");
        } else {
            System.out.println("Poprawny numer");
        }
    }

    public void handleReadFile(ActionEvent actionEvent) throws IOException {

        FileReader fileReader = new FileReader("temp.txt");

        ArrayList<String> resultFile = new ArrayList<>();
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            resultFile.add(line);
            System.out.println(line);
        }

        textField.setText(resultFile.get(resultFile.size() - 1));


        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(resultFile);
        listView.setItems(items);


    }

    public void saveToFile(ActionEvent actionEvent) throws IOException {

        List<String> lines = Arrays.asList("sa", "vv");
        Path file = Paths.get("temp.txt");
        FileReader fileReader = new FileReader("temp.txt");

        ArrayList<String> resultFile = new ArrayList<>();
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            resultFile.add(line);
            System.out.println(line);
        }
        resultFile.add(textField.getText());
        listView.getItems().add(textField.getText());

        Files.write(file, resultFile, Charset.forName("UTF-8"));
    }

    public void handleTest(ActionEvent actionEvent) {
        System.out.println("test");

        ObservableList<String> items = FXCollections.observableArrayList(
                "A", "B", "C", "D");
        listView.setItems(items);
    }
}
