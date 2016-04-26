package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pacote.MeuPreparedStatement;
import pacote.MeuResultSet;

import dao.Jogadores;
import dao.Times;

/**
 * Servlet implementation class Carregar
 */
@WebServlet("/Carregar")
public class Carregar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carregar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		
		try
		{
			
			MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
			
			if (comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
						"BDu14180", "cotuca");
				request.getSession().setAttribute("conexao", comando);
			}
			
			Jogadores jogs = new Jogadores(comando);			
			MeuResultSet ResultSet = jogs.dados();
			
			Times times = new Times(comando);
			MeuResultSet ResultSet1 = times.dados();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='utf-8'>");
			out.println("<title> Cadastro de Times e Jogadores </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1> Manipular Times </h1>  <br><br>");
			
			out.println("<br>");
			

			out.println("<form action='CRUDTime'>");
			out.println("<h3>  Inclusão de Time </h1> <br>");
			out.println("<table border='1'>");
			
			out.println("<tr>");
			out.println("<td>"+ "ID do Time"   +"</td>");
			out.println("<td>"+ "Nome " +"</td>");
			out.println("<td>"+ "Sigla"   +"</td>");
			out.println("<td>"+ "UF"  +"</td>");	
			out.println("<td>"+ "País"  +"</td>");	
			out.println("</tr>");
			
			while(ResultSet1.next()){
				
				String cpf, nome, sigla, uf, pais;
					
				cpf       = ResultSet1.getString("idTime");
				nome      = ResultSet1.getString("nome");
				sigla     = ResultSet1.getString("sigla");
				uf        = ResultSet1.getString("uf");
				pais      = ResultSet1.getString("pais");
				
				out.println("<tr>");
				out.println("<td>"+ cpf   +"</td>");
				out.println("<td>"+ nome +"</td>");
				out.println("<td>"+ sigla   +"</td>");
				out.println("<td>"+ uf  +"</td>");	
				out.println("<td>"+ pais  +"</td>");	
				out.println("</tr>");
			}
			
			out.println("</table>");
			out.println("<br>");
			out.println("<h5>  ID do Time: </h5> <input type='text' name='idTime'>");
			out.println("<h5>  Nome: </h5> <input type='text' name='nome'>");
			out.println("<h5>  Sigla: </h5> <input type='text' name='sigla'>");
			out.println("<h5>  UF: </h5> <input type='text' name='uf'>");
			out.println("<h5>  País: </h5> <input type='text' name='pais'> <br><br>");
						
			out.println("<button type='submit' name='sender' value='Incluir'> Incluir </button>");
			out.println("<button type='submit' name='sender' value='Excluir'> Excluir </button>");
			out.println("<button type='submit' name='sender' value='Alterar'> Alterar </button>");
			out.println("</form>");

			out.println("<form action='CRUDJogador'>");
			out.println("<h3>  Inclusão de Jogador </h1> <br>");
			out.println("<br>");
			out.println("<table border='1'>");
			
			out.println("<tr>");
			out.println("<td>"+ "CPF"   +"</td>");
			out.println("<td>"+ "Nome " +"</td>");
			out.println("<td>"+ "Data de Nascimento"   +"</td>");
			out.println("<td>"+ "Endereço"  +"</td>");	
			out.println("<td>"+ "Cidade"  +"</td>");	
			out.println("<td>"+ "UF"  +"</td>");	
			out.println("<td>"+ "País"  +"</td>");	
			out.println("</tr>");
			
			while(ResultSet.next()){
				
				String cpf, nome, data, endereco, cidade, uf, pais;
					
				cpf       = ResultSet.getString("cpf");
				nome      = ResultSet.getString("nome");
				data      = ResultSet.getString("dataNascimento");
				endereco  = ResultSet.getString("endereco");
				cidade    = ResultSet.getString("cidade");
				uf        = ResultSet.getString("uf");
				pais      = ResultSet.getString("pais");
				
				out.println("<tr>");
				out.println("<td>"+ cpf   +"</td>");
				out.println("<td>"+ nome +"</td>");
				out.println("<td>"+ data   +"</td>");
				out.println("<td>"+ endereco  +"</td>");
				out.println("<td>"+ cidade  +"</td>");	
				out.println("<td>"+ uf  +"</td>");	
				out.println("<td>"+ pais  +"</td>");	
				out.println("</tr>");
			}
			
			out.println("</table>");
			
			out.println("<br>");
			out.println("<h5>  CPF: </h5> <input type='text' name='cpf'>");
			out.println("<h5>  Nome: </h5> <input type='text' name='nome'>");
			out.println("<h5>  Data Nascimento: </h5> <input type='text' name='data'>");
			out.println("<h5>  Endereço: </h5> <input type='text' name='endereco'>");
			out.println("<h5>  Cidade: </h5> <input type='text' name='cidade'>");
			out.println("<h5>  UF: </h5> <input type='text' name='uf'>");
			out.println("<h5>  País: </h5> <input type='text' name='pais'> <br><br>");
						
			out.println("<button type='submit' name='sender' value='Incluir'> Incluir </button>");
			out.println("<button type='submit' name='sender' value='Excluir'> Excluir </button>");
			out.println("<button type='submit' name='sender' value='Alterar'> Alterar </button>");
			out.println("</form>");

			out.println("<br>");
			out.println("</body>");
			out.println("</html>");

			out.println("</body>");
			out.println("</html>");
			//response.getWriter().println("Usuário cadastrado com sucesso.");
			
		}
		catch (Exception e)
		{
			response.getWriter().println(e.getMessage());
			//Fazer as exceções
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
