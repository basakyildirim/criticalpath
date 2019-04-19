/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criticalpath;

import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import static yaseminkalpbasak.Node.details;

/**
 * A simple demonstration application showing how to create a horizontal bar chart.
 *
 */
public class BarChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    private static final long serialVersionUID = 1L;
    public BarChartDemo2(final String title) {

        super(title);

        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        setContentPane(chartPanel);
          

    }

  
    public static IntervalCategoryDataset createDataset() {

       
        
        final TaskSeries s1 = new TaskSeries("Faaliyet Suresi");
        for(int j = 0; j < details.size(); j++)
        {
             
            Node x = details.get(j); 
           
            final Task t4 = new Task(x.node_name, new SimpleTimePeriod(x.early_start, x.early_finish));
            s1.add(t4);
            
          
        }
      
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        
         return collection;
       
    }
   
    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
            "Kritik Yol Analizi",  
            "Faaliyet",             
            "Faaliyet Suresi",             
            dataset,             
            true,                
            true,               
            false                
        );    
      
        CategoryPlot plot = chart.getCategoryPlot();

        DateAxis axis = (DateAxis) plot.getRangeAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("S"));
        return chart;   
    }
}