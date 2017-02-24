package com.test;

import java.io.Reader;





import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.inter.IUserOperation;
import com.mybatis.model.User;

public class Test {
	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 

    static{
        try{
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    public void getUserList(String userName){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);          
            List<User> users = userOperation.selectUsers(userName);
            for(User user:users){
            	System.out.println(user.toString());
            }
        } finally {
            session.close();
        }
    }
    public void getUserOrders(int id){
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		IUserOperation userOperation=session.getMapper(IUserOperation.class);          
    		List<User> users = userOperation.selectUserFetchOrder(id);
    		for(User user:users){
    			System.out.println(user.getOrderList().size());
    			System.out.println(user.toString());
    		}
    	} finally {
    		session.close();
    	}
    }
    /**
     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
     */
    public void addUser(){
        User user=new User();
        user.setAddress("江苏省南京市");
        user.setName("张三");
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	IUserOperation userOperation=session.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            session.commit();
            System.out.println("当前增加的用户 id为:"+user.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            session.close();
        }
    }
    public void updateUser(){
        //先得到用户,然后修改，提交。
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(4);            
            user.setAddress("新园区");
            userOperation.updateUser(user);
            session.commit();
        } finally {
            session.close();
        }
    }
    /**
     * 删除数据，删除一定要 commit.
     * @param id
     */
    public void deleteUser(int id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);          
            userOperation.deleteUser(id);
            session.commit();            
        } finally {
            session.close();
        }
    }
    public static void main(String[] args) {
    	 Test testUser=new Test();
//    	 testUser.deleteUser(4);
//    	 testUser.addUser();
    	 testUser.getUserList("%");
    	 testUser.getUserOrders(1);
    }
}
