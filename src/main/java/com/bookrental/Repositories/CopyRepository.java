package com.bookrental.Repositories;

import com.bookrental.Models.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends CrudRepository<Copy, String> {
}
