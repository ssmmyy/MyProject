package com.na.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.na.dao.GroupApplyDao;
import com.na.entity.GroupApply;

@Repository("groupApplyDao")
public class GroupApplyDaoimp implements GroupApplyDao {

	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	
	@Override
	public boolean insert(GroupApply groupApply) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction ts = session.beginTransaction();
			session.saveOrUpdate(groupApply);
			ts.commit();
			session.flush(); 
			session.clear(); 
			//session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(GroupApply groupApply) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction ts = session.beginTransaction();
			session.saveOrUpdate(groupApply);
			ts.commit();
			session.flush(); 
			session.clear(); 
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(GroupApply groupApply) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction ts = session.beginTransaction();		
			session.delete(groupApply);
			ts.commit();
			session.flush(); 
			session.clear();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//查询hql
	public Object selectHql(String hql) {
		List<GroupApply> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query =  session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}

	//查询SQL
	public Object selectSql(String sql) {
		List<GroupApply> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query =  session.createSQLQuery(sql);
			list = query.list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	//执行无查询HQL语句
	public int otherHql(String hql) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			return query.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	//执行无查询SQL语句
	public int otherSql(String sql) {
		try {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			return query.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public GroupApply getGroupApply(long id) {
		Session session = sessionFactory.getCurrentSession();
		GroupApply groupApply = (GroupApply) session.get(GroupApply.class, id);
		return groupApply;
	}
}
