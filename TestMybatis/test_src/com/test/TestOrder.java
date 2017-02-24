package com.test;

import java.io.Reader;





import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.inter.IOrderOperation;
import com.mybatis.inter.IUserOperation;
import com.mybatis.model.Order;
import com.mybatis.model.User;

public class TestOrder {
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
    public void getOrderList(String billNo){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IOrderOperation userOperation=session.getMapper(IOrderOperation.class);          
            List<Order> orders = userOperation.selectOrders(billNo);
            for(Order order:orders){
                System.out.println(order.getId()+":"+order.getOrderNo()+" "+order.getPrice());
            }
            
        } finally {
            session.close();
        }
    }
    /**
     * ��������,���Ӻ󣬱����ύ���񣬷��򲻻�д�뵽���ݿ�.
     */
    public void addOrder(){
    	Order order = new Order();
    	order.setOrderNo("BN001");
    	order.setPrice("100");
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	IOrderOperation orderOperation=session.getMapper(IOrderOperation.class);
        	orderOperation.addOrder(order);
            session.commit();
            System.out.println("��ǰ���ӵ��û� idΪ:"+order.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            session.close();
        }
    }
    public void updateOrder(){
        //�ȵõ��û�,Ȼ���޸ģ��ύ��
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	IOrderOperation orderOperation=session.getMapper(IOrderOperation.class);
            Order order = orderOperation.selectOrderByID(4);            
            order.setPrice("111");
            orderOperation.updateOrder(order);
            session.commit();
        } finally {
            session.close();
        }
    }
    public void selectOrdersFetchPerson(int id){
    	SqlSession session = sqlSessionFactory.openSession();
        try {
            IOrderOperation userOperation=session.getMapper(IOrderOperation.class);          
            List<Order> orders = userOperation.selectOrdersFetchPerson(id);
            for(Order order:orders){
                System.out.println(order.toString());
            }
        } finally {
            session.close();
        }
    	
    }
    public static void main(String[] args) {
    	 TestOrder testOrder=new TestOrder();
//    	 testUser.deleteUser(4);
//    	 testOrder.addOrder();
    	 testOrder.getOrderList("%");
    	 testOrder.selectOrdersFetchPerson(1);
    }
}
