package jp.co.cyberagent.infosys.ams.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;
import jp.co.cyberagent.infosys.ams.model.User;
import jp.co.cyberagent.infosys.ams.model.User.Role;

import org.junit.BeforeClass;
import org.junit.Test;

public class LinkageSystemDaoJpaTest {
    private static final EntityManagerFactory factory
        = Persistence.createEntityManagerFactory("AMS_PERSISTENCE");
    private static final String TEST_USER = "LNKSYS";

    @BeforeClass
    public static void setUpBeforeClass() {
        try {
            if (getTestUser() == null) {
                addTestUser();
            }
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindAll001() {
        try {
            // 検索結果0件
            assertTrue(findAll().size() == 0);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindAll002() {
        try {
            // 検索結果1件
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, "key_" + val, getTestUser());
            assertNotNull(findAll());
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForName001() {
        try {
            // 追加失敗(name = null)
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save(null, "key_" + val, getTestUser());
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSaveForName002() {
        try {
            // 追加成功(name = "")
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("", "key_" + val, getTestUser());
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForName003() {
        try {
            // 追加成功(name)
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, "key_" + val, getTestUser());
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForAccessKey001() {
        try {
            // 追加失敗(accessKey = null)
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, null, getTestUser());
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSaveForAccessKey002() {
        try {
            // 追加成功(accessKey = "")
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, "", getTestUser());
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForAccessKey003() {
        try {
            // 追加成功(accessKey)
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, "key_" + val, getTestUser());
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForUser001() {
        try {
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, "key_" + val, null);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSaveForUser002() {
        try {
            // 追加成功(user)
            String val = String.valueOf(Calendar.getInstance().getTimeInMillis());
            save("name_" + val, "key_" + val, getTestUser());
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testDelete001() {
        try {
            // 削除成功
            delete(findAll().get(0).getId());
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    private List<LinkageSystem> findAll() throws AMSException {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();

            LinkageSystemDaoJpa linkageSystemDao = new LinkageSystemDaoJpa();
            linkageSystemDao.setEntityManager(entityManager);

            return linkageSystemDao.findAll();
        } catch (AMSException e) {
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }

    private void save(String name, String accessKey, User user) throws AMSException {
        EntityManager entityManager = null;
        EntityTransaction tran = null;
        try {
            entityManager = factory.createEntityManager();
            tran = entityManager.getTransaction();

            LinkageSystemDaoJpa linkageSystemDao = new LinkageSystemDaoJpa();
            linkageSystemDao.setEntityManager(entityManager);

            LinkageSystem linkageSystem = new LinkageSystem();
            linkageSystem.setName(name);
            linkageSystem.setAccessKey(accessKey);
            linkageSystem.setUser(user);

            tran.begin();
            linkageSystemDao.save(linkageSystem);
            tran.commit();
        } catch (AMSException e) {
            tran.rollback();
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }

    private void delete(long id) throws AMSException {
        EntityManager entityManager = null;
        EntityTransaction tran = null;
        try {
            entityManager = factory.createEntityManager();
            tran = entityManager.getTransaction();

            LinkageSystemDaoJpa linkageSystemDao = new LinkageSystemDaoJpa();
            linkageSystemDao.setEntityManager(entityManager);

            tran.begin();
            linkageSystemDao.delete(id);
            tran.commit();
        } catch (AMSException e) {
            tran.rollback();
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }

    private static void addTestUser() throws AMSException {
        EntityManager entityManager = null;
        EntityTransaction tran = null;
        try {
            entityManager = factory.createEntityManager();
            tran = entityManager.getTransaction();

            UserDaoJpa userDao = new UserDaoJpa();
            userDao.setEntityManager(entityManager);

            User user = new User();
            user.setName(TEST_USER);
            user.setRole(Role.USER);

            tran.begin();
            userDao.save(user);
            tran.commit();
        } catch (AMSException e) {
            tran.rollback();
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }

    private static User getTestUser() throws AMSException {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();

            UserDaoJpa userDao = new UserDaoJpa();
            userDao.setEntityManager(entityManager);

            return userDao.findByName(TEST_USER);
        } catch (AMSException e) {
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }
}
