package ee.larseckart.cvee.presentation.internal.di.components;

import android.content.Context;
import dagger.Component;
import ee.larseckart.cvee.domain.executor.PostExecutionThread;
import ee.larseckart.cvee.domain.executor.ThreadExecutor;
import ee.larseckart.cvee.presentation.internal.di.modules.ApplicationModule;
import ee.larseckart.cvee.presentation.view.activity.BaseActivity;
import javax.inject.Singleton;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
}