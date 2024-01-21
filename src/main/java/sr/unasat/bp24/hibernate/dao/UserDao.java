package sr.unasat.bp24.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.User;

public class UserDao {
    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public UserDao() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public User create(User user) {

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            if (!transaction.isActive()){
                transaction.begin();
            }

            entityManager.persist(user);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            /*e.printStackTrace();*/
        }
        return user;
    }
    public User verifyUser(String userName, String password) {
        User user = null;
        try {
            entityManager.getTransaction().begin();

            String jpql = "select u from User u where u.userName = ?1 and u.password = ?2";

            TypedQuery<User> query = entityManager.createQuery(jpql, User.class)
                    .setParameter(1, userName)
                    .setParameter(2, password);
            user = query.getSingleResult();
            entityManager.getTransaction().commit();

            return user;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            //e.printStackTrace();
        }
        return user;
    }
}
