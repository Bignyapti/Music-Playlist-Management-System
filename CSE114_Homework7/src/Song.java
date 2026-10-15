public class Song {
    private String songName;
    private String artistName;
    private String duration; // Format "m:ss"

    public Song(String songName, String artistName, String duration) {
        this.songName = songName;
        this.artistName = artistName;
        this.duration = duration;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        return songName.equalsIgnoreCase(other.songName) && artistName.equalsIgnoreCase(other.artistName) && duration.equals(other.duration);
    }

    @Override
    public String toString() {
        return songName + " by " + artistName + " (" + duration + ")";
    }
}
