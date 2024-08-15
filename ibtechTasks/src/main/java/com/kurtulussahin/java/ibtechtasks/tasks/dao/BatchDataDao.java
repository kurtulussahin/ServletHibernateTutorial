package com.kurtulussahin.java.ibtechtasks.tasks.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.kurtulussahin.java.ibtechtasks.tasks.model.BatchData;
import com.kurtulussahin.java.ibtechtasks.tasks.util.HibernateUtil;

public class BatchDataDao {

	public void create(BatchData batchData) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(batchData);
			transaction.commit();
			System.out.println("--> BatchData: " + batchData.getSiraNo());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public List<BatchData> getList() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from BatchData", BatchData.class).list();
		}
	}
	
	public void updateStatus(long batchDataId, boolean status) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				BatchData batchData = (BatchData) session.get(BatchData.class, batchDataId);			
				batchData.setStatus(status);
				session.update(batchData);
				transaction.commit();
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}

	public static void deleteAll() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				String stringQuery = "DELETE FROM BatchData";
				Query query = session.createQuery(stringQuery);
				query.executeUpdate();
				transaction.commit();
				System.out.println("-> Deletion successful");
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
}
