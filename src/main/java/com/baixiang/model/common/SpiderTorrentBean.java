package com.baixiang.model.common;


public class SpiderTorrentBean {
    private String movieName;
    private String torrentName;
    private String magnetUrl;
    private String torrentUrl;
    private String torrentSize;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTorrentName() {
        return torrentName;
    }

    public void setTorrentName(String torrentName) {
        this.torrentName = torrentName;
    }

    public String getMagnetUrl() {
        return magnetUrl;
    }

    public void setMagnetUrl(String magnetUrl) {
        this.magnetUrl = magnetUrl;
    }

    public String getTorrentUrl() {
        return torrentUrl;
    }

    public void setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
    }

    public String getTorrentSize() {
        return torrentSize;
    }

    public void setTorrentSize(String torrentSize) {
        this.torrentSize = torrentSize;
    }

    @Override
    public String toString() {
        return "SpiderTorrentBean{" +
                "movieName='" + movieName + '\'' +
                ", torrentName='" + torrentName + '\'' +
                ", magnetUrl='" + magnetUrl + '\'' +
                ", torrentUrl='" + torrentUrl + '\'' +
                '}';
    }
}
