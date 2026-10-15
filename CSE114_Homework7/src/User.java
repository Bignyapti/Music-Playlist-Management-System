import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<Playlist> playlists;

    public User(String userName) {
        this.userName = userName;
        this.playlists = new ArrayList<>();
    }

    public String getUsername() {
        return userName;
    }

    public void addPlaylist(Playlist playlist) {
        if (!playlists.contains(playlist)) {
            playlists.add(playlist);
        } else {
            System.out.println("Playlist already exists for user " + userName + ": " + playlist.getPlaylistTitle());
        }
    }

    public void removePlaylist(String playlistTitle) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getPlaylistTitle().equalsIgnoreCase(playlistTitle)) {
                playlists.remove(i);
                return;
            }
        }
    }

    public ArrayList<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    public Playlist getPlaylist(String playlistTitle) {
        for (Playlist p : playlists) {
            if (p.getPlaylistTitle().equalsIgnoreCase(playlistTitle)) {
                return p;
            }
        }
        return null;
    }

    public void makeCollaborativePlaylist(String playlistTitle, User friend) {
        Playlist ownPlaylist = getPlaylist(playlistTitle);
        if (ownPlaylist != null) {
            friend.addPlaylist(ownPlaylist);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return userName.equalsIgnoreCase(other.userName);
    }

    @Override
    public String toString() {
        return "User: " + userName;
    }
}