import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.PUB);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted())
            {
                System.out.println("waiting for message");
                byte[] reply = socket.recv(0);

                // Print the message
                System.out.println("Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                );
            }

        }

    }
}
