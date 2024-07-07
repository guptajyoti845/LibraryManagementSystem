package org.library.management.system;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.library.management.system.model.Book;
import org.library.management.system.model.BookHistory;
import org.library.management.system.model.LibraryMember;
import org.library.management.system.model.Search;
import org.library.management.system.model.SearchCriteria;
import org.library.management.system.model.SubjectType;

public class LibraryManagementSystem {

	private Map<Integer, List<Book>> shelfBooksMap;
	private List<Search> searchFns;

	public static void main(String[] args) {
		System.out.println("Hello world!");
	}

	public LibraryManagementSystem(Map<Integer, List<Book>> shelfBooksMap, List<Search> searchFns) {
		this.shelfBooksMap = shelfBooksMap;
		this.searchFns = searchFns;
	}

	public void sendNotificationForBooksIfBookNotReturnWithInDueDate() {
		for (Map.Entry<Integer, List<Book>> entry : shelfBooksMap.entrySet()) {
			List<Book> bookList = entry.getValue();
			for (Book book : bookList) {
				book.sendNotificationIfBookIsonDueDateAndIsAssigned();
			}
		}
	}

	public Book search(String title, String author, String name, SubjectType subject,
		Date publicationDate) {

		List<Book> books = getAllBooks();

		SearchCriteria searchCriteria = new SearchCriteria(title, author, name, subject,
			publicationDate);

		for (Search search : searchFns) {
			books = search.searchBy(searchCriteria, books);
		}

		return books.stream().findFirst().orElse(null);
	}

	private List<Book> getAllBooks() {

		return shelfBooksMap.values()
			.stream()
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}


	public void assign(Book book, LibraryMember member) {

		if (!isBookAvailableInSystem(book)) {
			System.out.println("Book is already assigned");
			return;
		}

		if (member.isAssignedBookSizeIs10()) {
			System.out.println("Member have already 10 books assigned");
			return;
		}

		for (Map.Entry<Integer, List<Book>> entry : shelfBooksMap.entrySet()) {
			List<Book> bookList = entry.getValue();

			Book searchedBook = bookList.stream()
				.filter(bk -> bk.getId().equals(book.getId()))
				.findFirst()
				.orElse(null);

			if (searchedBook != null) {
				searchedBook.setAssignedLibraryMember(member);
				searchedBook.setIssuedDate(new Date());

				BookHistory bookHistory = new BookHistory(searchedBook.getId(), member.getName(),
					searchedBook.getIssuedDate());

				searchedBook.addBookHistory(bookHistory);

				member.addTheBook(searchedBook);
				break;
			}
		}

	}

	private boolean isBookAvailableInSystem(Book book) {

		for (Map.Entry<Integer, List<Book>> entry : shelfBooksMap.entrySet()) {
			List<Book> bookList = entry.getValue();

			Book searchedBook = bookList.stream()
				.filter(bk -> bk.getId().equals(book.getId()) && !bk.isAssigned())
				.findFirst()
				.orElse(null);

			if (searchedBook != null) {
				return true;
			}
		}
		return false;
	}

	private void renewBook(Book book, LibraryMember member) {
		if (!member.isGivenBookAssigned(book)) { // Tell don't ask already being followed
			return;
		}

		book.setIssuedDate(new Date());
		BookHistory bookHistory = new BookHistory(book.getId(), member.getName(),
			book.getIssuedDate());
		book.getBookHistoryList().add(bookHistory);
	}
}
