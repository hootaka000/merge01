package jp.co.cyberagent.infosys.ams.service;

import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.LinkageSystemDao;
import jp.co.cyberagent.infosys.ams.dao.UserDao;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;
import jp.co.cyberagent.infosys.ams.model.User;
import jp.co.cyberagent.util.ad.ActiveDirectory;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link SystemService} の実装クラス.
 * @author shimizu.kenji
 *
 */
@Service("systemService")
public final class SystemServiceImpl implements SystemService {

    /** User DAO. */
    @Autowired
    //@Qualifier("dummyUserDao")
    private UserDao userDao;
    /** Linkage System DAO. */
    @Autowired
    private LinkageSystemDao linkageSystemDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public User login(final String name, final String password) throws AMSException {
        User user = userDao.findByName(name);
        if (user == null) {
            return null;
        }
        ActiveDirectory ad = ActiveDirectory.getInstance();
        if (ad.isAuthentication(name, password)) {
            return user;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAdminUsers() throws AMSException {
        List<User> admins = new LinkedList<>();
        for (User user : userDao.findAllAdmin()) {
            if (user.getRole() == User.Role.ADMIN) {
                admins.add(user);
            }
        }
        return admins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addAdminUser(final String name) throws AMSException {
        User admin = new User();
        admin.setName(name);
        admin.setRole(User.Role.ADMIN);
        userDao.save(admin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeUser(final long userId) throws AMSException {
        userDao.delete(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LinkageSystem> getLinkageSystems() throws AMSException {
        return linkageSystemDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addLinkageSystem(final String userName, final String systemName)
            throws AMSException {
        // TODO テスト
        User user = userDao.findByName(userName);
        if (user == null) {
            user = new User();
            user.setName(userName);
            user.setRole(User.Role.USER);
            userDao.save(user);
        }
        LinkageSystem linkageSystem = new LinkageSystem();
        linkageSystem.setName(systemName);
        linkageSystem.setUser(user);
        linkageSystem.setAccessKey(createAccessKey());
        linkageSystemDao.save(linkageSystem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeLinkageSystem(final long linkageSystemId) throws AMSException {
        linkageSystemDao.delete(linkageSystemId);
    }

    /**
     * 連携システムを認証するアクセスキーをランダムに生成する.
     * @return アクセスキー
     */
    private String createAccessKey() {
        return RandomStringUtils.randomAlphabetic(LinkageSystem.ACCESS_KEY_LENGTH);
    }
    /**
     * {@link UserDao} を設定する.
     * @param userDao {@link UserDao}
     */
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }
    /**
     * {@link LinkageSystemDao} を設定する.
     * @param linkageSystemDao {@link LinkageSystemDao}
     */
    public void setLinkageSystemDao(final LinkageSystemDao linkageSystemDao) {
        this.linkageSystemDao = linkageSystemDao;
    }
}
