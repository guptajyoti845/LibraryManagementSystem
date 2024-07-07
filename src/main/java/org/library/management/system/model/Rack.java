package org.library.management.system.model;

import java.util.List;

public class Rack {
	private String rackNumber;
	private List<Book> bookList;

	public List<Book> getBookList() {
		return bookList;
	}
}
