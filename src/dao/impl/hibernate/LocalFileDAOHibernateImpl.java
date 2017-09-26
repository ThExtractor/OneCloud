package dao.impl.hibernate;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dao.LocalFileDAO;
import dao.entity.LocalFileDO;
import manager.util.HibernateUtil;

public class LocalFileDAOHibernateImpl extends GenericDAOHibernateImpl<LocalFileDO> implements LocalFileDAO {

    public LocalFileDAOHibernateImpl() {
        super(LocalFileDO.class);
    }

    @Override
    public LocalFileDO getByPath(long userID, long parent, String localName, String localType) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.userID = :userID and file.parent = :parent and file.localName = :localName and file.localType = :localType");
        query.setParameter("userID", userID);
        query.setParameter("parent", parent);
        query.setParameter("localName", localName);
        query.setParameter("localType", localType);
        List<LocalFileDO> result = query.list();
        if (result.size() == 1) {
            return result.get(0);
        } else if (result.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<LocalFileDO> listByParent(long parent) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.parent = :parent");
        query.setParameter("parent", parent);
        List<LocalFileDO> result = query.list();
        return result;
    }
    
    @Override
    public List<LocalFileDO> listRecentFile(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime aWeekAgo = LocalDateTime.now().minusDays(7);
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.userID=:userID and file.ldtCreate > :aWeekAgo");
        query.setParameter("userID", userID);
        query.setParameter("aWeekAgo", aWeekAgo);
        List<LocalFileDO> result = query.list();
        return result;
    }

    @Override
    public List<LocalFileDO> listDocument(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.userID=:userID and (file.localType='doc' or file.localType='xls' or file.localType='ppt' or file.localType='txt')");
        query.setParameter("userID", userID);
        List<LocalFileDO> result = query.list();
        return result;
    }

    @Override
    public List<LocalFileDO> listPicture(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.userID=:userID and (file.localType='jpg' or file.localType='png' or file.localType='gif')");
        query.setParameter("userID", userID);
        List<LocalFileDO> result = query.list();
        return result;
    }

    @Override
    public List<LocalFileDO> listVideo(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.userID=:userID and file.localType='mp4'");
        query.setParameter("userID", userID);
        List<LocalFileDO> result = query.list();
        return result;
    }

    @Override
    public List<LocalFileDO> listMusic(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        Query<LocalFileDO> query = session.createQuery("from LocalFileDO file where file.userID=:userID and file.localType='mp3'");
        query.setParameter("userID", userID);
        List<LocalFileDO> result = query.list();
        return result;
    }
}
