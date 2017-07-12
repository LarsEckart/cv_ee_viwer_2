package ee.larseckart.cvee.data.repository;

import ee.larseckart.cvee.domain.JobAd;
import ee.larseckart.cvee.domain.repository.JobAdRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class JobAdDataRepository implements JobAdRepository {

  @Inject public JobAdDataRepository() {
    
  }

  @Override public Observable<List<JobAd>> jobAds() {
    return null;
  }

  @Override public Observable<JobAd> jobAd(int userId) {
    return null;
  }
}
