/*
 * Created on 28 Aug 2017 ( Time 15:06:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Slider;

/**
 * Business Service Interface for entity Slider.
 */
public interface SliderService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Slider findById( Long id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Slider> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Slider save(Slider entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Slider update(Slider entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Slider create(Slider entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Long id );


}
