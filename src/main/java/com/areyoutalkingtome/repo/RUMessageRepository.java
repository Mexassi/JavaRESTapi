package com.areyoutalkingtome.repo;

import com.areyoutalkingtome.model.RUMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Massimo on 29/03/2016.
 */
@Repository
public interface RUMessageRepository extends CrudRepository<RUMessage, Long>{
}
