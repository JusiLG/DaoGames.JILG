package mx.edu.utez.model.games;

import jdk.nashorn.internal.runtime.Context;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGames {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    private Logger CONSOLE = LoggerFactory.getLogger(DaoGames.class);

    public List<BeanGames> findAll(){
        List<BeanGames> listGames = new ArrayList<>();
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findGames}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanCategory beanCategory = new BeanCategory();
                BeanGames beanGames = new BeanGames();

                beanCategory.setIdCategory(rs.getInt("idCategory"));
                beanCategory.setName(rs.getString("name"));
                beanCategory.setStatus(rs.getInt("status"));

                beanGames.setIdGames(rs.getInt("idGames"));
                beanGames.setName(rs.getString("name"));
                beanGames.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGames")));
                beanGames.setDatePremiere(rs.getString("date_premiere"));
                beanGames.setCategory_idCategory(beanCategory);
                beanGames.setStatus(rs.getInt("status"));

                listGames.add(beanGames);
            }
        }catch (SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listGames;
    }

    public BeanGames findById(int id){
        BeanGames games = null;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM games WHERE idGames = ?;");
            cstm.setInt(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanCategory category = new BeanCategory();
                games = new BeanGames();

                category.setIdCategory(rs.getInt("idCategory"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getInt("status"));

                games.setIdGames(rs.getInt("idGames"));
                games.setName(rs.getString("name"));
                games.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGames")));
                games.setDatePremiere(rs.getString("date_premiere"));
                games.setCategory_idCategory(category);
                games.setStatus(rs.getInt("status"));

                games.setCategory_idCategory(category);
            }
        }catch (SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return games;
    }

    public boolean create(BeanGames games){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?)}");
            cstm.setString(1, games.getCategory_idCategory().getName());
            cstm.setString(2, games.getName());
            cstm.setString(3, games.getImgGame());
            cstm.setString(4, games.getDatePremiere());
            cstm.execute();
            flag = true;
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanGames games){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?)}");
            cstm.setString(1, games.getCategory_idCategory().getName());
            cstm.setString(2, games.getName());
            cstm.setString(3, games.getImgGame());
            cstm.setString(4, games.getDatePremiere());

            flag = cstm.execute();
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(int idGames){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete2(?)}");
            cstm.setLong(1, idGames);

            flag = cstm.execute();
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

}
