package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import dto.Tournament;

@Repository
public class TournamentsDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;



	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void createTournament(final Tournament tournament) {
		hibernateTemplate.execute(new HibernateCallback<List<Tournament>>() {

			public List<Tournament> doInHibernate(Session session) throws HibernateException {
				Transaction t = session.beginTransaction();
				session.save(tournament);
				t.commit();
				session.flush();
				session.close();
				return null;
			}
		});
	}
}
