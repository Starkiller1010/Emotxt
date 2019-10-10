package com.revature.repos;

import java.util.List;

public interface Repository<T> {

	List<T> getAll();
	T getById(int id);
	T add(T obj);
	T update(T updatedObj);
	boolean delete(int id);
	
}
