/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sample.entity.SanphamEntity;
import sample.exceptions.NonexistentEntityException;
import sample.exceptions.PreexistingEntityException;

/**
 *
 * @author dainguyen
 */
public class SanphamEntityJpaController implements Serializable {

    public SanphamEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SanphamEntity sanphamEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sanphamEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSanphamEntity(sanphamEntity.getMaSp()) != null) {
                throw new PreexistingEntityException("SanphamEntity " + sanphamEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SanphamEntity sanphamEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sanphamEntity = em.merge(sanphamEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = sanphamEntity.getMaSp();
                if (findSanphamEntity(id) == null) {
                    throw new NonexistentEntityException("The sanphamEntity with id " + id + " no longer exists.");
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
            SanphamEntity sanphamEntity;
            try {
                sanphamEntity = em.getReference(SanphamEntity.class, id);
                sanphamEntity.getMaSp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sanphamEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(sanphamEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SanphamEntity> findSanphamEntityEntities() {
        return findSanphamEntityEntities(true, -1, -1);
    }

    public List<SanphamEntity> findSanphamEntityEntities(int maxResults, int firstResult) {
        return findSanphamEntityEntities(false, maxResults, firstResult);
    }

    private List<SanphamEntity> findSanphamEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SanphamEntity.class));
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

    public SanphamEntity findSanphamEntity(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SanphamEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getSanphamEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SanphamEntity> rt = cq.from(SanphamEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
