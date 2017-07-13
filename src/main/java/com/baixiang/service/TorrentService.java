package com.baixiang.service;

import com.baixiang.model.MovieTorrent;
import com.baixiang.repository.TorrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjj on 2017/7/13.
 */

@Service
public class TorrentService {
    @Autowired
    private TorrentRepository torrentRepository;

    public List<MovieTorrent> getIncludeName(String name) {
        return torrentRepository.getByTorrentNameContaining(name);
    }

    public MovieTorrent update(MovieTorrent movieTorrent) {
        torrentRepository.saveAndFlush(movieTorrent);
        return movieTorrent;
    }
}
