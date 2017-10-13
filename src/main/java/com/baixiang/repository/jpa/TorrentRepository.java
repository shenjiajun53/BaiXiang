package com.baixiang.repository.jpa;

import com.baixiang.model.jpa.MovieTorrent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shenjj on 2017/7/13.
 */
public interface TorrentRepository extends JpaRepository<MovieTorrent, Long> {
    List<MovieTorrent> getByTorrentNameContaining(String nameStr);
}
