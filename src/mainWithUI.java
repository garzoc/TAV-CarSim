package src;

/**
 * Class for using the UI
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class mainWithUI extends Application{

    //Setups
    Sensor[] sensors = {new Ultrasonic(),new Ultrasonic()};
    Car car = new Car(0, false, sensors, new CarEngine());
    State state = new State(0,false);



    public static void main(String[] args){
        Application.launch(src.mainWithUI.class, (java.lang.String[])null);
    }


    //Start the UI
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane Mainpage = (AnchorPane) FXMLLoader.load(src.mainWithUI.class.getResource("tavCarSimUI.fxml"));
            Scene scene = new Scene(Mainpage);
            primaryStage.setScene(scene);
            primaryStage.setTitle("TAV Car SIM");
            primaryStage.show();
            generateAMap();
        } catch (Exception ex) {
            Logger.getLogger(src.mainWithUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Generate a map from .txt file
    public void generateAMap(){
        double[] map=read("src/map/MAP.txt");
        car.generateMap(map);
    }

    //Read in the .txt file
    public double[]read(String filename){
        try {
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferReader =new BufferedReader(fileReader);
            double[] lines=new double[500];
            //List<Double> lines =new ArrayList<Double>();
            String line=null;
            int i=0;
            while((line=bufferReader.readLine())!=null&&i<500){
                lines[i]=(int)Double.parseDouble(line);
                i++;
            }
            bufferReader.close();
            return lines;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    //Textfied that the car position should be written to
    @FXML
    private TextField positionTextField;

    //Textfied that the car state should be written to
    @FXML
    private TextField stateTextField;


    //Event for when moveForward button is pressed
    public void moveForwardUIEvent(){
        if(!state.isParked) {
            state = car.MoveForward();
        }
        if(state.isParked){
            stateTextField.setText("Parked");
            positionTextField.setText(Integer.toString(state.position));
        }else{
            stateTextField.setText("Not parked");
            positionTextField.setText(Integer.toString(state.position));
        }
    }

    //Event for when moveBackward button is pressed
    public void moveBackwardUIEvent(){
        if(!state.isParked) {
           state = car.MoveBackward();
        }
        if(state.isParked){
            stateTextField.setText("Parked");
            positionTextField.setText(Integer.toString(state.position));
        }else{
            stateTextField.setText("Not parked");
            positionTextField.setText(Integer.toString(state.position));
        }
    }

    //Event for when park button is pressed
    public void parkUIEvent(){
        if(!state.isParked) {
            car.Park();
            state.isParked=true;
        }
        if(state.isParked){
            stateTextField.setText("Parked");
            positionTextField.setText(Integer.toString(state.position));
        }else{
            stateTextField.setText("Not parked");
            positionTextField.setText(Integer.toString(state.position));
        }
    }

    //Event for when unPark button is pressed
    public void unParkUIEvent(){
        if(state.isParked) {
            car.UnPark();
            state.isParked = false;
        }
        if(state.isParked){
            stateTextField.setText("Parked");
            positionTextField.setText(Integer.toString(state.position));

        }else{
            stateTextField.setText("Not parked");
            positionTextField.setText(Integer.toString(state.position));
        }
    }
}