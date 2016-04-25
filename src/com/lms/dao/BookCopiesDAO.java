package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lms.entity.Book;
import com.lms.entity.LibraryBranch;

public class BookCopiesDAO extends BaseDAO {
	
	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	///librarian service.
	public Integer CheckBookInbranch(Book book,LibraryBranch branch) throws ClassNotFoundException, SQLException {
		return getCount("select count(*) from tbl_book_copies where bookId = ? and branchId = ?", new Object[] {book.getBookId(), branch.getBranchId()} );
		
	}
	
	///librarian service.
	/*public Integer CountBookInbranch(Book book, LibraryBranch branch) throws ClassNotFoundException, SQLException {
		//***********check this function specifically if it returns the nomber of copies or the number of ..!!!!!
		return getC("select noOfCopies from tbl_book_copies where bookId = ? and branchId = ?", new Object[] {book.getBookId(), branch.getBranchId()} );
	}
*/
	///librarian service.
	public void Insertbook_copies(Book book, LibraryBranch branch, Integer copies) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_copies (bookId, branchId,noOfCopies) values (?, ?, ?)", new Object[] {book.getBookId(), branch.getBranchId(),copies });
		
	}
	
	///librarian service.
	public void Updatebook_copies(Book book, LibraryBranch branch, Integer copies) throws ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies= ? where bookId = ? and branchId = ?", new Object[] {book.getBookId(), branch.getBranchId(),copies });
	}
	
	

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void Number_of_Copies(String bookId, String branchId, int nofc) throws ClassNotFoundException, SQLException {
		int bId=Integer.parseInt(bookId);
		int brId=Integer.parseInt(branchId);
		System.out.println("FInal step to updating the number of copies");
		// TODO Auto-generated method stub
		save("insert into tbl_book_copies (bookId, branchId,noOfCopies) values (?, ?, ?)", new Object[] {bId,brId,nofc });
	}
	

}
