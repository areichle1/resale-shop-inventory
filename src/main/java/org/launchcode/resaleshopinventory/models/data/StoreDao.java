package org.launchcode.resaleshopinventory.models.data;

import org.launchcode.resaleshopinventory.models.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StoreDao extends CrudRepository<Store, Integer> {
}
