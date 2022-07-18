import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;

public class MathServer extends UnicastRemoteObject implements MathService {

    private volatile int clientCount = 0;

    public MathServer() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + " and " + b + " in the Server");
        return a+b;
    }
 
 
    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Substracting " + a + " and " + b + " in the Server");
        return a-b;
    }
 
 
    public int multiply(int a, int b) throws RemoteException {
        System.out.println("Multiplying " + a + " and " + b + " in the Server");
        return a*b;
    }

    // this method is not remotely accessible as it's not in the remote interface
    public int test(int a){
      System.out.println("this is a test");
      return 0;
    }
 

    public int divide(int a, int b) throws RemoteException {
        System.out.println("Dividing " + a + " and " + b + " in the Server");
        //  TODO: Uncomment this to observe the clients get blocked
        for (double i = 0; i < 10000000000000000.0; i++) {
            System.out.println("I'm doing something that takes a long time.");
        }
        return a/b; // check for division with zero here!
    }

    public synchronized int increaseClientCount() throws RemoteException {
        return ++clientCount;
    }

    public static void main(String[] args){
        // set the policy file as the system security policy
        System.setProperty("java.security.policy", "file:allowall.policy");
        try {
            MathServer svr = new MathServer();
		    // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("CalculatorService", svr);
            System.out.println ("Service started....");
        }
        catch(RemoteException | AlreadyBoundException re) {
            System.err.println(re.getMessage());
        }
    }
}
