package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Ошибка добавления элемента", e);
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            // merge безопаснее для detached-объектов, но можно использовать и HQL UPDATE
            session.merge(item);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Ошибка обновления элемента", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Ошибка удаления элемента", e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        try {
            return session.createQuery("FROM Item ORDER BY id ASC", Item.class)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        try {
            // Примечание: поле 'name' должно совпадать с именем поля в сущности Item
            return session.createQuery(
                            "FROM Item AS i WHERE i.name LIKE :fKey", Item.class)
                    .setParameter("fKey", "%" + key + "%")
                    .getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        try {
            return session.createQuery(
                            "FROM Item AS i WHERE i.id = :fId", Item.class)
                    .setParameter("fId", id)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    @Override
    public void close() {
    }
}