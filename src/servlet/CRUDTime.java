package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import dbo.*;
import pacote.MeuPreparedStatement;

/**
 * Servlet implementation class CRUDTime
 */
@WebServlet("/CRUDTime")
public class CRUDTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try
		{
			String botao = request.getParameter("sender");
			
			if (botao.equals("Incluir"))
			{

				String nome = request.getParameter("nome");
				String sigla = request.getParameter("sigla");
				String uf = request.getParameter("uf");
				String pais = request.getParameter("pais");
				
				MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
				
				if (comando == null)
				{
					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
							"BDu14180", "cotuca");
					request.getSession().setAttribute("conexao", comando);
				}
				
				Times times = new Times(comando);			
				Time time = new Time(0, nome, sigla, uf, pais);			
				times.incluir(time);
				response.getWriter().println("Time cadastrado com sucesso.");
			}
			else
				if (botao.equals("Excluir"))
				{
					int idTime = Integer.parseInt(request.getParameter("idTime"));
    				
    				MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
    				
    				if (comando == null)
    				{
    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
    							"BDu14180", "cotuca");
    					request.getSession().setAttribute("conexao", comando);
    				}
    				
    				Times times = new Times(comando);			
    				times.excluir(idTime);
    				response.getWriter().println("Time excluido com sucesso.");
				}
				else
					if (botao.equals("Alterar"))
					{
						int idTime = Integer.parseInt(request.getParameter("idTime"));
						String nome = request.getParameter("nome");
						String sigla = request.getParameter("sigla");
						String uf = request.getParameter("uf");
						String pais = request.getParameter("pais");
	    				
	    				MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
	    				
	    				if (comando == null)
	    				{
	    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
	    							"BDu14180", "cotuca");
	    					request.getSession().setAttribute("conexao", comando);
	    				}
	    				
	    				Times times = new Times(comando);			
	    				Time time = new Time(idTime, nome, sigla, uf, pais);
	    				times.alterar(time);
	    				response.getWriter().println("Time alterado com sucesso.");
					}
			
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
