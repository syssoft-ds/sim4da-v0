package oxoo2a.broadcastapp;

import oxoo2a.sim4da.Simulator;
import oxoo2a.sim4da.Node;

public class Main {

    public static void main(String[] args) throws InstantiationException {
        if (args.length != 2) {
            System.out.println("arguments: <number of nodes> <duration in seconds>");
            System.exit(-1);
        }
        int n_nodes = Integer.parseInt(args[0]);
        int duration = Integer.parseInt(args[1]); // in seconds

        System.out.printf("Simulate %d nodes for %d seconds\n",n_nodes,duration);

        Simulator s = new Simulator(n_nodes);
        for (int id=0; id<n_nodes; id++) {
            Node n = new BroadcastNode(id);
            s.attachNode(id,n);
        }
        s.runSimulation(duration);
    }
}
