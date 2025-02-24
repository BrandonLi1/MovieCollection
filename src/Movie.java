public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private String runtime;
    private String rating;

    public Movie (String title, String cast, String director, String overview, String runtime, String rating) {
        this.title=title;
        this.cast=cast;
        this.director=director;
        this.overview=overview;
        this.runtime=runtime;
        this.rating=rating;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getOverview() {
        return overview;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getRating() {
        return rating;
    }
}
