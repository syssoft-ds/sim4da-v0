# sim4da - Simulator for Distributed Algorithms

The simulator strives to abstract from most of the details in network programming and to ease the development of algorithm simulations. The simulator core is self-contained in a Java module `dev.oxoo2a.sim4da`. The distributed algorithm simulation is controlled by an instance of class `Simulator`:

```Java
    Simulator s = new Simulator(n_nodes);
    for (int id=0; id<n_nodes; id++) {
      Node n = new ApplicationNode(id);
      s.attachNode(id,n);
    }
    s.runSimulation(duration);
```

By instantiating a Simulator object, a network of `n_nodes` is created. For each node id between `[n,n_nodes)` the code for the given id must be attached to the simulator. This `ApplicationNode` is derived from the abstract class `Node` which provides the required functionailty for implementing the algorithm simulation (see below). Finally, the simulation can be executed for `duration` seconds.

Extending class `Node` enables the implementation of the intended distributed algorith by implementing the method `main`:

```Java
    public void main () {
      ...
    }
```

Inside `main`, the following methods are available:

- `myId()`: returns the `id` of the given node
- `sendUnicast(recv_id,String message)`: sending a raw string `message` to node `recv_id`
- `sendUnicast(recv_id,Message message)`: sending a `HashMap`-like message encoded in JSON to node `recv_id`
- `sendBroadcast(String message)`: sending a raw string `message` to all nodes (except the sender itself)
- `sendBroadcast(Message message)`: sending a `HashMap`-like message encoded in JSON to all nodes (except the sender itself)
- `stillSimulating()`: returns `true` while the duration for the simulation is not exceeded
- `receive()`: blocks until a message is received by the node; an object of type `Network.Message` is returned, which stores `sender_id`, `receiver_id`, send mode (unicast or broadcast), and the `payload` as a string. If a `Message` is expected, this payload must be deserialized from JSON into an object of type `Message` by calling `Message.fromJson(payload)`.
