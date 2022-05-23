package zmqpubsub;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.function.Consumer;

public class ZmqSubscriber
{
    public ZContext context;
    public ZMQ.Socket socket;
    public String bind;

    public ZmqSubscriber(String ip, String port)
    {
        context = new ZContext();
        socket = context.createSocket(SocketType.SUB);
        bind = "tcp://"+ ip + ":" + port;
    }

    public void subscribe (String topic, Consumer<JsonObject> consumer)
    {
        socket.connect(bind);
        socket.subscribe(topic);
        new Thread(() ->
        {
            while (true)
            {
                byte[] recv = socket.recv();
                try
                {
                    String receivedStringMessage = new String(recv);
                    // s1 contains everything after = in the original string (i.e. =....) therefore +1 to remove the =
                    JsonObject jsonObject = (JsonObject) JsonParser.parseString(receivedStringMessage.substring(receivedStringMessage.indexOf(":") + 1));
                    consumer.accept(jsonObject);
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.out);
                    System.out.println("Error: parsing the received data (the data needs to be in JSON format");
                }
            }
        }).start();
    }
}
