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
import sample.entity.NhanvienEntity;
import sample.exceptions.NonexistentEntityException;
import sample.exceptions.PreexistingEntityException;

/**
 *
 * @author dainguyen
 */
public class NhanvienEntityJpaController implements Serializable {

    public NhanvienEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NhanvienEntity nhanvienEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nhanvienEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNhanvienEntity(nhanvienEntity.getMaNv()) != null) {
                throw new PreexistingEntityException("NhanvienEntity " + nhanvienEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NhanvienEntity nhanvienEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nhanvienEntity = em.merge(nhanvienEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = nhanvienEntity.getMaNv();
                if (findNhanvienEntity(id) == null) {
                    throw new NonexistentEntityException("The nhanvienEntity with id " + id + " no longer exists.");
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
            NhanvienEntity nhanvienEntity;
            try {
                nhanvienEntity = em.getReference(NhanvienEntity.class, id);
                nhanvienEntity.getMaNv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nhanvienEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(nhanvienEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NhanvienEntity> findNhanvienEntityEntities() {
        return findNhanvienEntityEntities(true, -1, -1);
    }

    public List<NhanvienEntity> findNhanvienEntityEntities(int maxResults, int firstResult) {
        return findNhanvienEntityEntities(false, maxResults, firstResult);
    }

    private List<NhanvienEntity> findNhanvienEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NhanvienEntity.class));
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

    public NhanvienEntity findNhanvienEntity(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NhanvienEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNhanvienEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NhanvienEntity> rt = cq.from(NhanvienEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
