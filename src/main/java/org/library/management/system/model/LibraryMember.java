package org.library.management.system.model;

import java.util.List;
import java.util.Objects;

public class LibraryMember {

	private LibraryCard card;
	private String name;
	private List<Book> assignedBooks;

	public String getName() {
		return name;
	}

	public Boolean isGivenBookAssigned(Book book) {

		Book searchedBook = this.assignedBooks.stream()
			.filter(book1 -> book1.getId().equals(book.getId()))
			.findFirst().orElse(null);

		return Objects.nonNull(searchedBook);
	}

	public boolean isAssignedBookSizeIs10() {
		return this.assignedBooks.size() == 10;
	}
	public void addTheBook(Book searchedBook) {
		this.assignedBooks.add(searchedBook);
	}
}
