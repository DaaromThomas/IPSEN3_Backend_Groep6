package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, String> {
}
