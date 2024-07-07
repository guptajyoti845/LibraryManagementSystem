package org.library.management.system.model;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByPublicationDate implements Search {

	@Override
	public List<Book> searchBy(SearchCriteria searchCriteria, List<Book> books) {
		return books.stream()
			.filter(book -> book.getPublicationDate().equals(searchCriteria.getPublicationDate()))
			.collect(Collectors.toList());
	}
}
