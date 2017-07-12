package ee.larseckart.cvee.presentation.mapper;

import ee.larseckart.cvee.domain.JobAd;
import ee.larseckart.cvee.presentation.internal.di.PerActivity;
import ee.larseckart.cvee.presentation.model.JobAdModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

@PerActivity
public class JobAdModelDataMapper {

  @Inject
  public JobAdModelDataMapper() {}

  public JobAdModel transform(JobAd user) {
    if (user == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    final JobAdModel jobAdModel = new JobAdModel();
    //TODO:
    return jobAdModel;
  }

  public Collection<JobAdModel> transform(Collection<JobAd> jobAdCollection) {
    Collection<JobAdModel> jobAdModelsCollection;

    if (jobAdCollection != null && !jobAdCollection.isEmpty()) {
      jobAdModelsCollection = new ArrayList<>();
      for (JobAd user : jobAdCollection) {
        jobAdModelsCollection.add(transform(user));
      }
    } else {
      jobAdModelsCollection = Collections.emptyList();
    }

    return jobAdModelsCollection;
  }
}


