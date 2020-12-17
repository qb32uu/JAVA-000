package io.kimmking.rpcfx.demo.consumer;

import java.lang.reflect.InvocationTargetException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.client.RpcfxByteBuddy;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

@SpringBootApplication
public class RpcfxClientApplication {

    // 二方库
    // 三方库 lib
    // nexus, userserivce -> userdao -> user
    //

    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        UserService userService;
        User user;

//        userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
//        user = userService.findById(1);
//        System.out.println("find user id=1 from server: " + user.getName());
//        System.out.println();
        
        userService = RpcfxByteBuddy.create(UserService.class, "http://localhost:8080/");
        user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());
        System.out.println();


        user = userService.query(1);
        System.out.println("query Integet: " + user.getName());
        System.out.println();
//          user = userService.query(new User());
//          System.out.println("query User: " + user.getName());
//          System.out.println();
        user = userService.query(1, "dd");
        System.out.println("query Integet String: " + user.getName());
        System.out.println();
        user = userService.query(1, System.currentTimeMillis());
        System.out.println("query Integet long: " + user.getName());
        System.out.println();
        user = userService.query(1, "dd", System.currentTimeMillis());
        System.out.println("query Integet String Long: " + user.getName());
        System.out.println();
        System.out.println();

        OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
        Order order;
        order = orderService.findOrderById(1);
        System.out.println("find order id=1 from server: " + order.getName());
    }

}
