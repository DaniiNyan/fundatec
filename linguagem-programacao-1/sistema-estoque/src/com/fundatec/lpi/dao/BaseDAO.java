package com.fundatec.lpi.dao;

import java.util.List;

public interface BaseDAO<T> {
	public void save(T objeto);
	public List<T> listAll();
	public void delete(int id);
	public void update(T objeto);

}
