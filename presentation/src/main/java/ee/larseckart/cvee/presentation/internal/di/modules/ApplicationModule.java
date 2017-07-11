package ee.larseckart.cvee.presentation.internal.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import ee.larseckart.cvee.presentation.AndroidApplication;
import ee.larseckart.cvee.data.executor.JobExecutor;
import ee.larseckart.cvee.domain.executor.PostExecutionThread;
import ee.larseckart.cvee.domain.executor.ThreadExecutor;
import ee.larseckart.cvee.presentation.UIThread;
import javax.inject.Singleton;

@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }


}

