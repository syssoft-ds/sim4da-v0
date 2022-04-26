package oxoo2a.sim4da;

public abstract class Node {

    public Node ( int my_id ) {
        id = my_id;
        stop = false;
        t_main = new Thread(this::main);
    }

    public void setNetwork ( Network network ) {
        this.network = network;
    }
    public void start () {
        t_main.start();
    }
    private void sleep ( long millis ) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {};
    }

    protected int myId() {
        return id;
    }

    protected int numberOfNodes() { return network.numberOfNodes(); };

    protected boolean stillSimulating () {
        return !stop;
    }
    protected void sendUnicast ( int receiver_id, String m ) {
        network.unicast(id,receiver_id,m);
    }

    protected void sendUnicast ( int receiver_id, Message m ) {
        network.unicast(id,receiver_id, m.toJson());
    }

    protected void sendBroadcast ( String m ) {
        network.broadcast(id,m);
    }

    protected void sendBroadcast ( Message m ) {
        network.broadcast(id,m.toJson());
    }

    protected Network.Message receive () {
        return network.receive(id);
    }

    // Module implements basic node functionality
    protected abstract void main ();

    public void stop () {
        stop = true;
        try {
            t_main.join();
        }
        catch (InterruptedException ignored) {};
    }

    private final int id;
    private Network network;
    private final Thread t_main;
    private boolean stop;
}
