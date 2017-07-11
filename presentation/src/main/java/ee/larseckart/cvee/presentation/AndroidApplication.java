package ee.larseckart.cvee.presentation;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import ee.larseckart.cvee.data.BuildConfig;
import ee.larseckart.cvee.presentation.internal.di.components.ApplicationComponent;
import ee.larseckart.cvee.presentation.internal.di.components.DaggerApplicationComponent;
import ee.larseckart.cvee.presentation.internal.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    this.initializeInjector();
    this.initializeLeakDetection();
  }

  private void initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  private void initializeLeakDetection() {
    if (BuildConfig.DEBUG) {
      LeakCanary.install(this);
    }
  }
}
