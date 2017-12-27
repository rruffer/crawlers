package br.com.rruffer.crawlers.domain.repository.shared;

import java.io.Serializable;
import java.util.List;

public interface Insertable<T extends Serializable> extends Repository {

    void insert(T object);

    void insert(List<T> list);
}
