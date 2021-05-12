package cn.mrcode.study.rpc.s06;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import cn.mrcode.study.rpc.s06.grpc.hello.HelloReply;
import cn.mrcode.study.rpc.s06.grpc.hello.HelloRequest;
import cn.mrcode.study.rpc.s06.grpc.hello.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * @author zq99299
 * @date 2021/5/12 14:16
 */
public class HelloWorldClient {

    private final ManagedChannel channel;
    private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

    /**
     * 通过 ip:端口 构建 Channel 连接
     **/
    public HelloWorldClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    /**
     * 构建 Stub 用于发请求
     **/
    HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 调用完手动关闭
     **/
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * 发送rpc请求
     **/
    public void say(String name) {
        // 构建入参对象
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            // 发送请求
            response = blockingStub.say(request);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(response);
    }

    public static void main(String[] args) throws Exception {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50051);
        try {
            client.say("world");
        } finally {
            client.shutdown();
        }
    }
}
