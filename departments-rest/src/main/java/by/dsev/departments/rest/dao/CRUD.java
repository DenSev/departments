package by.dsev.departments.rest.dao;

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
    void create(T t);

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
    void update(T t);

    /**
     * deletes an entity from db
     * @param k - key by which the entity is deleted
     */
    void delete(K k);

}
