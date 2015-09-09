package com.liuliume.portal.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.OrdersDao;
import com.liuliume.portal.dao.cond.OrdersCond;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.model.OrderStatusEnum;
import com.liuliume.portal.model.OrderTypeEnum;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public List<Orders> list(Seed<Orders> seed) throws Exception {

		List<Orders> result = new ArrayList<Orders>();
		Parameter parameter = MBox.convertParameter(seed);

		OrdersCond cond = new OrdersCond();
		Orders orders = new Orders();
		Account account = new Account();
		orders.setAccount(account);

		if (seed.getFilter().containsKey("id")) {
			String id = seed.getFilter().get("id");
			if (StringUtils.isNotBlank(id))
				orders.setOrderId(Integer.parseInt(id));
		}
		if (seed.getFilter().containsKey("account_name")) {
			String account_name = seed.getFilter().get("account_name");
			if (StringUtils.isNotBlank(account_name))
				orders.getAccount().setUniqname(account_name);
		}
		if (seed.getFilter().containsKey("mobile")) {
			String mobile = seed.getFilter().get("mobile");
			if (StringUtils.isNotBlank(mobile))
				orders.getAccount().setMobile(mobile);
		}
		if (seed.getFilter().containsKey("create_time")) {
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
			String create_time = seed.getFilter().get("create_time");
			if (StringUtils.isNotBlank(create_time)) {
				Date date = sf.parse(create_time);
				orders.setCreateTime(date);
			}
		}
		cond.setOrders(orders);
		parameter.setCond(cond);

		int count = ordersDao.count(parameter);
		seed.setTotalSize(count);
		if (count > 0) {
			result = ordersDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

	@Override
	public Orders findOrdersByOrderId(Integer orderId) throws Exception {
		if(orderId==null || orderId<=0)
			return null;
		
		Orders orders=ordersDao.findOrdersByOrderId(orderId);
		return orders;
	}

	@Override
	@Transactional
	public void createOrUpdate(Orders orders) throws Exception {
		if(orders == null)
			throw new IllegalArgumentException("order为空");
		orders.setCreateTime(new Date());
		orders.setStatus(OrderStatusEnum.ORDERED.getId());
		orders.setPaymentStatus(Constants.PAYMENT_NO);
		if(orders.getOrderId()==null || orders.getOrderId()<=0){
			ordersDao.createOrder(orders);
		}else{
			ordersDao.updateOrder(orders);
		}
	}

	@Override
	@Transactional
	public void payOrder(Integer orderId) throws Exception {
		if(orderId == null || orderId<=0){
			throw new IllegalArgumentException("订单ID错误,请检查");
		}
		Orders orders = findOrdersByOrderId(orderId);
		if(orders.getPaymentStatus()==Constants.PAYMENT_YES){
			throw new Exception("订单已支付,请检查");
		}
		orders.setPaymentStatus(Constants.PAYMENT_YES);
		orders.setPaymentType(Constants.PAYMENTTYPE_OFFLINE);
		ordersDao.updateOrder(orders);
	}

	@Override
	@Transactional
	public void invalidOrder(Integer orderId) throws Exception {
		if(orderId == null || orderId<=0){
			throw new IllegalArgumentException("订单ID错误,请检查");
		}
		Orders orders = findOrdersByOrderId(orderId);
		if(orders.getStatus() == OrderStatusEnum.INVALID.getId()){
			throw new Exception("订单不能重复置无效");
		}
		if(orders.getStatus() >= OrderStatusEnum.SERVICING.getId()){
			throw new Exception("订单已经开始服务,不能置无效");
		}
		if(orders.getPaymentStatus() == Constants.PAYMENT_YES){
			throw new Exception("订单已经付款,不能置无效,请先退款");
		}
		orders.setStatus(OrderStatusEnum.INVALID.getId());
		ordersDao.updateOrder(orders);
	}

	@Override
	@Transactional
	public void transferOrder(Integer orderId) throws Exception {
		if(orderId == null)
			throw new IllegalArgumentException("Order Id错误");
		Orders orders = findOrdersByOrderId(orderId);
		if(orders == null)
			throw new Exception("订单ID["+orderId+"]对应的订单不存在");
		if(orders.getOrderType()!=OrderTypeEnum.BEAUTY.getId()){
			throw new Exception("订单ID["+orderId+"]不是美容订单,不能转发");
		}
		if(orders.getStatus()!=OrderStatusEnum.ORDERED.getId()){
			throw new Exception("订单ID["+orderId+"]不是下单状态,不能转发");
		}
		if(orders.getServiceType()!=Constants.SERVICE_DOOR){
			throw new Exception("订单ID["+orderId+"]不是上门服务,不能转发");
		}
		orders.setStatus(OrderStatusEnum.TRANSFER.getId());
		ordersDao.updateOrder(orders);
	}

	@Override
	public void completeOrder(Integer orderId) throws Exception {
		if(orderId == null)
			throw new IllegalArgumentException("Order Id错误");
		Orders orders = findOrdersByOrderId(orderId);
		if(orders == null)
			throw new Exception("订单ID["+orderId+"]对应的订单不存在");
		if(orders.getStatus()==OrderStatusEnum.COMPLETE.getId()){
			throw new Exception("订单ID["+orderId+"]已经完成,不能重复操作");
		}
		if(orders.getStatus() == OrderStatusEnum.INVALID.getId() ||orders.getStatus() == OrderStatusEnum.DELETE.getId()){
			throw new Exception("订单ID["+orderId+"]无效,不能完成");
		}
		orders.setStatus(OrderStatusEnum.COMPLETE.getId());
		ordersDao.updateOrder(orders);
	}

	
}