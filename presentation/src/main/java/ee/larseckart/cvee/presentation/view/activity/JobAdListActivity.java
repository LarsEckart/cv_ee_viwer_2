package ee.larseckart.cvee.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import ee.larseckart.cvee.presentation.R;
import ee.larseckart.cvee.presentation.internal.di.HasComponent;
import ee.larseckart.cvee.presentation.internal.di.components.DaggerJobAdComponent;
import ee.larseckart.cvee.presentation.internal.di.components.JobAdComponent;
import ee.larseckart.cvee.presentation.view.fragment.JobAdListFragment;

public class JobAdListActivity extends BaseActivity implements HasComponent<JobAdComponent> {

  private JobAdComponent jobAdComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, JobAdListActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new JobAdListFragment());
    }
  }

  private void initializeInjector() {
    this.jobAdComponent = DaggerJobAdComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public JobAdComponent getComponent() {
    return jobAdComponent;
  }
}
