import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GlobalBean implements Serializable {
    private static final String persistenceUnit = "StudsPU";

    private ManagedBean bean;
    private List<ManagedBean> beans;

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public GlobalBean() {
        bean = new ManagedBean();
        beans = new ArrayList<>();

        connection();
        loadEntries();
    }

    private void connection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    private void loadEntries() {
        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT e FROM ManagedBean e");
            beans = query.getResultList();
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }

    }

    public String addBean() {
        try {
            transaction.begin();
            bean.getXValue();
            bean.checkHit();
            bean.result();
            entityManager.persist(bean);
            beans.add(bean);
            bean = new ManagedBean();
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
        return "redirect";
    }



    public ManagedBean getBean() {
        return bean;
    }

    public void setBean(ManagedBean bean) {
        this.bean = bean;
    }

    public List<ManagedBean> getBeans() {
        return beans;
    }

    public void setBeans(List<ManagedBean> beans) {
        this.beans = beans;
    }
}

