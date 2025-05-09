package edu.msudenver.cs3013.movielist

class Movie(val title: String?, val year: String?, val genre: String?, val rating: String?) {
    override fun toString(): String {
        return "TITLE = " + title + ", YEAR = " + year + ", GENRE = " + genre + ", RATING = " + rating
    }
}