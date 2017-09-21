package seng202.team7;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import com.sun.java.util.jar.pack.PackerImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.util.Observable;


/**
 * Controller for Graph viewing of analytics
 * @author Morgan English
 */
public class TripAnalyticController{
    @FXML private Button userButton;
    @FXML private Button timeButton;
    @FXML private Button genderButton;
    @FXML private Button durButton;
    @FXML private Button distButton;
    @FXML private Button ageButton;
    @FXML private PieChart pie;
    @FXML private BarChart<?,?> bar;
    @FXML private CategoryAxis barX;
    @FXML private NumberAxis barY;
    @FXML private Text title;
    @FXML private TextField datagroupField;


    ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList();

    ObservableList<XYChart.Data<String, Number>> barChartData =
            FXCollections.observableArrayList();

    CategoryAxis categoryAxisX = new CategoryAxis();
    NumberAxis numberAxisY = new NumberAxis();

    DatabaseRetriever databaseRetriever = new DatabaseRetriever();

    /**
     * Creates a PIE graph showing Gender of selected Bike trips
     */
    public void genderGraph()
    {
        bar.setVisible(false);
        Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 254 arial;");
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        System.out.println(dataGroupToSearch);
        pieChartData.clear();
        pieChartData.add(new PieChart.Data("Male",SQLAnalytics.totalGenderTrips("Male",dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("Female", SQLAnalytics.totalGenderTrips("Female",dataGroupToSearch)));
        pieChartData.forEach(data -> data.nameProperty().bind(
                Bindings.concat(data.getName()," ", data.pieValueProperty())
        ));
        pie.setData(pieChartData);
        pie.setVisible(true);


    }


    /**
     * Creates a PIE graph showing Usertype of selected BikeTrips
     */
    public void userGraph()
    {
        bar.setVisible(false);
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        pieChartData.clear();
        pieChartData.add(new PieChart.Data("Customer",SQLAnalytics.totalUserTypeTrips("Customer",dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("Subscriber", SQLAnalytics.totalUserTypeTrips("Subscriber",dataGroupToSearch)));
        pieChartData.forEach(data -> data.nameProperty().bind(
                Bindings.concat(data.getName()," ", data.pieValueProperty())
        ));
        pie.setData(pieChartData);
        pie.setVisible(true);
    }


    /**
     * creates Bar graph of Age
     */
    public void ageGraph()
    {
        bar.setVisible(false);
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        pieChartData.clear();
        pieChartData.add(new PieChart.Data("0-15",SQLAnalytics.totalAgeTrips(0,15,dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("15-25", SQLAnalytics.totalAgeTrips(15,25,dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("25-35",SQLAnalytics.totalAgeTrips(25,35,dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("35-45", SQLAnalytics.totalAgeTrips(35,45,dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("45-55", SQLAnalytics.totalAgeTrips(45,55,dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("55+", SQLAnalytics.totalAgeTrips(55,Integer.MAX_VALUE,dataGroupToSearch)));
        pieChartData.forEach(data -> data.nameProperty().bind(
                Bindings.concat(data.getName()," ", data.pieValueProperty())
        ));
        pie.setData(pieChartData);
        pie.setVisible(true);
    }


    /**
     * creates Bar graph of times by time of day
     */
    public void timeGraph()
    {
        pie.setVisible(false);
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Time of Day");
        barY.setLabel("Number of Trips");
        bar.setTitle("Time of Day Trips Taken");

        barChartData.addAll(
                new XYChart.Data<>("12AM to 3AM", 2),
                new XYChart.Data<>("3AM to 6AM", 3),
                new XYChart.Data<>("6AM to 9AM", 6),
                new XYChart.Data<>("9AM to 12PM", 5),
                new XYChart.Data<>("12PM to 3PM", 5),
                new XYChart.Data<>("3PM to 6PM", 5),
                new XYChart.Data<>("6PM to 9PM", 5),
                new XYChart.Data<>("9PM to 12AM", 5)
        );

        XYChart.Series timeSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(timeSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }

    /**
     * Creates bar graph by duration
     */
    public void durationGraph()
    {
        pie.setVisible(false);
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Duration of Trips");
        barY.setLabel("Number of Trips");
        bar.setTitle("Duration of Trips Taken");

        barChartData.addAll(
                new XYChart.Data<>("0-5 min", SQLAnalytics.totalDurTrips(0,5*60,dataGroupToSearch)),
                new XYChart.Data<>("5-10 min", SQLAnalytics.totalDurTrips(5*60,10*60,dataGroupToSearch)),
                new XYChart.Data<>("10-15 min", SQLAnalytics.totalDurTrips(10*60,15*60,dataGroupToSearch)),
                new XYChart.Data<>("15-30 min", SQLAnalytics.totalDurTrips(15*60,30*60,dataGroupToSearch)),
                new XYChart.Data<>("30-60 min", SQLAnalytics.totalDurTrips(30*60,60*60,dataGroupToSearch)),
                new XYChart.Data<>("60+ min", SQLAnalytics.totalDurTrips(60*60,Integer.MAX_VALUE,dataGroupToSearch))
        );

        XYChart.Series durationSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(durationSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }


    /**
     * Creates bar graph by distance
     */
    public void distanceGraph()
    {
        pie.setVisible(false);
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Distance (km)");
        barY.setLabel("Number of Trips");
        bar.setTitle("Distance of Trips Taken");

        barChartData.addAll(
                new XYChart.Data<>("0 to 0.5", SQLAnalytics.totalDistTrips(0,500,dataGroupToSearch)),
                new XYChart.Data<>("0.5 to 1", SQLAnalytics.totalDistTrips(500,1000,dataGroupToSearch)),
                new XYChart.Data<>("1 to 2.5", SQLAnalytics.totalDistTrips(1000,2500,dataGroupToSearch)),
                new XYChart.Data<>("2.5 to 5", SQLAnalytics.totalDistTrips(2500,5000,dataGroupToSearch)),
                new XYChart.Data<>("5+", SQLAnalytics.totalDistTrips(5000,Integer.MAX_VALUE,dataGroupToSearch))
        );

        XYChart.Series distanceSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(distanceSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }
}
