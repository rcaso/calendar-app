/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.controller;

import com.shava.calendar.entitymanager.DbApplication;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;

/**
 *
 * @author raul
 */
@Stateless
public class CrudServiceController {

    @Inject
    @DbApplication
    protected EntityManager em;

    /**
     * Creates the.
     *
     * @param <T> el tipo generico
     * @param t el t
     * @return the t
     */
    public <T> T create(T t) {
        this.em.persist(t);
        return t;
    }

    /**
     * Comprueba si es loaded entity.
     *
     * @param <T> el tipo generico
     * @param entity el entity
     * @param atribute el atribute
     * @return true, si es loaded entity
     */
    public <T> boolean isLoadedEntity(T entity, String atribute) {
        boolean valid = false;
        PersistenceUnitUtil persistenceUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
        try {
            if (entity != null && persistenceUtil.isLoaded(entity, atribute)) {
                valid = true;
            }
        } catch (Exception eix) {
            eix.printStackTrace();
        }
        return valid;
    }

    /**
     * Detachment entities.
     *
     * @param <T> el tipo generico
     * @param entities el entities
     */
    public <T> void detachmentEntities(List<T> entities) {
        for (T entity : entities) {
            detachmentEntity(entity);
        }
    }

    /**
     * Detachment entity.
     *
     * @param <T> el tipo generico
     * @param entity el entity
     */
    public <T> void detachmentEntity(T entity) {
        if (em.contains(entity)) {
            em.detach(entity);
        }
    }

    /**
     * Detachment all.
     */
    public void detachmentAll() {
        em.clear();
    }

    /**
     * Find.
     *
     * @param <T> el tipo generico
     * @param type el type
     * @param id el id
     * @return the t
     */
    public <T> T find(Class<T> type, Object id) {
        return (T) this.em.find(type, id);
    }

    /**
     * Delete.
     *
     * @param type el type
     * @param id el id
     */
    public void delete(Class<?> type, Object id) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }

    /**
     * Update.
     *
     * @param <T> el tipo generico
     * @param t el t
     * @return the t
     */
    public <T> T update(T t) {
        T mergeEntity = this.em.merge(t);
        this.em.flush();
        return mergeEntity;
    }

    /**
     * Find with named query.
     *
     * @param namedQueryName el named query name
     * @return the list
     */
    public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Find with query.
     *
     * @param jpaQuery el jpa query
     * @return the list
     */
    public List findWithQuery(String jpaQuery) {
        return this.em.createQuery(jpaQuery).getResultList();
    }

    /**
     * Find with native query.
     *
     * @param nativeQuery el native query
     * @return the list
     */
    public List findWithNativeQuery(String nativeQuery) {
        return this.em.createNativeQuery(nativeQuery).getResultList();
    }

    /**
     * Find with named query.
     *
     * @param namedQueryName el named query name
     * @param parameters el parameters
     * @return the list
     */
    public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * Find with query.
     *
     * @param jpaQuery el jpa query
     * @param parameters el parameters
     * @return the list
     */
    public List findWithQuery(String jpaQuery, Map<String, Object> parameters) {
        return findWithQuery(jpaQuery, parameters, 0);
    }

    /**
     * Find with native query.
     *
     * @param nativeQuery el native query
     * @param parameters el parameters
     * @return the list
     */
    public List findWithNativeQuery(String nativeQuery, Map<String, Object> parameters) {
        return findWithNativeQuery(nativeQuery, parameters, 0);
    }

    /**
     * Find with named query.
     *
     * @param queryName el query name
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * Find with query.
     *
     * @param jpaQuery el jpa query
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithQuery(String jpaQuery, int resultLimit) {
        return this.em.createQuery(jpaQuery).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * Find with native query.
     *
     * @param nativeQuery el native query
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithNativeQuery(String nativeQuery, int resultLimit) {
        return this.em.createNativeQuery(nativeQuery).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * Find by native query.
     *
     * @param sql el sql
     * @param type el type
     * @return the list
     */
    public List findByNativeQuery(String sql, Class type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    /**
     * Find with named query.
     *
     * @param namedQueryName el named query name
     * @param parameters el parameters
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /**
     * Find with query.
     *
     * @param jpaQuery el jpa query
     * @param parameters el parameters
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithQuery(String jpaQuery, Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createQuery(jpaQuery);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /**
     * Obtiene pagination query.
     *
     * @param jpaQuery el jpa query
     * @param parameters el parameters
     * @param pageIndex el page index
     * @param pageSize el page size
     * @return pagination query
     */
    public List getPaginationQuery(String jpaQuery, Map<String, Object> parameters, int pageIndex, int pageSize) {
        Query query = this.em.createQuery(jpaQuery);
        if (parameters != null && !parameters.isEmpty()) {
            Set<Entry<String, Object>> rawParameters = parameters.entrySet();
            for (Entry<String, Object> entry : rawParameters) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        query.setFirstResult((pageIndex - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * Obtiene pagination named query.
     *
     * @param namedQueryName el named query name
     * @param parameters el parameters
     * @param pageIndex el page index
     * @param pageSize el page size
     * @return pagination named query
     */
    public List getPaginationNamedQuery(String namedQueryName, Map<String, Object> parameters, int pageIndex, int pageSize) {
        Query query = this.em.createNamedQuery(namedQueryName);
        if (parameters != null && !parameters.isEmpty()) {
            Set<Entry<String, Object>> rawParameters = parameters.entrySet();
            for (Entry<String, Object> entry : rawParameters) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        query.setFirstResult((pageIndex - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * Find with native query.
     *
     * @param nativeQuery el native query
     * @param parameters el parameters
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithNativeQuery(String nativeQuery, Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNativeQuery(nativeQuery);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    
    public EntityManager getEntityManager(){
        return em;
    }
    

    /**
     * Instancia un nuevo crud service controller.
     */
    public CrudServiceController() {
        // TODO Auto-generated constructor stub
    }
}
