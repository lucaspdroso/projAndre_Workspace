package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pacote.MeuPreparedStatement;
import dbo.Jogador;
import dao.Jogadores;

/**
 * Servlet implementation class Inclusao
 */
@WebServlet("/CRUDJogador")
public class CRUDJogador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDJogador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {    		
    		try
    		{

    			String botao = request.getParameter("sender");
    			
    			if (botao.equals("Incluir"))
    			{

    				String cpf = request.getParameter("cpf");
    				String nome = request.getParameter("nome");
    				String data = request.getParameter("data");
    				String endereco = request.getParameter("endereco");
    				String cidade = request.getParameter("cidade");
    				String uf = request.getParameter("uf");
    				String pais = request.getParameter("pais");
    				
    				MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
    				
    				if (comando == null)
    				{
    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
    							"BDu14180", "cotuca");
    					request.getSession().setAttribute("conexao", comando);
    				}
    				
    				Jogadores jogs = new Jogadores(comando);			
    				Jogador jog = new Jogador(cpf, nome, data, endereco, cidade, uf, pais);			
    				jogs.incluir(jog);
    				response.getWriter().println("Jogador cadastrado com sucesso.");
    			}
    			else
    				if (botao.equals("Excluir"))
    				{
    					String cpf = request.getParameter("cpf");
        				
        				MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
        				
        				if (comando == null)
        				{
        					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
        							"BDu14180", "cotuca");
        					request.getSession().setAttribute("conexao", comando);
        				}
        				
        				Jogadores jogs = new Jogadores(comando);			
        				jogs.excluir(cpf);
        				response.getWriter().println("Jogador excluido com sucesso.");
    				}
    				else
    					if (botao.equals("Alterar"))
    					{
    						String cpf = request.getParameter("cpf");
    	    				String nome = request.getParameter("nome");
    	    				String data = request.getParameter("data");
    	    				String endereco = request.getParameter("endereco");
    	    				String cidade = request.getParameter("cidade");
    	    				String uf = request.getParameter("uf");
    	    				String pais = request.getParameter("pais");
    	    				
    	    				MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
    	    				
    	    				if (comando == null)
    	    				{
    	    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
    	    							"BDu14180", "cotuca");
    	    					request.getSession().setAttribute("conexao", comando);
    	    				}
    	    				
    	    				Jogadores jogs = new Jogadores(comando);			
    	    				Jogador jog = new Jogador(cpf, nome, data, endereco, cidade, uf, pais);			
    	    				jogs.alterar(jog);
    	    				response.getWriter().println("Jogador alterado com sucesso.");
    					}
    					else
    						if (botao.equals("IncluirRelacao"))
    						{
    							String cpf = request.getParameter("cpf");
    							int idTime = Integer.parseInt(request.getParameter("idTime"));
    							
    							
    							MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
        	    				
        	    				if (comando == null)
        	    				{
        	    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
        	    							"BDu14180", "cotuca");
        	    					request.getSession().setAttribute("conexao", comando);
        	    				}
        	    				
        	    				Jogadores jogs = new Jogadores(comando);
        	    				jogs.incluirNoTime(cpf, idTime);
        	    				response.getWriter().println("Jogador incluido no time com sucesso.");
    						}
    						else
    							if (botao.equals("ExcluirRelacao"))
        						{
    								String cpf = request.getParameter("cpf");       							
        							
        							MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
            	    				
            	    				if (comando == null)
            	    				{
            	    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
            	    							"BDu14180", "cotuca");
            	    					request.getSession().setAttribute("conexao", comando);
            	    				}
            	    				
            	    				Jogadores jogs = new Jogadores(comando);
            	    				jogs.excluirDoTime(cpf);
            	    				response.getWriter().println("Jogador excluido no time com sucesso.");
        						}
        						else
        							if (botao.equals("AlterarRelacao"))
            						{
        								String cpf = request.getParameter("cpf");
            							int idTime = Integer.parseInt(request.getParameter("idTime"));
            							
            							
            							MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
                	    				
                	    				if (comando == null)
                	    				{
                	    					comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14180",
                	    							"BDu14180", "cotuca");
                	    					request.getSession().setAttribute("conexao", comando);
                	    				}
                	    				
                	    				Jogadores jogs = new Jogadores(comando);
                	    				jogs.AlterarTimeJogador(cpf, idTime);
                	    				response.getWriter().println("Time do Jogador alterado com sucesso.");
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
