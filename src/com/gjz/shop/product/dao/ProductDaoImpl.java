package com.gjz.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.product.entity.Product;
import com.gjz.shop.utils.PageHibernateCallback;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao{

	@Override
	public List<Product> findHot() {
		
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//��ѯ���ŵ���Ʒ����������is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		
		//��ʱ�䵹�����
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	@Override
	public List<Product> findNew() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> nList = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return nList;
	}

	@Override
	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
		
	}

	@Override
	public int findCountCid(Integer cid) {
		
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	/**
	 * ͨ��cid ����һҳ����Ʒ��Ϣ
	 */
	@Override
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		
		//SELECT p.* FROM product p, categorysecond cs, category c WHERE cs.`cid` = c.`cid` AND p.`csid` = cs.`csid` AND c.`cid` = 1;
		
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		
		//��ҳд��
		
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		
		
		if(list != null && list.size() > 0)
		{
			return list;
		}
		
		return null;
	}

	/**
	 * ͨ��csid���������������Ʒ����
	 */
	@Override
	public int findCountCsid(Integer csid) {
		
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@Override
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		
		String  hql = "select p from Product p join p.categorySecond cs where cs.csid = ? ";
		
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		
		if(list != null && list.size() > 0)
		{
			return list;
		}
		
		return null;
	}

}
