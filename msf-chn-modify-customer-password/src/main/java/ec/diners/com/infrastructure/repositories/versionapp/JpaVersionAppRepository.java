package ec.diners.com.infrastructure.repositories.versionapp;

import ec.diners.com.domain.views.VersionView;
import ec.diners.com.infrastructure.modelsDb.versionapp.VersionAppDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaVersionAppRepository extends JpaRepository<VersionAppDbModel, UUID>, JpaSpecificationExecutor<VersionAppDbModel> {

    VersionAppDbModel findByIsActive(Boolean enabled);

    List<VersionAppDbModel> findByIsActiveOrderByVersionNumberDescPublishedDateDesc(Boolean enabled);

    @Query(value = "SELECT max(v.updateRequiredAndroid) AS requiredAndroid, max(v.updateRequiredIos) AS requiredIos, max(v.clearStorage) AS clearStorage from versions_app v where v.versionNumber > :versionNumber and v.enabled = 1 order by v.versionNumber desc", nativeQuery = true)
    VersionView findAllVersionHistoryView(@Param("versionNumber") String versionNumber);
}
