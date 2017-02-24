package com.mybatis.inter;

import java.util.List;

import com.mybatis.model.User;

public interface IUserOperation {
	    public User selectUserByID(int id);
	    public List<User> selectUsers(String userName);    
	    public List<User> selectUserFetchOrder(int id);    
	    public void addUser(User user);   
	    public void updateUser(User user);
	    public void deleteUser(int id);
}
