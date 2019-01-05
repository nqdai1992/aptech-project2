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
import sample.entity.GiohangEntity;
import sample.exceptions.NonexistentEntityException;
import sample.exceptions.PreexistingEntityException;

/**
 *
 * @author dainguyen
 */
public class GiohangEntityJpaController implements Serializable {

    public GiohangEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GiohangEntity giohangEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(giohangEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGiohangEntity(giohangEntity.getMaGh()) != null) {
                throw new PreexistingEntityException("GiohangEntity " + giohangEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GiohangEntity giohangEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            giohangEntity = em.merge(giohangEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = giohangEntity.getMaGh();
                if (findGiohangEntity(id) == null) {
                    throw new NonexistentEntityException("The giohangEntity with id " + id + " no longer exists.");
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
            GiohangEntity giohangEntity;
            try {
                giohangEntity = em.getReference(GiohangEntity.class, id);
                giohangEntity.getMaGh();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The giohangEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(giohangEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GiohangEntity> findGiohangEntityEntities() {
        return findGiohangEntityEntities(true, -1, -1);
    }

    public List<GiohangEntity> findGiohangEntityEntities(int maxResults, int firstResult) {
        return findGiohangEntityEntities(false, maxResults, firstResult);
    }

    private List<GiohangEntity> findGiohangEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GiohangEntity.class));
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

    public GiohangEntity findGiohangEntity(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GiohangEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getGiohangEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GiohangEntity> rt = cq.from(GiohangEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
