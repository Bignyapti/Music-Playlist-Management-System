import java.util.ArrayList;

public class Playlist {
    private String playlistTitle;
    private ArrayList<Song> songs;

    public Playlist(String playlistTitle) {
        this.playlistTitle = playlistTitle;
        this.songs = new ArrayList<>();
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public void addSong(Song song) {
        if (!songs.contains(song)) {
            songs.add(song);
        } else {
            System.out.println("Song already exists in the playlist: " + song);
        }
    }

    public void removeSong(String songTitle) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongName().equalsIgnoreCase(songTitle)) {
                songs.remove(i);
                return;
            }
        }
    }

    public void swapSongs(Song song1, Song song2) {
        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).equals(song1)) {
                index1 = i;
            }
            if (songs.get(i).equals(song2)) {
                index2 = i;
            }
        }

        if (index1 != -1 && index2 != -1) {
            Song temp = songs.get(index1);
            songs.set(index1, songs.get(index2));
            songs.set(index2, temp);
        }
    }

    public String getTotalDuration() {
        int totalMinutes = 0;
        int totalSeconds = 0;
        for (Song song : songs) {
            String[] parts = song.getDuration().split(":");
            if (parts.length != 2) {
                System.err.println("Invalid duration format for song: " + song);
                continue;
            }
            try {
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                if (seconds >= 60 || seconds < 0 || minutes < 0) {
                    System.err.println("Invalid time values for song: " + song);
                    continue;
                }
                totalMinutes += minutes;
                totalSeconds += seconds;
            } catch (NumberFormatException e) {
                System.err.println("Number format exception for song: " + song);
            }
        }
        totalMinutes += totalSeconds / 60;
        totalSeconds = totalSeconds % 60;
        return totalMinutes + " min, " + totalSeconds + " sec";
    }

    public void sortSongs() {
        if (songs == null || songs.size() < 2) {
            return;
        }
        songs = mergeSort(songs);
    }

    private ArrayList<Song> mergeSort(ArrayList<Song> list) {
        if (list.size() <= 1) {
            return list;
        }
        int mid = list.size() / 2;
        ArrayList<Song> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Song> right = new ArrayList<>(list.subList(mid, list.size()));
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private ArrayList<Song> merge(ArrayList<Song> left, ArrayList<Song> right) {
        ArrayList<Song> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (compareSongs(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }
        return merged;
    }

    private int compareSongs(Song s1, Song s2) {
        int nameCompare = s1.getSongName().compareToIgnoreCase(s2.getSongName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        return s1.getArtistName().compareToIgnoreCase(s2.getArtistName());
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(playlistTitle).append(" - ").append(getTotalDuration()).append("\n");
        for (int i = 0; i < songs.size(); i++) {
            sb.append((i + 1)).append(". ").append(songs.get(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }
}