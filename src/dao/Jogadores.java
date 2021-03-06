package dao;

import java.sql.SQLException;
import pacote.*;
import dbo.*;

public class Jogadores {
	
	MeuPreparedStatement bd;
	Jogador j;
	
	
	public Jogadores (MeuPreparedStatement bd) throws Exception
    {
        if (bd == null)
            throw new Exception ("Acesso ao BD n�o fornecido.");
        else
        this.bd = bd;
    }
	
	public boolean EstaCadastrado(String cpf) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "select COUNT(*) as quantos from [BDu14180].tdi_Jogador where cpf = ?";

            bd.prepareStatement(sql);
            
            bd.setString(1, cpf);

            MeuResultSet resultado = bd.executeQuery();

            resultado.first();

            retorno = resultado.getInt("quantos") != 0;

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar o jogador.");
        }

        return retorno;
    }
	
	//incluir retorna a chave gerada
	//implementar isso
	public void incluir(Jogador jogador) throws Exception
    {
        if (jogador == null)
            throw new Exception ("Livro n�o fornecido.");

        try
        {
            String sql;

            sql = "insert into [BDu14180].tdi_Jogador values(?,?,?,?,?,?,?)";

            bd.prepareStatement (sql);

            bd.setString (1, jogador.getCpf());
            bd.setString (2, jogador.getNome());
            bd.setString (3, jogador.getDataNascimento());
            bd.setString (4, jogador.getEndereco());
            bd.setString (5, jogador.getCidade());
            bd.setString (6, jogador.getUf());
            bd.setString (7, jogador.getPais());

            bd.executeUpdate ();
            bd.commit();
            
        }
        catch (SQLException erro)
        {
            throw new Exception (erro.getMessage()/*"Erro ao inserir jogador."*/);
        }
    }

    public void excluir(String cpf) throws Exception
    {
        if (!EstaCadastrado (cpf))
            throw new Exception ("N�o cadastrado.");

        try
        {
            String sql;

            sql = "delete from [BDu14180].tdi_Jogador where cpf = ?";

            bd.prepareStatement (sql);

            bd.setString (1, cpf);

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir jogador.");
        }
    }

    public void alterar(Jogador jogador) throws Exception
    {
        if (jogador == null)
            throw new Exception ("Jogador n�o fornecido");

        if (!EstaCadastrado (jogador.getCpf()))
            throw new Exception ("N�o cadastrado.");

        try
        {
            String sql;

            sql = "update [BDu14180].tdi_Jogador set nome=?, dataNascimento=?, endereco=?, cidade=?, UF=?, pais=? "
            		+ "where cpf=?";

            bd.prepareStatement (sql);

            bd.setString (1, jogador.getNome());
            bd.setString (2, jogador.getDataNascimento());
            bd.setString (3, jogador.getEndereco());
            bd.setString (4, jogador.getCidade());
            bd.setString (5, jogador.getUf());
            bd.setString (6, jogador.getPais());
            bd.setString (7, jogador.getCpf());

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar os dados do jogador.");
        }
    }
    
    public MeuResultSet dados() throws Exception
    {

        try
        {
            String sql;

            sql = "select * from [BDu14180].tdi_Jogador";

            bd.prepareStatement (sql);
            
            MeuResultSet resultado = bd.executeQuery();

            return resultado;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao jogadores.");
        }
    }
    
    public MeuResultSet pesquisa(String cpf) throws Exception
    {

        try
        {
            String sql;

            sql = "select * from [BDu14180].tdi_Jogador where CPF = ?";     

            bd.prepareStatement (sql);
            
            bd.setString (1, cpf);
            
            MeuResultSet resultado = bd.executeQuery();

            return resultado;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao jogadores.");
        }
    }
    
    public void incluirNoTime(String CPF, int idTime) throws Exception
    {
        if (CPF.equals(""))
            throw new Exception ("Jogador n�o fornecido.");

        try
        {
            String sql;

            sql = "insert into [BDu14180].tdi_TimeJogador values(?,?)";

            bd.prepareStatement (sql);

            bd.setString (1, CPF);
            bd.setInt    (2, idTime);


            bd.executeUpdate ();
            bd.commit();
            
        }
        catch (SQLException erro)
        {
            throw new Exception (erro.getMessage()/*"Erro ao inserir jogador."*/);
        }
    }
    
    public void AlterarTimeJogador(String CPF, int idTime) throws Exception
    {
        if (CPF.equals(""))
            throw new Exception ("Jogador n�o fornecido.");

        try
        {
            String sql;

            sql = "update [BDu14180].tdi_TimeJogador set idTime = ? where CPF = ?";

            bd.prepareStatement (sql);

            bd.setInt    (1, idTime);
            bd.setString (2, CPF);

            bd.executeUpdate ();
            bd.commit();
            
        }
        catch (SQLException erro)
        {
            throw new Exception ("deu erro aqui.");
        }
    }
    
    public void excluirDoTime(String CPF) throws Exception
    {
        if (CPF.equals(""))
            throw new Exception ("Jogador n�o fornecido.");

        try
        {
            String sql;

            sql = "delete from [BDu14180].tdi_TimeJogador where cpf = ?";

            bd.prepareStatement (sql);

            bd.setString (1, CPF);


            bd.executeUpdate ();
            bd.commit();
            
        }
        catch (SQLException erro)
        {
            throw new Exception (erro.getMessage()/*"Erro ao inserir jogador."*/);
        }
    }
    
    public MeuResultSet dadosTimeJogador() throws Exception
    {
    	try
        {
            String sql;

            sql = "select [BDu14180].tdi_Jogador.nome as nomeJogador, [BDu14180].tdi_Time.nome as nomeTime from [BDu14180].tdi_Jogador, "
            		+ "[BDu14180].tdi_Time, [BDu14180].tdi_TimeJogador "
            		+ "where [BDu14180].tdi_Jogador.CPF = [BDu14180].tdi_TimeJogador.CPF and "
            		+ "[BDu14180].tdi_Time.idTime = [BDu14180].tdi_TimeJogador.idTime";

            bd.prepareStatement (sql);
            
            MeuResultSet resultado = bd.executeQuery();

            return resultado;
        }
        catch (SQLException erro)
        {
            throw new Exception ("giovanna man�");
        }
    }

}