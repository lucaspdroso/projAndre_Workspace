package dao;

import java.sql.SQLException;
import pacote.*;
import dbo.Time;

public class Times {
	MeuPreparedStatement bd;
	Time t;
	
	public Times (MeuPreparedStatement bd) throws Exception
    {
        if (bd == null)
            throw new Exception ("Acesso ao BD não fornecido.");
        else
        this.bd = bd;
    }
	
	public boolean EstaCadastrado(int idTime) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "select COUNT(*) as quantos from [BDu14180].tdi_Time where idTime = ?";

            bd.prepareStatement(sql);
            
            bd.setInt(1, idTime);

            MeuResultSet resultado = bd.executeQuery();

            resultado.first();

            retorno = resultado.getInt("quantos") != 0;

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar o time.");
        }

        return retorno;
    }
	
	//incluir retorna a chave gerada
	//implementar isso
	public void incluir(Time time) throws Exception
    {
        if (time == null)
            throw new Exception ("Time não fornecido.");

        try
        {
            String sql;

            sql = "insert into [BDu14180].tdi_Time values(?,?,?,?)";

            bd.prepareStatement (sql);

            bd.setString (1, time.getNome());
            bd.setString (2, time.getSigla());
            bd.setString (3, time.getUf());
            bd.setString (4, time.getPais());

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir time.");
        }
    }

    public void excluir(int idTime) throws Exception
    {
        if (!EstaCadastrado (idTime))
            throw new Exception ("Não cadastrado.");

        try
        {
            String sql;

            sql = "delete from [BDu14180].tdi_Time where idTime = ?";

            bd.prepareStatement (sql);

            bd.setInt (1, idTime);

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir time.");
        }
    }

    public void alterar(Time time) throws Exception
    {
        if (time == null)
            throw new Exception ("Time não fornecido");

        if (!EstaCadastrado (time.getIdTime()))
            throw new Exception ("Time não cadastrado.");

        try
        {
            String sql;

            sql = "update [BDu14180].tdi_Time set nome= ?, sigla = ?, uf = ?, pais = ? where idTime = ?";

            bd.prepareStatement (sql);

            bd.setString (1, time.getNome());
            bd.setString (2, time.getSigla());
            bd.setString (3, time.getUf());
            bd.setString (4, time.getPais());
            bd.setInt    (5, time.getIdTime());

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar os dados do time.");
        }
    }
    
    public MeuResultSet dados() throws Exception
    {

        try
        {
            String sql;

            sql = "select * from [BDu14180].tdi_Time";

            bd.prepareStatement (sql);
            
            MeuResultSet resultado = bd.executeQuery();

            return resultado;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao Times.");
        }
    }
}
