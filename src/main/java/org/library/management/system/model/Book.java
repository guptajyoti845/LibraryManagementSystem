package org.library.management.system.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Book {

	private String id;
	private Integer rackNumber;
	private Integer ISBN;
	private String author;
	private String title;
	private SubjectType subject;
	private Date publicationDate;
	private LibraryMember assignedLibraryMember;
	private Date issuedDate;
	private List<BookHistory> bookHistoryList;


	public boolean isAssigned() {
		return assignedLibraryMember != null;
	}

	public boolean isDueOnDate() {
		if (issuedDate == null) {
			return false;
		}
		Date today = new Date();
		long diffInMillis = today.getTime() - issuedDate.getTime();
		long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
		return diffInDays < 15;
	}

	public String getTitle() {
		return title;
	}
	public String getId() {
		return id;
	}

	public Integer getRackNumber() {
		return rackNumber;
	}

	public Integer getISBN() {
		return ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public SubjectType getSubject() {
		return subject;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public List<BookHistory> getBookHistoryList() {
		return bookHistoryList;
	}

	public void setAssignedLibraryMember(
		LibraryMember assignedLibraryMember) {
		this.assignedLibraryMember = assignedLibraryMember;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}


	public void addBookHistory(BookHistory bookHistory) {
		this.bookHistoryList.add(bookHistory);
	}

	public void sendNotificationIfBookIsonDueDateAndIsAssigned() {
		if (this.isDueOnDate() && this.isAssigned()) {
			Notification.send(title + assignedLibraryMember.getName()
				+ "due date notification");
		}
	}
}
