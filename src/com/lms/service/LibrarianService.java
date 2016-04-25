package com.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lms.dao.AuthorDAO;
import com.lms.dao.BookCopiesDAO;
import com.lms.dao.BookDAO;
import com.lms.dao.LibraryBranchDAO;
import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.LibraryBranch;

public class LibrarianService {

	public void UpdateDetails_branches(LibraryBranch branch) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
		LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
		ldao.updateLirbaryBranch(branch);
		conn.commit();
	}catch (Exception e){
		conn.rollback();
	}finally{
		conn.close();
	}
	}

	public List<LibraryBranch> getAllBranches() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.readAllBranches();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
		
	}
	
	/*public List<Book> getAllBooks() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}*/

	/////////////////// Display all the books with authors...
	/////////////////// Update the no of copies..............
	
	public Integer GetNumberOfCopies(Book book,LibraryBranch branch) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer bookId = null;
		Integer branchId = null;
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			//Integer bookCopies = bdao.CountBookInbranch(book,branch); 
			return bdao.CheckBookInbranch(book,branch);
			
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	
	public void UpdateBookCopies(Book book,LibraryBranch branch,Integer copies) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer bookId = null;
		Integer branchId = null;
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			Integer bookExistance = bdao.CheckBookInbranch(book,branch); 
			
			if(bookExistance==0){
				//insert the book in the tbl_book_copies
				bdao.Insertbook_copies(book,branch,copies);
			}
			else{//update the book copies of the existing entry
				bdao.Updatebook_copies(book,branch,copies);
			}
			
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	
	}

	public LibraryBranch getBranchByID(Integer branchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.readAuthorsByID(branchId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	
}
