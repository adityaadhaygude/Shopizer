package karatemaven;

import org.junit.runner.RunWith;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;

@SuppressWarnings("deprecation")
@RunWith(Karate.class)
@KarateOptions(tags= {"@smoke"})	//Run Scenarios with tag "Smoke" Serially
public class TestRunnerWithTags{
   
}
