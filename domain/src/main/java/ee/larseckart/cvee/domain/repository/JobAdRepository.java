package ee.larseckart.cvee.domain.repository;

import ee.larseckart.cvee.domain.JobAd;
import io.reactivex.Observable;
import java.util.List;

public interface JobAdRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link JobAd}.
   */
  Observable<List<JobAd>> jobAds();

  /**
   * Get an {@link Observable} which will emit a {@link JobAd}.
   *
   * @param userId The job ad id used to retrieve job ad data.
   */
  Observable<JobAd> jobAd(final int userId);
}