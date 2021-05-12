package cn.mrcode.study.rpc.s06.service;

import cn.mrcode.study.rpc.s06.grpc.hello.HelloReply;
import cn.mrcode.study.rpc.s06.grpc.hello.HelloRequest;
import cn.mrcode.study.rpc.s06.grpc.hello.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * 服务实现
 *
 * @author zhuqiang
 * @date 2021/5/12 15:00
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void say(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        // 构造一个响应对象
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();

        // 将响应对象发出去
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
