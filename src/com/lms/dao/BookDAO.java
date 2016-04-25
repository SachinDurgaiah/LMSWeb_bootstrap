package com.lms.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.Genre;
import com.lms.entity.LibraryBranch;
import com.lms.entity.Publisher;

@SuppressWarnings("unchecked")
public class BookDAO extends BaseDAO{
	
	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}


	public void addBook(Book book) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book (title, publisherId) values (?, ?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
	}
	
	//USe this wen you are adding the author. If he wants to select the book for the author. Then add the bookId and Author ID in the Book_AUthor table.
	public List<Book> readAllBooks(int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return (List<Book>) readAll("select * from tbl_book", null);///call the readall first level function.
	}
	
	
	public List<Book> readAllBooks1(String name,int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		name = "%"+name+"%";
		return (List<Book>) readAll("select * from tbl_book where title like ?",new Object[] {name});///call the readall first level function.
		//return (List<Author>) readAll("select * from tbl_author as a left join tbl_book_authors as ba on ba.authorId=a.authorId left join tbl_book as b on ba.bookId=b.bookId where a.authorName like ? or b.title like ?", new Object[] {name,name});
	}
	
	
	public List<Book> readAllBooksByAuthors(String name,int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		name = "%"+name+"%";
		//return (List<Book>) readAll("select * from tbl_book where title like ?",new Object[] {name});///call the readall first level function.
		return (List<Book>) readAll("select * from tbl_book as b left join tbl_book_authors as ba on ba.bookId=a.bookId left join tbl_author as a on ba.authorId=b.authorId where a.authorName like ?", new Object[] {name});
	}
	public List<Book> readAllBooksByAll(String name,int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		name = "%"+name+"%";
		//return (List<Book>) readAll("select * from tbl_book where title like ?",new Object[] {name});///call the readall first level function.
		return (List<Book>) readAll("select * from tbl_book as b left join tbl_book_authors as ba on ba.bookId=a.bookId left join tbl_author as a on ba.authorId=b.authorId where a.authorName like ? or b.title like ?", new Object[] {name,name});
	}
	
	
	//display all the books associated with the branch
	public List<Book> readAllBookswithBranch(int branch_id,int pageNo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		setPageNo(pageNo);
		return (List<Book>) readAll("select * from tbl_book where bookId in (select bc.bookId from tbl_book_copies as bc where bc.branchId = ? and noOfCopies>0)", new Object[] {branch_id});
	}
	
	public Integer getCount() throws ClassNotFoundException, SQLException{
		return getCount("select count(*) from tbl_book",null);
	}
	
	public Integer getCount1(int bId) throws ClassNotFoundException, SQLException{
		return getCount12("select count(*) from tbl_book left join tbl_book_loans on tbl_book.bookId=tbl_book_loans.bookId where tbl_book_loans.branchId=?",new Object[] {bId});
	}
	
	public Integer getCount11(int card_no) throws ClassNotFoundException, SQLException{
		return getCount12("select count(*) from tbl_book left join tbl_book_loans on tbl_book.bookId=tbl_book_loans.bookId where tbl_book_loans.cardNo=?",new Object[] {card_no});
	}
	
	
	
	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		AuthorDAO adao = new AuthorDAO(getConnection());
		GenreDAO gdao = new GenreDAO(getConnection());
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			b.setPublisher(p);
			try {
				b.setAuthor((List<Author>) adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));
				b.setGenre((List<Genre>) gdao.readFirstLevel("select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId = ?)", new Object[] {b.getBookId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			books.add(b);
		}
		return books;
	}
	
	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			b.setPublisher(p);

			books.add(b);
		}
		return books;
	}


	public void deleteBook(Integer bookId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("delete from tbl_book where bookId = ?", new Object[] {bookId});
		
	}
	public void updateBook1(String title, int booki) throws ClassNotFoundException, SQLException {
		save("update tbl_book set title = ? where bookId = ?", new Object[] {title,booki});
		
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("update tbl_book set title = ? where bookId = ?", new Object[] {book.getTitle(),book.getPubId()});
	}


	public Integer saveWithId(Book b) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Integer b_Id = saveWithID("insert into tbl_book (title, pubId) values (?, ?)", new Object[] {b.getTitle(), b.getPublisher().getPublisherId()});
		return b_Id;
		
	}
	public Integer saveWithId1(String title,int pub_id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Integer b_Id = saveWithID("insert into tbl_book (title, pubId) values (?, ?)", new Object[] {title,pub_id});
		return b_Id;
		
	}


	public void saveIO(Integer bId, int auId) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_authors (bookId, authorId) values (?, ?)",bId, auId);
		
	}
	
	public void update_book_author(Integer auId, int bId) throws ClassNotFoundException, SQLException {
		
		boolean b=getCt("select * from tbl_book_authors where bookId=? and authorId=?", new Object[] {bId,auId});
		if(!b){
			save("insert into tbl_book_authors (bookId,authorId) values (?,?)",bId,auId);
		}
		
	}

	
	public void updatebook_author(Integer bId, int auId) throws ClassNotFoundException, SQLException {
	
		boolean b=getCt("select * from tbl_book_authors where bookId=? and authorId=? ", new Object[] {bId,auId});
		if(!b){
			save("insert into tbl_book_authors (bookId,authorId) values (?,?)",bId,auId);
		}
		
	}

	public void saveIO1(int gId, Integer bId) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_genres (genre_id,bookId) values (?,?)",gId,bId);
		
	}
	public void Updatebook_genre(int gId, Integer bId) throws ClassNotFoundException, SQLException {
		//List <Genre> g = (List<Genre>) readAll("select * from tbl_book_genres where bookId=?",new Object[] {bId});
		boolean b=getCt("select * from tbl_book_genres where bookId=? and genre_id=? ", new Object[] {bId,gId});
		if(!b){
			save("insert into tbl_book_genres (genre_id,bookId) values (?,?)",gId,bId);
		}
		
		
	}

	public Book readBookByID(Integer bookId) throws ClassNotFoundException, SQLException {
		List<Book> b = (List<Book>) readAll("select * from tbl_book where bookId = ?", new Object[] {bookId});
		if(b!=null && b.size() >0){
			return b.get(0);
		}
		return null;
	}
	public List<Book> readAuthorsBybook(String name, int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		name = "%"+name+"%";
		return (List<Book>) readAll("select * from tbl_author as a left join tbl_book_authors as ba on ba.authorId=a.authorId left join tbl_book as b on ba.bookId=b.bookId where b.title like ?", new Object[] {name,name});
	}

	


	






	


	

	

}

