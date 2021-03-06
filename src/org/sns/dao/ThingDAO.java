package org.sns.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sns.model.Thing;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Thing
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sns.model.Thing
 * @author MyEclipse Persistence Tools&BaiQiang
 */
public class ThingDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ThingDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String COMMENT_NUM = "commentNum";
	public static final String SHARE_NUM = "shareNum";
	public static final String IMAGE = "image";
	public static final String TYPE = "type";

	protected void initDao() {
		// do nothing
	}

	public void save(Thing transientInstance) {
		log.debug("saving Thing instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Thing persistentInstance) {
		log.debug("deleting Thing instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Thing findById(java.lang.Integer id) {
		log.debug("getting Thing instance with id: " + id);
		try {
			Thing instance = (Thing) getHibernateTemplate().get(
					"org.sns.model.Thing", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Thing> findByExample(Thing instance) {
		log.debug("finding Thing instance by example");
		try {
			List<Thing> results = (List<Thing>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Thing instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Thing as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Thing> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Thing> findByCommentNum(Object commentNum) {
		return findByProperty(COMMENT_NUM, commentNum);
	}

	public List<Thing> findByShareNum(Object shareNum) {
		return findByProperty(SHARE_NUM, shareNum);
	}

	public List<Thing> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Thing> findByType(Object type) {
		return findByProperty(TYPE, type);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findPart(final int start, final int size) {
		log.debug("finding part Friend instances");
		try {
			final String queryString = "from Thing";
			return this.getHibernateTemplate().execute(new HibernateCallback() {
				public List doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(queryString);
					query.setFirstResult(start);
					query.setMaxResults(size);
					return query.list();
				}
			});
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findAll() {
		log.debug("finding all Thing instances");
		try {
			String queryString = "from Thing";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Thing merge(Thing detachedInstance) {
		log.debug("merging Thing instance");
		try {
			Thing result = (Thing) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Thing instance) {
		log.debug("attaching dirty Thing instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Thing instance) {
		log.debug("attaching clean Thing instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ThingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ThingDAO) ctx.getBean("thingDAO");
	}
}