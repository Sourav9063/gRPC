import com.demo.grpc.UserClient;
import com.demo.grpc.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class ClientClass {
    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//        UserClient.LogInRequest.Builder request = UserClient.LogInRequest.newBuilder();
        System.out.println("Waiting to connect to server...");
    ManagedChannel managedchannel=    ManagedChannelBuilder.forAddress("localhost", 1234).usePlaintext().build();
    userGrpc.userBlockingStub stub = userGrpc.newBlockingStub(managedchannel);
        System.out.println("Connected to server");
        System.out.println("To sign up, enter s");
        System.out.println("To log in, enter l");
        System.out.println("To exit, enter e");
        String choice = "";
        while (!choice.equals("e")) {
            System.out.println("Enter your choice");
            choice = new Scanner(System.in).nextLine();
            if (choice.equals("s")) {
                System.out.println("To sign up enter your email");
                String email = new Scanner(System.in).nextLine();
                System.out.println("To sign up enter your password");
                String password = new Scanner(System.in).nextLine();
                UserClient.SignUpRequest.Builder request = UserClient.SignUpRequest.newBuilder();
                request.setEmail(
                        email);
                request.setPassword(password);


                UserClient.SignUpResponse response= stub.signUp( request.build());
                System.out.println(response.getMessage());
                System.out.println(response.getCode());
                System.out.println(response.getToken());

            }
            if (choice.equals("l")) {
                System.out.println("To log in enter your email");
                String email = new Scanner(System.in).nextLine();
                System.out.println("To log in enter your password");
                String password = new Scanner(System.in).nextLine();
                UserClient.LogInRequest.Builder request = UserClient.LogInRequest.newBuilder();
                request.setEmail(
                        email);
                request.setPassword(password);
                UserClient.LogInResponse response= stub.logIn( request.build());
                System.out.println(response.getMessage());
                System.out.println(response.getCode());
                System.out.println(response.getToken());

            }
        }

    }
}
