package com.journalapp.repository;

import com.journalapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.spi.QueryOptions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUserForSA(){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq= cb.createQuery(User.class);

        Root<User> root= cq.from(User.class);

        cq.select(root)
                .where(cb.equal(
                                root.get("userName"),"ritesh"
                        )
                );

        return entityManager.createQuery(cq).getResultList();
    }
}
