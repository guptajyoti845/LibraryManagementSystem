package org.library.management.system.model;

import java.util.Date;

public class BookHistory {
	private String bookId;
	private String issueBy;
	private Date issueOn;
	private Date unIssueOn;

	public BookHistory(String bookId, String issueBy, Date issueOn) {
		this.bookId = bookId;
		this.issueBy = issueBy;
		this.issueOn = issueOn;
	}
}
