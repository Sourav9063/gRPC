import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.userService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GrpcServer {
    private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());

    public static void main(String[] args) {
        Database database =  Database.getInstance();
        database.insertUser(Hash.getHashed("admin1"), "admin");
        database.insertUser(Hash.getHashed("user2"), "user");
        database.close();
//        Server server = ServerBuilder.forPort(1234).addService(new userService()).build();
//        try {
//            server.start();
//            logger.info("Server started, listening on " + server.getPort());
//        } catch (IOException e) {
//            logger.severe("Server failed to start");
//            e.printStackTrace();
//        }
//        try {
//            server.awaitTermination(120, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
