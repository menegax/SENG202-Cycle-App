package seng202.team7.Controllers.MainWindowControllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seng202.team7.Database.DatabaseRetriever;
import seng202.team7.Analysis.SQLAnalytics;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.ArrayList;

import static seng202.team7.Datagroup.getDatagroups;


/**
 * Controller for Graph viewing of analytics
 * @author Morgan English
 */
public class TripAnalyticController implements Initializable{

    @FXML private PieChart pie;
    @FXML private BarChart<?,?> bar;
    @FXML private CategoryAxis barX;
    @FXML private NumberAxis barY;
    @FXML private Text title;
    @FXML private TextField datagroupField;
    @FXML private ComboBox<String> typeCombo;
    @FXML private ComboBox dataGroupCombo;

    @FXML public void setDataGroupComboItems() {
        ArrayList<String> list = getDatagroups();
        list.add("All");
        ObservableList<String> items = FXCollections.observableArrayList(list);
        dataGroupCombo.setItems(items);
    }


    ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList();

    ObservableList<XYChart.Data<String, Number>> barChartData =
            FXCollections.observableArrayList();

    CategoryAxis categoryAxisX = new CategoryAxis();
    NumberAxis numberAxisY = new NumberAxis();

    DatabaseRetriever databaseRetriever = new DatabaseRetriever();


    public void initialize(URL url, ResourceBundle rb)
    {
        typeCombo.getItems().addAll("User","Age","Time","Duration","Distance");
        userGraph();
    }
    /**
     * Creates a PIE graph showing Gender of selected Bike trips
     */
    public void genderGraph()
    {
        title.setText("Gender");
        bar.setVisible(false);
        Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 254 arial;");
        String dataGroupToSearch = "";
        try {
            dataGroupToSearch = ((((String) dataGroupCombo.getSelectionModel().getSelectedItem()).trim().isEmpty() ? "" : (String) dataGroupCombo.getValue()));
        } catch (NullPointerException e) { }
        if (dataGroupToSearch.equals("All")) {
            dataGroupToSearch = "";
        }

        System.out.println(dataGroupToSearch);
        pieChartData.clear();
        pieChartData.add(new PieChart.Data("Male", SQLAnalytics.totalGenderTrips("Male",dataGroupToSearch)));
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
        title.setText("User");
        bar.setVisible(false);
        String dataGroupToSearch = "";
        try {
            dataGroupToSearch = ((((String) dataGroupCombo.getSelectionModel().getSelectedItem()).trim().isEmpty() ? "" : (String) dataGroupCombo.getValue()));
            System.out.println(dataGroupToSearch);
        } catch (NullPointerException e) { }
        if (dataGroupToSearch.equals("All")) {
            dataGroupToSearch = "";
        }

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
        title.setText("Age");
        bar.setVisible(false);
        String dataGroupToSearch = "";
        try {
            dataGroupToSearch = ((((String) dataGroupCombo.getSelectionModel().getSelectedItem()).trim().isEmpty() ? "" : (String) dataGroupCombo.getValue()));
        } catch (NullPointerException e) { }
        if (dataGroupToSearch.equals("All")) {
            dataGroupToSearch = "";
        }

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
        title.setText("Time of Day");
        pie.setVisible(false);
        String dataGroupToSearch = "";
        try {
            dataGroupToSearch = ((((String) dataGroupCombo.getSelectionModel().getSelectedItem()).trim().isEmpty() ? "" : (String) dataGroupCombo.getValue()));
        } catch (NullPointerException e) { }
        if (dataGroupToSearch.equals("All")) {
            dataGroupToSearch = "";
        }

        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Time of Day");
        barY.setLabel("Number of Trips");
        //bar.setTitle("Time of Day Trips Taken");

        barChartData.addAll(
                //Times have been adjusted to match timezones
                new XYChart.Data<>("12AM to 3AM", SQLAnalytics.totalTimeTrips(12,15,"")),
                new XYChart.Data<>("3AM to 6AM", SQLAnalytics.totalTimeTrips(15,18,"")),
                new XYChart.Data<>("6AM to 9AM", SQLAnalytics.totalTimeTrips(18,21,"")),
                new XYChart.Data<>("9AM to 12PM", SQLAnalytics.totalTimeTrips(21,24,"")),
                new XYChart.Data<>("12PM to 3PM", SQLAnalytics.totalTimeTrips(0,3,"")),
                new XYChart.Data<>("3PM to 6PM", SQLAnalytics.totalTimeTrips(3,6,"")),
                new XYChart.Data<>("6PM to 9PM", SQLAnalytics.totalTimeTrips(6,9,"")),
                new XYChart.Data<>("9PM to 12AM", SQLAnalytics.totalTimeTrips(9,12,""))
        );

        XYChart.Series timeSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(timeSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }


    /**
     * Shows the graph that is currentyl selected in the drop down
     */
    public void showGraph()
    {
        String graph = "";
        if(typeCombo.getSelectionModel().getSelectedItem() == null)
            return;
        else
            graph = typeCombo.getSelectionModel().getSelectedItem();
        //("User","Age","Time","Duration","Distance")
        switch (graph){
            case("User"):
                userGraph();
                break;
            case("Age"):
                ageGraph();
                break;
            case("Time"):
                timeGraph();
                break;
            case("Duration"):
                durationGraph();
                break;
            case("Distance"):
                distanceGraph();
                break;

        }
    }
    /**
     * Creates bar graph by duration
     */
    public void durationGraph()
    {
        title.setText("Duration");
        pie.setVisible(false);
        String dataGroupToSearch = "";
        try {
            dataGroupToSearch = ((((String) dataGroupCombo.getSelectionModel().getSelectedItem())).trim().isEmpty() ? "" : (String) dataGroupCombo.getValue());
        } catch (NullPointerException e) { }
        if (dataGroupToSearch.equals("All")) {
            dataGroupToSearch = "";
        }

        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Duration of Trips");
        barY.setLabel("Number of Trips");
        //bar.setTitle("Duration of Trips Taken");

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
        title.setText("Distance");
        pie.setVisible(false);
        String dataGroupToSearch = "";
        try {
            dataGroupToSearch = ((((String) dataGroupCombo.getSelectionModel().getSelectedItem()).trim().isEmpty() ? "" : (String) dataGroupCombo.getValue()));
        } catch (NullPointerException e) { }
        if (dataGroupToSearch.equals("All")) {
            dataGroupToSearch = "";
        }

        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Distance (km)");
        barY.setLabel("Number of Trips");
        //bar.setTitle("Distance of Trips Taken");

        barChartData.addAll(
                new XYChart.Data<>("0 to 0.5", SQLAnalytics.totalDistTrips(0,0.5,dataGroupToSearch)),
                new XYChart.Data<>("0.5 to 1", SQLAnalytics.totalDistTrips(0.5,1.0,dataGroupToSearch)),
                new XYChart.Data<>("1 to 2.5", SQLAnalytics.totalDistTrips(1.0,2.5,dataGroupToSearch)),
                new XYChart.Data<>("2.5 to 5", SQLAnalytics.totalDistTrips(2.5,5.0,dataGroupToSearch)),
                new XYChart.Data<>("5+", SQLAnalytics.totalDistTrips(5.0,Integer.MAX_VALUE,dataGroupToSearch))
        );

        XYChart.Series distanceSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(distanceSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }
}
