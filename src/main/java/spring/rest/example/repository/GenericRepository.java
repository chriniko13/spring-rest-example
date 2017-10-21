package spring.rest.example.repository;

import java.util.Collection;

public interface GenericRepository<ID, ENT> {

    Collection<ENT> list();

    void save(ENT ent);

    ENT get(ID id);

    void update(ENT ent);

    void delete(ID id);
}
