package com.transform.test;

import com.example.grpc.api.RPCDateRequest;
import com.example.grpc.api.RPCDateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class GrpcClient {
    public static void main(String[] args) {
        //1.拿到一个通信的channel
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8888)
                .usePlaintext(true).build();
        try{
            //2.将信息传入request对象
            RPCDateRequest build = RPCDateRequest.newBuilder()
                    .setId(17)
                    .setUserName("yyx")
                    .setMessage("喜欢吃蔬菜")
                    .setAge(22).build();
            System.out.println("build:" + build+"...");
            RPCDateServiceGrpc.RPCDateServiceBlockingStub stub = RPCDateServiceGrpc.newBlockingStub(channel);
            stub.getDate(build);
            while(true){
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                if (next.equals("exit")){
                    break;
                }
            }
        }finally {
            channel.shutdown();
        }

    }
}
