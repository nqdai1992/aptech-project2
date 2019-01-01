package sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sample.entity.DanhmucEntity;


public class Controller {
    public static void createDanhMuc () {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("supermarket");

        EntityManager em = emf.createEntityManager();

        DanhmucEntity danhmuc = new DanhmucEntity();
        danhmuc.setMa(1);
        danhmuc.setTenDanhMuc("Thực phẩm");
        danhmuc.setDescription("Thực phẩm");

        em.getTransaction().begin();
        em.persist(danhmuc);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }


}
