package zmqpubsub;

import com.google.gson.JsonObject;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ZmqPublisher
{
    public ZContext context;
    public ZMQ.Socket socket;
    public String bind;

    public ZmqPublisher(String ip, String port) throws Exception
    {
        context = new ZContext();
        socket = context.createSocket(SocketType.PUB);
        bind = "tcp://"+ ip + ":" + port;
        start();
    }

    public void start()
    {
        socket.bind(bind);
    }

    public void publish(String topic, JsonObject payload)
    {
        socket.send(topic+":"+payload);
    }
}
