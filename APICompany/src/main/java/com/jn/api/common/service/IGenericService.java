package com.jn.api.common.service;

import java.util.List;

public interface IGenericService<T> {

	public abstract List<T> findAll();

	public abstract T save(T entity);

	public abstract T findById(Integer id);

	public abstract void delete(T entity);

	public abstract void deleteById(Integer id);

	public abstract long count();

	public abstract boolean existsById(Integer id);
}
