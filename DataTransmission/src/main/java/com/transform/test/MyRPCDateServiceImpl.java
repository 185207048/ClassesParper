package com.transform.test;

import com.example.grpc.api.RPCDateRequest;
import com.example.grpc.api.RPCDateResponse;
import com.example.grpc.api.RPCDateServiceGrpc;
import io.grpc.stub.StreamObserver;

public class MyRPCDateServiceImpl extends RPCDateServiceGrpc.RPCDateServiceImplBase{
    @Override
    public void getDate(RPCDateRequest request, StreamObserver<RPCDateResponse> responseObserver) {
//        super.getDate(request, responseObserver);
        try {
            String message = request.getId() + "\ngetUserName:" +
                    request.getUserName() + "\ngetMessage:" +
                    request.getMessage() + "\ngetAge:" +
                    request.getAge() + "\ngetSerializedSize:" +
                    request.getAllFields();
            System.out.println("message" + message);
            RPCDateResponse build = RPCDateResponse.newBuilder().setServerDate(message).build();
            System.out.println("build:" + build);
            responseObserver.onNext(build);
            responseObserver.onCompleted();
        }catch (Exception e){
            System.out.println("impl---错误信息====》");
            responseObserver.onError(e);
        }
    }
}
