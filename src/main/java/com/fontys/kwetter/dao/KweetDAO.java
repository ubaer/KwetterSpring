package com.fontys.kwetter.dao;

import com.fontys.kwetter.domain.Kweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kevin.
 */
@Repository
public interface KweetDAO extends CrudRepository<Kweet, Long> {
    List<Kweet> findByPosterId(Long posterId);
}
