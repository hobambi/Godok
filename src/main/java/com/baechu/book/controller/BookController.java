package com.baechu.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baechu.book.dto.BookDto;
import com.baechu.book.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	private final BookService bookService;

	@GetMapping("/detail/{id}")
	public ModelAndView detailPage(@PathVariable Long id){

		ModelAndView modelAndView = new ModelAndView("detail");

		Map<String, Object> info = bookService.bookdetail(id);

		modelAndView.addObject("info", info);

		return modelAndView;
	}

	@GetMapping("/detail/buybooks/{bookid}/{quantity}")
	public ModelAndView Buybook(@PathVariable String bookid, @PathVariable String quantity){

		ModelAndView modelAndView = new ModelAndView("main");

		String ans = bookid+"번 책을 "+quantity+" 권 주문한다용";
		System.out.println(ans);
		return modelAndView;
	}




	@GetMapping("/search")
	public String searchByWord(
		Model model,
		@RequestParam(value = "query", defaultValue = "") String query
	) {
		// 검색어만 적용한 검색
		List<BookDto> result = bookService.searchByWord(query);
		model.addAttribute("result", result);
		return "search";
	}
}