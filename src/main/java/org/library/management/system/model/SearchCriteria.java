package org.library.management.system.model;

import java.util.Date;

public class SearchCriteria {

	String title;
	String author;
	String name;
	SubjectType subject;
	Date publicationDate;

	public SearchCriteria(String title, String author, String name, SubjectType subject,
		Date publicationDate) {
		this.title = title;
		this.author = author;
		this.name = name;
		this.subject = subject;
		this.publicationDate = publicationDate;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public SubjectType getSubject() {
		return subject;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}
}
