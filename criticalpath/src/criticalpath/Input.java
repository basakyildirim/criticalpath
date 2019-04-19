
package criticalpath;
import java.util.*;
public class Input
{
    int no_of_nodes;
    public Input(int nodes)
    {
        this.no_of_nodes = nodes;

    }

    public void node_details()
    {
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < no_of_nodes; i++)
        {
            
            System.out.print("Faaliyet Adi : ");
            String name = scan.next();
            System.out.println();
            
            System.out.print("Faaliyet Suresi : ");
            int dur = scan.nextInt();
            List<String> dependency  = new ArrayList<String>();
            while(true)
            {
                System.out.print("Öncül Faaliyet : ");
                String depend = scan.next();
                if(depend.equals("0"))
                {
                    System.out.println();
                    break;
                    
                }
                else
                {
                    dependency.add(depend);
                }
            }
            Node one = new Node(name,dur,dependency );
        }

    }
}