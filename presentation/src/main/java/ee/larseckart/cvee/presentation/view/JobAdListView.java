package ee.larseckart.cvee.presentation.view;

import ee.larseckart.cvee.presentation.model.JobAdModel;
import java.util.Collection;

public interface JobAdListView extends LoadDataView {

  void renderJobAdList(Collection<JobAdModel> jobAdModelCollection);

  void viewJobAd(JobAdModel jobAd);
}
