package com.lms.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	
import com.lms.entity.Genre;
	

	public class GenreDAO extends BaseDAO{
		public GenreDAO(Connection conn) {
			super(conn);
			// TODO Auto-generated constructor stub
		}
		public void addGenre(Genre genre) throws ClassNotFoundException, SQLException{
			save("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenre_name()});
		}
		
		public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException{
			save("update tbl_author set authorName = ? where authorId = ?", new Object[] {genre.getGenre_name(), genre.getGenre_id()});
		}
		
		public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException{
			save("delete from tbl_author where authorId = ?", new Object[] {genre.getGenre_id()});
		}
		
		public List<Genre> readAllGenre() throws ClassNotFoundException, SQLException{
			return (List<Genre>) readAll("select * from tbl_genre", null);
		}
		
		/*public List<Genre> readAuthorsByBookId(int ) throws ClassNotFoundException, SQLException{
			return (List<Author>) readAll("select * from tbl_author where authorName like ?", new Object[] {name});
		}*/
		
		public Integer getCount() throws ClassNotFoundException, SQLException{
			return getCount("select count(*) from tbl_genre",null);
		}
		
		@Override
		public List<Genre> extractData(ResultSet rs) throws SQLException {
			List<Genre> genre = new ArrayList<Genre>();
			
			while(rs.next()){
				Genre g = new Genre();
				//BookDAO bookDao = new BookDAO();
				g.setGenre_id(rs.getInt("genre_id"));
				g.setGenre_name(rs.getString("genre_name"));
				//a.setBookList(bookDAO.readAllBookByAuthorId(rs.getInt("authorId"))
				
				genre.add(g);
			}
			return genre;
		}
		@Override
		public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
			List<Genre> genre = new ArrayList<Genre>();
			
			while(rs.next()){
				Genre g = new Genre();
				//BookDAO bookDao = new BookDAO();
				g.setGenre_id(rs.getInt("genre_id"));
				g.setGenre_name(rs.getString("genre_name"));
				//a.setBookList(bookDAO.readAllBookByAuthorId(rs.getInt("authorId"))
				
				genre.add(g);
			}
			return genre;
		}

	

}
