package de.linuxhotel.jf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Alternative
public abstract class JPACrudDaoImpl <T, PK extends Serializable> implements CrudDao <T, PK>{
	
	private Class <T> initClass;
	
	@SuppressWarnings("unchecked")
	public JPACrudDaoImpl() {
		ParameterizedType p = (ParameterizedType) this.getClass().getGenericSuperclass();
		initClass = (Class <T>) p.getActualTypeArguments()[0];
	}
	
	@Inject
	private EntityManager em;
	
	@Override
	public List<T> getAll() {
		return em.createQuery("select p from "+initClass.getName()+" p", initClass).getResultList();
	}

	@Override
	public T getById(PK id) {
		return em.find(initClass, id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(T person) {
		//em.getTransaction().begin();
		em.persist(person);
		//em.getTransaction().commit();
	}

	@Override
	public T deleteById(PK id) {
		T p = this.getById(id);
		em.remove(id);
		return p;
	}
}
