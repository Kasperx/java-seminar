package de.linuxhotel.jf.dao;

import java.io.Serializable;
import java.util.List;

public interface CrudDao <T, PK extends Serializable> {
	public List <T> getAll();
	public T getById(PK id);
	public void saveOrUpdate(T person);
	public T deleteById(PK id);
}
