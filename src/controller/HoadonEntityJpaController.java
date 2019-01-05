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
import sample.entity.HoadonEntity;
import sample.exceptions.NonexistentEntityException;
import sample.exceptions.PreexistingEntityException;

/**
 *
 * @author dainguyen
 */
public class HoadonEntityJpaController implements Serializable {

    public HoadonEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HoadonEntity hoadonEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hoadonEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHoadonEntity(hoadonEntity.getMaHd()) != null) {
                throw new PreexistingEntityException("HoadonEntity " + hoadonEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HoadonEntity hoadonEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hoadonEntity = em.merge(hoadonEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = hoadonEntity.getMaHd();
                if (findHoadonEntity(id) == null) {
                    throw new NonexistentEntityException("The hoadonEntity with id " + id + " no longer exists.");
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
            HoadonEntity hoadonEntity;
            try {
                hoadonEntity = em.getReference(HoadonEntity.class, id);
                hoadonEntity.getMaHd();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hoadonEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(hoadonEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HoadonEntity> findHoadonEntityEntities() {
        return findHoadonEntityEntities(true, -1, -1);
    }

    public List<HoadonEntity> findHoadonEntityEntities(int maxResults, int firstResult) {
        return findHoadonEntityEntities(false, maxResults, firstResult);
    }

    private List<HoadonEntity> findHoadonEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HoadonEntity.class));
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

    public HoadonEntity findHoadonEntity(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HoadonEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getHoadonEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HoadonEntity> rt = cq.from(HoadonEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
