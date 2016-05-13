package integrationTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sainsburys.productscrapper.Application;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class ApplicationIntegrationTest {

    @Autowired
    private Application application;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void shouldGenerateResultFiles() {

        application.main("sampleOutPut");

        assertThat(outputCapture.toString().contains("results")).isTrue();

    }
}