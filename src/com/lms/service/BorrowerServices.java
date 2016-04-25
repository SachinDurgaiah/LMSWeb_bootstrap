package com.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
//import com.lms.entity.LibraryBranch;

import com.lms.dao.AuthorDAO;
import com.lms.dao.BookCopiesDAO;
import com.lms.dao.BookDAO;
import com.lms.dao.BookLoansDAO;
import com.lms.dao.BorrowerDAO;
import com.lms.dao.LibraryBranchDAO;
import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.Borrower;
import com.lms.entity.LibraryBranch;

public class BorrowerServices {

	//verify carNo
	//List the branches
	//list the books associated with that branch
	//check if the books is already checked out by that user
	//add an entry in the book loans table
	//list the books loaned out by the user and let him select which one he chooses to return.
	//check for the due date and update the book_loans table with curdate for dateIn.
	
	//verifying the cardNo for now pass card number as integer which is retieved as an integer
	public int checkCardNo(Integer cardNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			
			System.out.println("in borower DAO");
			return bdao.CheckCard(cardNo);
			
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return 1;
	
	}
	
	//listing the branches
	public List<LibraryBranch> getAllBranches() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.readAllBranches(1);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
		
	}
	
	//listing the books associated with that branch
	public List<Book> getAllBooksWithBranch(int branch_id,int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBookswithBranch(branch_id,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	//checking if the books is already checked out by that user. Make a call to this function form testservice and continue based on the results.
	public boolean CheckIfBookIsLoaned(Book book,Borrower borrower) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.CheckForbooks (book, borrower);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return false;
	}
	
	//add an entry in the book loans table
	public void CheckOutBook(int bookID,int cardNo,int branchID) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			System.out.println("BookId :"+bookID);
			System.out.println("Branch ID "+branchID);
			System.out.println("card number "+cardNo);
			bdao.CheckOutbook (bookID, cardNo,branchID);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	//update the bookCopies table after the check out.
	public void UpdateBooksAfterCheckOut(Book book,LibraryBranch branch) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.UpdateBooksafterCheckOutbook (book,branch);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	
	public Integer getBookCount(int branchId) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.getCount1(branchId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	//list the books loaned out by the user and let him select which one he chooses to return.
	public List<Book> getAllbooksOfBorrower(int cardNo,int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.readAllBookswithCard(cardNo,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;

		
		
	}
	
	
	//check for the due date and update the book_loans table with curdate for dateIn.
	public void CheckInBook(String card,String book ) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			System.out.println("In check in boooks in borrower services");
			bdao.returnbook(card,book);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
}
