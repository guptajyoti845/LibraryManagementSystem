package org.library.management.system.model;

import java.util.List;

public interface Search {

	public List<Book> searchBy(SearchCriteria searchCriteria, List<Book> books);
}
