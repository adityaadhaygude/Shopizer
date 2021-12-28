package karatemaven;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;
import com.intuit.karate.KarateOptions;


@SuppressWarnings("deprecation")
@RunWith(Karate.class)
@KarateOptions( features = {"Catalog management resource.feature","Category management resource.feature"} )
public class TestRunnerWithSelectiveFeatures{

}
