package com.densev.departments.rest.dao;

/**
 * Create, Read, Update, Delete template for Dao interfaces
 * @author DENIS SEVOSTEENKO
 * @param <K> - key
 * @param <T> - entity
 */
public interface CRUD <K, T>{

    /**
     * writes a new entity to db
     * @param t - entity to be created
     */
    T create(T t);

    /**
     * reads an entity from db
     * @param k - key by which the entity is read
     * @return read entity
     */
    T read (K k);

    /**
     * updates an entity in db
     * @param t - entity to be updated
     */
    T update(T t);

    /**
     * deletes an entity from db
     * @param k - key by which the entity is deleted
     */
    boolean delete(K k);

}
