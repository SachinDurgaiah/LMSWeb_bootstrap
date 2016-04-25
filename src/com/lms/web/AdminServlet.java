package com.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.Borrower;
import com.lms.entity.Genre;
import com.lms.entity.LibraryBranch;
import com.lms.entity.Publisher;
import com.lms.service.AdministratorService;
import com.lms.service.BorrowerServices;
import com.lms.service.LibrarianService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor",
	"/viewAuthor",
	"/editAuthor", 
	"/deleteAuthor",

	"/addBook",
	"/editbook",
	"/deletebook",

	"/addcopies",
	"/editBorrower12",
	"/editBorrower",
	"/addBorrower",
	"/deleteBorrower",

	"/editpublisher",
	"/deletePublisher",
	"/addPublisher",

	"/addBranch",
	"/editbranch",
	"/deletebranch",

	"/editBranchDetails",
	"/modifyBranch",
	"/addBcopies",
	"/verifyBorrower",
	"/renewBook",
	"/findBforcheckout",
	"/verifyBorrowerforbookreturn",
	"/returnBook",
	"/branchSelected",
	"/checkoutbook" ,
	"/ChangeAuthor",
	"/pageAuthors",
	"/pageBooks",
	"/pageBranch",
	"/pagePublisher",
	"/searchAuthors",
	"/searchBorrowers",
	"/searchBranch",
	"/searchPublisher",
	"/searchBook"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/branchSelected":
			branchSelected(request, response);
			break;
		case "/findBforcheckout":
			findBforcheckout(request, response);
			break;
		case "/returnBook":
			returnBook(request, response);
			break;
		
		case "/verifyBorrowerforbookreturn":
			verifyBorrowerforbookreturn(request, response);
			break;

		
		case "/editbook":
			editbook(request, response);
			break;
		case "/editAuthor":
			try {
				editAuthor(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/deleteAuthor":
			deleteAuthor(request, response);
			break;

		case "/modifyBranch":
			try {
				modifyBranch(request, response);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/editBorrower":
			try {
				editBorrower(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/editpublisher":
			try {
				editPublisher(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/deletePublisher":
			deletePublisher(request, response);
			break;
		case "/deleteBorrower":
			deleteBorrower(request, response);
			break;
		case "/editbranch":
			editBranch(request, response);
			break;
		case "/deletebranch":
			deletebranch(request, response);
			break;
		case "/deletebook":
			deletebook(request, response);
			break;
		case "/verifyBorrower":
			verifyBorrower(request, response);
			break;
		case "/renewBook":
			renewBook(request, response);
			break;
		case "/checkoutbook":
			checkoutbook(request, response);
			break;
		case "/pageAuthors":
			pageAuthors(request, response);
			break;
		case "/pageBooks":
			pageBooks(request, response);
			break;
		case "/pageBorrower":
			pageBorrower(request, response);
			break;
		case "/pageBranch":
			pageBranch(request, response);
			break;
		case "/pagePublisher":
			pagePublisher(request,response);
			break;
		case "/searchAuthors":
			searchAuthors(request,response);
			break;
		case "/searchPublisher":
			searchPublisher(request,response);
			break;
		case "/searchBorrowers":
			searchBorrowers(request,response);
			break;

		case "/searchBranch":
			searchBranch(request,response);
			break;
		case "/searchBook":
			searchBook(request,response);
			break;
		default:
			break;

		}
	}

	
	private void searchAuthors(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		String option = request.getParameter("text");
		System.out.println("option selected : "+option);
		System.out.println("searchString "+searchString);
		List<Author> authors= null;
		StringBuilder str = new StringBuilder();
		try {
			if(option.equals("Search by All")){
				 authors = service.getAllAuthorsByNameandBook(searchString, 1);
			}
			else if(option.equals("Search by Authors")){
			 authors = service.getAllAuthorsByName(searchString, 1);
			}
			else if(option.equals("Search by Books")){
				authors = service.getAllAuthorsByBook1(searchString, 1);
			}
			
			//request.setAttribute("authors", authors);
			System.out.println("sending the results");
			
			
			str.append("<tr><th>Author Name</th><th>Book Title</th><th>Edit</th><th>Delete</th></tr>");
			for (Author a : authors) {
				str.append("<tr><td>" + a.getAuthorName()+"</td>");
				if(a.getBooks()!=null && a.getBooks().size() >0){
					
					str.append("<td>");
					for(Book b: a.getBooks()){
						str.append(b.getTitle());
						str.append(",");
						
					
						
					}
				}	
			
				str.append("<td align='center'><button type='button' class='btn btn btn-primary' data-toggle='modal' data-target='#myModal1' href='editauthor.jsp?authorId"+ a.getAuthorName()+"'>EDIT</button></td>"
						+ "	<td><button type='button' class='btn btn-sm btn-danger'	onclick='deleteAuthor" + a.getAuthorName()+"'>DELETE</button></td></tr>" );
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//System.out.println(str.toString());
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void searchPublisher(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		String option = request.getParameter("text");
		System.out.println("option selected : "+option);
		System.out.println("searchString "+searchString);
		List<Publisher> publisher=null;
		StringBuilder str = new StringBuilder();
		try {
			
			if(option.equals("Search by All")){
				publisher = service.getAllPublisher(searchString, 1);
			}
			else if(option.equals("Search by Publisher Name")){
				publisher = service.getAllPublisherByName(searchString, 1);
			}
			else if(option.equals("Search by Publisher Address")){
				publisher = service.getAllPublisherByAddress(searchString, 1);
			}
			
			str.append("<tr><th>Publisher Name</th><th>Publisher Address</th><th>Edit</th><th>Delete</th></tr>");
			
			for (Publisher a: publisher) {
				str.append("<tr><td>" + a.getPublisherName()+"</td>");
				str.append("<td>"+a.getPublisherAddress()+"</td>");
					
			
				str.append("<td align='center'><button type='button' class='btn btn btn-primary' data-toggle='modal' data-target='#myModal1' href='editpublisher?publisherId="+a.getPublisherId()+"'>EDIT</button></td>"
						+ "	<td><button type='button' class='btn btn-sm btn-danger'	onclick='deleteAuthor" + a.getPublisherId()+"'>DELETE</button></td></tr>" );
			}
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//System.out.println(str.toString());
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void searchBorrowers(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		String option = request.getParameter("text");
		System.out.println("option selected : "+option);
		System.out.println("searchString "+searchString);
		List<Borrower> borrower=null;
		StringBuilder str = new StringBuilder();
		try {
			
			if(option.equals("Search by All")){
				borrower = service.getAllBorrower1(searchString, 1);
			}
			else if(option.equals("Search by Borrower name")){
				borrower = service.getAllBorrowerByName(searchString, 1);
			}
			else if(option.equals("Search by Borrower Address")){
				borrower = service.getAllBorrowerByAddress(searchString, 1);
			}
			
			
			str.append("<tr><th>Borrower Name</th><th>Address</th><th>Phone</th><th>Edit</th><th>Delete</th></tr>");
			
			for (Borrower br: borrower){
				str.append("<tr><td>" + br.getName()+"</td>");
				str.append("<td>"+br.getAddress()+"</td>");
				str.append("<td>"+br.getPhone()+"</td>");
			
				str.append("<td align='center'><button type='button' class='btn btn btn-primary' data-toggle='modal' data-target='#myModal1' href='editpublisher?publisherId="+br.getCardNo()+"'>EDIT</button></td>"
						+ "	<td><button type='button' class='btn btn-sm btn-danger'	onclick='deleteAuthor" + br.getCardNo()+"'>DELETE</button></td></tr>" );
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//System.out.println(str.toString());
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	private void searchBranch(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		String option = request.getParameter("text");
		System.out.println("option selected : "+option);
		System.out.println("searchString "+searchString);
		List<LibraryBranch> Branch=null;
		StringBuilder str = new StringBuilder();
		try {
			
			if(option.equals("Search by All")){
				Branch = service.getAllBranch(searchString, 1);
			}
			else if(option.equals("Search by Branch name")){
				Branch = service.getAllBranchName(searchString, 1);
			}
			else if(option.equals("Search by Branch Address")){
				Branch = service.getAllBranchAddress(searchString, 1);
			}
			
			str.append("<tr><th>Branch Name</th><th>Branch Address</th><th>Edit</th><th>Delete</th></tr>");
			
			for (LibraryBranch a: Branch){
				str.append("<tr><td>" + a.getBranchName()+"</td>");
				str.append("<td>"+a.getBranchAddress()+"</td>");
			
			
				str.append("<td align='center'><button type='button' class='btn btn btn-primary' data-toggle='modal' data-target='#myModal1' href='editpublisher?publisherId="+a.getBranchId()+"'>EDIT</button></td>"
						+ "	<td><button type='button' class='btn btn-sm btn-danger'	onclick='deleteAuthor" +a.getBranchId()+"'>DELETE</button></td></tr>" );
			}
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//System.out.println(str.toString());
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void searchBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		String option = request.getParameter("text");
		System.out.println("option selected : "+option);
		System.out.println("searchString "+searchString);
		List<Book> book=null;
		StringBuilder str = new StringBuilder();
		try {
			
			if(option.equals("Search by All")){
				book = service.getAllBooks(searchString, 1);
			}
			else if(option.equals("Search by Title")){
				book = service.getAllBookByTitle(searchString, 1);
			}
			else if(option.equals("Search by Author")){
				book = service.getAllBookByAuthor(searchString, 1);
			}
			
			str.append("<tr><th>Book Title</th><th>Author Name</th><th>Genre</th><th>Edit</th><th>Delete</th></tr>");
			
			for (Book b: book){ 
				str.append("<tr><td>" + b.getTitle()+"</td>");
				if(b.getAuthor()!=null && b.getAuthor().size() >0){
					
					str.append("<td>");
					for(Author a: b.getAuthor()){
						str.append(a.getAuthorName());
						str.append(",");
						
					
						
					}
				}	
				if(b.getGenre()!=null && b.getGenre().size() >0){
					for(Genre g: b.getGenre()){
						str.append(g.getGenre_name());
						str.append(", ");
					}
				}	
				str.append("<td align='center'><button type='button' class='btn btn btn-primary' data-toggle='modal' data-target='#myModal1' href='editauthor.jsp?authorId"+b.getBookId()+"'>EDIT</button></td>"
						+ "	<td><button type='button' class='btn btn-sm btn-danger'	onclick='deleteAuthor" + b.getBookId()+"'>DELETE</button></td></tr>" );
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//System.out.println(str.toString());
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
private void pagePublisher(HttpServletRequest request, HttpServletResponse response) {
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService service = new AdministratorService();
		try {
			List<Publisher> publisher = service.getAllPubliser(pageNo);
			request.setAttribute("publisher", publisher);
			RequestDispatcher rd = request.getRequestDispatcher("viewpublisher.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
private void pageBranch(HttpServletRequest request, HttpServletResponse response) {
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService service = new AdministratorService();
		try {
			List<LibraryBranch> branch = service.getAllBranches(pageNo);
			request.setAttribute("branch", branch);
			RequestDispatcher rd = request.getRequestDispatcher("viewbranch.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
private void pageBorrower(HttpServletRequest request, HttpServletResponse response) {
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService service = new AdministratorService();
		try {
			List<Borrower> borrower = service.getAllBorrower(pageNo);
			request.setAttribute("borrower", borrower);
			RequestDispatcher rd = request.getRequestDispatcher("viewborrower.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
private void pageBooks(HttpServletRequest request, HttpServletResponse response) {
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService service = new AdministratorService();
		try {
			List<Book> book = service.getAllBooks(pageNo);
			request.setAttribute("book", book);
			RequestDispatcher rd = request.getRequestDispatcher("viewbook.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
private void pageAuthors(HttpServletRequest request, HttpServletResponse response) {
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		System.out.println("Page number in pageAuthors "+pageNo);
		AdministratorService service = new AdministratorService();
		try {
			List<Author> authors = service.getAllAuthors(pageNo);
			request.setAttribute("authors", authors);
			System.out.println("in pageAUthors");
			RequestDispatcher rd = request.getRequestDispatcher("viewauthor.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void branchSelected(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello renew");
		
		String branchId1 = request.getParameter("branchId");
		String cardNo1 = request.getParameter("cardNo");
		System.out.println("cn "+cardNo1);
		System.out.println("bi "+branchId1);
		String addAuthorResult = null;
		int c_no = Integer.parseInt(cardNo1);
		int b_id = Integer.parseInt(branchId1);
		/*request.setAttribute("cardNo", c_no);
		request.setAttribute("branchId", b_id);*/
		String searchString = request.getParameter("searchString");
		String option = request.getParameter("text");
		System.out.println("option selected : "+option);
		System.out.println("searchString "+searchString);
		List<LibraryBranch> Branch=null;
		StringBuilder str = new StringBuilder();
		try {
			
			if(option.equals("Search by All")){
				Branch = service.getAllBranch(searchString, 1);
			}
			else if(option.equals("Search by Branch name")){
				Branch = service.getAllBranchName(searchString, 1);
			}
			else if(option.equals("Search by Branch Address")){
				Branch = service.getAllBranchAddress(searchString, 1);
			}
			
			//str.append("<tr><th>Branch Name</th><th>Branch Address</th><th>Edit</th><th>Delete</th></tr>");
			str.append("<tr><th>Branch Name</th><th>Branch Address</th><th>Edit</th><th>Delete</th></tr>");
			
			for (LibraryBranch a: Branch){
				str.append("<tr><td>" + a.getBranchName()+"</td>");
				str.append("<td>"+a.getBranchAddress()+"</td>");
			
			
				str.append("<td><button type='button' class='btn btn-sm btn-primary' data-toggle='modal' data-target='#myModal1' href='branchSelected?branchId"+a.getBranchId()+"&cardNo"+c_no+"'>Select</button></td>" );
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//System.out.println(str.toString());
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void findBforcheckout(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String cardNo = request.getParameter("cardNo");
		String addAuthorResult = null;
		
		int cardN = Integer.parseInt(cardNo);
		if (cardN != 0) {

			try {
				//System.out.println("result of verification :" + b);

				if (service.verifyCard(cardN)) {
					System.out.println("the result was true");
					request.setAttribute("cardNo", cardN);
					RequestDispatcher rd = request.getRequestDispatcher("DisplayAllBranches.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("borrower.html");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/verifyBAdmin.jsp";
				addAuthorResult = "Card verification failed";
				e.printStackTrace();
			}
		}

	}

	private void returnBook(HttpServletRequest request, HttpServletResponse response) {
		BorrowerServices service = new BorrowerServices();
		String returnPath = "/administrator.html";
		System.out.println("Hello renew");
		String cardNo1 = request.getParameter("cardNo");
		String bookId1 = request.getParameter("bookId");
		System.out.println(cardNo1);
		System.out.println(bookId1);
		String addAuthorResult = null;
		try {
			int c = Integer.parseInt(cardNo1);
			int b = Integer.parseInt(bookId1);
			service.CheckInBook(cardNo1, bookId1);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		returnPath = "/index.html";
		// addAuthorResult = "Book renewed sucessfully.";
		RequestDispatcher rd = request.getRequestDispatcher("Book_Return.html");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void verifyBorrowerforbookreturn(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String cardNo = request.getParameter("cardNo");
		String addAuthorResult = null;
		
		int cardN = Integer.parseInt(cardNo);
		if (cardN != 0) {

			try {
				//System.out.println("result of verification :" + b);

				if (service.verifyCard(cardN)) {
					System.out.println("the result was true");
					request.setAttribute("cardNo", cardN);
					RequestDispatcher rd = request.getRequestDispatcher("Displaycheckedoutbooks.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("borrower1.html");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/verifyBAdmin.jsp";
				addAuthorResult = "Card verification failed";
				e.printStackTrace();
			}
		}
	}

	private void verifyBorrower(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String cardNo = request.getParameter("cardNo");
		String addAuthorResult = null;
		
		int cardN = Integer.parseInt(cardNo);
		if (cardN != 0) {

			try {
				//System.out.println("result of verification :" + b);

				if (service.verifyCard(cardN)) {
					System.out.println("the result was true");
					request.setAttribute("cardNo", cardN);
					RequestDispatcher rd = request.getRequestDispatcher("DisplayBorrowerBooks.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("verifyBAdmin.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/verifyBAdmin.jsp";
				addAuthorResult = "Card verification failed";
				e.printStackTrace();
			}
		}
		
	}

	private void deletebook(HttpServletRequest request, HttpServletResponse response) {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteBook(bookId);
			List<Book> book = service.getAllBooks(pageNo);

			str.append("<tr><th>Title Name</th><th>Edit</th><th>Delete</th></tr>");
			for (Book a : book) {
				str.append("<tr><td>" + a.getTitle() + "</td><td>Book Name</td>");
				str.append("<td><button type='button' onclick='javascript:location.href='editAuthor?authorId="
						+ a.getBookId() + "''>EDIT</button><td>"
						+ "<button type='button' onclick='javascript:location.href='deleteAuthor?authorId="
						+ a.getBookId() + "''>DELETE</button><td>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editbook(HttpServletRequest request, HttpServletResponse response) {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		System.out.println("bookId :" + bookId);
		AdministratorService service = new AdministratorService();
		Book book = null;

		try {
			book = service.getbookByID(bookId);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("book :" + book.getBookId());
		request.setAttribute("book", book);
		RequestDispatcher rd = request.getRequestDispatcher("editbook.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deletebranch(HttpServletRequest request, HttpServletResponse response) {
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		System.out.println("branchId: " + branchId);
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteLibraryBranch(branchId);
			List<LibraryBranch> br = service.getAllBranches(pageNo);

			str.append("<tr><th>Branch Name</th><th>Branch Address</th><th>Edit</th><th>Delete</th></tr>");
			for (LibraryBranch b : br) {
				str.append("<tr><td>" + b.getBranchName() + "" + b.getBranchAddress() + "");
				str.append("<td><button type='button' onclick='javascript:location.href='editBranch?branchId="
						+ b.getBranchId() + "''>EDIT</button><td>"
						+ "<button type='button' onclick='javascript:location.href='deletebranch?branchId="
						+ b.getBranchId() + "''>DELETE</button></tr>");
				

				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", "Branch Deleted Sucessfully");
		RequestDispatcher rd = request.getRequestDispatcher("viewbranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editBranch(HttpServletRequest request, HttpServletResponse response) {
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		System.out.println("branch ID:" + branchId);
		LibrarianService serv = new LibrarianService();
		LibraryBranch branch = null;

		try {
			branch = serv.getBranchByID(branchId);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("branch Id :" + branchId);
		request.setAttribute("branch", branch);
		RequestDispatcher rd = request.getRequestDispatcher("updateBranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteBorrower(HttpServletRequest request, HttpServletResponse response) {
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		System.out.println("cardNo: " + cardNo);
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteBorrower(cardNo);
			List<Borrower> br = service.getAllBorrower(pageNo);

			str.append("<tr><th>Author Name</th><th>Book Title</th><th>Edit</th><th>Delete</th></tr>");
			for (Borrower a : br) {
				str.append("<tr><td>" + a.getName() + "</td><td>"+ a.getAddress() + "</td>" );
				
				str.append("<td><button type='button' onclick='javascript:location.href='editBorrower?cardNo="
						+a.getCardNo()+ "''>EDIT</button><td>"
						+ "<button type='button' onclick='deleteBorrower?cardNo=("+a.getCardNo()+")'>DELETE</button></tr>)" );
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*request.setAttribute("result", "Borrower Deleted Sucessfully");
		RequestDispatcher rd = request.getRequestDispatcher("viewborrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deletePublisher(HttpServletRequest request, HttpServletResponse response) {
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		System.out.println("publisher Id:" + publisherId);
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deletePublisher(publisherId);
			List<Publisher> pr = service.getAllPubliser(pageNo);

			str.append("<tr><th>Name</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
			for (Publisher a : pr) {
				str.append("<tr><td>" + a.getPublisherName() + "</td><td>" + a.getPublisherAddress() + "</td>");
				
				str.append("<td>"+"<button type='button' onclick='editPublisher("+a.getPublisherId()+")'>EDIT</button><td>)"
						
						+"<button type='button' onclick='deletePublisher("+a.getPublisherId()+")'>DELETE</button></tr>)" );
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*request.setAttribute("result", "Publisher Deleted Sucessfully");
		RequestDispatcher rd = request.getRequestDispatcher("viewpublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editPublisher(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		System.out.println("publisher Id:" + publisherId);
		AdministratorService service = new AdministratorService();
		Publisher publisher = null;

		publisher = service.getpublisherByID(publisherId);
		System.out.println("publisher Id:" + publisher.getPublisherId());
		request.setAttribute("publisher", publisher);
		RequestDispatcher rd = request.getRequestDispatcher("editpublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) {
		
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		
		System.out.println("authorId ::"+authorId);
		String pn = request.getParameter("pageNo");
		Integer pageNo1 =Integer.parseInt(pn);
		System.out.println("Page no :"+pageNo1);
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		try {
			service.deleteAuthors(authorId);
			System.out.println("after authorId ::"+authorId);
			List<Author> authors = service.getAllAuthors(pageNo1);

			str.append("<tr><th>Author Name</th><th>Book Title</th><th>Edit</th><th>Delete</th></tr>");
			for (Author a : authors) {
				
				if(a.getBooks()!=null && a.getBooks().size() >0){
					str.append("<tr><td>" + a.getAuthorName()+"</td>");
					str.append("<td>");
					for(Book b: a.getBooks()){
						str.append(b.getTitle()+ ", " );
						
					
						
					}
				}	
			
				str.append("<td><button type='button' onclick='javascript:location.href='editAuthor?authorId="
						+ a.getAuthorId() + "''>EDIT</button><td>"
						+ "<button type='button' onclick='deleteAuthor("+a.getAuthorId()+")'>DELETE</button></tr>)" );
			}
		} catch (ClassNotFoundException | SQLException e) {
		System.out.println(e);
			e.printStackTrace();
		}

		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private void editBorrower(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		System.out.println("card No:" + cardNo);
		AdministratorService service = new AdministratorService();
		Borrower borrower = null;

		borrower = service.getBorrowerByID(cardNo);
		System.out.println("cardNo:" + borrower.getCardNo());
		request.setAttribute("borrower", borrower);
		RequestDispatcher rd = request.getRequestDispatcher("editborrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		System.out.println("Author ID: " +authorId);
		AdministratorService service = new AdministratorService();
		Author author = null;
		try {
			author = service.getAuthorByID(authorId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("author", author);
		RequestDispatcher rd = request.getRequestDispatcher("editauthor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private void modifyBranch(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		System.out.println("branch ID:" + branchId);
		LibrarianService serv = new LibrarianService();
		LibraryBranch branch = null;

		branch = serv.getBranchByID(branchId);
		System.out.println("branch Id :" + branchId);
		request.setAttribute("branch", branch);
		RequestDispatcher rd = request.getRequestDispatcher("updateBranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void renewBook(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello renew");
		String cardNo1 = request.getParameter("cardNo");
		String bookId1 = request.getParameter("bookId");
		System.out.println(cardNo1);
		System.out.println(bookId1);
		String addAuthorResult = null;
		try {
			service.renewBook(cardNo1, bookId1);
			returnPath = "/admistrator.html";
			// addAuthorResult = "Book renewed sucessfully.";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/ChangeAuthor":
			ChangeAuthor(request,response);
			break;

		case "/addBcopies":
			addBcopies(request, response);
			break;
		case "/addBook":
			addBook(request, response);
			break;
		case "/addAuthor":
			addAuthor(request, response);
			break;

		case "/addPublisher":
			addPublisher(request, response);
			break;

		case "/addBorrower":
			addBorrower(request, response);
			break;

		case "/updateBranchDetails":
			updateBranchDetails(request, response);
			break;

		case "/editBranchDetails":
			editBranchDetails(request, response);
			break;

		case "/addBranch":
			addBranch(request, response);
			break;

		case "/editBorrower12":
			updateBorrowerDetails(request, response);
			break;

		case "/editpublisher":
			updatepublisher(request, response);
			break;
		case "/editbook":
			updatebook1(request, response);
			break;
		
		
		
		default:
			break;
		}
	}

	private void ChangeAuthor(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello changing author");
		String au_Id = request.getParameter("authorId");
		int a_id=Integer.parseInt(au_Id);
		String authorName = request.getParameter("authorName");
		String[] bookId = request.getParameterValues("bookId");
		if(authorName!=null && authorName.length()!=0){
			try {
				service.ChangeAuthor(au_Id, authorName);
				for(String bid:bookId){
					int BId = Integer.parseInt(bid);
					System.out.println("Genre iD "+bid);
					service.updateBook_Authors(a_id, BId);
				}
				returnPath = "/viewauthor.jsp";
				RequestDispatcher rd = request.getRequestDispatcher("/viewauthor.jsp");
				//request.setAttribute("result", addAuthorResult);
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			returnPath = "/editauthor.jsp";
			
		}
		
	}

	private void checkoutbook(HttpServletRequest request, HttpServletResponse response) {
		BorrowerServices service = new BorrowerServices();
		String returnPath = "/administrator.html";
		System.out.println("Hello check out book");
		String bookId = request.getParameter("bookId");
		String cardNo = request.getParameter("cardNo");
		String branchId = request.getParameter("branchId");
		System.out.println("bId: "+bookId);
		System.out.println("Cno: "+cardNo);
		System.out.println("brId: "+branchId);
		String addAuthorResult = "";

		try {
			int bookID = Integer.parseInt(bookId);
			int CardNo = Integer.parseInt(cardNo);
			int branchID = Integer.parseInt(branchId);

			service.CheckOutBook(bookID, CardNo, branchID);
			returnPath="Book_Loaned.html";
			RequestDispatcher rd = request.getRequestDispatcher("Book_Loaned.html");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException | SQLException e) {
			returnPath = "/addborrower.jsp";
			addAuthorResult = "Borrower add failed";
			e.printStackTrace();
		}

	}

	private void addBcopies(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String bookId = request.getParameter("bookId");
		String branchId = request.getParameter("branchId");
		String noOfCopies = request.getParameter("noOfCopies");
		int nofc = Integer.parseInt(noOfCopies);
		String addAuthorResult = "";
		if (nofc != 0) {

			try {
				service.setNoOfCopies(bookId, branchId, nofc);
				returnPath = "/librarian.html";

			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/librarian.html";
				addAuthorResult = "Number of copies update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addBcopies.jsp";
			addAuthorResult = "Number of copies cannot be empty 0";
		}
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editBranchDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LibrarianService service = new LibrarianService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String branchId = request.getParameter("branchId");
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");

		String addAuthorResult = "";
		if (branchName != null && branchName.length() > 3 && branchName.length() < 45) {
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(Integer.parseInt(branchId));
			System.out.println("branch Id " + branchId);
			a.setBranchName(branchName);
			a.setBranchAddress(branchAddress);
			try {
				service.UpdateDetails_branches(a);
				returnPath = "/viewbranch.jsp";
				addAuthorResult = "Branch updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/updateBranch.jsp";
				addAuthorResult = "Branch update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/librarian.html";
			addAuthorResult = "Branch Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		rd.forward(request, response);

	}

	private void updatebook1(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Updating Books");
		String bookId = request.getParameter("bookId");
		// String pubId= request.getParameter("")
		String title = request.getParameter("bookName");
		String[] AuthorId = request.getParameterValues("authorId");
		String[] GenreId = request.getParameterValues("genreId");
		//String publishereId = request.getParameter("publisherId");

		String addAuthorResult = "";
		if (title != null && title.length() > 3 && title.length() < 45) {
			Book a = new Book();
			int booki = Integer.parseInt(bookId);
			a.setBookId(Integer.parseInt(bookId));
			System.out.println("bookId " + a.getBookId());
			a.setTitle(title);

			try {
				service.UpdateBook(title, booki);
				if(GenreId.length>0){
				for(String gid:GenreId){
					int gId = Integer.parseInt(gid);
					System.out.println("Genre iD "+gId);
					service.updateBookGenre(gId, booki);
				}
				}
				//List<Author> author= new ArrayList<Author>();
				if(AuthorId.length>0){
				for(String aId:AuthorId){
					int auId = Integer.parseInt(aId);
					System.out.println("authorId "+auId);
					service.updateBookAuthors(booki, auId);
				}
				}
				returnPath = "/viewbook.jsp";
				addAuthorResult = "Book updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editbook.jsp";
				addAuthorResult = "Book update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/administrator.html";
			addAuthorResult = "Book Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Adding a book");
		String booktitle = request.getParameter("booktitle");
		String[] AuthorId = request.getParameterValues("authorId");
		String[] GenreId = request.getParameterValues("genreId");
		String publishereId = request.getParameter("publisherId");
		// String borrowerPhone = request.getParameter("borrowerPhone");
		String addAuthorResult = "";
		if (booktitle != null && booktitle.length() > 3 && booktitle.length() < 45) {
			Book b = new Book();
			b.setTitle(booktitle);
			b.setPubId(Integer.parseInt(publishereId));
			try {
				int pub_id=Integer.parseInt(publishereId);				
				System.out.println("publisher id "+pub_id);
				Integer BId = service.createBook(booktitle,pub_id);
				System.out.println("bId "+BId);
				//List<Genre> genre= new ArrayList<Genre>();
				for(String gid:GenreId){
					int gId = Integer.parseInt(gid);
					System.out.println("Genre iD "+gId);
					service.createBookGenre(gId, BId);
				}
				//List<Author> author= new ArrayList<Author>();
				for(String aId:AuthorId){
					int auId = Integer.parseInt(aId);
					System.out.println("authorId "+auId);
					service.createBookAuthors(BId, auId);
				}
			
				returnPath = "/viewbook.jsp";
				addAuthorResult = "Book added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addbook.jsp";
				addAuthorResult = "Book add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addbook.jsp";
			addAuthorResult = "Borrower Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addBorrower(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		String addAuthorResult = "";
		if (borrowerName != null && borrowerName.length() > 3 && borrowerName.length() < 45) {
			Borrower b = new Borrower();
			b.setName(borrowerName);
			b.setAddress(borrowerAddress);
			b.setPhone(borrowerPhone);

			try {
				service.createBorrower(b);
				returnPath = "/viewborrower.jsp";
				addAuthorResult = "Borrower added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addborrower.jsp";
				addAuthorResult = "Borrower add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/borrower.jsp";
			addAuthorResult = "Borrower Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addPublisher(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String addAuthorResult = "";
		if (publisherName != null && publisherName.length() > 3 && publisherName.length() < 45) {
			/*Publisher p = new Publisher();
			p.setPublisherName(publisherName);
			p.setPublisherAddress(publisherAddress);*/
			try {
				service.createPublisher(publisherName,publisherAddress);
				returnPath = "/viewpublisher.jsp";
				addAuthorResult = "Publisher added sucessfully.";
			} catch (SQLException e) {
				returnPath = "/addpublisher.jsp";
				addAuthorResult = "Publisher add failed";
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			returnPath = "/addpublisher.jsp";
			addAuthorResult = "Publisher Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updatepublisher(HttpServletRequest request, HttpServletResponse response) {

		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String publisherId = request.getParameter("publisherId");
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");

		String addAuthorResult = "";
		if (publisherName != null && publisherName.length() > 3 && publisherName.length() < 45) {
			Publisher a = new Publisher();
			a.setPublisherId(Integer.parseInt(publisherId));
			a.setPublisherName(publisherName);
			a.setPublisherAddress(publisherAddress);
			try {
				service.Update_publisher(a);
				returnPath = "/viewpublisher.jsp";
				addAuthorResult = "Publisher updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editpublisher.jsp";
				addAuthorResult = "Branch update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/administrator.html";
			addAuthorResult = "Publisher Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addBranch(HttpServletRequest request, HttpServletResponse response) {

		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		String addAuthorResult = "";
		if (branchName != null && branchName.length() > 3 && branchName.length() < 45) {
			LibraryBranch l = new LibraryBranch();
			l.setBranchName(branchName);
			l.setBranchAddress(branchAddress);
			try {
				service.createLibraryBranch(l);
				returnPath = "/viewbranch.jsp";
				addAuthorResult = "Branch added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addbranch.jsp";
				addAuthorResult = "Branch add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addbranch.jsp";
			addAuthorResult = "Author Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String authorName = request.getParameter("authorName");
		String bookId = request.getParameter("bookId");
		int b_id=Integer.parseInt(bookId);
		String addAuthorResult = "";
		if (authorName != null && authorName.length() > 3 && authorName.length() < 45) {
			Author a = new Author();
			Book b = new Book();
			a.setAuthorName(authorName);
			
			//System.out.println("book name is: " + bookName);
			try {
				int au_id=service.createAuthor(a);
				 service.createAuthorWBook(au_id, b_id);
				returnPath = "/viewauthor.jsp";
				addAuthorResult = "Author added sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/addauthor.jsp";
				addAuthorResult = "Author add failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/addauthor.jsp";
			addAuthorResult = "Author Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		rd.forward(request, response);
	}

	private void updateBranchDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LibrarianService service = new LibrarianService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String branchId = request.getParameter("branchId");
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");

		String addAuthorResult = "";
		if (branchName != null && branchName.length() > 3 && branchName.length() < 45) {
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(Integer.parseInt(branchId));
			System.out.println("branch Id " + branchId);
			a.setBranchName(branchName);
			a.setBranchAddress(branchAddress);
			try {
				service.UpdateDetails_branches(a);
				returnPath = "/viewbranch.jsp";
				addAuthorResult = "Branch updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/updateBranch.jsp";
				addAuthorResult = "Branch update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/librarian.html";
			addAuthorResult = "Branch Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		rd.forward(request, response);
	}

	private void updateBorrowerDetails(HttpServletRequest request, HttpServletResponse response) {
		AdministratorService service = new AdministratorService();
		String returnPath = "/administrator.html";
		System.out.println("Hello Post");
		String cardNo=request.getParameter("cardNo");
		String Name = request.getParameter("borrowerName");
		String Address = request.getParameter("borrowerAddress");
		String phone = request.getParameter("borrowerPhone");
		String addAuthorResult = "";

		if (Name != null && Name.length() > 3 && Name.length() < 45) {
			Borrower a = new Borrower();
			int cn=Integer.parseInt(cardNo);
			a.setCardNo(cn);
			a.setName(Name);
			a.setAddress(Address);
			a.setPhone(phone);
			try {
				service.updateBorrower(a);
				returnPath = "/viewborrower.jsp";
				addAuthorResult = "Borrower updated sucessfully.";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/editborrower.jsp";
				addAuthorResult = "Borrower update failed";
				e.printStackTrace();
			}
		} else {
			returnPath = "/administrator.html";
			addAuthorResult = "Borrower Name cannot be empty or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	
	
	@SuppressWarnings("unused")
	private void searchAuthors1(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		System.out.println("searchString 1"+searchString);
		List<Author> authors;
		try {
			authors = service.getAllAuthorsByName(searchString, 1);
			request.setAttribute("authors", authors);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/viewauthor.jsp");
		rd.forward(request, response);
		
	}
	
	@SuppressWarnings("unused")
	private void searchAuthors2(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String searchString = request.getParameter("searchString");
		System.out.println("searchString 2"+searchString);
		List<Book> authors;
		try {
			authors = service.getAllAuthorsByBook(searchString, 1);
			request.setAttribute("authors", authors);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/viewauthor.jsp");
		rd.forward(request, response);
		
	}
	
	
	
	
	
	
	
	
}
