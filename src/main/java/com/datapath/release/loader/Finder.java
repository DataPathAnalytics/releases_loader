package com.datapath.release.loader;

import com.datapath.release.loader.exceptions.HtmlParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class Finder {

    public ReleaseFolder findLastReleases() {
        try {
            List<ReleaseFolder> folders = new ArrayList<>();
            Document document = Jsoup.connect("http://ocds.prozorro.openprocurement.io").get();
            Elements links = document.select("li > a");
            for (Element link : links) {
                String linkText = link.text();
                if (linkText.startsWith("merged_with_extensions")) {
                    ReleaseFolder folder = new ReleaseFolder();
                    folder.setName(linkText);
                    folder.setDate(LocalDate.parse(linkText.substring(23)));
                    folder.setAddress(link.attr("abs:href"));
                    folders.add(folder);
                }
            }
            return Collections.max(folders, Comparator.comparing(ReleaseFolder::getDate));
        } catch (IOException e) {
            throw new HtmlParseException(e.getMessage(), e);
        }
    }

    public List<String> getReleaseFiles(ReleaseFolder releaseFolder) {
        try {
            List<String> releaseFiles = new ArrayList<>();
            Document document = Jsoup.connect(releaseFolder.getAddress()).get();
            Elements links = document.select("li > a");
            for (Element link : links) {
                releaseFiles.add(link.attr("abs:href"));
            }
            return releaseFiles;
        } catch (IOException e) {
            throw new HtmlParseException(e.getMessage(), e);
        }
    }
}