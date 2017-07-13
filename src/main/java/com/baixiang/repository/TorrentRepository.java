package com.baixiang.repository;

import com.baixiang.model.MovieTorrent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shenjj on 2017/7/13.
 */
public interface TorrentRepository extends JpaRepository<MovieTorrent, Long> {
    List<MovieTorrent> getByTorrentNameContaining(String nameStr);
}
