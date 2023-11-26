package com.codingdojo.bookclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.repositories.BookRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo bRepo;
// CREATE--------------------------------
	public Book create(Book newBook, BindingResult result) {
		return bRepo.save(newBook);
	}
// READ ALL------------------------------
	public List<Book> getAll(){
		return bRepo.findAll();
	}
// READ ONE------------------------------
	public Book getById(Long id) {
		return bRepo.findById(id).orElse(null);
	}
// UPDATE--------------------------------
	public Book update(Book newBook, BindingResult result) {
		return this.bRepo.save(newBook);
	}
// DELETE--------------------------------
	public void delete(Long id) {
		this.bRepo.deleteById(id);
	}

}
