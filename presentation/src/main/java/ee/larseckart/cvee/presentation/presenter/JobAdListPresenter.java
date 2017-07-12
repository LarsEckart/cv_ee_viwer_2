package ee.larseckart.cvee.presentation.presenter;

import android.support.annotation.NonNull;
import ee.larseckart.cvee.domain.JobAd;
import ee.larseckart.cvee.domain.exception.DefaultErrorBundle;
import ee.larseckart.cvee.domain.exception.ErrorBundle;
import ee.larseckart.cvee.domain.interactor.DefaultObserver;
import ee.larseckart.cvee.domain.interactor.GetJobAdList;
import ee.larseckart.cvee.presentation.exception.ErrorMessageFactory;
import ee.larseckart.cvee.presentation.mapper.JobAdModelDataMapper;
import ee.larseckart.cvee.presentation.model.JobAdModel;
import ee.larseckart.cvee.presentation.view.JobAdListView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class JobAdListPresenter implements Presenter {

  private JobAdListView viewListView;

  private final GetJobAdList getJobAdListUseCase;
  private final JobAdModelDataMapper userModelDataMapper;

  @Inject public JobAdListPresenter(GetJobAdList getUserListUserCase, JobAdModelDataMapper userModelDataMapper) {
    this.getJobAdListUseCase = getUserListUserCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull JobAdListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getJobAdListUseCase.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the jobAd list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void onUserClicked(JobAdModel jobAdModel) {
    this.viewListView.viewJobAd(jobAdModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(), errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showUsersCollectionInView(Collection<JobAd> usersCollection) {
    final Collection<JobAdModel> userModelsCollection = this.userModelDataMapper.transform(usersCollection);
    this.viewListView.renderJobAdList(userModelsCollection);
  }

  private void getUserList() {
    this.getJobAdListUseCase.execute(new JobAdListObserver(), null);
  }

  private final class JobAdListObserver extends DefaultObserver<List<JobAd>> {

    @Override public void onComplete() {
      JobAdListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      JobAdListPresenter.this.hideViewLoading();
      JobAdListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      JobAdListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<JobAd> users) {
      JobAdListPresenter.this.showUsersCollectionInView(users);
    }
  }
}