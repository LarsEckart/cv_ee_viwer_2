package ee.larseckart.cvee.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import ee.larseckart.cvee.presentation.view.activity.JobAdListActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton public class Navigator {

  @Inject public Navigator() {
    //empty
  }

  public void navigateToJobAdList(Context context) {
    if (context != null) {
      Intent intentToLaunch = JobAdListActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }
}

