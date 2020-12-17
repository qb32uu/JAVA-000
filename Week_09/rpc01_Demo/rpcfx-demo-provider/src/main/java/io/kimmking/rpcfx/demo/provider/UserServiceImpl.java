package io.kimmking.rpcfx.demo.provider;

import org.springframework.stereotype.Service;

import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }

    @Override
    public User query(Integer id) {
        return new User(id, "id");
    }

    @Override
    public User query(Integer id, String name) {
        return new User(id, "id&name");
    }

    @Override
    public User query(Integer id, long time) {
        return new User(id, "id&time");
    }

    @Override
    public User query(Integer id, String name, Long time) {
        return new User(id, "id&name&time");
    }

//    @Override
//    public User query(User user) {
//        return new User(-1, "User");
//    }
}
