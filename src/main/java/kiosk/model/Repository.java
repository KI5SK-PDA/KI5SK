package kiosk.model;

import java.util.List;
import java.util.Optional;

public interface Repository<ID, T> {
    Optional<T> findById(ID id);
    T save(T t); // id가 객체에 존재하지 않거나 저장소에 없으면 추가, id가 존재하면 업데이트
    List<T> findAll();
    void deleteById(ID id);
    void clear();
}
