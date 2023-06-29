package com.movies_rating.controllee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movies_rating.Model.Movie;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MoviesRatingController {


	private final JdbcTemplate jdbcTemplate;

	public MoviesRatingController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	@GetMapping("/longest-duration-movies")
	public List<Map<String, Object>> getLongestDurationMovies() {
		String sql = "SELECT TOP 10 tconst, primary_title, runtime_minutes, genres " +
				"FROM movies " +
				"ORDER BY runtime_minutes DESC";
		return jdbcTemplate.queryForList(sql);

	}



	@PostMapping("/new-movie")
	public String saveNewMovie(@RequestBody Movie movie) {
		try {
			String sql = "INSERT INTO movies (tconst, primary_title, runtime_minutes, genres) " +
					"VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, movie.getTconst(), movie.getPrimaryTitle(),
					movie.getRuntimeMinutes(), movie.getGenres());
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}



	@GetMapping("/top-rated-movies")
	public List<Map<String, Object>> getTopRatedMovies() {
		String sql = "SELECT m.tconst, m.primary_title, m.genres, r.average_rating " +
				"FROM movies m " +
				"JOIN ratings r ON m.tconst = r.tconst " +
				"WHERE r.average_rating > 6.0 " +
				"ORDER BY r.average_rating DESC";
		return jdbcTemplate.queryForList(sql);
	}

	@GetMapping("/genre-movies-with-subtotals")
	public String getGenreMoviesWithSubtotals() {
		String sql = "SELECT m.genres, m.primary_title, SUM(r.num_votes) AS num_votes " +
				"FROM movies m " +
				"JOIN ratings r ON m.tconst = r.tconst " +
				"GROUP BY m.genres, m.primary_title " +
				"ORDER BY m.genres, m.primary_title";

		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

		StringBuilder output = new StringBuilder();
		String currentGenre = "";
		int totalGenreVotes = 0;

		output.append(String.format("%-20s %-40s %-10s\n", "Genre", "Primary Title", "Num Votes"));
		output.append(String.format("%-20s %-40s %-10s\n", "-----", "-------------", "---------"));

		for (Map<String, Object> row : results) {
			String genre = (String) row.get("genres");
			String primaryTitle = (String) row.get("primary_title");
			int numVotes = ((Number) row.get("num_votes")).intValue();

			if (!genre.equals(currentGenre)) {
				// New genre
				if (!currentGenre.isEmpty()) {
					// Append total for previous genre
					output.append(String.format("%-20s %-40s %-10s\n", "TOTAL", "", totalGenreVotes));
					output.append("\n");
				}
				output.append(String.format("%-20s %-40s %-10s\n", genre, primaryTitle, numVotes));
				currentGenre = genre;
				totalGenreVotes = numVotes;
			} else {
				// Same genre
				output.append(String.format("%-20s %-40s %-10s\n", "", primaryTitle, numVotes));
				totalGenreVotes += numVotes;
			}
		}

		// Append total for last genre
		if (!currentGenre.isEmpty()) {
			output.append(String.format("%-20s %-40s %-10s\n", "TOTAL", "", totalGenreVotes));
			output.append("\n");
		}

		return output.toString();
	}



	@PostMapping("/update-runtime-minutes")
	public String updateRuntimeMinutes() {
		try {
			String sql = "UPDATE movies " +
					"SET runtime_minutes = CASE " +
					"    WHEN genres = 'Documentary' THEN runtime_minutes + 15 " +
					"    WHEN genres = 'Animation' THEN runtime_minutes + 30 " +
					"    ELSE runtime_minutes + 45 " +
					"END";
			jdbcTemplate.update(sql);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}


}
