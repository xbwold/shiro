package com.wold.shiro.dao;

import com.wold.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Mapper
public interface UserDao {
    public User getUserByName(String name);

    public boolean addUser(User user);

    public boolean updateUser(User user);

    public Set<Integer> getRoleByUserId(int id);

    public Set<Integer> getPewerByUserId(int id);
}
