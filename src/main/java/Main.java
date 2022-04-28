import netscape.javascript.JSObject;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import zmqpubsub.ZmqPublisher;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String jsonString = "{'test1':'value1','test2':{'id':0,'name':'testName'}}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);
        System.out.println(jsonObject);

         ZmqPublisher publisher = new ZmqPublisher("127.0.0.1", "2001");
        for(int i=0; i<10; i++)
        {
            publisher.publish("allData", jsonObject);
            System.out.println("published");
            TimeUnit.SECONDS.sleep(1);

        }

    }
}
