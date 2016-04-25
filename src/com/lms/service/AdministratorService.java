package com.lms.service;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lms.dao.AuthorDAO;
import com.lms.dao.BookCopiesDAO;
import com.lms.dao.BookDAO;
import com.lms.dao.BookLoansDAO;
import com.lms.dao.BorrowerDAO;
import com.lms.dao.GenreDAO;
import com.lms.dao.LibraryBranchDAO;
import com.lms.dao.PublisherDAO;
import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.Borrower;
import com.lms.entity.Genre;
import com.lms.entity.LibraryBranch;
import com.lms.entity.Publisher;


//addbranch,deletebranch,updatebranch.


public class AdministratorService {

	
	///write createnew book function
	public void deleteBook(Integer bookId) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			adao.deleteBook(bookId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	
	public Integer getBookCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Integer getBookCount1(int cardNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.getCount11(cardNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Integer getBorrowerCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO adao = new BorrowerDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public Integer getAuthorCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Integer getPublisherCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public Integer getBranchCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			adao.updateBook(book);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	
	
	public int createAuthor(Author a) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		int k1=0;
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			k1= adao.addAuthorWithID(a);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return k1;
	}
	
	public void createAuthorWBook(int au_id,int b_id) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthorWithBook(au_id,b_id);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void deleteAuthors(Integer authorId) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			System.out.println("----");
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(authorId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		
	}
	
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	
	public void createPublisher(String name,String address) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			adao.addpublisher(name,address);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void deletePublisher(Integer publisherId) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			adao.deletePublisher(publisherId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			adao.updatePublisher(publisher);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	
	
	public void createLibraryBranch(LibraryBranch branch) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			adao.addLirbaryBranch(branch);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void deleteLibraryBranch(Integer branchId) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			adao.deleteLirbaryBranch(branchId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void updateLibraryBranch(LibraryBranch branch) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			adao.updateLirbaryBranch(branch);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO adao = new BorrowerDAO(conn);
			adao.addBorrower(borrower);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void deleteBorrower(Integer cardNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO adao = new BorrowerDAO(conn);
			adao.deleteBorrower(cardNo);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO adao = new BorrowerDAO(conn);
			adao.updateBorrower(borrower);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	
	public Integer createAuthorWithID(Author author) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer authorId = null;
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			authorId = adao.addAuthorWithID(author);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return authorId;
	}
	
	public List<Genre> getAllGenre() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO adao = new GenreDAO(conn);
			return adao.readAllGenre();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Book> getAllBooks(int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks(pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<Author> getAllAuthors(int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthors(pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Author> getAllAuthors() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthors();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<LibraryBranch> getAllBranches(int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.readAllBranches(pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<LibraryBranch> getAllBranches() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.readAllBranches();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Publisher> getAllPubliser(int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.readAllPublihser(pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Borrower> getAllBorrower(int pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readAllBorrower(pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Borrower> getAllBorrower() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readAllBorrower();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Book> getAllBooksOfBorrower(int cardNo,int pageNo) throws ClassNotFoundException, SQLException{
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

	public Author getAuthorByID(Integer authorId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsByID(authorId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Borrower getBorrowerByID(Integer cardNo) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowerByID(cardNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}

	public Publisher getpublisherByID(Integer publisherId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO bdao = new PublisherDAO(conn);
			return bdao.readPublisherByID(publisherId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}

	public Book getbookByID(Integer bookId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBookByID(bookId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public void Update_publisher(Publisher a) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			adao.updatePublisher(a);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}

	public Integer createBook(Book b) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer b_id=0;
		try{
			BookDAO bdao = new BookDAO(conn);
		b_id=bdao.saveWithId(b);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return b_id;
	}

	public Integer createBook(String title,int pub_id) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer b_id=0;
		try{
			BookDAO bdao = new BookDAO(conn);
		b_id=bdao.saveWithId1(title,pub_id);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return b_id;
	}
	public void createBookAuthors(Integer bId, int auId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		
		try{
			BookDAO bdao = new BookDAO(conn);
		bdao.saveIO(bId,auId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	
		
	}

	public void updateBookAuthors(Integer bId, int auId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		
		try{
			BookDAO bdao = new BookDAO(conn);
		bdao.saveIO(bId,auId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	
		
	}
	
	public void createBookGenre(int gId, Integer bId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		
		try{
			BookDAO bdao = new BookDAO(conn);
		bdao.saveIO1(gId,bId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}

	public void updateBook_Authors(int au_Id, Integer bId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		
		try{
			BookDAO bdao = new BookDAO(conn);
		bdao.update_book_author(au_Id,bId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}

	
	public void updateBookGenre(int gId, Integer bId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		System.out.println("in book genre table updating the genre");
		try{
			BookDAO bdao = new BookDAO(conn);
		bdao.Updatebook_genre(gId,bId);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	
	public void UpdateBook(Book a) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			adao.updateBook(a);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}

	public void UpdateBook(String title, int booki) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			adao.updateBook1(title,booki);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public void setNoOfCopies(String bookId, String branchId, int nofc) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			System.out.println("bookid :"+bookId);
			System.out.println("branchId :"+branchId);
			System.out.println("no :"+nofc);
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			bdao.Number_of_Copies(bookId,branchId,nofc);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public boolean verifyCard(int cardN) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		boolean present = false;
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(bdao.readBorrowerByID(cardN) != null)
				present = true;
			
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
		return present;
	}

	public void renewBook(String cardNo1, String bookId1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			System.out.println("bookid :"+bookId1);
			System.out.println("cardNo :"+cardNo1);
			BookLoansDAO bl =new BookLoansDAO(conn);
			bl.renew(cardNo1,bookId1);
			//bl.Number_of_Copies(bookId,branchId,nofc);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	
	public void returnbook(String cardNo1, String bookId1) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			System.out.println("bookid :"+bookId1);
			System.out.println("cardNo :"+cardNo1);
			BookLoansDAO bl =new BookLoansDAO(conn);
			bl.returnbook(cardNo1,bookId1);
			//bl.Number_of_Copies(bookId,branchId,nofc);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}

	public void ChangeAuthor(String au_Id, String authorName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			System.out.println("author id :"+au_Id);
			System.out.println("new author name :"+authorName);
			AuthorDAO a = new AuthorDAO(conn);
			a.changeAuthor(au_Id,authorName);
			//bl.Number_of_Copies(bookId,branchId,nofc);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public List<Author> getAllAuthorsByName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Author> getAllAuthorsByNameandBook(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsByNameandbook(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Book> getAllBooks(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.readAllBooksByAll(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<Book> getAllBookByTitle(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.readAllBooks1(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<Book> getAllBookByAuthor(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.readAllBooksByAuthors(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public List<Author> getAllAuthorsByBook1(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsByBook(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Borrower> getAllBorrower1(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			System.out.println("In admin service ");
			return bdao.readAllBorrower(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Borrower> getAllBorrowerByName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			System.out.println("In admin service ");
			return bdao.readAllBorrowerByName(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<Borrower> getAllBorrowerByAddress(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			System.out.println("In admin service ");
			return bdao.readAllBorrowerByAddress(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<LibraryBranch> getAllBranchName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO bdao = new LibraryBranchDAO(conn);
			System.out.println("In admin service ");
			return bdao.getAllBranchByName(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<LibraryBranch> getAllBranchAddress(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO bdao = new LibraryBranchDAO(conn);
			System.out.println("In admin service ");
			return bdao.getAllBranchByAddress(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<LibraryBranch> getAllBranch(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO bdao = new LibraryBranchDAO(conn);
			System.out.println("In admin service ");
			return bdao.getAllBranch(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Publisher> getAllPublisher(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO bdao = new PublisherDAO(conn);
			System.out.println("In admin service of publisher");
			return bdao.getAllPublisher(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Publisher> getAllPublisherByName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO bdao = new PublisherDAO(conn);
			System.out.println("In admin service of publisher");
			return bdao.getAllPublisherByName(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Publisher> getAllPublisherByAddress(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO bdao = new PublisherDAO(conn);
			System.out.println("In admin service of publisher");
			return bdao.getAllPublisherByAddress(searchString,pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Book> getAllAuthorsByBook(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO adao = new BookDAO(conn);
			return adao.readAuthorsBybook(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
}
