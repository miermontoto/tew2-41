package impl.tewrrss.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tewrrss.business.DatabaseService;
import com.tewrrss.dto.*;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.*;
import com.tewrrss.util.Role;

public class DatabaseServiceImpl implements DatabaseService {

	private Random random = new Random();

	@Override
	public boolean reset() {
		PostDAO postDAO = Factories.persistence.getPostDAO();
		UserDAO userDAO = Factories.persistence.getUserDAO();
		CommunityDAO communityDAO = Factories.persistence.getCommunityDAO();
		MemberDAO memberDAO = Factories.persistence.getMemberDAO();

		// Drop all values
		postDAO.dropAll();
		communityDAO.dropAll();
		userDAO.dropAll();

		// Fill with sample values
		List<Community> sampleCommunities = new ArrayList<>();
		List<User> sampleUsers = new ArrayList<>();
		List<Member> sampleMemberships = new ArrayList<>();
		List<Post> samplePosts = new ArrayList<>();

		sampleCommunities.add(new Community("tew.music", "the music community."));
		sampleCommunities.add(new Community("tew.food", "the food community."));
		sampleCommunities.add(new Community("tew.programming", "the programming community."));
		sampleCommunities.add(new Community("tew.cars", "the cars community."));
		sampleCommunities.add(new Community("tew.daftpunk", "the better music community."));
		sampleCommunities.add(new Community("tew.linux", "the linux community."));

		sampleUsers.add(new User("admin@email.com", "admin", "admin", Role.ADMIN));
		sampleCommunities.forEach(c -> sampleMemberships.add(new Member(c, sampleUsers.get(0)))); // add admin to all communities
		for (int i = 1; i <= 10; i++) {
			sampleUsers.add(new User(String.format("user%d@email.com", i), // add sample users
				String.format("user%d", i), String.format("user%d", i))
			);
		}

		// generate random memberships
		sampleUsers.stream().filter(u -> u.getRole() != Role.ADMIN).forEach(u ->
			sampleCommunities.stream()
				.filter(c -> Math.random() < 0.33)
				.forEach(c -> sampleMemberships.add(new Member(c, u)))
		);


		sampleCommunities.forEach(communityDAO::add);
		sampleUsers.forEach(userDAO::add);
		for(Member m : sampleMemberships) {
			memberDAO.join(m.getCommunity(), m.getUser());

			if(m.getUser().getRole() == Role.ADMIN) continue; // don't generate posts for admin

			String date = LocalDateTime.now().minusDays(random.nextInt(365))
				.minusHours(random.nextInt(24))
				.minusMinutes(random.nextInt(60))
				.minusSeconds(random.nextInt(60))
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			String content = random.ints(97, 123)
				.limit(random.nextInt(50))
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

			samplePosts.add(new Post(content, date, m.getUser().getEmail(), m.getCommunity().getName())); // build random posts
		}

		samplePosts.forEach(postDAO::add);

		return true;
	}

}
