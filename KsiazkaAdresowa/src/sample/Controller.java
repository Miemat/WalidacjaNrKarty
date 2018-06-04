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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {

    @FXML
    Button button;
    @FXML
    Label label;
    @FXML
    TextField textField;
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

        checkCreditNumberFromInput(textField.getText());
    }

    private void checkCreditNumberFromInput(String number) {
        if (number.matches("^[0-9]+$")) {

            char[] numberCard = number.toCharArray();
            ArrayList<Integer> numbers;
            numbers = charToArray(numberCard);
            ArrayList<Integer> numbersAfterMulti = new ArrayList<>();
            Integer sum = multiToWeight(numbers);

            if (sum % 10 == 0) {
                ArrayList<String> resultFile = new ArrayList<>();
                resultFile.add(number);
                ObservableList<String> items = FXCollections.observableArrayList();
                items.addAll(resultFile);
                listView.setItems(items);
                label.setText("Poprawny numer karty kredytowej");
            } else {
                label.setText("Bledny numer karty kredytowej");
            }
        } else {
            System.out.println("blad walidacji//Bledny numer");
            label.setText("Bledny numer karty kredytowej");
            label.setStyle("-fx-background-color: red");
        }
    }

    private boolean checkCreditNumberFromFile(String number) {
        if (number.matches("^[0-9]+$")) {

            char[] numberCard = number.toCharArray();
            ArrayList<Integer> numbers;
            numbers = charToArray(numberCard);
            ArrayList<Integer> numbersAfterMulti = new ArrayList<>();
            Integer sum = multiToWeight(numbers);

            if (sum % 10 == 0) {

                return true;
            }
        }
        return false;
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

        System.err.println("validation");
        if (!textField.getText().matches("^[0-9]+$")) {
            System.out.println("bledny numer");
        } else {
            System.out.println("Poprawny numer");
        }
    }

    public void handleReadFile(ActionEvent actionEvent) throws IOException {

        BufferedReader reader = null;
        try {
            FileReader fileReader = new FileReader("temp.txt");

            ArrayList<String> resultFile = new ArrayList<>();
            reader = new BufferedReader(fileReader);
            String line;
            ObservableList<String> items = FXCollections.observableArrayList();
            while ((line = reader.readLine()) != null) {

                if (checkCreditNumberFromFile(line)) {
                    items.add(line);
                }
                System.out.println(line);
            }
            if (items != null) {
                listView.setItems(items);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            reader.close();
        }
    }

    public void handleTest(ActionEvent actionEvent) {
        System.out.println("test");

        ObservableList<String> items = FXCollections.observableArrayList(
                "A", "B", "C", "D");
    }
}