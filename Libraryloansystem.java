
package libraryloansystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Libraryloansystem {

 
// Book Class

class Book {

private String isbn;

private String title;

private String author;

private boolean isAvailable;

// Constructor 1

public Book(String isbn, String title, String author) {

this.isbn = isbn;

this.title = title;

this.author = author;

this.isAvailable = true; // Default to available

}

// Constructor 2 (overloaded)

public Book(String isbn, String title, String author, boolean isAvailable) {

this.isbn = isbn;

this.title = title;

this.author = author;

this.isAvailable = isAvailable;

}

public String getIsbn() {

return isbn;

}

public String getTitle() {

return title;

}

public String getAuthor() {

return author;

}

public boolean isAvailable() {

return isAvailable;

}

public void setAvailable(boolean available) {

isAvailable = available;

}

@Override

public String toString() {

return "Book{" +

"isbn='" + isbn + '\'' +

", title='" + title + '\'' +

", author='" + author + '\'' +

", isAvailable=" + isAvailable +

'}';

}

@Override

public boolean equals(Object o) {

if (this == o) return true;

if (o == null || getClass() != o.getClass()) return false;

Book book = (Book) o;

return Objects.equals(isbn, book.isbn);

}

@Override

public int hashCode() {

return Objects.hash(isbn);

}

}


// Create a new loan

Date borrowDate = new Date();

// For simplicity, let's set due date to 14 days from borrow date

long fourteenDaysInMillis = 14 * 24 * 60 * 60 * 1000;

Date dueDate = new Date(borrowDate.getTime() + fourteenDaysInMillis);

Loan newLoan = new Loan(book, member, borrowDate, dueDate);

// Update book availability

 book.setAvailable(false);

// Add loan to member's list and library's loan list

member.addLoan(newLoan);

loans.add(newLoan);

System.out.println("Book '" + book.getTitle() + "' lent to " + member.getName() + ".");

}

public void returnBook(int memberId, String isbn) {

Member member = findMemberById(memberId);

Book book = findBookByIsbn(isbn);

if (member == null) {

System.out.println("Error: Member with ID " + memberId + " not found.");

return;

}

if (book == null) {

System.out.println("Error: Book with ISBN " + isbn + " not found.");

return;

}

Loan loanToRemove = null;

for (Loan loan : loans) {

if (loan.getMember().getMemberId() == memberId && loan.getBook().getIsbn().equals(isbn)) {

loanToRemove = loan;

break;

}

}

if (loanToRemove == null) {

System.out.println("Error: No active loan found for member " + member.getName() + " and book '" + book.getTitle() + "'.");

return;

}

// Update book availability

book.setAvailable(true);

// Remove loan from member's list and library's loan list

member.removeLoan(loanToRemove);

loans.remove(loanToRemove);

System.out.println("Book '" + book.getTitle() + "' returned by " + member.getName() + ".");

}

public Book findBookByTitle(String title) {

for (Book book : books) {

if (book.getTitle().equalsIgnoreCase(title)) {

return book;

}

}

return null;

}

// Helper method to find a book by ISBN

private Book findBookByIsbn(String isbn) {

for (Book book : books) {

if (book.getIsbn().equals(isbn)) {

return book;

}

}

return null;

}

// Helper method to find a member by ID

private Member findMemberById(int memberId) {

for (Member member : members) {

if (member.getMemberId() == memberId) {

return member;

}

}

return null;

}

@Override

public String toString() {

StringBuilder sb = new StringBuilder();

sb.append("Library State:\n");

sb.append("Books:\n");

for (Book book : books) {

sb.append(" ").append(book).append("\n");

}

sb.append("Members:\n");

for (Member member : members) {

sb.append(" ").append(member).append("\n");

}

sb.append("Active Loans:\n");

if (loans.isEmpty()) {

sb.append(" No active loans.\n");

} else {

for (Loan loan : loans) {

sb.append(" ").append(loan).append("\n");

}

}

return sb.toString();

}

}
