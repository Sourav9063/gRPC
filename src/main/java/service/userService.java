package service;

import com.demo.grpc.User;
import com.demo.grpc.userGrpc;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class userService extends userGrpc.userImplBase{
    private static final Logger logger= Logger.getLogger(userService.class.getName());

    @Override
    public void logIn(User.LogInRequest request, StreamObserver<User.LogInResponse> responseObserver) {
        String userName=request.getUsername();
        String password=request.getPassword();

        User.LogInResponse.Builder response=User.LogInResponse.newBuilder();
        if(userName.equals("admin")&&password.equals("admin")){

            response.setMessage("Log in successfully");
            response.setCode("200");
            response.setToken("Token");
            logger.info("Log in successfully");


        }
        else{
            response.setMessage("Log in failed");
            response.setCode("400");
            response.setToken("");
            logger.info("Log in failed");
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

    }
}
