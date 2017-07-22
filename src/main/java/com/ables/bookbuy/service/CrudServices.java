package com.ables.bookbuy.service;

import java.util.List;
import java.util.Optional;

public interface CrudServices<T> {

	  List<?> listAll();
	  
	   Optional <T> findById(Long id);
	 
	    T saveOrUpdate(T domainObject);
	 
	    void delete(Long id);
}
