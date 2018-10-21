package org.primefaces.examples;
 
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class ChartView{
 
    private LineChartModel lineModel1;
    
     
    @PostConstruct
    public void init() {
    	createLineModels();
    }
 
   
     
    @SuppressWarnings("unused")
	private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Statistique de Vente");
        lineModel1.setAnimate(true);
        lineModel1.setLegendPosition("se");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(1000);
         
       
    }
     
    public LineChartModel getLineModel1() {
		return lineModel1;
	}



	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Ordinateur");
 
        series1.set(1, 200);
        series1.set(2, 134);
        series1.set(3, 30);
        series1.set(4, 65);
        series1.set(5, 277);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Television");
 
        series2.set(1, 600);
        series2.set(2, 300);
        series2.set(3, 231);
        series2.set(4, 341);
        series2.set(5, 250);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
 
}