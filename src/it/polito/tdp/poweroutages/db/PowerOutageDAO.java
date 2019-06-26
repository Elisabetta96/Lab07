package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Po;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	
	
	public List<Po> getOutagesFromNerc(int idNerc) {

		String sql = "SELECT id,customers_affected,date_event_began,date_event_finished, " + 
				"TIME_TO_SEC(TIMEDIFF(date_event_finished,date_event_began))/3600 duration "+
				"FROM poweroutages p WHERE p.nerc_id=? ORDER BY date_event_began ASC";
		
		List<Po> OutagesFromNerc  = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idNerc);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Po p = new Po(res.getInt("id"), res.getInt("customers_affected"),
						res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime(),res.getLong("duration"));
				OutagesFromNerc.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return OutagesFromNerc;
	}
	
	
	
	
	
	
}
