package com.datapath.release.loader;

import com.datapath.release.loader.containers.Release;
import com.datapath.release.loader.containers.ReleasesWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class LoaderManager implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoaderManager.class);
    private ObjectMapper objectMapper;
    private Finder finder;
    private DatabaseDAO dao;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setFinder(Finder finder) {
        this.finder = finder;
    }

    @Autowired
    public void setDao(DatabaseDAO dao) {
        this.dao = dao;
    }

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

        ReleaseFolder releaseFolder = finder.findLastReleases();
        List<String> releaseFiles = finder.getReleaseFiles(releaseFolder);
        for (String releaseFile : releaseFiles) {
            List<Future<String>> futures = new ArrayList<>();
            log.info("Start parsing file with name - {}", releaseFile);
            ReleasesWrapper wrapper = objectMapper.readValue(new URL(releaseFile), ReleasesWrapper.class);
            log.info("File parsed successfully");
            for (Release release : wrapper.getReleases()) {
                Future<String> future = executor.submit(() -> {
                    try {
                        dao.saveRelease(release);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                    return release.getOcid();
                });
                futures.add(future);
            }
            for (Future<String> future : futures) {
                log.info("Saved {}", future.get());
            }
        }
        dao.clearAutoCreatedLotsInMultiLotsTenders();
        executor.shutdown();
    }
}