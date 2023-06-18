//package com.my.netty;
//
//import io.netty.channel.Channel;
//import io.netty.channel.pool.AbstractChannelPoolMap;
//import io.netty.channel.pool.FixedChannelPool;
//import io.netty.util.concurrent.Promise;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.http.RequestEntity;
//
//import java.net.InetSocketAddress;
//import java.net.URL;
//import java.util.concurrent.Future;
//import java.util.function.Consumer;
//
//import static com.my.netty.NettyClient.bootstrap;
//
//public class NettyHttpClientServiceImpl implements NettyHttpClientService, DisposableBean {
//        // channelPoolMap是一个InetSocketAddress与ChannelPool的映射关系
//        private AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool> channelPoolMap = new AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool>() {
//            // 构建新的大小为200的ChannelPool
//            @Override
//            protected FixedChannelPool newPool(InetSocketAddress key) {
//                return new FixedChannelPool(bootstrap.remoteAddress(key), new NettyHttpPoolHandler(), 200);
//            }
//        };
//
//        // NettyHttpClientService的入口，参数是请求体RequestEntity，成功回调successCallback，失败回调errorCallback
//        @Override
//        public Promise<SimpleResponse> get(RequestEntity bean, Consumer<SimpleResponse> successCallback, Consumer<Throwable> errorCallback) throws PlatformException {
//            final URL url= new URL(bean.getUrl());
//            // 构造远程服务的InetSocketAddress
//            InetSocketAddress address = new InetSocketAddress(url.getHost(), url.getPort() == -1 ? url.getDefaultPort() : url.getPort());
//            Promise<SimpleResponse> promise = newPromise();
//            // 从ChannelPool中获取channel
//            acquireChannel(address).addListener(future -> {
//                if (!future.isSuccess()) {
//                    promise.tryFailure(future.cause());
//                    return;
//                }
//                try {
//                    // 发送request
//                    send(bean, (Channel) future.get(), promise);
//                } catch (Exception e) {
//                    promise.tryFailure(e);
//                }
//            });
//
//            // 回调
//            promise.addListener(future -> {
//                if (future.isSuccess()) {
//                    successCallback.accept(future.get());
//                } else {
//                    errorCallback.accept(future.cause());
//                }
//            });
//            return promise;
//        }
//
//        private Future<Channel> acquireChannel(InetSocketAddress address) {
//            Promise<Channel> promise = newPromise();
//            // 找到address对应的那个ChannelPool，再从ChannelPool中获取一个Channel
//            channelPoolMap.get(address).acquire().addListener(future -> {
//                if (future.isSuccess()) {
//                    Channel channel = (Channel) future.get();
//                    promise.trySuccess(channel);
//                } else {
//                    promise.tryFailure(future.cause());
//                }
//            });
//            return promise;
//        }
//}
