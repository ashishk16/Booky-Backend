package com.bookrental.Repositories;

import com.bookrental.Models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository <Author, Integer>{
    Author findByName(String name);
}
