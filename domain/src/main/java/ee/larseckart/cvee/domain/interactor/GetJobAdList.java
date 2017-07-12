package ee.larseckart.cvee.domain.interactor;

import ee.larseckart.cvee.domain.JobAd;
import ee.larseckart.cvee.domain.executor.PostExecutionThread;
import ee.larseckart.cvee.domain.executor.ThreadExecutor;
import ee.larseckart.cvee.domain.repository.JobAdRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class GetJobAdList extends UseCase<List<JobAd>, Void> {

  private final JobAdRepository userRepository;

  @Inject GetJobAdList(JobAdRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override Observable<List<JobAd>> buildUseCaseObservable(Void unused) {
    return this.userRepository.jobAds();
  }
}
