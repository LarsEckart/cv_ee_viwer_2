package ee.larseckart.cvee.presentation.internal.di.components;

import dagger.Component;
import ee.larseckart.cvee.presentation.internal.di.PerActivity;
import ee.larseckart.cvee.presentation.internal.di.modules.ActivityModule;
import ee.larseckart.cvee.presentation.internal.di.modules.JobAdModule;
import ee.larseckart.cvee.presentation.view.fragment.JobAdListFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, JobAdModule.class })
public interface JobAdComponent extends ActivityComponent {
  void inject(JobAdListFragment jobAdListFragment);
}