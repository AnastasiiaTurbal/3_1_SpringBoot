package web.example.PP_3_2_1_SpringBoot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.example.PP_3_2_1_SpringBoot.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(){
        return entityManager.createQuery("from User").getResultList();
    }

    @Transactional
    @Override
    public User showUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) { entityManager.merge(user); }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("DELETE User where id=:id").setParameter("id", id).executeUpdate();
    }


}