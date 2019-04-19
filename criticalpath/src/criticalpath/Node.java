package criticalpath;
import java.util.ArrayList;
import java.util.*;

public class Node
{
    int node_id;
    String node_name;
    List<String> dependency_names;
    int duration;
    int checked;
    static List<Node> details = new ArrayList<Node>();
    List<Node> child_list;
    List<Node> parent_list;
    int early_start;
    int early_finish;
    int latest_start;
    int latest_finish;
    int slag_time;

    public Node ( String node_name, int duration, List<String> dependencies)
    {
     
        this.node_name = node_name;
        this.duration = duration;
        dependency_names = new ArrayList<String>();
        this.dependency_names = dependencies;
        this.early_finish = 0;
        this.early_start = 0;
        this.latest_finish = 0;
        this.latest_start = 0;
        this.slag_time = 0;
        child_list = new ArrayList<Node>();
        parent_list = new ArrayList<Node>();

        details.add(this);
    }

    public Node get_node_by_name(String name)
    {
        Node x = null;
        for(int i = 0; i < details.size(); i++)
        {
            if(details.get(i).node_name.equals(name))
            {
                x = details.get(i);
            }
        }
        return x;
    }

    public static void print_answer()
    {
        tablo ta=new tablo();
        //System.out.printf("|%15s |%15s |%15s |%15s |%15s |%15s|\n","Faaliyet","Erken Baslangic","Erken Bitis","Gec Baslangic","Gec Bitis","slack5");
        int dizi[]=new int[details.size()];
        for(int i = 0; i < details.size(); i++)
        {
            Node x = details.get(i);
           System.out.printf("|%15s |%15d |%15d |%15d |%15d |%15d|\n",x.node_name,x.early_start,x.early_finish,x.latest_start,x.latest_finish,x.slag_time);
                     
        }
         for(int j = 0; j < details.size(); j++)
        {
            Node x = details.get(j);
            dizi[j]=x.latest_finish;
        }
         System.out.println("Proje Suresi:"+dizi[details.size()-1]);
        
    }

    public static void critical_paths(Node m,String path)
    {
        int verify =0;
        if(m.slag_time == 0)
        {
            
            path = path + " -> "+ m.node_name;
          
            verify = 1;
        }
        if(verify == 1)
        {
             
            if(m.child_list.size() == 0)
            {
               // System.out.println(path);
            }
            for(int i = 0; i < m.child_list.size(); i++)
            {
                // System.out.println(path);
                critical_paths(m.child_list.get(i), path);
            }
        }
    }   
}