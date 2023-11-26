package com.codingdojo.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bService;
	@Autowired
	private UserService uService;

// SHOW--------------------------------
	@GetMapping("/new")
	public String index(@ModelAttribute("newBook")Book newBook, Model viewModel,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		viewModel.addAttribute("allTheThings", this.bService.getAll());
		
//		User creatorId = uService.findById((Long) session.getAttribute("user_id"));
//		session.setAttribute("loggedInUserId", creatorId);
		return "create.jsp";
	}
// CREATE-------------------------------
	@PostMapping("/create")
	public String createBook(@Valid @ModelAttribute("newBook")Book newBook,
			BindingResult result, Model viewModel, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		if(result.hasErrors()) {
			viewModel.addAttribute("allTheThings", this.bService.getAll());
			return "create.jsp";
		}
		
//		session.setAttribute("userId", newestBook.getId());
//		System.out.println(session.getAttribute("userId"));
//		System.out.println(this.uService.findById((Long)session.getAttribute("userId")));
		newBook.setCreator(this.uService.findById((Long)session.getAttribute("userId")));
//		System.out.println(newBook.getCreator().getId());
		this.bService.create(newBook, result);
		return"redirect:/users/home";
	}

// SHOW-----------------------------------
	@GetMapping("/{id}")
	public String showBook(@PathVariable("id") Long id,
			Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Long currentUser = (Long) session.getAttribute("userId");
//		System.out.println(currentUser);
		Book book = bService.getById(id);
//		System.out.println(book.getCreator().getId());
		if (session.getAttribute("userId") == book.getCreator().getId()){
			User creator = book.getCreator();
			model.addAttribute("creator", creator);
		}
		model.addAttribute("book", book);
		model.addAttribute("currentUser", currentUser);
		return "show.jsp";
	}

// EDIT AND UPDATE----------------------------------
	@GetMapping("/edit/{bookId}")
	public String editRender(Model viewModel, 
			@PathVariable("bookId")Long idToEdit,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		viewModel.addAttribute("bookToEdit", this.bService.getById(idToEdit));
//		System.out.println("userId " + session.getAttribute("userId"));
		return "edit.jsp";
	}
	// {id} parameter needs to be "id" on the post route otherwise it will create a new instance
	@PutMapping("/update/{id}")
		// @PutMapping need to change method in the form as "PUT"
	public String updateValues(
			@Valid @ModelAttribute("bookToEdit")Book editedBook, BindingResult result, 
			@PathVariable("id") Long bookId, 
			Model viewModel, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		User currentUser = this.uService.findById((Long)session.getAttribute("userId"));
		viewModel.addAttribute("currentUser", currentUser);
//		viewModel.addAttribute("bookToEdit", this.bService.getById(bookId));
		editedBook.setCreator(currentUser);
		if(result.hasErrors()) {
//			viewModel.addAttribute("allTheThings", bService.getAll());
			return"edit.jsp";
		}
		this.bService.update(editedBook, result);
//		System.out.println(currentUser);
		return"redirect:/books/{id}";
	}

// DELETE--------------------------------------
	@DeleteMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable("bookId")Long bookId,
			HttpSession session) {
//		System.out.println(bookId);
		this.bService.delete(bookId);
		return "redirect:/users/home";
	}

}
