package cntr;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.TeamDao;
import dao.TournamentsDao;
import dao.UserDao;
import dto.Team;
import dto.Tournament;
import dto.User;


@Controller
public class UserController {

	@Autowired
	private TournamentsDao tournamentsDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

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
	
	@RequestMapping(value="/teamProfile.htm")
	public String showTeamProfile() {
		return "teamProfile";
	}
	
	@RequestMapping(value="/loginPage.htm")
	public String loginform(ModelMap model) {
		model.put("user", new User());
		return "loginPage";
	}
	
	@RequestMapping(value="/signUpPage.htm")
	public String SignUpform(ModelMap model) {
		model.put("user", new User());
		return "signUpPage";
	}
	
	@RequestMapping(value="/signup.htm")
	public String CreateUser(final User user) {
		userDao.createUser(user);
		return "loginPage";
	}
	
	
	@RequestMapping(value="/loginStatus.htm")
	public String validateUser(final User user ,ModelMap model) {
		List<User> list = userDao.login(user);
		if( list.isEmpty() )
		{
			model.put("user", new User());
			return "loginPage";
		}
		
		for(User u : list) 
		{
			if(u.getUserRole().equals("Tournament Representative"))
			{
				return "tournamentProfile";
			}
			return "teamProfile";
		}
		
		return "loginPage";
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

	@RequestMapping(value="/tournaments.htm")
	public String showTournamentList(ModelMap model) {
		List<Tournament> tournamentList = tournamentsDao.selectTournaments();
		model.put("tournamentList",tournamentList);
		return "tournaments";
	}
	
	@RequestMapping(value="/playersList.htm")
	public String showplayersList() {

		return "playersList";
	}
	
	@RequestMapping(value="/preTeamForm.htm")
	public String showTeamForm(ModelMap model) {
		model.put("team", new Team());
		return "teamForm";
	}
	
	@RequestMapping(value="/postTeamForm.htm")
	public String TeamFormSuccess(Team team,ModelMap model) {
		teamDao.createTeam(team);
		model.put("team", team);
		return "teamList";
	}
	
	@RequestMapping(value="/teamList.htm")
	public String showteamList(ModelMap model) {
		List<Team> teamList = teamDao.selectTeam();
		model.put("teamList", teamList);
		return "teamList";
	}
	
	@RequestMapping(value="/viewScoreCard.htm")
	public String showScoreCard() {

		return "scoreCard";
	}
	
	@RequestMapping(value="/about.htm")
	public String showabout() {

		return "about";
	}

}