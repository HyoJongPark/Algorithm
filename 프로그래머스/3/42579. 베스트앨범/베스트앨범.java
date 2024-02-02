import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();

    public int[] solution(String[] genres, int[] plays) {
        List<Music> musics = new ArrayList<>();

        for (int i = 0 ; i < plays.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            musics.add(new Music(genres[i], i, plays[i]));
        }

        musics.sort(Music::compareTo);

        List<Integer> result = new ArrayList<>();
        String beforeGenre = musics.get(0).genre;
        int genreCount = 1;
        result.add(musics.get(0).musicNo);
        for (int i = 1; i < musics.size(); i++) {
            Music music = musics.get(i);
            if (music.genre.equals(beforeGenre)) {
                if (++genreCount <= 2) {
                    result.add(music.musicNo);
                }
            } else {
                beforeGenre = music.genre;
                genreCount = 1;
                result.add(music.musicNo);
            }
        }
        return result.stream().mapToInt(count -> count).toArray();
    }

    static class Music implements Comparable<Music> {
        String genre;
        int musicNo, playCount;

        public Music(String genre, int musicNo, int playCount) {
            this.genre = genre;
            this.musicNo = musicNo;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Music o) {
            if (!this.genre.equals(o.genre)) {
                return map.get(o.genre) - map.get(this.genre);
            } else if (this.playCount == o.playCount) {
                return this.musicNo - o.musicNo;
            }

            return o.playCount - this.playCount;
        }
    }
}
