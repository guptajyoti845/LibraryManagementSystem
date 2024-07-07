package org.library.management.system.model;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByTitle implements Search {
	@Override
	public List<Book> searchBy(SearchCriteria searchCriteria, List<Book> books) {
		return books.stream()
			.filter(book -> book.getTitle().equals(searchCriteria.getTitle()))
			.collect(Collectors.toList());
	}
}
