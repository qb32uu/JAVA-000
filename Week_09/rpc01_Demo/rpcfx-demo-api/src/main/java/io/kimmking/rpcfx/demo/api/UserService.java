package io.kimmking.rpcfx.demo.api;

public interface UserService {

    User findById(int id);

    User query(Integer id);

//    User query(User user);

    User query(Integer id, String name);

    User query(Integer id, long time);

    User query(Integer id, String name, Long time);
}
