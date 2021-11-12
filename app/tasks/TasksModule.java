package tasks;
import com.google.inject.AbstractModule;

/**
 * This is where tasks get initialized on start
 */
public class TasksModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MyActorTask.class).asEagerSingleton();
    }
}
