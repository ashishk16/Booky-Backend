package com.bookrental.Services;

import com.bookrental.Models.Book;
import com.bookrental.Models.Copy;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CopyService {
    @Autowired
    CopyRepository copyRepository;

    public CopyService() {
    }

    public List<Copy> getCopies() {
        List<Copy> copies = new ArrayList<>();
        copyRepository.findAll().forEach(copies::add);
        return copies;
    }

    public Copy getCopyById(String id) {
        return copyRepository.findOne(id);
    }

    public boolean add(List<Copy> copies) {
        copyRepository.save(copies);
        return true;
    }
}
