package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.BaseDAO;
import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.BookLoans;
import com.lms.entity.Borrower;
import com.lms.entity.LibraryBranch;
import com.lms.entity.Publisher;

public class BookLoansDAO extends BaseDAO{

	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}



	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	//checking for a book if they are already checked-out by the borrower. 
	public boolean CheckForbooks(Book book, Borrower borrower) throws ClassNotFoundException, SQLException {
		Integer result = getCount("select count(*) from tbl_book_loans where bookId = ? and cardNo= ? and dateIn is NULL", new Object[] {book.getBookId(),borrower.getCardNo()});
		
		if(result ==0){
			return true;
		}
		else{
			return false;
		}
	}

	//inserting a new book to the Book loans table.
	public void CheckOutbook(int book_id, int card_no,int branch_id) throws ClassNotFoundException, SQLException {
		System.out.println("Final step of bookloaning");
		try{
			System.out.println("bookId "+book_id);
			System.out.println("branchID "+branch_id);
			System.out.println("CardNo "+card_no);
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate ) "
				+ "values( ?, ?, ?,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 15 DAY))",new Object[] {book_id,branch_id,card_no});
		System.out.println("entry added to the book loans table");
		save("update tbl_book_copies set noOfCopies = noOfCopies-1 where bookId=? and branchId =?", new Object[] {book_id,branch_id});
		}
		catch(Exception e){
		System.out.println(e);
		}
	}

	//updating the number of copies after updating the number of copies.
	public void UpdateBooksafterCheckOutbook(Book book, LibraryBranch branch) throws ClassNotFoundException, SQLException {
	
		save("update tbl_book_copies set noOfCopies = noOfCopies-1 where bookId=? and brachId =?", new Object[] {book.getBookId(),branch.getBranchId()});
	}

	public void CheckInbook(Book book, LibraryBranch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("update tbl_book_loans set dateIn = CURDATE() where bookId=? and brachId =? and cardNo = ?", new Object[] {book.getBookId(),borrower.getCardNo()});
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAllBookswithCard(int cardNo,int pageNo) throws ClassNotFoundException, SQLException { //return the book object.!!
		// TODO Auto-generated method stub
		setPageNo(pageNo);
		return (List<Book>) readAll("select * from tbl_book as b where b.bookId in (select bookId from tbl_book_loans where cardNo=? and dateIn is NULL )", new Object[] {cardNo});
		//select b.title from books where b.Id in (select bId from bookLoans where cardNo = ?)", new Object[] {borrower.getCardNo()}
	}
	public List<?> extractData(ResultSet rs) throws SQLException {
	 
		List<Book> books = new ArrayList<Book>();
		//Here i am returning the books that a borrower has checked out.
		BookDAO bdao = new BookDAO(getConnection());
		while(rs.next()){
			Book a = new Book();
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			a.setPubId(rs.getInt("pubId"));
		
			books.add(a);
		}
		return books;

	}



	public void renew(String cardNo1, String bookId1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int cardNo=Integer.parseInt(cardNo1);
		int bookId=Integer.parseInt(bookId1);
		System.out.println("renewing");
		try{
		save1("update tbl_book_loans set dueDate=DATE_ADD(CURDATE(),INTERVAL 25 DAY) where bookId=? and cardNo=?",bookId,cardNo);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}



	public void returnbook(String cardNo1, String bookId1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("In return book in boooks in BookloansDAO");
		int c_no=Integer.parseInt(cardNo1);
		int b_id=Integer.parseInt(bookId1);
		int branch_id = getC("select branchId from tbl_book_loans where bookId=? and cardNo = ? and dateIn is NULL", new Object[] {b_id,c_no});
		System.out.println("branch id: "+branch_id);
		save("delete from tbl_book_loans where bookId=? and branchId=? and cardNo = ?",new Object[] {b_id,branch_id,c_no});
		System.out.println("book returned.");
		save1("update tbl_book_copies set noOfCopies = noOfCopies+1 where bookId=? and branchId =?",b_id,branch_id);
		
	}
	
	
	

}
