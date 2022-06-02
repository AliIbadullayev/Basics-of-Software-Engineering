package web;

import mbeans.PointsInfo;
import mbeans.MiddleTime;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.management.*;
import javax.persistence.*;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * It is a global bean that do following: 1) Become a data from JSF pages 2) Handle with data (give result: good/bad) 3) send information back to client
 */
public class GlobalBean implements Serializable {
    private static final String persistenceUnit = "StudsPU";

    private ManagedBean bean;
    private List<ManagedBean> beans;

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private String previousPage = null;
    private final MiddleTime mtBean;
    private final PointsInfo piBean;

    public GlobalBean() throws NotCompliantMBeanException, MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException {
        bean = new ManagedBean();
        beans = new ArrayList<>();
        //init of MBeans
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        mtBean = new MiddleTime();
        ObjectName mtName = new ObjectName("web:type=MiddleTime");
        server.registerMBean(mtBean, mtName);
        ObjectName piName = new ObjectName("web:type=PointsInfo");
        piBean = new PointsInfo();
        server.registerMBean(piBean, piName);

        connection();
        loadEntries();
    }

    /**
     * Method that generate something like manager for SQL
     */
    private void connection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }


    /**
     * This method returns all the points for the client
     */
    private void loadEntries() {
        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT e FROM ManagedBean e");
            beans = query.getResultList();
            transaction.commit();

            piBean.setHits(loadHit());
            piBean.setTotalPoints(beans.size());

            mtBean.setTimes(beans.stream().map(ManagedBean::getTime).collect(Collectors.toList()));
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
            bean.setTime(LocalDateTime.now());
            bean.checkHit();
            entityManager.persist(bean);
            transaction.commit();

            if (bean.getResult().equals("bad")) piBean.incrementBadResult();
            else piBean.setBadResult(0);

            bean = new ManagedBean();

        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
        return "redirect";
    }

    public int loadHit() {
        try {
            transaction.begin();
            int count = entityManager.createQuery("SELECT e FROM ManagedBean e WHERE e.result = :result").setParameter("result", "good").getResultList().size();
            transaction.commit();
            return count;
        } catch (RuntimeException exception) {
            return 0;
        }
    }

    // when reload page, load beans
    public void checkF5() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        String id = viewRoot.getViewId();
        if (previousPage != null && (previousPage.equals(id))) {
            loadEntries();
        }
        previousPage = id;
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

