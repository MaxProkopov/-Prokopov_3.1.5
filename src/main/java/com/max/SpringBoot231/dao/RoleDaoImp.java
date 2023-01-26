package com.max.SpringBoot231.dao;

import com.max.SpringBoot231.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> listRole() {
        String e = "from Role";
        TypedQuery<Role> query = entityManager.createQuery(e, Role.class);
        return query.getResultList();
    }


    @Override
    public Role show(int id) {
        String e = "from Role where id =" + id;
        TypedQuery<Role> query = entityManager.createQuery(e, Role.class);
        return query.getSingleResult();
    }
    @Override
    public Role showName(String name){
        return entityManager.createQuery("from Role where name = ?1", Role.class).setParameter(1, name).getSingleResult();
    }

    @Override
    public void save(Role role) {
    entityManager.persist(role);
    }
}
