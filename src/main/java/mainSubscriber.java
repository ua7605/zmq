import com.google.gson.JsonObject;
import zmqpubsub.ZmqSubscriber;

public class mainSubscriber
{
    protected static void handleIncomingData(JsonObject jsonObject)
    {
        System.out.println(jsonObject);

        // you can uncomment this if you are working with the included publisher.
//        System.out.println(jsonObject.get("test2"));
//        JsonElement test2 = jsonObject.get("test2");
//        JsonObject jsonObject1 = test2.getAsJsonObject();
//        System.out.println(jsonObject1.get("id"));
//        JsonElement id1 = jsonObject1.get("id");
//        int id1AsInt = id1.getAsInt();
//        System.out.println(id1AsInt);
    }

    public static void main(String[] args)
    {
//        ZmqSubscriber subscriber = new ZmqSubscriber("127.0.0.1", "2001");
        ZmqSubscriber subscriber = new ZmqSubscriber("193.190.127.147", "2001"); // To test it with VS8
        subscriber.subscribe("allData", mainSubscriber::handleIncomingData);
    }

}
