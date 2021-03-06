package com.baixiang.service;

import com.baixiang.model.jpa.MovieTorrent;
import com.baixiang.repository.jpa.TorrentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjj on 2017/7/13.
 */

@Service
public class TorrentService {
    private static final Logger logger = LoggerFactory.getLogger(TorrentService.class);

    @Autowired
    private TorrentRepository torrentRepository;

    public List<MovieTorrent> getIncludeName(String name) {
        return torrentRepository.getByTorrentNameContaining(name);
    }

    public MovieTorrent update(MovieTorrent movieTorrent) {
        torrentRepository.saveAndFlush(movieTorrent);
        return movieTorrent;
    }

    public void delete(long id) {
        torrentRepository.delete(id);
    }
}
