package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.GenreBean;
import frame.exception.ResourceException;

public class GenreDao extends Dao {
	public ArrayList<GenreBean> selectGenre() throws ResourceException {
		ArrayList<GenreBean> genreList = new ArrayList<>();
		
		connect();
		
		String sql = "SELECT * FROM genre ORDER BY genre_no ASC";
		
		
		try {
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while (rs.next()) {
				GenreBean genre = new GenreBean();
				genre.setGenre_no(rs.getInt("genre_no"));
                genre.setGenre_title(rs.getString("genre_title"));

                genreList.add(genre);
            }
			
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
		
		return genreList;
		
	}
}
