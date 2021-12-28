package karatemaven;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

@SuppressWarnings("deprecation")
@KarateOptions(tags= {"@smoke"})		//Run scenarios with tag "Smoke" Parallely
public class ParallelRunner {
	@Test
    public void testParallel() {
        
        Results results = Runner.parallel(getClass(),5);
    }
   
}
