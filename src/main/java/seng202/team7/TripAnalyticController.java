package seng202.team7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.sun.java.util.jar.pack.PackerImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Observable;

public class TripAnalyticController {
    @FXML
    private Button userButton;
    @FXML private Button timeButton;
    @FXML private Button genderButton;
    @FXML private Button durButton;
    @FXML private Button distButton;
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

    public void genderGraph()
    {
        bar.setVisible(false);
        String dataGroupToSearch = ((datagroupField.getText().trim().isEmpty() ? "":datagroupField.getText()));
        System.out.println(dataGroupToSearch);
        pieChartData.clear();
        pieChartData.add(new PieChart.Data("Male",SQLAnalytics.totalGenderTrips("Male",dataGroupToSearch)));
        pieChartData.add(new PieChart.Data("Female", SQLAnalytics.totalGenderTrips("Female",dataGroupToSearch)));
        pie.setData(pieChartData);
        pie.setVisible(true);

    }

    public void userGraph()
    {
        bar.setVisible(false);
        pieChartData.clear();
        pieChartData.add(new PieChart.Data("Customer",130));
        pieChartData.add(new PieChart.Data("Temporary", 5));
        pie.setData(pieChartData);
        pie.setVisible(true);
    }

    public void timeGraph()
    {
        pie.setVisible(false);
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

    public void durationGraph()
    {
        pie.setVisible(false);
        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Duration of Trips");
        barY.setLabel("Number of Trips");
        bar.setTitle("Duration of Trips Taken");

        barChartData.addAll(
                new XYChart.Data<>("0 to 15 min", 2),
                new XYChart.Data<>("15 to 30 min", 3),
                new XYChart.Data<>("30 to 45 min", 6),
                new XYChart.Data<>("45 to 60 min", 5),
                new XYChart.Data<>("1 to 2 hr", 5),
                new XYChart.Data<>("2hr+", 5)
        );

        XYChart.Series durationSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(durationSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }

    public void distanceGraph()
    {
        pie.setVisible(false);
        barChartData.clear();
        bar.getData().clear();
        bar.setAnimated(false);
        barX.setLabel("Distance (km)");
        barY.setLabel("Number of Trips");
        bar.setTitle("Distance of Trips Taken");

        barChartData.addAll(
                new XYChart.Data<>("0 to 1", 2),
                new XYChart.Data<>("1 to 2.5", 3),
                new XYChart.Data<>("2.5 to 5", 6),
                new XYChart.Data<>("5 to 10", 5),
                new XYChart.Data<>("10+", 5)
        );

        XYChart.Series distanceSeries = new XYChart.Series(barChartData);

        bar.getData().addAll(distanceSeries);
        bar.setLegendVisible(false);
        bar.setVisible(true);
    }
}
