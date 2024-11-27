package ec.diners.com.domain.interfaces.repositories.versionapp;

import ec.diners.com.domain.entities.versionapp.VersionApp;
import ec.diners.com.domain.interfaces.repositories.BaseRepository;
import ec.diners.com.domain.views.VersionView;

public interface VersionAppRepository extends BaseRepository<VersionApp> {
    VersionApp findByIsActive(Boolean isActive);
    VersionApp findVersionCurrentApp();
    VersionView findHistoryVersionApp(String versionNumber);
}
