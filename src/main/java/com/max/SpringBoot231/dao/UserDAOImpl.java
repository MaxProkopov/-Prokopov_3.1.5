package com.max.SpringBoot231.dao;

import com.max.SpringBoot231.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void userSave(User user) {

        entityManager.persist(user);
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id =?1");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    public User showName(String name) {
        return entityManager.createQuery("from User where name = ?1", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }
}
