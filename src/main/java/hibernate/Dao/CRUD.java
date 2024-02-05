package hibernate.Dao;

import java.util.List;

public interface CRUD<T> {
    void save(T object);

    T findById(Long id);

    List<T> getAll();

    void update(Long id, T object);

    void delete(Long id);

}
