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
     * ��������,���Ӻ󣬱����ύ���񣬷��򲻻�д�뵽���ݿ�.
     */
    public void addUser(){
        User user=new User();
        user.setAddress("����ʡ�Ͼ���");
        user.setName("����");
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	IUserOperation userOperation=session.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            session.commit();
            System.out.println("��ǰ���ӵ��û� idΪ:"+user.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            session.close();
        }
    }
    public void updateUser(){
        //�ȵõ��û�,Ȼ���޸ģ��ύ��
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(4);            
            user.setAddress("��԰��");
            userOperation.updateUser(user);
            session.commit();
        } finally {
            session.close();
        }
    }
    /**
     * ɾ�����ݣ�ɾ��һ��Ҫ commit.
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
