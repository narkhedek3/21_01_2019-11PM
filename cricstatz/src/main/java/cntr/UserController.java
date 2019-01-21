package cntr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.TournamentsDao;
import dto.Tournament;


@Controller
public class UserController {

	@Autowired
	private TournamentsDao tournamentsDao;
	
	public TournamentsDao getTournamentsDao() {
		return tournamentsDao;
	}

	public void setUserDao(TournamentsDao tournamentsDao) {
		this.tournamentsDao = tournamentsDao;
	}

	@RequestMapping(value="/index.htm")
	public String showindex() {

		return "index";
	}



	@RequestMapping(value="/livescores.htm")
	public String showLiveScores() {

		return "livescores";
	}

	@RequestMapping(value="/preTournamentsRegistration.htm")
	public String showregister(ModelMap model) {
		model.put("tournament", new Tournament());
		return "tournamentsRegistration";
	}

	@RequestMapping(value="/postTournamentsRegistraion.htm")
	public String showtournaments(Tournament tournament , ModelMap model) {
		tournamentsDao.createTournament(tournament);
		model.put("tournamnet",tournament);
		return "tournaments";
	}


	@RequestMapping(value="/playersList.htm")
	public String showplayersList() {

		return "playersList";
	}

	@RequestMapping(value="/teamList.htm")
	public String showteamList() {

		return "teamList";
	}

	@RequestMapping(value="/about.htm")
	public String showabout() {

		return "about";
	}

}