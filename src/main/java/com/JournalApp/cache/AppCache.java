package com.journalapp.cache;

import com.journalapp.entity.ConfigJournalAppEntity;
import com.journalapp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {




    public Map<String, String> APP_CACHE= new HashMap<>();
    private final ConfigJournalAppRepository configJournalAppRepository;

    public AppCache(ConfigJournalAppRepository configJournalAppRepository) {
        this.configJournalAppRepository = configJournalAppRepository;
    }

    @PostConstruct
    public void init() {

        List<ConfigJournalAppEntity> all =
                configJournalAppRepository.findAll();

        for (ConfigJournalAppEntity config : all) {
            APP_CACHE.put(
                    config.getKey(),
                    config.getValue()
            );
        }

        System.out.println("Cache loaded: " + APP_CACHE);
    }

    public String getValue(String key) {
        return APP_CACHE.get(key);
    }
}
