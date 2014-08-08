package jp.co.cyberagent.infosys.ams.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.User;
import jp.co.cyberagent.infosys.ams.model.User.Role;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserDaoJpaTest {
    private EntityManagerFactory factory
        = Persistence.createEntityManagerFactory("AMS_PERSISTENCE");

    @Test
    public void testFindByName001() {
        try {
            // 検索結果0件
            assertNull(findByName(null));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindByName002() {
        try {
            // 検索結果0件
            assertNull(findByName(""));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindByName003() {
        try {
            // 検索結果0件
            assertNull(findByName("999999"));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindByName004() {
        try {
            // 検索結果1件
            save("USER01", Role.USER);
            assertNotNull(findByName("USER01"));

            delete(findByName("USER01"));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testfindAllAdmin001() {
        try {
            // 検索結果0件
            assertTrue(findAllAdmin().size() == 0);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testfindAllAdmin002() {
        try {
            // 検索結果1件
            save("ADMN01", Role.ADMIN);
            assertTrue(findAllAdmin().size() == 1);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testfindAllAdmin003() {
        try {
            // 検索結果2件
            save("ADMN02", Role.ADMIN);
            assertTrue(findAllAdmin().size() == 2);

            delete(findByName("ADMN01"));
            delete(findByName("ADMN02"));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSave001() {
        try {
            // 追加失敗(name = null)
            save(null, Role.USER);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSave002() {
        try {
            // 追加成功(name = "")
            save("", Role.USER);
            User ret = findByName("");
            assertNotNull(ret);
            
            delete(findByName(""));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSave003() {
        try {
            save("DOUBLE", Role.USER);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSave004() {
        try {
            // 追加失敗(role = null)
            save("ADMN01", null);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSave005() {
        try {
            // 追加成功(Admin)
            save("ADMN01", Role.ADMIN);
            User ret = findByName("ADMN01");
            assertNotNull(ret);
            
            delete(findByName("ADMN01"));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSave006() {
        try {
            // 追加成功(User)
            save("USER01", Role.USER);
            User ret = findByName("USER01");
            assertNotNull(ret);
            
            delete(findByName("USER01"));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSave007() {
        try {
            // 追加失敗(重複名)
            save("DOUBLE", Role.USER);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDelete001() {
        try {
            // 削除成功
            delete(findByName("DOUBLE"));
            assertNull(findByName("DOUBLE"));
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    private User findByName(String name) throws AMSException {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();

            UserDaoJpa userDao = new UserDaoJpa();
            userDao.setEntityManager(entityManager);

            return userDao.findByName(name);
        } catch (AMSException e) {
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }

    private List<User> findAllAdmin() throws AMSException {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();

            UserDaoJpa userDao = new UserDaoJpa();
            userDao.setEntityManager(entityManager);

            return userDao.findAllAdmin();
        } catch (AMSException e) {
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }
    }

    private void save(String name, Role role) throws AMSException {
        EntityManager entityManager = null;
        EntityTransaction tran = null;
        try {
            entityManager = factory.createEntityManager();
            tran = entityManager.getTransaction();

            UserDaoJpa userDao = new UserDaoJpa();
            userDao.setEntityManager(entityManager);

            User user = new User();
            user.setName(name);
            user.setRole(role);

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

    private void delete(User target) throws AMSException {
        EntityManager entityManager = null;
        EntityTransaction tran = null;
        try {
            entityManager = factory.createEntityManager();
            tran = entityManager.getTransaction();

            UserDaoJpa userDao = new UserDaoJpa();
            userDao.setEntityManager(entityManager);

            tran.begin();
            userDao.delete(target.getId());
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

}
