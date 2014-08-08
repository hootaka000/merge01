package jp.co.cyberagent.infosys.ams.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;
import jp.co.cyberagent.infosys.ams.model.OperationLog;
import jp.co.cyberagent.infosys.ams.model.OperationLog.OperationResult;
import jp.co.cyberagent.infosys.ams.model.OperationLog.OperationType;
import jp.co.cyberagent.infosys.ams.model.User.Role;
import jp.co.cyberagent.infosys.ams.model.User;

import org.junit.BeforeClass;
import org.junit.Test;

public class OperationLogDaoJpaTest {

    private static final EntityManagerFactory factory
        = Persistence.createEntityManagerFactory("AMS_PERSISTENCE");
    private static final String TEST_USER = "OPELOG";
    
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
    public void testSaveForType001() {
        try {
            // 追加失敗(type = null)
            save(null, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSaveForType002() {
        try {
            // 追加成功(type = ADD)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testSaveForType003() {
        try {
            // 追加成功(type = UPDATE)
            save(OperationType.UPDATE, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForType004() {
        try {
            // 追加成功(type = REMOVE)
            save(OperationType.REMOVE, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveForDateTime001() {
        try {
            // 追加失敗(dateTime = null)
            save(OperationType.ADD, 
                null, 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSaveForDateTime002() {
        try {
            // 追加成功(dateTime)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }

    @Test
    public void testSaveForUser001() {
        try {
            // 追加失敗(user = null)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                null, 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testSaveForUser002() {
        try {
            // 追加成功(user)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }
    
    @Test
    public void testSaveForLinkageSystem001() {
        try {
            // 追加成功(linkageSystem = null)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                null, 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }
    
    @Test
    public void testSaveForDetail001() {
        try {
            // 追加失敗(detail = null)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                null, 
                OperationResult.ABORT);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testSaveForDetail002() {
        try {
            // 追加成功(detail)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }

    @Test
    public void testSaveForResult001() {
        try {
            // 追加失敗(result = null)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                null);
            fail();
        } catch (AMSException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testSaveForResult002() {
        try {
            // 追加成功(result = ABORT)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ABORT);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }

    @Test
    public void testSaveForResult003() {
        try {
            // 追加成功(result = ERROR)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.ERROR);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }

    @Test
    public void testSaveForResult004() {
        try {
            // 追加成功(result = SUCCESS)
            save(OperationType.ADD, 
                Calendar.getInstance().getTime(), 
                getTestUser(), 
                new LinkageSystem(), 
                "詳細", 
                OperationResult.SUCCESS);
            assertTrue(true);
        } catch (AMSException e) {
        	e.printStackTrace();
        	fail();
        }
    }
    
    private void save (
            OperationType type, 
            Date dateTime, 
            User user, 
            LinkageSystem linkageSystem, 
            String detail, 
            OperationResult result) throws AMSException {

        EntityManager entityManager = null;
        EntityTransaction tran = null;
        try {
            entityManager = factory.createEntityManager();
            tran = entityManager.getTransaction();

            OperationLogDaoJpa logDao = new OperationLogDaoJpa();
            logDao.setEntityManager(entityManager);

            OperationLog log = new OperationLog();
            log.setType(type);
            log.setDateTime(dateTime);
            log.setUser(user);
            //log.setLinkageSystem(linkageSystem);
            log.setDetail(detail);
            log.setResult(result);

            tran.begin();
            logDao.save(log);
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
