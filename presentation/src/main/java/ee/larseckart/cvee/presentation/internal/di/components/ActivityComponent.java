package ee.larseckart.cvee.presentation.internal.di.components;

import android.app.Activity;
import dagger.Component;
import ee.larseckart.cvee.presentation.internal.di.PerActivity;
import ee.larseckart.cvee.presentation.internal.di.modules.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
  Activity activity();
}