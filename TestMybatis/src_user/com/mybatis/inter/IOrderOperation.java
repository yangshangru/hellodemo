package com.mybatis.inter;

import java.util.List;

import com.mybatis.model.Order;

public interface IOrderOperation {
	public Order selectOrderByID(int id);
    public List<Order> selectOrders(String billNo);    
    public List<Order> selectOrdersFetchPerson(int id);    
    public void addOrder(Order Order);
    public void updateOrder(Order Order);
    public void deleteOrder(int id);
}
