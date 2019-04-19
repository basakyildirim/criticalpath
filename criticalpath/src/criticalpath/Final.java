
package criticalpath;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JPanel;
import org.jfree.ui.RefineryUtilities;


public class Final extends Throwable
{      
    public static void main(String[] args)
    {  
    
               
        System.out.print("Faaliyet Sayisi : ");
        Scanner scan = new Scanner(System.in);
        Input takeInput = new Input(scan.nextInt());
        takeInput.node_details();
        
        Final f = new Final(); 
        Graph_formation graph = new Graph_formation();     
        graph.graph_design();     
        f.forward(graph);
        f.backtrack(graph);
        Node.print_answer();
        f.cpf(graph);
     
        BarChartDemo2 ganttdemo = new BarChartDemo2("Kritik Yol Analizi");
        ganttdemo.pack();
        RefineryUtilities.centerFrameOnScreen(ganttdemo);
        ganttdemo.setVisible(true);
               
    }

       
    public void forward(Graph_formation one)
    {
        Queue<Node> q = new LinkedList<Node>();
        for(int i = 0; i < one.root.size(); i++)
        {
            q.add(one.root.get(i));
            
        }
     
        while(q.size() != 0)
        {
            int verified = 0;
            Node x = q.remove();
            one.early_times_calculator(x);
         
            for(int i = 0; i < x.child_list.size(); i++)
            {
                Node child = x.child_list.get(i);
                for(int k = 0; k < child.parent_list.size(); k++)
                {
                    Node parent = child.parent_list.get(k);
                    if(parent.checked != 1)
                    {
                        verified = 0;
                        break;
                    }
                    verified = 1;
                }
                if(verified != 0)
                {
                    q.add(child);
                }
            }
        }
    }  

    public void backtrack(Graph_formation one)
    {
        Queue<Node> q = new LinkedList<Node>();
        int global_finish= 0;
        for(int i = 0; i < one.end_root.size(); i++)
        {
            q.add(one.end_root.get(i));
         
            if(one.end_root.get(i).early_finish > global_finish)
            {
                global_finish = one.end_root.get(i).early_finish;
            }

        }
        
        while(q.size() != 0)
        {
            int verified = 0;
            Node x = q.remove();
           
            one.latest_times_calculator(x, global_finish);
            for(int i = 0; i < x.parent_list.size(); i++)
            {
                Node parent = x.parent_list.get(i);
                
                for(int k = 0; k < parent.child_list.size(); k++)
                {
                    Node child = parent.child_list.get(k);
                    if(child.checked != 0)
                    {
                        verified = 0;
                        break;
                    }
                    verified = 1;
                }
                if(verified != 0)
                {
                    q.add(parent);
                    
                }
            }
        }
    }

    public void cpf(Graph_formation one)
    {
        for(int i = 0; i < one.root.size(); i++)
        {
            Node.critical_paths(one.root.get(i), "");
        }
    }
}