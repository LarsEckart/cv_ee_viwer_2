package ee.larseckart.cvee.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ee.larseckart.cvee.presentation.R;
import ee.larseckart.cvee.presentation.internal.di.components.JobAdComponent;
import ee.larseckart.cvee.presentation.model.JobAdModel;
import ee.larseckart.cvee.presentation.presenter.JobAdListPresenter;
import ee.larseckart.cvee.presentation.view.JobAdListView;
import java.util.Collection;
import javax.inject.Inject;

public class JobAdListFragment extends BaseFragment implements JobAdListView {

  public interface JobAdListListener {
    void onJobAdClicked(final JobAdModel jobAdModel);
  }

  private JobAdListListener jobAdsListListener;
  private Unbinder unbinder;

  @BindView(R.id.rv_job_ads) RecyclerView rv_users;
  @BindView(R.id.rl_progress) RelativeLayout rl_progress;
  @BindView(R.id.rl_retry) RelativeLayout rl_retry;
  @BindView(R.id.bt_retry) Button bt_retry;

  @Inject JobAdListPresenter jobAdListPresenter;

  public JobAdListFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof JobAdListListener) {
      this.jobAdsListListener = (JobAdListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(JobAdComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_job_ad_list, container, false);
    ButterKnife.bind(this, fragmentView);
    //setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.jobAdListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadUserList();
    }
  }

  private void loadUserList() {
    this.jobAdListPresenter.initialize();
  }

  @Override public void onResume() {
    super.onResume();
    this.jobAdListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.jobAdListPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_users.setAdapter(null);
    unbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.jobAdListPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.jobAdsListListener = null;
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  @Override public void renderJobAdList(Collection<JobAdModel> jobAdModelCollection) {
    if (jobAdModelCollection != null) {
      //this.usersAdapter.setUsersCollection(jobAdModelCollection);
    }
  }

  @Override public void viewJobAd(JobAdModel jobAd) {
    if (this.jobAdsListListener != null) {
      this.jobAdsListListener.onJobAdClicked(jobAd);
    }
  }
}
