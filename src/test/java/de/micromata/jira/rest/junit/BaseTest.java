package de.micromata.jira.rest.junit;

import de.micromata.jira.rest.JiraRestClient;
import de.micromata.jira.rest.core.jql.JqlConstants;
import de.micromata.jira.rest.core.misc.RestPathConstants;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Junit-Test for JiraRestClient.
 * You need a running Jira-Instance with the TEST_SYSTEM_URL.
 * Best use is the atlassian-plugin-sdk
 * <p>
 * User: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 09.08.2014
 */
class BaseTest implements JqlConstants, RestPathConstants {

    static final String CONFIGFILENAME = "config.properties";

    static final String URL_PARAM = "url";
    static final String LOGIN_PARAM = "login";
    static final String PASSWORD_PARAM = "password";
    static final String USERNAME_PARAM = "username";
    static final String ISSUE_KEY_PARAM = "issueKey";
    static final String PROJECT_KEY_PARAM = "projectKey";

    static String usernameToSearch = "admin";

    // Attention: Issue must have an attachment and environment field must be in the issue screen
    static String issueKeyToSearch = "DEMO-1";

    // Attention: Project must have components
    static String projectKeyToSearch = "DEMO";

    String testSystemUrl = "http://localhost:2990/jira";
    String login = "admin";
    String password = "admin";

    JiraRestClient jiraRestClient;

    public BaseTest() {
        try {
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void connect() throws URISyntaxException, IOException, ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        ProxyHost proxy = new ProxyHost("proxy", 3128);
        URI uri = new URI(testSystemUrl);
        jiraRestClient = new JiraRestClient(executorService);
        jiraRestClient.connect(uri, login, password);
    }

    private void loadConfig() throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Properties config = new Properties();
        config.load(new FileInputStream(path + CONFIGFILENAME));
        testSystemUrl = config.getProperty(URL_PARAM);
        login = config.getProperty(LOGIN_PARAM);
        password = config.getProperty(PASSWORD_PARAM);
        if (config.getProperty(USERNAME_PARAM) != null) {
            usernameToSearch = config.getProperty(USERNAME_PARAM);
        }
        if (config.getProperty(ISSUE_KEY_PARAM) != null) {
            issueKeyToSearch = config.getProperty(ISSUE_KEY_PARAM);
        }
        if (config.getProperty(PROJECT_KEY_PARAM) != null) {
            projectKeyToSearch = config.getProperty(PROJECT_KEY_PARAM);
        }
    }
}
