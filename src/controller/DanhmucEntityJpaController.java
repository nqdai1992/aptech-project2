/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sample.entity.DanhmucEntity;
import sample.exceptions.NonexistentEntityException;
import sample.exceptions.PreexistingEntityException;

/**
 *
 * @author dainguyen
 */
public class DanhmucEntityJpaController implements Serializable {

    public DanhmucEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DanhmucEntity danhmucEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(danhmucEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDanhmucEntity(danhmucEntity.getMa()) != null) {
                throw new PreexistingEntityException("DanhmucEntity " + danhmucEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DanhmucEntity danhmucEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            danhmucEntity = em.merge(danhmucEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = danhmucEntity.getMa();
                if (findDanhmucEntity(id) == null) {
                    throw new NonexistentEntityException("The danhmucEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DanhmucEntity danhmucEntity;
            try {
                danhmucEntity = em.getReference(DanhmucEntity.class, id);
                danhmucEntity.getMa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The danhmucEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(danhmucEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DanhmucEntity> findDanhmucEntityEntities() {
        return findDanhmucEntityEntities(true, -1, -1);
    }

    public List<DanhmucEntity> findDanhmucEntityEntities(int maxResults, int firstResult) {
        return findDanhmucEntityEntities(false, maxResults, firstResult);
    }

    private List<DanhmucEntity> findDanhmucEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DanhmucEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DanhmucEntity findDanhmucEntity(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DanhmucEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getDanhmucEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DanhmucEntity> rt = cq.from(DanhmucEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
